import React, { useEffect } from 'react';
import MedicationHeader from '../MedicationHeader';
import MedicationCard from '../MedicationCard';
import { Grid } from '@material-ui/core';

const MedicationList = ({ medication, meds, onDelete, readOnly }) => {

    useEffect(() => console.log(medication), [medication]);

    return <Grid containter direction="column">
        <Grid item>
            <MedicationHeader medId={medication.id} medStartDate={medication.startDate} medEndDate={medication.endDate} readOnly={readOnly}/>
        </Grid>
        {meds != null && meds.length > 0 && meds.map((item) => <Grid item>
            <MedicationCard meds={item} onDelete={onDelete} readOnly={readOnly}/>
        </Grid>)}
    </Grid>
}

export default MedicationList;