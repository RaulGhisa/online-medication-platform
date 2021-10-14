import React from 'react';
import MedicalRecordCard from '../MedicalRecordCard';
import { Grid, Button } from '@material-ui/core';
import ActivityCard from '../ActivityCard';

import { withRouter } from 'react-router-dom';

const ActivityList = ({ activities, readOnly, history }) => {
    return <Grid container direction="column">
        <Button variant="contained" onClick={() => history.push("/chart")}>
            see charts
        </Button>
        {activities != null && activities.length > 0 && activities.map((item) =>
            <Grid item key={item.activityId} >
                <ActivityCard id={item.activityId} activity={item.activityLabel} startTime={item.startTime} endTime={item.endTime} isProblem={item.isProblem} recommendation={item.recommendation} readOnly={readOnly} />
            </Grid>)}
    </Grid>
}

export default withRouter(ActivityList);