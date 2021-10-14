import React from 'react';
import { Card, CardHeader, Avatar, Typography, CardContent, CardActions, Button, Grid, CardActionArea } from '@material-ui/core';
import { red } from '@material-ui/core/colors';
import { withStyles } from '@material-ui/styles';
import Api from '../../Api';
import { withRouter } from 'react-router-dom';

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

const API = new Api();

const CustomCard = ({ classes, history, id, type, firstName, lastName, birthDate, gender, onDelete }) => {

    return <Card className={classes.card}>
        <CardHeader
            avatar={
                <Avatar className={classes.avatar}>
                    {firstName.charAt(0)}
                </Avatar>
            }
            title={firstName + " " + lastName}
            subheader={`${birthDate}, ${gender}`}
        />
        <CardActions>
            <Button variant="contained" size="small" color="primary" className={classes.button}
                onClick={() => API.deleteUser(type, id, (response) => onDelete(id))}
            >
                delete
            </Button>
            <Button variant="contained" size="small" color="primary" className={classes.button}
                onClick={() => history.push({ pathname: `/doctor/${type}`, state: { id: id } })}
            >
                see more
            </Button>
        </CardActions>
    </Card>
}

export default withRouter(withStyles(styles)(CustomCard));