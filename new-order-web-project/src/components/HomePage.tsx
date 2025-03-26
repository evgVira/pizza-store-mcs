import {useNavigate} from "react-router";
import {Button, FormContainer, Title} from "../style/registerStyle.ts";
import {PageContainer} from "../style/pageStyle.ts";

export default function HomePage() {
    const navigate = useNavigate();

    return (
        <PageContainer>
            <FormContainer>
                <Title>PixelPizza</Title>
                <Button onClick={() => navigate("/login")}>Войти</Button>
                <Button onClick={() => navigate("/register")}>Зарегестрироваться</Button>
            </FormContainer>
        </PageContainer>
    )
}