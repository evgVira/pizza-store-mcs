import styled from 'styled-components'

export const PizzaListContainer = styled.div`
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
    gap: 5rem;
    padding: 2rem;
    border-radius: 25px;
`
export const PizzaCard = styled.div`
    background: ghostwhite;
    width: 100%;
    border-radius: 25px;
    box-shadow: 0 4px 8px rgb(0, 0, 0, 0.1);
    overflow: hidden;
    transition: transform 0.3s ease, box-shadow 0.3s ease;

    &:hover {
        transform: translate(-5px);
        box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
    }
`
export const PizzaImage = styled.img`
    width: 100%;
    object-fit: cover;
`

export const PizzaContent = styled.div`
    padding: 1.5rem;
`

export const PizzaName = styled.h3`
    font-size: 1.5rem;
    margin: 0 0 1rem;
    color: #333;
`

export const PizzaDescription = styled.p`
    font-size: 1rem;
    color: #666;
    margin: 0 0 1rem;
`

export const PizzaDetails = styled.div`
    display: flex;
    justify-content: space-between;
    align-items: center;
    font-size: 1rem;
    color: #4444;
`
export const PizzaPrice = styled.span`
    font-weight: bold;
    color: #e91e63;
`

export const PizzaStatus = styled.span`
    color: #4caf50
`