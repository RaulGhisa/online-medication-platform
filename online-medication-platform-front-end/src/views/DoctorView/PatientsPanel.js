import React, { useState, useEffect, Fragment } from 'react';
import { Grid, withStyles } from '@material-ui/core';
import CustomCard from '../../components/CustomCard';
import Api from '../../Api';

const styles = (theme) => ({
    card: {
        maxWidth: 450,
    }
});

const API = new Api();

const PatientsView = ({ classes, setId, patients, onDelete }) => {

    return <Grid container direction="column" className={classes.card}>
        {patients.map((element) =>
            <div key={element.id} onClick={() => setId(element.id)}>
                <CustomCard
                    key={element.id}
                    id={element.id}
                    type="patient"
                    firstName={element.firstName}
                    lastName={element.lastName}
                    birthDate={element.birthDate}
                    gender={element.gender}
                    address={element.address}
                    onDelete={onDelete}
                />
            </div>
        )}
    </Grid>
}

export default withStyles(styles)(PatientsView);