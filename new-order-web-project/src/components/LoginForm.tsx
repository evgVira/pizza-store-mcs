import {Button, FormContainer, Input, Title} from "../style/registerStyle.ts"
import {PageContainer} from "../style/pageStyle.ts";
import {useState} from "react";
import {useNavigate} from "react-router";
import api from "../utils/api.tsx"

export default function LoginForm() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("")
    const navigate = useNavigate();

    const handleLogin = async () => {
        const {data} = await api.post(`/user/login/user/${username}/password/${password}`)
        localStorage.setItem("accessToken", data.accessToken)
        localStorage.setItem("refreshToken", data.refreshToken)
        navigate("/dashboard")
    }
    return (
        <PageContainer>
            <FormContainer>
                <Title>Вход</Title>
                <Input type={"text"} placeholder={"Имя"} value={username}
                       onChange={(e) => setUsername(e.target.value)}></Input>
                <Input type={"text"} placeholder={"Пароль"} value={password}
                       onChange={(e) => setPassword(e.target.value)}></Input>
                <Button onClick={handleLogin}>Войти</Button>
            </FormContainer>
        </PageContainer>
    )
}