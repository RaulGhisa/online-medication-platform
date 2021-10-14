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

const CaregiverDetailsForm = ({ classes, patientId, caregiver, readOnly }) => {

    const [type, setType] = useState("");
    const [id, setId] = useState("");
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [birthDate, setBirthDate] = useState("");
    const [address, setAddress] = useState("");
    const [gender, setGender] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const [caregivers, setCaregivers] = useState([]);
    const [selectedCaregiver, setSelectedCaregiver] = useState(null);

    const genders = ["male", "female", "other"];

    useEffect(() => {
        if (caregiver != null) {
            setId(caregiver.id);
            setFirstName(caregiver.firstName);
            setLastName(caregiver.lastName);
            setBirthDate(caregiver.birthDate);
            setAddress(caregiver.address);
            setGender(caregiver.gender);
            setEmail(caregiver.email);
            setPassword(caregiver.password);
        }
    }, [caregiver]);

    useEffect(() => API.getUsers("caregiver", (response) => setCaregivers(response)), []);

    useEffect(() => {
        if (selectedCaregiver != null) {
            setId(selectedCaregiver.data.id);
            setFirstName(selectedCaregiver.data.firstName);
            setLastName(selectedCaregiver.data.lastName);
            setBirthDate(selectedCaregiver.data.birthDate);
            setAddress(selectedCaregiver.data.address);
            setGender(selectedCaregiver.data.gender);
            setEmail(selectedCaregiver.data.email);
            setPassword(selectedCaregiver.data.password);
        }
    }, [selectedCaregiver]);

    return <Container component="main" maxWidth="xs">
        <CssBaseline />
        <div className={classes.paper}>
            {/* <Avatar className={classes.avatar}>
                <AccountCircleIcon />
            </Avatar>
            <Typography component="h1" variant="h5">
                caregiver info
            </Typography> */}
            <form className={classes.form} noValidate autoComplete="false">
                <Grid container spacing={2}>
                    <Grid item xs={12}>
                        <AutoCompleteFormatter options={caregivers} field="id" selectedOption={selectedCaregiver} setSelectedOption={setSelectedCaregiver} />
                    </Grid>
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
                        />
                    </Grid>
                    <Grid item xs={12} sm={6}>
                        <TextField
                            variant="outlined"
                            required
                            fullWidth
                            label="first name"
                            InputProps={{
                                readOnly: true,
                            }}
                            value={firstName}
                        />
                    </Grid>
                    <Grid item xs={12} sm={6}>
                        <TextField
                            variant="outlined"
                            required
                            fullWidth
                            label="last name"
                            InputProps={{
                                readOnly: true,
                            }}
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
                                readOnly: true,
                            }}
                            value={birthDate}
                        />
                    </Grid>
                    <Grid item xs={6} sm={6}>
                        <TextField
                            variant="outlined"
                            fullWidth
                            required
                            label="gender"
                            InputProps={{
                                readOnly: true,
                            }}
                            value={gender}
                        />
                    </Grid>
                    <Grid item xs={12}>
                        <TextField
                            variant="outlined"
                            fullWidth
                            required
                            label="address"
                            InputProps={{
                                readOnly: true,
                            }}
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
                                readOnly: true,
                            }}
                            value={email}
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
                                readOnly: true,
                            }}
                            value={password}
                        />
                    </Grid>
                </Grid>
                {!readOnly &&
                    <Button
                        fullWidth
                        variant="contained"
                        color="primary"
                        className={classes.submit}
                        onClick={() =>
                            API.updatePatientCaregiver(patientId, id,
                                (response) => console.log(response))}
                    >
                        Save
                </Button>
                }
            </form>
        </div>
    </Container>
}

export default withStyles(styles)(CaregiverDetailsForm);
