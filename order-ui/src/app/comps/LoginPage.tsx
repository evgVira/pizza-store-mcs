import {useState} from "react";
import api from "../api.tsx";
import {useNavigate} from "react-router-dom";
import {Button, FormWrapper, Input, Title} from "../styles/LoginPage.styles.ts";

export default function LoginPage() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();

    const handleLogin = async () => {
        const {data} = await api.post(`/user/login/user/${username}/password/${password}`);
        localStorage.setItem("accessToken", data.accessToken);
        localStorage.setItem("refreshToken", data.refreshToken);
        navigate("/dashboard");
    };

    return (
        <div className={"form-container"}>
            <FormWrapper>
                <Title>Вход</Title>
                <Input
                    type="text"
                    placeholder="Username"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                />
                <Input
                    type="password"
                    placeholder="Password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                />
                <Button onClick={handleLogin}>Войти</Button>
            </FormWrapper>
        </div>
    );
}