import { useEffect } from "react";
import { useLocation, Navigate, Outlet } from "react-router-dom";
import axios from "../api/axios";

const RequireAuth = ({ allowedRoles }) => {
    const location = useLocation();

    useEffect(() => {
        async function getToken() {
            try {

                const response = await axios.get('/auth/' + JSON.parse(localStorage.getItem("state")).token,
                    {
                        headers: { 'Content-Type': 'application/json' }
                    }
                );

                const userId = response?.data?.id;
                const role = response?.data?.role;
                const name = response?.data?.name;
                const expenseLimit = response?.data?.expenseLimit;
                const caloriesLimit = response?.data?.caloriesLimit;
                const token = JSON.parse(localStorage.getItem("state")).token

                localStorage.setItem("state", JSON.stringify({ userId, token, role, name, expenseLimit, caloriesLimit }));


            } catch (err) {
                localStorage.removeItem("state");
            }
        }
        getToken();
    }, [])
    
    return (
        allowedRoles.includes(JSON.parse(localStorage.getItem("state"))?.role)
        ? <Outlet/> : <Navigate to='/login' state={{ from: location }} replace />
    );
}

export default RequireAuth;