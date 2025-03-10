import {useState} from "react";
import api from "../api.tsx";
import {useNavigate} from "react-router-dom";
import {Button, FormWrapper, Input, Title} from "../styles/LoginPage.styles.ts";

export default function RegisterPage() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [email, setEmail] = useState("");
    const navigate = useNavigate();

    const handleRegister = async () => {
        const {data} = await api.post("/register", {username, password});
        localStorage.setItem("accessToken", data.accessToken);
        localStorage.setItem("refreshToken", data.refreshToken);
        navigate("/dashboard");
    };

    return (
        <div className={"form-container"}>
            <FormWrapper>
                <Title>Регистрация</Title>

                <Input type="text" placeholder="Username" value={username}
                       onChange={(e) => setUsername(e.target.value)}>
                </Input>

                <Input type="password" placeholder="Password" value={password}
                       onChange={(e) => setPassword(e.target.value)}>
                </Input>

                <Input type="email" placeholder="Email" value={email}
                       onChange={(e) => setEmail(e.target.value)}>
                </Input>

                <Button onClick={handleRegister}>Зарегестрироваться</Button>
            </FormWrapper>
        </div>
    );
}