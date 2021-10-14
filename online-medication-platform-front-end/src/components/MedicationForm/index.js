import React, { useState, useEffect } from 'react';
import { Button, Grid, TextField } from '@material-ui/core';
import AutoCompleteFormatter from '../AutoCompleteSelect';
import Api from '../../Api';

const API = new Api();
const MedicationForm = ({ medicationId, onInsert }) => {

    const [medId, setMedId] = useState("");
    const [medName, setMedName] = useState("");
    const [dosage, setDosage] = useState("");
    const [sideEffect, setSideEffect] = useState("");
    const [startDate, setStartDate] = useState("");
    const [endDate, setEndDate] = useState("");

    const [meds, setMeds] = useState([]);
    const [selectedMed, setSelectedMed] = useState(null);

    useEffect(() => API.getMeds((response) => setMeds(response)), []);

    useEffect(() => {
        if (selectedMed != null) {
            setMedId(selectedMed.data.id);
            setMedName(selectedMed.data.name);
            setDosage(selectedMed.data.dosage);
            setSideEffect(selectedMed.data.sideEffect);
        }
    }, [selectedMed]);

    return <form noValidate autoComplete={false}>
        <Grid container spacing={2}>
            <Grid item xs={12}>
                <AutoCompleteFormatter options={meds} field="name" selectedOption={selectedMed} setSelectedOption={setSelectedMed} />
            </Grid>
            <Grid item xs={12} sm={6}>
                <TextField
                    variant="outlined"
                    required
                    fullWidth
                    label="dosage"
                    value={dosage}
                    autoFocus
                    InputProps={{
                        readOnly: true,
                    }}
                />
            </Grid>
            <Grid item xs={12} sm={6}>
                <TextField
                    variant="outlined"
                    required
                    fullWidth
                    label="side effect"
                    value={sideEffect}
                    autoFocus
                    InputProps={{
                        readOnly: true,
                    }}
                />
            </Grid>
            <Grid item xs={12} sm={6}>
                <TextField
                    variant="outlined"
                    required
                    fullWidth
                    label="start hour"
                    value={startDate}
                    autoFocus
                    onChange={(event) => setStartDate(event.target.value)}
                />
            </Grid>
            <Grid item xs={12} sm={6}>
                <TextField
                    variant="outlined"
                    required
                    fullWidth
                    label="end hour"
                    value={endDate}
                    autoFocus
                    onChange={(event) => setEndDate(event.target.value)}
                />
            </Grid>
            <Grid item xs={12}>
                <Button
                    fullWidth
                    variant="contained"
                    color="primary"
                    onClick={() => API.addInterval(medicationId, medId, startDate, endDate, (response) => {
                        let data = {
                            dosage: dosage,
                            sideEffect: sideEffect,
                            medicationId: medicationId,
                            medName: medName,
                            id: response,
                            startDate: startDate,
                            endDate: endDate
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

export default MedicationForm;