import {NavLink} from "react-router-dom";
import {HeaderWrapper, Logo, NavLinksWrapper} from "../styles/Header.styles.ts";

export default function Header() {
    return (
        <HeaderWrapper>
            <Logo>Pixel-Pizza</Logo>
            <NavLinksWrapper>
                <NavLink to="/">Главная</NavLink>
                <NavLink to="/login">Вход</NavLink>
                <NavLink to="/register">Регистрация</NavLink>
                <NavLink to="/dashboard">Дашбоард</NavLink>
                <NavLink to={"/list"}>Список пицц</NavLink>
            </NavLinksWrapper>
        </HeaderWrapper>
    );
}