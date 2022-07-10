import { Container, makeStyles, Paper, TextField } from "@material-ui/core";
import { useRef, useState } from "react";
import axios from "../../api/axios";

const ADD_MEAL = '/meals/';

const useStyles = makeStyles((theme) => ({
    root: {
        '& > *': {
            margin: theme.spacing(1),
        },
    }
}));

const AddMeal = ({ userId, currentDate }) => {
    const classes = useStyles();

    const mealNameRef = useRef();
    const [mealName, setMealName] = useState('');
    const caloriesRef = useRef();
    const [calories, setCalories] = useState(1);
    const priceRef = useRef();
    const [price, setPrice] = useState(1);
    const dateRef = useRef();
    const [date, setDate] = useState(currentDate);

    const customStyle = {
        largeIcon: {
            width: 60,
            height: 60,
        },
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
                alert('Invalid Data-type!');
            }
        }
    }

    return (
        <section>
            <Paper style={customStyle.paperStyle}>
                <Container>
                    <form className={classes.root} onSubmit={handleSubmit}>

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
                            label='Calories'
                            variant='outlined'
                            ref={caloriesRef}
                            autoComplete='off'
                            onChange={(e) => setCalories(e.target.value)}
                            value={calories}
                            required
                            fullWidth />

                        <TextField id='price'
                            label='Price'
                            variant='outlined'
                            ref={priceRef}
                            autoComplete='off'
                            onChange={(e) => setPrice(e.target.value)}
                            value={price}
                            required
                            fullWidth />

                        <TextField id="date"
                            label="Date"
                            variant='outlined'
                            ref={dateRef}
                            autoComplete='off'
                            onChange={(e) => setDate(e.target.value)}
                            value={date}
                            required
                            fullWidth
                            type="date"
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

export default AddMeal;
