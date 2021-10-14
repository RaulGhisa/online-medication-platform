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

const MedForm = ({ classes }) => {

    const [patientId, setPatientId] = useState(undefined);
    const [medName, setMedName] = useState("");
    const [startDate, setStartDate] = useState("");
    const [endDate, setEndDate] = useState("");

    return <Container component="main" maxWidth="xs">
        <CssBaseline />
        <div className={classes.paper}>
            <Avatar className={classes.avatar}>
                <LocalHospitalIcon />
            </Avatar>
            <Typography component="h1" variant="h5">
                Add / Edit med
        </Typography>
            <form className={classes.form} noValidate autoComplete="false">
                <Grid container spacing={2}>
                    <Grid item xs={12}>
                        <TextField
                            variant="outlined"
                            required
                            fullWidth
                            label="patient identification no."
                            onChange={(event) => setPatientId(event.target.value)}
                            value={patientId}
                        />
                    </Grid>
                    <Grid item xs={12}>
                        <TextField
                            variant="outlined"
                            required
                            fullWidth
                            label="med name"
                            onChange={(event) => setMedName(event.target.value)}
                            value={medName}
                        />
                    </Grid>
                    <Grid item xs={12} sm={6}>
                        <TextField
                            variant="outlined"
                            required
                            fullWidth
                            label="start date"
                            onChange={(event) => setStartDate(event.target.value)}
                            value={startDate}
                        />
                    </Grid>
                    <Grid item xs={12} sm={6}>
                        <TextField
                            variant="outlined"
                            required
                            fullWidth
                            label="end date"
                            onChange={(event) => setEndDate(event.target.value)}
                            value={endDate}
                        />
                    </Grid>
                </Grid>
                <Button
                    fullWidth
                    variant="contained"
                    color="primary"
                    className={classes.submit}
                    onClick={() => API.addMed(patientId, medName, startDate, endDate)}
                >
                    Save
                </Button>
            </form>
        </div>
    </Container>
}

export default withStyles(styles)(MedForm);
