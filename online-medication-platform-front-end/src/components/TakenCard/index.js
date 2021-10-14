import React, { useEffect, useState, Fragment } from 'react';
import { Container, TextField, Button, CssBaseline, Grid, Card, CardHeader, Avatar } from '@material-ui/core';
import Api from '../../Api';

const TakenCard = ({ id, medName, taken, date }) => {

    return <Card style={{ padding: 10, marginBottom: 10 }}>
        <CardHeader
            avatar={
                <Avatar>
                    {medName.charAt(0)}
                </Avatar>
            }
            title={medName}
            subheader={taken === "true" ? `taken on ${date}` : "not taken"}
        />
    </Card >
}

export default TakenCard;