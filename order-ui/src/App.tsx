import {BrowserRouter as Router, Navigate, Route, Routes} from "react-router-dom";
import LoginPage from "./app/comps/LoginPage.tsx";
import RegisterPage from "./app/comps/RegisterPage.tsx";
import DashBoard from "./app/comps/DashBoard.tsx";
import HomePage from "./app/comps/HomePage.tsx";
import {JSX} from "react";
import Header from "./app/comps/Header.tsx";
import PizzaList from "./app/comps/PizzaList.tsx";

function PrivateRoute({children}: { children: JSX.Element }) {
    return localStorage.getItem("accessToken") ? children : <Navigate to="/login"/>
}

export default function App() {
    return (
        <Router>
            <Header/>
            <Routes>
                <Route path="/" element={<HomePage/>}></Route>
                <Route path="/login" element={<LoginPage/>}/>
                <Route path="/register" element={<RegisterPage/>}/>
                <Route path="/list" element={<PizzaList/>}/>
                <Route
                    path="/dashboard"
                    element={
                        <PrivateRoute>
                            <DashBoard/>
                        </PrivateRoute>
                    }
                />
            </Routes>
        </Router>
    )
};
