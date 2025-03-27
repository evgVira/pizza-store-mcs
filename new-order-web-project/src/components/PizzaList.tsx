import {
    PizzaCard,
    PizzaContent,
    PizzaDescription,
    PizzaDetails,
    PizzaImage,
    PizzaListContainer,
    PizzaName,
    PizzaPrice,
    PizzaStatus
} from "../style/pizzaListStyel.ts";
import logo from "../../public/slice-of-pizza-cartoon-cartoon-illustration-cartoon-clipart-free-vector.jpg"


export default function PizzaList() {
    const pizzas = [
        {
            id: '550e8400-e29b-41d4-a716-446655440000',
            name: 'Маргарита',
            description: 'Классическая пицца с томатами и моцареллой',
            price: 500,
            imageUrl: 'https://via.placeholder.com/300x200', // Заглушка для изображения
            createdAt: '2023-10-01T12:00:00Z',
            updatedAt: '2023-10-01T12:00:00Z',
            status: 'AVAILABLE',
        },
        {
            id: '550e8400-e29b-41d4-a716-446655440001',
            name: 'Пепперони',
            description: 'Пицца с острой колбаской',
            price: 600,
            imageUrl: 'https://via.placeholder.com/300x200', // Заглушка для изображения
            createdAt: '2023-10-01T12:00:00Z',
            updatedAt: '2023-10-01T12:00:00Z',
            status: 'AVAILABLE',
        },
        {
            id: '550e8400-e29b-41d4-a716-446655440002',
            name: 'Гавайская',
            description: 'Пицца с ананасами и ветчиной',
            price: 550,
            imageUrl: 'https://via.placeholder.com/300x200', // Заглушка для изображения
            createdAt: '2023-10-01T12:00:00Z',
            updatedAt: '2023-10-01T12:00:00Z',
            status: 'UNAVAILABLE',
        },
        {
            id: '550e8400-e29b-41d4-a716-446655440000',
            name: 'Маргарита',
            description: 'Классическая пицца с томатами и моцареллой',
            price: 500,
            imageUrl: 'https://via.placeholder.com/300x200', // Заглушка для изображения
            createdAt: '2023-10-01T12:00:00Z',
            updatedAt: '2023-10-01T12:00:00Z',
            status: 'AVAILABLE',
        },
        {
            id: '550e8400-e29b-41d4-a716-446655440001',
            name: 'Пепперони',
            description: 'Пицца с острой колбаской',
            price: 600,
            imageUrl: 'https://via.placeholder.com/300x200', // Заглушка для изображения
            createdAt: '2023-10-01T12:00:00Z',
            updatedAt: '2023-10-01T12:00:00Z',
            status: 'AVAILABLE',
        },
        {
            id: '550e8400-e29b-41d4-a716-446655440002',
            name: 'Гавайская',
            description: 'Пицца с ананасами и ветчиной',
            price: 550,
            imageUrl: 'https://via.placeholder.com/300x200', // Заглушка для изображения
            createdAt: '2023-10-01T12:00:00Z',
            updatedAt: '2023-10-01T12:00:00Z',
            status: 'UNAVAILABLE',
        },
        {
            id: '550e8400-e29b-41d4-a716-446655440000',
            name: 'Маргарита',
            description: 'Классическая пицца с томатами и моцареллой',
            price: 500,
            imageUrl: 'https://via.placeholder.com/300x200', // Заглушка для изображения
            createdAt: '2023-10-01T12:00:00Z',
            updatedAt: '2023-10-01T12:00:00Z',
            status: 'AVAILABLE',
        },
        {
            id: '550e8400-e29b-41d4-a716-446655440001',
            name: 'Пепперони',
            description: 'Пицца с острой колбаской',
            price: 600,
            imageUrl: 'https://via.placeholder.com/300x200', // Заглушка для изображения
            createdAt: '2023-10-01T12:00:00Z',
            updatedAt: '2023-10-01T12:00:00Z',
            status: 'AVAILABLE',
        },
        {
            id: '550e8400-e29b-41d4-a716-446655440002',
            name: 'Гавайская',
            description: 'Пицца с ананасами и ветчиной',
            price: 550,
            imageUrl: 'https://via.placeholder.com/300x200', // Заглушка для изображения
            createdAt: '2023-10-01T12:00:00Z',
            updatedAt: '2023-10-01T12:00:00Z',
            status: 'UNAVAILABLE',
        },
        {
            id: '550e8400-e29b-41d4-a716-446655440000',
            name: 'Маргарита',
            description: 'Классическая пицца с томатами и моцареллой',
            price: 500,
            imageUrl: 'https://via.placeholder.com/300x200', // Заглушка для изображения
            createdAt: '2023-10-01T12:00:00Z',
            updatedAt: '2023-10-01T12:00:00Z',
            status: 'AVAILABLE',
        },
        {
            id: '550e8400-e29b-41d4-a716-446655440001',
            name: 'Пепперони',
            description: 'Пицца с острой колбаской',
            price: 600,
            imageUrl: 'https://via.placeholder.com/300x200', // Заглушка для изображения
            createdAt: '2023-10-01T12:00:00Z',
            updatedAt: '2023-10-01T12:00:00Z',
            status: 'AVAILABLE',
        },
        {
            id: '550e8400-e29b-41d4-a716-446655440002',
            name: 'Гавайская',
            description: 'Пицца с ананасами и ветчиной',
            price: 550,
            imageUrl: 'https://via.placeholder.com/300x200', // Заглушка для изображения
            createdAt: '2023-10-01T12:00:00Z',
            updatedAt: '2023-10-01T12:00:00Z',
            status: 'UNAVAILABLE',
        }
    ];

    return (
        <PizzaListContainer>
            {pizzas.map((pizza) => (
                <PizzaCard key={pizza.id}>
                    <PizzaImage src={logo}/>
                    <PizzaContent>
                        <PizzaName>{pizza.name}</PizzaName>
                        <PizzaDescription>{pizza.description}</PizzaDescription>
                        <PizzaDetails>
                            <PizzaPrice>{pizza.price} руб.</PizzaPrice>
                            <PizzaStatus>{pizza.status === 'AVAILABLE' ? 'Доступна' : 'Недоступна'}</PizzaStatus>
                        </PizzaDetails>
                    </PizzaContent>
                </PizzaCard>
            ))}
        </PizzaListContainer>
    )
}