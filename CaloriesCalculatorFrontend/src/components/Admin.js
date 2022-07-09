import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "../api/axios";

const GET_ALL_MEALS = '/meals';

const Admin = () => {
    const state = JSON.parse(localStorage.getItem("state"));
    const navigate = useNavigate();

    const [ meals, setMeals ] = useState([]);

    useEffect(() => {
        async function getToken(){
            try {

                const response = await axios.get(GET_ALL_MEALS,
                    {
                        headers: { 'Content-Type': 'application/json' }
                    }
                );
                setMeals(response.data);
                console.log(meals);
    
            } catch (err) {
                console.log(meals);
            }
        }
        getToken();
    }, )

    const logout = () => {
        localStorage.removeItem("state");
        navigate('/login', { replace: true });
    };

    return (
        <div className="main-div">
            <h1 className="welcome-header">{'Welcome to Admin Portal, ' + state.name + '!'}</h1>
            <button className="logout-button" onClick={logout}>Logout</button>
            <button className="add-button" onClick={logout}>Add</button>
            <h1 className="welcome-header">__________</h1>
        </div>
    )
}

export default Admin
