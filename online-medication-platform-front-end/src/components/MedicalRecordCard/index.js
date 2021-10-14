import React from 'react';
import { Card, CardHeader, Avatar, Typography, CardContent, CardActions, Button, Grid, CardActionArea } from '@material-ui/core';
import { withStyles } from '@material-ui/styles';
import Api from '../../Api';

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

const MedicalRecordCard = ({ classes, id, disease, hospital, doctor, onDelete, readOnly }) => {

    return <Card className={classes.card}>
        <CardHeader
            title={disease}
            subheader={`${hospital}, ${doctor}`}
        />
        {!readOnly &&
            <CardActions>
                <Button
                    variant="contained"
                    color="primary"
                    onClick={() => API.deleteRecord(id, (response) => onDelete(id))}
                >
                    delete
                </Button>

            </CardActions>
        }
    </Card>
}

export default withStyles(styles)(MedicalRecordCard);