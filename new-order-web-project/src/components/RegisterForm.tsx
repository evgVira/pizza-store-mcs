import {Button, FormContainer, Input, Title} from "../style/registerStyle.ts"
import {PageContainer} from "../style/pageStyle.ts";
import api from "../utils/api.tsx"
import {useNavigate} from "react-router-dom";
import {useState} from "react"

export default function RegisterForm() {

    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")
    const [email, setEmail] = useState("")
    const navigate = useNavigate()

    const handleRegister = async () => {
        const {data} = await api.post("/register", {username, password});
        localStorage.setItem("accessToken", data.accessToken)
        localStorage.setItem("refreshToken", data.refreshToken)
        navigate("/dashboard")
    }
    return (
        <PageContainer>
            <FormContainer>
                <Title>Регистрация</Title>
                <Input type={"text"} placeholder={"Имя"} value={username}
                       onChange={(e) => setUsername(e.target.value)}></Input>
                <Input type={"text"} placeholder={"Пароль"} value={password}
                       onChange={(e) => setPassword(e.target.value)}></Input>
                <Input type={"text"} placeholder={"Почта"} value={email}
                       onChange={(e) => setEmail(e.target.value)}></Input>
                <Button onClick={handleRegister}>Зарегестрироваться</Button>
            </FormContainer>
        </PageContainer>
    )
}