import React from 'react';
import MedicalRecordCard from '../MedicalRecordCard';
import { Grid } from '@material-ui/core';

const MedicalRecordList = ({ medicalRecords, onDelete, readOnly }) => {
    return <Grid container direction="column">
        {medicalRecords != null && medicalRecords.length > 0 && medicalRecords.map((item) => <Grid item>
            <MedicalRecordCard key={item.id} id={item.id} disease={item.disease} hospital={item.hospital} doctor={item.doctor} onDelete={onDelete} readOnly={readOnly} />
        </Grid>)}
    </Grid>
}

export default MedicalRecordList;