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

const CaregiversPanel = ({ classes, setId, caregivers, onDelete }) => {

    return <Grid container direction="column" className={classes.card}>
        {caregivers.map((element) =>
            <div key={element.id} onClick={() => setId(element.id)}>
                <CustomCard
                    type="caregiver"
                    id={element.id}
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

export default withStyles(styles)(CaregiversPanel);