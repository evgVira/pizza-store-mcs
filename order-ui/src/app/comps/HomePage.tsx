import {useNavigate} from "react-router-dom";
import {Button, FormWrapper, Title} from "../styles/LoginPage.styles.ts";

export default function HomePage() {
    const navigate = useNavigate();

    return (
        <div className={"form-container"}>
            <FormWrapper>
                <Title>Добро пожаловать!</Title>
                <Button onClick={() => navigate('/login')}>Войти</Button>
                <Button onClick={() => navigate('/register')}>Зарегестрироваться</Button>
            </FormWrapper>
        </div>
    );
}