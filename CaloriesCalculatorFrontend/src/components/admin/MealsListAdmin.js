import { Container, TextField } from "@material-ui/core";
import { useRef, useState } from "react";
import axios from "../../api/axios";

const MEAL = '/meals/';

const MealsListAdmin = ({ meals }) => {
    const [mealArr, setMealArr] = useState(meals);
    const [updateStatus, setUpdateStatus] = useState(false);
    const [currentMeal, setCurrentMeal] = useState();

    const mealNameRef = useRef();
    const [mealName, setMealName] = useState('');
    const caloriesRef = useRef();
    const [calories, setCalories] = useState(1);
    const priceRef = useRef();
    const [price, setPrice] = useState(1);
    const dateRef = useRef();
    const [date, setDate] = useState('');

    const deleteMeal = id => {
        try {
            axios.delete(MEAL + id,
                {
                    headers: { 'Content-Type': 'application/json' }
                }
            );
            const removedArr = [...mealArr].filter(meal => meal.id !== id);

            setMealArr(removedArr);
        }
        catch (err) {
            alert('Some issue occured');
        }
    };

    const updateMeal = () => {

        try {
            if (currentMeal.mealName !== mealName || currentMeal.calories !== calories ||
                currentMeal.price !== price || currentMeal.date !== date) {
                currentMeal.mealName = mealName;
                currentMeal.calories = calories;
                currentMeal.price = price;
                currentMeal.date = date;

                axios.put(MEAL + currentMeal.id,
                    {
                        mealName: mealName,
                        calories: calories,
                        price: price,
                        date: date
                    }
                );

                setMealArr(prev => prev.map(meal => (meal.id === currentMeal.id ? currentMeal : meal)));
            }

            setCurrentMeal('');
            setMealName('');
            setCalories(1);
            setPrice(1);
            setDate('');
            setUpdateStatus(false);
        }
        catch (err) {
            alert('Some issue occured');
        }
    };

    const editMeal = meal => {
        setCurrentMeal(meal);
        setMealName(meal.mealName);
        setCalories(meal.calories);
        setPrice(meal.price);
        setDate(meal.date);
        setUpdateStatus(true);
    }

    return mealArr.map((meal) => (
        <div className='user-list-row'>
            <Container>{meal.userName}</Container>
            {
                updateStatus && currentMeal.id === meal.id ?
                    (
                        <>
                            <Container>
                                <TextField id='mealName'
                                    label='Meal Name'
                                    variant='outlined'
                                    ref={mealNameRef}
                                    autoComplete='off'
                                    onChange={(e) => setMealName(e.target.value)}
                                    value={mealName}
                                    fullWidth />
                            </Container>
                            <Container className="container-alignment">
                                <TextField id='calories'
                                    type='number'
                                    label='Calories'
                                    variant='outlined'
                                    ref={caloriesRef}
                                    autoComplete='off'
                                    onChange={(e) => setCalories(e.target.value)}
                                    value={calories}
                                    fullWidth />
                            </Container>
                            <Container className="container-alignment">
                                <TextField id='price'
                                    type='number'
                                    label='Price'
                                    variant='outlined'
                                    ref={priceRef}
                                    autoComplete='off'
                                    onChange={(e) => setPrice(e.target.value)}
                                    value={price}
                                    fullWidth />
                            </Container>
                            <Container className="container-alignment">
                                <TextField id='date'
                                    type='date'
                                    label="Date"
                                    ref={dateRef}
                                    autoComplete='off'
                                    onChange={(e) => setDate(e.target.value)}
                                    value={date}
                                    InputLabelProps={{
                                        shrink: true,
                                    }} />
                            </Container>
                            <Container className="container-alignment" >
                                <button className="edit-button" onClick={() => updateMeal()} >Update</button>
                            </Container>
                        </>
                    )
                    :
                    (
                        <>
                            <Container>{meal.mealName}</Container>
                            <Container className="container-alignment" >{meal.calories}</Container>
                            <Container className="container-alignment" >{meal.price}</Container>
                            <Container className="container-alignment" >{meal.date}</Container>
                            <Container className="container-alignment" >
                                <button className="edit-button" onClick={() => editMeal(meal)}>Edit</button>
                                <button className="delete-button" onClick={() => deleteMeal(meal.id)} >Delete</button>
                            </Container>
                        </>
                    )
            }
        </div>
    ));

};

export default MealsListAdmin;
