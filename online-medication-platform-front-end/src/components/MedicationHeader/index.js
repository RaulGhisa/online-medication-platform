import React, { useState, useEffect } from 'react';
import { TextField, Grid, Card, Button } from '@material-ui/core';
import Api from '../../Api';

const API = new Api();

const MedicationHeader = ({ medId, medStartDate, medEndDate, readOnly }) => {

    const [startDate, setStartDate] = useState("");
    const [endDate, setEndDate] = useState("");

    useEffect(() => {
        setStartDate(medStartDate);
        setEndDate(medEndDate);
    }, [medStartDate, medEndDate]);

    return <Card style={{ padding: 10, marginBottom: 10 }}>
        <Grid container>
            <form autoComplete={false} style={{ width: "100%" }}>
                <Grid container spacing={2}>
                    <Grid item xs={12} sm={6}>
                        <TextField
                            variant="outlined"
                            required
                            fullWidth
                            label="start date"
                            value={startDate}
                            onChange={(event) => setStartDate(event.target.value)}
                        />
                    </Grid>
                    <Grid item xs={12} sm={6}>
                        <TextField
                            variant="outlined"
                            required
                            fullWidth
                            label="end date"
                            value={endDate}
                            onChange={(event) => setEndDate(event.target.value)}
                        />
                    </Grid>
                    {!readOnly &&
                        <Grid item xs={12}>
                            <Button
                                fullWidth
                                variant="contained"
                                color="primary"
                                onClick={() => API.updateMedication(medId, startDate, endDate, (response) => console.log(response))}
                            >
                                save
                        </Button>
                        </Grid>
                    }
                </Grid>
            </form>
        </Grid>
    </Card>
}

export default MedicationHeader;