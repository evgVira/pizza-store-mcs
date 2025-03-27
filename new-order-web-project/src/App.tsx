import {Fragment, JSX} from 'react'
import Header from "./components/Header.tsx";
import {BrowserRouter as Router, Navigate, Route, Routes} from "react-router-dom";
import HomePage from "./components/HomePage.tsx";
import LoginForm from "./components/LoginForm.tsx";
import RegisterForm from "./components/RegisterForm.tsx";
import DashBoard from "./components/DashBoard.tsx";
import AuthenticatedLayout from "./components/AuthenticatedLayout.tsx";
import PizzaList from "./components/PizzaList.tsx";

function PrivateRoute({children}: { children: JSX.Element }) {
    return localStorage.getItem("accessToken") ? children : <Navigate to={"/login"}/>
}

export default function App() {
    return (
        <Router>
            <Fragment>
                <Header/>
                <Routes>
                    <Route path={"/"} element={<HomePage/>}></Route>
                    <Route path={"/login"} element={<LoginForm/>}></Route>
                    <Route path={"/register"} element={<RegisterForm/>}></Route>
                    <Route path={"/catalog"} element={<PizzaList/>}></Route>
                    <Route element={<PrivateRoute><AuthenticatedLayout/></PrivateRoute>}>
                        <Route path="/dashboard" element={<DashBoard/>}/>
                    </Route>
                </Routes>
            </Fragment>
        </Router>
    );
}
