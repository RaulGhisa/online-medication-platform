import React, { useEffect, useState } from 'react';
import { Card, CardHeader, Avatar, Typography, CardContent, CardActions, Button, Grid, CardActionArea, TextareaAutosize } from '@material-ui/core';
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

const ActivityCard = ({ classes, id, startTime, endTime, activity, isProblem, recommendation, readOnly }) => {

    const [newRecommendation, setNewRecommendation] = useState("");

    useEffect(() => {
        let rec = "".concat(recommendation);

        setNewRecommendation(rec);
    }, [recommendation])

    return <Card className={classes.card}>
        <CardHeader
            avatar={
                <Avatar className={classes.avatar}>
                    {activity.charAt(0)}
                </Avatar>
            }
            title={activity}
            subheader={`${startTime} - ${endTime}`}
        />
        <CardContent>
            <Typography>{isProblem === "true" ? "wierd behavior" : "normal behavior"}</Typography>
            <TextareaAutosize
                rowsMax={4}
                placeholder={newRecommendation && "write a recommendation"}
                defaultValue={newRecommendation}
                onChange={(event) => setNewRecommendation(event.target.value)}
            />
        </CardContent>
        <CardActions>
            <Button variant="contained" size="small" color="primary" className={classes.button}
                onClick={() => API.soapInsertRecommendation(id, newRecommendation)}
            >
                save
            </Button>
        </CardActions>
    </Card>
}

export default withRouter(withStyles(styles)(ActivityCard));