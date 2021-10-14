import React, { useState, useEffect } from 'react';
import Api from '../../Api';
import { withStyles } from '@material-ui/styles';
import { Grid } from '@material-ui/core';
import CustomCard from '../../components/CustomCard';
import MedCard from '../../components/MedCard';

const API = new Api();

const MedsView = ({ id }) => {

    const [meds, setMeds] = useState([]);

    useEffect(() => {
        let meds = API.getMeds(id).meds;
        setMeds(meds);
    }, []);

    return <Grid container direction="column">
        {meds.map((element, index) =>
            <MedCard key={index} medName={element.name} start={element.start} end={element.end} />
        )}
    </Grid>
}
export default MedsView;