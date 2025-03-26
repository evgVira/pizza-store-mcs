import styled from "styled-components";

export const FormContainer = styled.div`
    padding: 5rem;
    box-shadow: 0px 2px 100px grey;
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 400px;
    height: 300px;
`

export const Input = styled.input`
    width: 100%;
    padding: 10px;
    margin: 10px 0;
    border: none;
    border-radius: 5px;
    background: whitesmoke;
    color: black;
    font-size: 16px;
`
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
    color: black;
`