import { Container } from "@material-ui/core";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "../../api/axios";
import MealsListAdmin from "./MealsListAdmin";

const customStyle = {
    centerAlign: {
        textAlign: 'center'
    },
};

const GET_ALL_MEALS = '/meals';

const Admin = () => {
    const state = JSON.parse(localStorage.getItem("state"));
    const navigate = useNavigate();

    const [meals, setMeals] = useState([]);
    const [addButtonStatus, setAddButtonStatus] = useState(false);
    const [reportButtonStatus, setReportButtonStatus] = useState(false);

    function addRecord() {
        setAddButtonStatus(true);
    }
    function report() {
        setReportButtonStatus(true);
    }

    function back() {
        setAddButtonStatus(false);
        setReportButtonStatus(false);
        window.location.reload();
    }

    useEffect(() => {
        async function getData() {
            try {

                if (meals.length === 0) {
                    const allMeals = await axios.get(GET_ALL_MEALS,
                        {
                            headers: { 'Content-Type': 'application/json' }
                        }
                    );
                    setMeals(allMeals.data);
                }

            } catch (err) {
                console.log(err);
            }
        }
        getData();
    })

    const logout = () => {
        localStorage.removeItem("state");
        navigate('/login', { replace: true });
    };

    return (
        <div className="main-div">
            <h1 className="welcome-header">{'Welcome, ' + state.name + '!'}</h1>
            <button className="logout-button" onClick={logout}>Logout</button>
            {addButtonStatus || reportButtonStatus ?
                (
                    <>
                        <button className="add-button" onClick={back}>Back</button>
                        {addButtonStatus ?
                            (
                                <>
                                </>
                            )
                            :
                            (
                                <>
                                </>
                            )

                        }
                    </>
                )
                :
                (
                    <>
                        <button className="add-button" onClick={addRecord}>Add</button>
                        <button className="report-button" onClick={report}>Report</button>
                        <div className='user-list-columns'>
                            <Container><b>User Name</b></Container>
                            <Container><b>Meal Name</b></Container>
                            <Container style={customStyle.centerAlign} ><b>Calories</b></Container>
                            <Container style={customStyle.centerAlign} ><b>Price($)</b></Container>
                            <Container style={customStyle.centerAlign} ><b>Date</b></Container>
                            <Container style={customStyle.centerAlign} ><b>Action</b></Container>
                        </div>
                        <MealsListAdmin
                            allMeal={meals}
                        />
                    </>
                )
            }
            <h1 className="welcome-header">__________</h1>
        </div>
    )
}

export default Admin
