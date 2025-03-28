import {useLocation, useNavigate} from "react-router-dom";
import {PageContainer} from "../style/pageStyle.ts";
import {Button, FormContainer, Title} from "../style/registerStyle.ts";
import {useEffect, useState} from "react";
import {UserImage} from "../style/userImageStyle.ts";
import logo from "../../public/360_F_394899054_4TMgw6eiMYUfozaZU3Kgr5e0LdH4ZrsU.jpg"

export default function UserPage() {
    const location = useLocation();
    const navigate = useNavigate();

    const [username, setUsername] = useState(location.state?.username || localStorage.getItem("username"));
    const [email, setEmail] = useState(location.state?.email || localStorage.getItem("email"));

    useEffect(() => {
        if (!username || !email) {
            navigate("/")
        }
    }, [username, email, navigate]);

    if (!username || !email) {
        return null;
    }

    return (
        <PageContainer>
            <FormContainer>
                <UserImage src={logo}/>
                <Title>Добро пожаловать {username}</Title>
                <Title>Email: {email}</Title>
                <Button onClick={() => navigate("/catalog")}>Каталог</Button>
            </FormContainer>
        </PageContainer>
    )
}