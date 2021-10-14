import React, { useEffect } from 'react';
import MedicationHeader from '../MedicationHeader';
import MedicationCard from '../MedicationCard';
import { Grid } from '@material-ui/core';
import TakenCard from '../TakenCard';

const TakenList = ({ medication }) => {

    return <Grid containter direction="column">
        {medication != null && medication.length > 0 && medication.map((item) => <Grid item>
            <TakenCard id={item.id} medName={item.medName} taken={item.taken} date={item.date} />
        </Grid>)}
    </Grid>
}

export default TakenList;