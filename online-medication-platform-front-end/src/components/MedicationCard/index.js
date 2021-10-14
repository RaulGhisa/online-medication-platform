import React, { useEffect, useState, Fragment } from 'react';
import { Container, TextField, Button, CssBaseline, Grid, Card } from '@material-ui/core';
import Api from '../../Api';

const API = new Api();

const MedicationCard = ({ meds, onDelete, readOnly }) => {

    const [intervalId, setIntervalId] = useState("");
    const [medName, setMedName] = useState("");
    const [dosage, setDosage] = useState("");
    const [sideEffect, setSideEffect] = useState("");
    const [startDate, setStartDate] = useState("");
    const [endDate, setEndDate] = useState("");

    useEffect(() => {
        setMedName(meds.name);
        setDosage(meds.dosage);
        setSideEffect(meds.sideEffect);
        setIntervalId(meds.intakeInterval.id);
        setStartDate(meds.intakeInterval.startDate);
        setEndDate(meds.intakeInterval.endDate);
    }, [meds]);

    return <Card style={{ padding: 10, marginBottom: 10 }}>
        <form noValidate autoComplete={false}>
            <Grid container spacing={2}>
                <Grid item xs={12} sm={6}>
                    <TextField
                        variant="outlined"
                        required
                        fullWidth
                        label="med name"
                        InputProps={{
                            readOnly: true,
                        }}
                        value={medName}
                        autoFocus
                        onChange={(event) => setMedName(event.target.value)}
                    />
                </Grid>
                <Grid item xs={12} sm={6}>
                    <TextField
                        variant="outlined"
                        required
                        fullWidth
                        label="dosage"
                        InputProps={{
                            readOnly: true,
                        }}
                        value={dosage}
                        onChange={(event) => setDosage(event.target.value)}
                    />
                </Grid>
                <Grid item xs={12}>
                    <TextField
                        variant="outlined"
                        required
                        fullWidth
                        label="side effect"
                        InputProps={{
                            readOnly: true,
                        }}
                        value={sideEffect}
                        onChange={(event) => setSideEffect(event.target.value)}
                    />
                </Grid>
                <Grid item xs={12} sm={6}>
                    <TextField
                        variant="outlined"
                        required
                        fullWidth
                        label="start hour"
                        value={startDate}
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
                        onChange={(event) => setEndDate(event.target.value)}
                    />
                </Grid>
                {!readOnly &&
                    <Fragment>
                        <Grid item xs={12} sm={6}>
                            <Button
                                fullWidth
                                variant="contained"
                                color="primary"
                                onClick={() => API.updateInterval(meds.intakeInterval.id, startDate, endDate, (response) => console.log(response))}
                            >
                                save
                    </Button>
                        </Grid>
                        <Grid item xs={12} sm={6}>
                            <Button
                                fullWidth
                                variant="contained"
                                color="primary"
                                onClick={() => API.deleteInterval(meds.intakeInterval.id, (response) => onDelete(meds.intakeInterval.id))}
                            >
                                delete
                    </Button>
                        </Grid>
                    </Fragment>
                }
            </Grid>
        </form>
    </Card >
}

export default MedicationCard;