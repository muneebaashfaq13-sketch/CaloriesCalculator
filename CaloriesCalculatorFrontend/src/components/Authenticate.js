import React from 'react';
import { useRef, useState, useEffect } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';
import { Container, Paper } from '@material-ui/core';
import VerifiedUserRoundedIcon from '@mui/icons-material/VerifiedUserRounded';
import axios from '../api/axios';
import { useNavigate } from 'react-router-dom';

const AUTH = '/auth/';

const useStyles = makeStyles((theme) => ({
    root: {
        '& > *': {
            margin: theme.spacing(1),
        },
    }
}));

const customStyle = {
    paperStyle: {
        padding: '50px 20px',
        width: 600,
        margin: '20px auto',
        elevation: 3,
        textAlign: 'center'
    },
};

export default function Authenticate() {
    const classes = useStyles();
    const tokenRef = useRef();
    const errRef = useRef();

    const [token, setToken] = useState('');
    const [errMsg, setErrMsg] = useState('');

    const navigate = useNavigate();

    useEffect(() => {
        tokenRef.current.focus();
    }, [])

    useEffect(() => {
        setErrMsg('');
    }, [token])

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {

            const response = await axios.get(AUTH + token,
                {
                    headers: { 'Content-Type': 'application/json' }
                }
            );

            const userId = response?.data?.id;
            const role = response?.data?.role;
            const name = response?.data?.name;
            const expenseLimit = response?.data?.expenseLimit;
            const caloriesLimit = response?.data?.caloriesLimit;

            setToken('');

            localStorage.setItem("state", JSON.stringify({ userId, token, role, name, expenseLimit, caloriesLimit }));

            if (role === 'Admin')
                navigate('/admin', { replace: true });
            else if (role === 'User')
                navigate('/user', { replace: true });
            else
                navigate('/login', { replace: true });

        } catch (err) {
            if (!err?.response) {
                setErrMsg('No Server Response');
            } else if (err.response?.status === 417) {
                setErrMsg('No Such User');
            } else if (err.response?.status === 406) {
                setErrMsg('Token Expired');
            } else if (err.response?.status === 400) {
                setErrMsg('Backend Exception');
            } else {
                setErrMsg('Login Failed');
            }
            errRef.current.focus();
        }
    }

    return (
        <section>
            <Paper style={customStyle.paperStyle}>
                <Container>
                    <form className={classes.root} onSubmit={handleSubmit}>

                        <VerifiedUserRoundedIcon style={customStyle.largeIcon} />

                        <h1>Please Authenticate</h1>

                        <p>Paste the token against the User to Login.</p>

                        <p ref={errRef} aria-live='assertive' className='error-message'>{errMsg}</p>

                        <TextField id='token'
                            label='Token'
                            variant='outlined'
                            ref={tokenRef}
                            autoComplete='off'
                            onChange={(e) => setToken(e.target.value)}
                            value={token}
                            required
                            fullWidth />

                        <button variant='contained' className='authenticate-button'>Authenticate</button>

                    </form>
                </Container>
            </Paper>
        </section>
    );
}
