import { Container, makeStyles, Paper, TextField } from "@material-ui/core";
import { useEffect, useRef, useState } from "react";
import axios from "../../api/axios";

const ADD_MEAL = '/meals/';
const IDS_LIST = '/users/idsList'

const useStyles = makeStyles((theme) => ({
    root: {
        '& > *': {
            margin: theme.spacing(1),
        },
    }
}));

const AddMealAdmin = () => {
    const classes = useStyles();

    const userIdRef = useRef();
    const [userId, setUserId] = useState();
    const mealNameRef = useRef();
    const [mealName, setMealName] = useState('');
    const caloriesRef = useRef();
    const [calories, setCalories] = useState(1);
    const priceRef = useRef();
    const [price, setPrice] = useState(1);
    const dateRef = useRef();
    const [date, setDate] = useState('');
    const errRef = useRef();
    const [errMsg, setErrMsg] = useState('');
    const [idsList, setIdsList] = useState([]);


    const customStyle = {
        paperStyle: {
            padding: '50px 20px',
            width: 600,
            margin: '20px auto',
            elevation: 3,
            textAlign: 'center'
        },
    };

    useEffect(() => {
        async function getIds() {
            try {

                if (idsList.length === 0) {
                    const response = await axios.get(IDS_LIST,
                        {
                            headers: { 'Content-Type': 'application/json' }
                        }
                    );
                    setIdsList(response.data);
                }

            } catch (err) {
                console.log(err);
            }
        }
        getIds();
    })

    useEffect(() => {
        setErrMsg('');
    }, [userId])

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            var isValidId = false;

            idsList.forEach(id => {
                if (Number(userId) === id)
                    isValidId = true;
            });

            if (isValidId) {
                await axios.post(ADD_MEAL + userId,
                    {
                        mealName,
                        calories,
                        price,
                        date
                    }
                );

                setUserId('');
                setMealName('');
                setCalories(1);
                setPrice(1);
                setDate('');
                setErrMsg('');
                alert('Item Added Successfully!');

            } else {
                setErrMsg('Not A valid User-ID');
            }


        } catch (err) {
            if (err.response?.status === 400) {
                setErrMsg('Request Exception! Please validate.');
            }
            else {
                setErrMsg('Service not available!');
            }
        }
    }

    return (
        <section>
            <Paper style={customStyle.paperStyle}>
                <Container>
                    <form className={classes.root} onSubmit={handleSubmit}>
                        <p ref={errRef} aria-live='assertive' className='error-message'>{errMsg}</p>
                        <TextField id='userId'
                            type='number'
                            label='User-ID'
                            variant='outlined'
                            ref={userIdRef}
                            autoComplete='off'
                            onChange={(e) => setUserId(e.target.value)}
                            value={userId}
                            required
                            fullWidth />

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

export default AddMealAdmin;
