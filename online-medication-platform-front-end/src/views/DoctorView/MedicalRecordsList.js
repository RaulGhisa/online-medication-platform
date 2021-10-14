import React, { useState, useEffect } from 'react';
import Api from '../../Api';
import { Typography, Grid } from '@material-ui/core';
import MedicalRecordCard from '../../components/MedicalRecordCard';

const API = new Api();

const MedicalRecordsList = ({ id }) => {

    const [records, setRecords] = useState([]);

    useEffect(() => {
        let records = API.getMedicalRecords(id).records;
        setRecords(records);
    }, []);

    return <Grid container direction="column">
        {records.map((element, index) =>
            <Grid item key={index} >
                <MedicalRecordCard disease={element.disease} hospital={element.hospital} doctor={element.doctor} />
            </Grid>
        )}
    </Grid>
}

export default MedicalRecordsList;