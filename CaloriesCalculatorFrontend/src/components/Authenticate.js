import React from 'react';
import { useRef, useState, useEffect, useContext } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';
import { Container, Paper} from '@material-ui/core';
import VerifiedUserRoundedIcon from '@mui/icons-material/VerifiedUserRounded';
import AuthContext from '../context/AuthProvider';
import axios from '../api/axios';


const LOGIN_URL = '/auth';

const useStyles = makeStyles((theme) => ({
  root: {
    '& > *': {
      margin: theme.spacing(1),
    },
  }
}));

const customStyle = {
    largeIcon: {
      width: 60,
      height: 60,
    },
    paperStyle: {
        padding: "50px 20px",
        width: 600,
        margin: "20px auto",
        elevation: 3,
    },
    error: {
        color: "red",
    },
  };

export default function Authenticate() {
    const classes = useStyles();
    const { setAuth } = useContext(AuthContext);
    const tokenRef = useRef();
    const errRef = useRef();

    const [token, setToken] = useState('');
    const [errMsg, setErrMsg] = useState('');
    const [success, setSuccess] = useState(false);

    useEffect(() => {
        tokenRef.current.focus();
    }, [])

    useEffect(() => {
        setErrMsg('');
    }, [token])

    const handleSubmit = async (e) => {
        e.preventDefault();
        
        try {
            const response = await axios.get(LOGIN_URL+"/"+token,
                {
                    headers: { 'Content-Type': 'application/json' }
                }
            );
            console.log(JSON.stringify(response?.data));
            const accessToken = response?.data?.accessToken;
            const roles = response?.data?.roles;
            setAuth({ token, roles, accessToken });
            setToken('');
            setSuccess(true);
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
        <>
            {
                success ? 
                (<section>
                    <h1>You are logged in!</h1>
                    <br />
                    <p>
                        <a href="#">Go to Home</a>
                    </p>
                </section>
                ) : 
                (<section>
                    <Paper style={customStyle.paperStyle}>
                            <Container>
                                <form className={classes.root} onSubmit={handleSubmit}>
                
                                    <VerifiedUserRoundedIcon style={customStyle.largeIcon}/>
                
                                    <h1>Please Authentication</h1>
                
                                    <p>Paste the token against the User to Login.</p>
                
                                    <p ref={errRef} className={errMsg ? "errmsg" : "offscreen"} aria-live="assertive" style={customStyle.error}>{errMsg}</p>

                                    <TextField id="token" 
                                    label="Token" 
                                    variant="outlined" 
                                    ref={tokenRef}
                                    autoComplete="off"
                                    onChange={(e) => setToken(e.target.value)}
                                    value={token}
                                    required
                                    fullWidth/>
                
                                    <button variant="contained" className='Submit-button'>Authenticate</button>
            
                                </form>
                            </Container>
                        </Paper>
                    </section>
                )
            }
        </>
    );
}
