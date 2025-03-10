// import {useEffect, useState} from "react";
// import axios from "axios";
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
} from "../styles/PizzaList.styles.ts";

const PizzaList = () => {
    // Хардкоженный список пицц
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
    ];
    // const [pizzas, setPizzas] = useState([]);
    // const [loading, setLoading] = useState(true);
    // const [error, setError] = useState(null);
    //
    // const fetchPizzas = async () => {
    //     try{
    //         const response = await axios.get('http://localhost:8080/api/v1/catalog/pizzas/info')
    //         setPizzas(response.data);
    //         setLoading(false);
    //     }catch (error){
    //         setError(null);
    //         setLoading(false)
    //     }
    // };
    // useEffect(() => {
    //     fetchPizzas();
    // }, []);
    //
    // if(loading){
    //     return <div>Loading...</div>
    // }
    // if(error){
    //     return <div>Error: {error}</div>
    // }

    return (
        <div className={"form-container"}>
            <PizzaListContainer>
                {pizzas.map((pizza) => (
                    <PizzaCard key={pizza.id}>
                        <PizzaImage
                            src="https://via.placeholder.com/300x200" // Замените на реальное изображение
                            alt={pizza.name}
                        />
                        <PizzaContent>
                            <PizzaName>{pizza.name}</PizzaName>
                            <PizzaDescription>{pizza.description}</PizzaDescription>
                            <PizzaDetails>
                                <PizzaPrice>{pizza.price} руб.</PizzaPrice>
                                <PizzaStatus status={pizza.status}>
                                    {pizza.status === 'AVAILABLE' ? 'Доступна' : 'Недоступна'}
                                </PizzaStatus>
                            </PizzaDetails>
                        </PizzaContent>
                    </PizzaCard>
                ))}
            </PizzaListContainer>
        </div>
    );
}

export default PizzaList;