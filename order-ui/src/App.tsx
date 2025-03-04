import {BrowserRouter as Router, Navigate, Route, Routes} from "react-router-dom";
import LoginPage from "./app/LoginPage";
import RegisterPage from "./app/RegisterPage";
import DashBoard from "./app/DashBoard";
import {JSX} from "react";

function PrivateRoute({children}: { children: JSX.Element }) {
    return localStorage.getItem("accessToken") ? children : <Navigate to="/login"/>
}

export default function App() {
    return (
        <Router>
            <Routes>
                <Route path="/login" element={<LoginPage/>}/>
                <Route path="/register" element={<RegisterPage/>}/>
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
