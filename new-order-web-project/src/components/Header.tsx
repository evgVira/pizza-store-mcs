import logo from "../../public/pngtree-pizza-logo-png-image_6147023.png"
import {AppName, HeaderContainer, LogoContainer, LogoImage, Nav, NavButton} from "../style/headerStyle.ts";
import {NavLink} from "react-router-dom";

export default function Header() {

    return (
        <HeaderContainer>
            <NavLink to={"/"}>
                <LogoContainer>
                    <LogoImage src={logo}/>
                    <AppName>PixelPizza</AppName>
                </LogoContainer>
            </NavLink>
            <Nav>
                <NavLink to={"/"}>
                    <NavButton>Главная</NavButton>
                </NavLink>
                <NavLink to={"/login"}>
                    <NavButton>Вход</NavButton>
                </NavLink>
                <NavLink to={"/catalog"}>
                    <NavButton>Каталог</NavButton>
                </NavLink>
            </Nav>
        </HeaderContainer>
    )
}