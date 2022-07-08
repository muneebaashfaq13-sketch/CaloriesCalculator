import { useEffect } from "react";
import { useLocation, Navigate, Outlet } from "react-router-dom";
import axios from "../api/axios";

const RequireAuth = ({ allowedRoles }) => {
    const location = useLocation();

    useEffect(() => {
        async function getToken(){
            try {

                await axios.get('/auth/' + JSON.parse(localStorage.getItem("state")).token,
                    {
                        headers: { 'Content-Type': 'application/json' }
                    }
                );
    
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