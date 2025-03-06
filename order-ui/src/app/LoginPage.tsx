import {useState} from "react";
import api from "./api";
import {useNavigate} from "react-router-dom";
import {Button, Container, FormWrapper, Input, Title} from "./LoginPage.styles.ts";

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
        <Container>
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
        </Container>
    );
}