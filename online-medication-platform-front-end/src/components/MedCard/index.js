import React from 'react';
import { Card, CardHeader, Avatar, Typography, CardContent, CardActions, Button, Grid, CardActionArea } from '@material-ui/core';
import { withStyles } from '@material-ui/styles';

const styles = (theme) => ({
    card: {
        marginBottom: 15,
        cursor: "pointer",
    },
    avatar: {
        backgroundColor: "primary",
    },
    button: {
        textAlign: "right",
    }
});

const CustomCard = ({ classes, medName, start, end }) => {

    return <Card className={classes.card}>
        <CardHeader
            title={medName}
            subheader={`${start} => ${end}`}
        />
        <CardActions>
            <Button variant="contained" size="small" color="primary" className={classes.button}>
                delete
            </Button>
        </CardActions>
    </Card>
}

export default withStyles(styles)(CustomCard);