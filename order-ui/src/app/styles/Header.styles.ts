import styled from "styled-components";
import {Link} from "react-router-dom";

export const HeaderWrapper = styled.header`
    background-color: #333;
    padding: 1rem;
    display: flex;
    justify-content: space-around;
    align-items: center;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.3); 
    position: fixed; 
    top: 0;
    left: 0;
    width: 100%;
    z-index: 1000; 
`;

export const NavLink = styled(Link)`
    color: white;
    text-decoration: none;
    font-size: 1.2rem;
    position: relative;
    transition: color 0.3s ease;

    &:hover {
        color: #ccc;
    }

    /* Анимация подчеркивания */
    &::after {
        content: '';
        position: absolute;
        width: 100%;
        height: 2px;
        background-color: #007bff;
        bottom: -5px;
        left: 0;
        transform: scaleX(0);
        transform-origin: bottom right;
        transition: transform 0.3s ease;
    }

    &:hover::after {
        transform: scaleX(1);
        transform-origin: bottom left;
    }
`;

export const Logo = styled.div`
    font-size: 1.5rem;
    font-weight: bold;
    color: #fff;
    margin-right: auto; 
    padding-left: 1rem;
`;

export const NavLinksWrapper = styled.div`
    display: flex;
    justify-content: space-around; 
    flex-grow: 1; 
    padding: 0 1rem; 
`;
