import styled from "styled-components";

export const HeaderContainer = styled.header`
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
    padding: 0px 10px;
    background-color: #336;
    height: auto;
    width: auto;
`
export const LogoContainer = styled.div`
    display: flex;
    align-items: center;
    gap: 1rem;
`

export const LogoImage = styled.img`
    width: 100px;
`
export const AppName = styled.h1`
    margin: 0;
    font-size: 1.5rem;
    color: honeydew;
`
export const Nav = styled.nav`
    display: flex;
    gap: 1rem;
`

export const NavButton = styled.button`
    padding: 0.5rem 1rem;
    border: none;
    border-radius: 20px;
    background-color: #007bff;
    color: white;
    cursor: pointer;
    transition: background-color 0.3s;
    width: 100px;
    height: 50px;
`
export const NavLinksWrapper = styled.div`
    display: flex;
    justify-content: space-around; 
    flex-grow: 1; 
    padding: 0 1rem; 
`;
