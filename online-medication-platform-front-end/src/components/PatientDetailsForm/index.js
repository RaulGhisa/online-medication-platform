import React, { useState, useEffect } from 'react';
import Avatar from '@material-ui/core/Avatar';
import Button from '@material-ui/core/Button';
import CssBaseline from '@material-ui/core/CssBaseline';
import TextField from '@material-ui/core/TextField';
import Link from '@material-ui/core/Link';
import Grid from '@material-ui/core/Grid';
import Box from '@material-ui/core/Box';
import AccountCircleIcon from '@material-ui/icons/AccountCircle';
import Typography from '@material-ui/core/Typography';
import Container from '@material-ui/core/Container';
import { withStyles } from '@material-ui/styles';
import { FormControl, InputLabel, Select, MenuItem } from '@material-ui/core';
import Api from '../../Api';
import AutoCompleteFormatter from '../../components/AutoCompleteSelect';

const styles = (theme) => ({
    '@global': {
        body: {
            // backgroundColor: theme.palette.common.white,
        },
    },
    paper: {
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
    },
    avatar: {
        margin: 10,
        // backgroundColor: theme.palette.secondary.main,
    },
    form: {
        width: '100%', // Fix IE 11 issue.
        marginTop: 15,
    },
    submit: {
        marginTop: 15,
        marginBottom: 15,
    },
    genderSelect: {
        width: "100%",
    },
});

const API = new Api();

const PatientDetailsFrom = ({ classes, label, patient, readOnly }) => {

    const [type, setType] = useState("");
    const [id, setId] = useState("");
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [birthDate, setBirthDate] = useState("");
    const [address, setAddress] = useState("");
    const [gender, setGender] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const genders = ["male", "female", "other"];

    useEffect(() => {
        setId(patient.id);
        setFirstName(patient.firstName);
        setLastName(patient.lastName);
        setBirthDate(patient.birthDate);
        setAddress(patient.address);
        setGender(patient.gender);
        setEmail(patient.email);
        setPassword(patient.password);
    }, [patient]);

    return <Container component="main" maxWidth="xs">
        <CssBaseline />
        <div className={classes.paper}>
            <Avatar className={classes.avatar}>
                <AccountCircleIcon />
            </Avatar>
            <Typography component="h1" variant="h5">
                {label}
            </Typography>
            <form className={classes.form} noValidate autoComplete="false">
                <Grid container spacing={2}>
                    <Grid item xs={12}>
                        <TextField
                            variant="outlined"
                            required
                            fullWidth
                            label="identification no."
                            InputProps={{
                                readOnly: true,
                            }}
                            value={id}
                            onChange={(event) => setId(event.target.value)}
                        />
                    </Grid>
                    <Grid item xs={12} sm={6}>
                        <TextField
                            variant="outlined"
                            required
                            fullWidth
                            label="first name"
                            InputProps={{
                                readOnly: readOnly,
                            }}
                            value={firstName}
                            onChange={(event) => setFirstName(event.target.value)}
                        />
                    </Grid>
                    <Grid item xs={12} sm={6}>
                        <TextField
                            variant="outlined"
                            required
                            fullWidth
                            label="last name"
                            InputProps={{
                                readOnly: readOnly,
                            }}
                            onChange={(event) => setLastName(event.target.value)}
                            value={lastName}
                        />
                    </Grid>
                    <Grid item xs={6} sm={6}>
                        <TextField
                            variant="outlined"
                            fullWidth
                            required
                            label="birth date"
                            InputProps={{
                                readOnly: readOnly,
                            }}
                            onChange={(event) => setBirthDate(event.target.value)}
                            value={birthDate}
                        />
                    </Grid>
                    <Grid item xs={6} sm={6}>
                        <FormControl variant="outlined" className={classes.genderSelect}>
                            <InputLabel>gender</InputLabel>
                            <Select value={gender} onChange={(event) => setGender(event.target.value)}>
                                <MenuItem value="">
                                    <em>None</em>
                                </MenuItem>
                                {genders.map((item, index) => <MenuItem key={index} value={item}>{item}</MenuItem>)}
                            </Select>
                        </FormControl>
                    </Grid>
                    <Grid item xs={12}>
                        <TextField
                            variant="outlined"
                            fullWidth
                            required
                            label="address"
                            InputProps={{
                                readOnly: readOnly,
                            }}
                            onChange={(event) => setAddress(event.target.value)}
                            value={address}
                        />
                    </Grid>
                    <Grid item xs={12}>
                        <TextField
                            variant="outlined"
                            required
                            fullWidth
                            label="email"
                            InputProps={{
                                readOnly: readOnly,
                            }}
                            value={email}
                            onChange={(event) => setEmail(event.target.value)}
                        />
                    </Grid>
                    <Grid item xs={12}>
                        <TextField
                            variant="outlined"
                            required
                            fullWidth
                            label="password"
                            type="password"
                            InputProps={{
                                readOnly: readOnly,
                            }}
                            value={password}
                            onChange={(event) => setPassword(event.target.value)}
                        />
                    </Grid>
                </Grid>
                {!readOnly && <Button
                    fullWidth
                    variant="contained"
                    color="primary"
                    className={classes.submit}
                    onClick={() =>
                        API.updatePatient(id, firstName, lastName, birthDate, gender, address, email, password,
                            (response) => console.log(response))}
                >
                    Save
                    </Button>
                }
            </form>
        </div>
    </Container>
}

export default withStyles(styles)(PatientDetailsFrom);
