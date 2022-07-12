import { Container, TextField } from "@material-ui/core";
import { useEffect, useRef, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "../../api/axios";
import AddMealUser from "./AddMealUser";
import UserDatewiseList from "./UserDatewiseList";

const GET_ALL_MEALS = '/meals/allMealsForUser/';
const GET_EARLIEST_DATE = '/meals/earliestMealDate/'

const User = () => {
    const state = JSON.parse(localStorage.getItem("state"));
    const navigate = useNavigate();

    const [meals, setMeals] = useState(new Map());
    const [addButtonStatus, setAddButtonStatus] = useState(false);
    const startDateRef = useRef();
    const [startDate, setStartDate] = useState('');
    const endDateRef = useRef();
    const [endDate, setEndDate] = useState('');

    function getCurrentDate() {
        var today = new Date();
        var dd = String(today.getDate()).padStart(2, '0');
        var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
        var yyyy = today.getFullYear();

        today = yyyy + '-' + mm + '-' + dd;
        return today;
    }

    function addRecord() {
        setAddButtonStatus(true);
    }

    function back() {
        setAddButtonStatus(false);
        window.location.reload();
    }

    useEffect(() => {
        async function getMeals() {
            try {

                if (startDate.length === 0) {
                    const getEarliestDate = await axios.get(GET_EARLIEST_DATE + state.userId,
                        {
                            headers: { 'Content-Type': 'application/json' }
                        }
                    );
                    setStartDate(getEarliestDate.data);
                }

                if (meals.size === 0 && startDate.length !== 0) {
                    const response = await axios.get(GET_ALL_MEALS + state.userId,
                        {
                            headers: { 'Content-Type': 'application/json' }
                        }
                    );
                    setMeals(response.data);
                }

                if (endDate.length === 0 && meals.size !== 0 && startDate.length !== 0) {
                    setEndDate(getCurrentDate());
                }

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
            {
                addButtonStatus ?
                    (
                        <>
                            <button className="add-button" onClick={back}>Back</button>
                            <AddMealUser
                                userId={state.userId}
                                currentDate={getCurrentDate()}
                            />
                        </>
                    )
                    :
                    (
                        <>
                            <div className='user-list-columns'>
                                <Container>
                                    <TextField id='startDate'
                                        type='date'
                                        label='Start Date'
                                        variant='outlined'
                                        ref={startDateRef}
                                        autoComplete='off'
                                        onChange={(e) => setStartDate(e.target.value)}
                                        value={startDate}
                                        InputLabelProps={{
                                            shrink: true,
                                        }}
                                    />
                                    <TextField id='endDate'
                                        type='date'
                                        label='End Date'
                                        variant='outlined'
                                        ref={endDateRef}
                                        autoComplete='off'
                                        onChange={(e) => setEndDate(e.target.value)}
                                        value={endDate}
                                        InputLabelProps={{
                                            shrink: true,
                                        }}
                                    />
                                </Container>
                            </div>
                            <button className="add-button" onClick={addRecord}>Add</button>
                            <UserDatewiseList
                                mealsMap={meals}
                                startDate={startDate}
                                endDate={endDate}
                                caloriesLimit={state.caloriesLimit}
                                expenseLimit={state.expenseLimit}
                            />
                        </>
                    )
            }
            <h1 className="welcome-header">__________</h1>
        </div >
    )
}

export default User
