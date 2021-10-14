import React, { useEffect, useState } from 'react';
import { withStyles, Grid, Button } from '@material-ui/core';
import { withSnackbar } from 'notistack';

import Api from '../../Api';
import PatientDetailsForm from '../../components/PatientDetailsForm';
import Connection from '../../connection';

import _ from 'lodash';

const styles = (theme) => {

}

const API = new Api();
const wsSourceUrl = window.location.protocol + "//" + "localhost:8080" + "/notification";

let stompClient = null;

const CaregiverView = ({ classes, enqueueSnackbar, id, type }) => {

    const [patients, setPatients] = useState([]);
    const [caregiver, setCaregiver] = useState({});

    const notify = (msg) => {
        enqueueSnackbar(`The patient: ${msg.patientName} has ${msg.content}.`, { variant: "warning" });
    }

    useEffect(() => API.getUserAccount("caregiver", id, (response) => {
        setPatients(response.patients);
        setCaregiver(response.caregiver);
        stompClient = Connection(wsSourceUrl);
        stompClient.connect({}, (frame) => {
            stompClient.subscribe(`/topic/problems/${id}`, (notification) => {
                notify(JSON.parse(notification.body));
            })
        })
        enqueueSnackbar('You have subscribed successfully to the notification provider.', { variant: "info" })
    }), []);

    const sendMsg = (msg) => {
        stompClient.send(`/app/caregiver/${id}`, {}, JSON.stringify(msg));
    }

    return <Grid container>
        <Grid item xs={6}>
            <PatientDetailsForm label="caregiver info" patient={caregiver} readOnly={type != "doctor"} />
        </Grid>
        <Grid item xs={6}>
            {patients.map((item) => <PatientDetailsForm label="patient info" patient={item} readOnly={type != "doctor"} />)}
        </Grid>
    </Grid >
}

export default withSnackbar(withStyles(styles)(CaregiverView));