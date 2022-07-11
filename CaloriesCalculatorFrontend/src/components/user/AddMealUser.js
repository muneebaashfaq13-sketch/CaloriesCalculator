import { Container, makeStyles, Paper, TextField } from "@material-ui/core";
import { useEffect, useRef, useState } from "react";
import axios from "../../api/axios";

const ADD_MEAL = '/meals/';

const useStyles = makeStyles((theme) => ({
    root: {
        '& > *': {
            margin: theme.spacing(1),
        },
    }
}));

const AddMealUser = ({ userId, currentDate }) => {
    const classes = useStyles();

    const mealNameRef = useRef();
    const [mealName, setMealName] = useState('');
    const caloriesRef = useRef();
    const [calories, setCalories] = useState(1);
    const priceRef = useRef();
    const [price, setPrice] = useState(1);
    const dateRef = useRef();
    const [date, setDate] = useState(currentDate);
    const errRef = useRef();
    const [errMsg, setErrMsg] = useState('');

    const customStyle = {
        paperStyle: {
            padding: '50px 20px',
            width: 600,
            margin: '20px auto',
            elevation: 3,
            textAlign: 'center'
        },
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {

            await axios.post(ADD_MEAL + userId,
                {
                    mealName,
                    calories,
                    price,
                    date
                }
            );

            setMealName('');
            setCalories(1);
            setPrice(1);
            setDate(currentDate);
            alert('Item Added Successfully!');

        } catch (err) {
            if (err.response?.status === 400) {
                setErrMsg('Request Exception! Please validate.');
            }
            else {
                setErrMsg('Service not available!');
            }
        }
    }

    useEffect(() => {
        setErrMsg('');
    }, [mealName,calories,price,date])

    return (
        <section>
            <Paper style={customStyle.paperStyle}>
                <Container>
                    <form className={classes.root} onSubmit={handleSubmit}>
                        <p ref={errRef} aria-live='assertive' className='error-message'>{errMsg}</p>
                        <TextField id='mealName'
                            label='Meal Name'
                            variant='outlined'
                            ref={mealNameRef}
                            autoComplete='off'
                            onChange={(e) => setMealName(e.target.value)}
                            value={mealName}
                            required
                            fullWidth />

                        <TextField id='calories'
                            type='number'
                            label='Calories'
                            variant='outlined'
                            ref={caloriesRef}
                            autoComplete='off'
                            onChange={(e) => setCalories(e.target.value)}
                            value={calories}
                            required
                            fullWidth />

                        <TextField id='price'
                            type='number'
                            label='Price'
                            variant='outlined'
                            ref={priceRef}
                            autoComplete='off'
                            onChange={(e) => setPrice(e.target.value)}
                            value={price}
                            required
                            fullWidth />

                        <TextField id='date'
                            type='date'
                            label='Date'
                            variant='outlined'
                            ref={dateRef}
                            autoComplete='off'
                            onChange={(e) => setDate(e.target.value)}
                            value={date}
                            required
                            fullWidth
                            className={classes.textField}
                            InputLabelProps={{
                                shrink: true,
                            }}
                        />

                        <button variant='contained' className='authenticate-button'>Submit</button>

                    </form>
                </Container>
            </Paper>
        </section>
    )

};

export default AddMealUser;
