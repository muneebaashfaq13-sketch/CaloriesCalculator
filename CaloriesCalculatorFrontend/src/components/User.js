import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "../api/axios";
import UserDatewiseList from "./UserDatewiseList";

const GET_ALL_MEALS = '/meals/userMealsByDate/';
const GET_EARLIEST_DATE = '/meals/earliestMealDate/'

const User = () => {
    const state = JSON.parse(localStorage.getItem("state"));
    const navigate = useNavigate();

    const [meals, setMeals] = useState([]);
    const [userEarliestMealDate, setEarliestDate] = useState('');

    function getCurrentDate() {
        var today = new Date();
        var dd = String(today.getDate()).padStart(2, '0');
        var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
        var yyyy = today.getFullYear();

        today = yyyy + '-' + mm + '-' + dd;
        return today;
    }


    useEffect(() => {
        async function getMeals() {
            try {

                if (!userEarliestMealDate.length) {
                    const getEarliestDate = await axios.get(GET_EARLIEST_DATE + state.userId,
                        {
                            headers: { 'Content-Type': 'application/json' }
                        }
                    );
                    setEarliestDate(getEarliestDate.data);
                }

                if (!meals.length && userEarliestMealDate.length) {
                    const response = await axios.get(GET_ALL_MEALS + state.userId,
                        {
                            headers: { 'Content-Type': 'application/json' },
                            params: { 'date': userEarliestMealDate }
                        }
                    );
                    setMeals(response.data); 
                }
                console.log(meals);
                console.log(userEarliestMealDate);

            } catch (err) {
                console.log(err);
            }
        }
        getMeals();
    })

    const logout = () => {
        localStorage.removeItem("state");
        navigate('/login', { replace: true });
    };

    return (
        <div className="main-div">
            <h1 className="welcome-header">{'Welcome to User Portal, ' + state.name + '!'}</h1>
            <button className="logout-button" onClick={logout}>Logout</button>
            <button className="add-button" onClick={logout}>Add</button>
            <UserDatewiseList
                meals={meals}
                startDate={userEarliestMealDate}
                endDate={getCurrentDate()}
                caloriesLimit={state.caloriesLimit}
                expenseLimit={state.expenseLimit}
            />
            <h1 className="welcome-header">__________</h1>
        </div>
    )
}

export default User
