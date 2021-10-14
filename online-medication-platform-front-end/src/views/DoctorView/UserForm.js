import React, { useState, useEffect } from 'react';
import Avatar from '@material-ui/core/Avatar';
import Button from '@material-ui/core/Button';
import CssBaseline from '@material-ui/core/CssBaseline';
import TextField from '@material-ui/core/TextField';
import Link from '@material-ui/core/Link';
import Grid from '@material-ui/core/Grid';
import Box from '@material-ui/core/Box';
import LocalHospitalIcon from '@material-ui/icons/LocalHospital';
import Typography from '@material-ui/core/Typography';
import Container from '@material-ui/core/Container';
import { withStyles } from '@material-ui/styles';
import { FormControl, InputLabel, Select, MenuItem } from '@material-ui/core';
import Api from '../../Api';
import AutoCompleteFormatter from '../../components/AutoCompleteSelect';
import { SSL_OP_DONT_INSERT_EMPTY_FRAGMENTS } from 'constants';

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

const UserForm = ({ classes, onInsert }) => {

    const [type, setType] = useState("");
    const [id, setId] = useState("");
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [birthDate, setBirthDate] = useState("");
    const [address, setAddress] = useState("");
    const [gender, setGender] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const types = ["patient", "caregiver"]
    const genders = ["male", "female", "other"];

    return <Container component="main" maxWidth="xs">
        <CssBaseline />
        <div className={classes.paper}>
            <Avatar className={classes.avatar}>
                <LocalHospitalIcon />
            </Avatar>
            <Typography component="h1" variant="h5">
                add patients or caregivers
            </Typography>
            <form className={classes.form} noValidate autoComplete="false">
                <Grid container spacing={2}>
                    <Grid item xs={12}>
                        <FormControl variant="outlined" className={classes.genderSelect}>
                            <InputLabel>type</InputLabel>
                            <Select value={type} onChange={(event) => setType(event.target.value)}>
                                <MenuItem value="">
                                    <em>None</em>
                                </MenuItem>
                                {types.map((item, index) => <MenuItem key={index} value={item}>{item}</MenuItem>)}
                            </Select>
                        </FormControl>
                    </Grid>
                    <Grid item xs={12}>
                        <TextField
                            variant="outlined"
                            required
                            fullWidth
                            label="identification no."
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
                            value={password}
                            onChange={(event) => setPassword(event.target.value)}
                        />
                    </Grid>
                </Grid>
                <Button
                    fullWidth
                    variant="contained"
                    color="primary"
                    className={classes.submit}
                    onClick={() => API.addUser(type, id, firstName, lastName, birthDate, gender, address, email, password, (response) => {
                        let data = {};
                        if (response != 0) {
                            data = {
                                id: id,
                                email: email,
                                password: password,
                                firstName: firstName,
                                lastName: lastName,
                                birthDate: birthDate,
                                gender: gender,
                                address: address,
                                type: type,
                            }
                        } else {
                            data = null;
                        }

                        onInsert(data);
                    })}
                >
                    Save
                </Button>
            </form>
        </div>
    </Container>
}

export default withStyles(styles)(UserForm);
