import React, { useState } from 'react';
import { Grid, TextField, Button } from '@material-ui/core';
import Api from '../../Api';

const API = new Api();

const MedicalRecordForm = ({ patientId, onInsert }) => {

    const [disease, setDisease] = useState("");
    const [doctor, setDoctor] = useState("");
    const [hospital, setHospital] = useState("");

    return <form noValidate autoComplete={false}>
        <Grid container spacing={2}>
            <Grid item xs={12}>
                <TextField
                    variant="outlined"
                    required
                    fullWidth
                    label="disease"
                    value={disease}
                    autoFocus
                    onChange={(event) => setDisease(event.target.value)}
                />
            </Grid>
            <Grid item xs={12}>
                <TextField
                    variant="outlined"
                    required
                    fullWidth
                    label="hospital"
                    value={hospital}
                    autoFocus
                    onChange={(event) => setHospital(event.target.value)}
                />
            </Grid>
            <Grid item xs={12}>
                <TextField
                    variant="outlined"
                    required
                    fullWidth
                    label="doctor"
                    value={doctor}
                    autoFocus
                    onChange={(event) => setDoctor(event.target.value)}
                />
            </Grid>
            <Grid item xs={12}>
                <Button
                    fullWidth
                    variant="contained"
                    color="primary"
                    onClick={() => API.addMedicalRecord(patientId, disease, hospital, doctor, (response) => {
                        let data = {
                            disease: disease,
                            hospital: hospital,
                            doctor: doctor,
                            id: response
                        }
                        onInsert(data);
                    })}
                >
                    save
                </Button>
            </Grid>
        </Grid>
    </form>
}

export default MedicalRecordForm;