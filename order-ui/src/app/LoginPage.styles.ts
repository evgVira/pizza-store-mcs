import styled from "styled-components";

export const Container = styled.div`
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    background-color: #121212;
`;

export const FormWrapper = styled.div`
    background: #1e1e1e;
    padding: 8rem;
    border-radius: 100px;
    box-shadow: 0px 0px 10px rgba(255, 255, 255, 0.1);
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 500px;
`;

export const Input = styled.input`
    width: 100%;
    padding: 10px;
    margin: 10px 0;
    border: none;
    border-radius: 5px;
    background: #333;
    color: #fff;
    font-size: 16px;
`;

export const Button = styled.button`
    width: 100%;
    padding: 10px;
    margin-top: 10px;
    border: none;
    border-radius: 5px;
    background: #007bff;
    color: #fff;
    font-size: 16px;
    cursor: pointer;
    transition: background 0.3s;

    &:hover {
        background: #0056b3;
    }
`;

export const Title = styled.h2`
    color: #fff;
    margin-bottom: 20px;
`;
