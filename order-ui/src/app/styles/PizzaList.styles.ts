import styled from 'styled-components';

// Стили для контейнера списка пицц
export const PizzaListContainer = styled.div`
    display: flex;
    grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
    gap: 2rem;
    padding: 2rem;
    background-color: #1e1e1e;
    border-radius: 25px;
`;

// Стили для карточки пиццы
export const PizzaCard = styled.div`
    background: ghostwhite;
    width: 200%;
    border-radius: 25px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    overflow: hidden;
    transition: transform 0.3s ease, box-shadow 0.3s ease;

    &:hover {
        transform: translateY(-5px);
        box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
    }
`;

// Стили для изображения пиццы
export const PizzaImage = styled.img`
    width: 100%;
    height: 200px;
    object-fit: cover;
`;

// Стили для контента карточки
export const PizzaContent = styled.div`
    padding: 1.5rem;
`;

// Стили для названия пиццы
export const PizzaName = styled.h3`
    font-size: 1.5rem;
    margin: 0 0 1rem;
    color: #333;
`;

// Стили для описания пиццы
export const PizzaDescription = styled.p`
    font-size: 1rem;
    color: #666;
    margin: 0 0 1rem;
`;

// Стили для цены и статуса
export const PizzaDetails = styled.div`
    display: flex;
    justify-content: space-between;
    align-items: center;
    font-size: 1rem;
    color: #444;
`;

export const PizzaPrice = styled.span`
    font-weight: bold;
    color: #e91e63;
`;


export const PizzaStatus = styled.span`
    color: #4caf50;
`;