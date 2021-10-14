import React, { useEffect, useState, Fragment } from 'react';

import Chart from '../../components/Chart';
import Api from '../../Api';
import { Grid } from '@material-ui/core';

const API = new Api();

const ChartView = () => {

    const [data, setData] = useState([]);

    useEffect(() => {
        API.soapGetActivities(1970927, (response) => {
            console.log(response);

            const sortedByDate = {}

            response.activities.map((element) => {
                const start = element.startTime;
                const end = element.endTime;

                const startDate = new Date(start);
                const endDate = new Date(end);

                const dd = String(startDate.getDate()).padStart(2, '0');
                const mm = String(startDate.getMonth() + 1).padStart(2, '0'); //January is 0!
                const yyyy = startDate.getFullYear();

                if (sortedByDate.hasOwnProperty(dd + '/' + mm + '/' + yyyy)) {
                    sortedByDate[dd + '/' + mm + '/' + yyyy].push(element);
                } else {
                    sortedByDate[dd + '/' + mm + '/' + yyyy] = response.activities.filter((item) => item.startTime === start);
                }

                const minutes = Math.floor((endDate.getTime() - startDate.getTime()) / 1000 / 60);
                const currentActivity = sortedByDate[dd + '/' + mm + '/' + yyyy].filter((item) => item.activityLabel === element.activityLabel)[0];

                if (!currentActivity.hasOwnProperty("minutes")) {
                    currentActivity.minutes = 0;
                }
                currentActivity.minutes += minutes;
                const sum = {
                    "Sleeping": 0,
                    "Toileting": 0,
                    "Showering": 0,
                    "Breakfast": 0,
                    "Grooming": 0,
                    "Spare_Time/TV": 0,
                    "Leaving": 0,
                    "Lunch": 0,
                    "Grooming": 0,
                    "Snack": 0,
                }
            });

            console.log(sortedByDate);
            let labels = [];

            Object.keys(sortedByDate).map((item) => {
                let activities = sortedByDate[item];
                console.log("act", activities);
                let val = {};
                val.date = item;
                val.values = [].concat(activities.map((item2) => {
                    let temp = {};
                    temp.label = item2.activityLabel;
                    temp.y = item2.minutes;

                    return temp;
                }));
                labels.push(val);
            });

            console.log("labels", labels);
            setData(labels);
        });


    }, []);

    return <Grid container direction="column">
        {data.length > 0 && data.map((element, index) =>
            <Grid item key={index}>
                <Chart key={index} id={index} data={element} />
            </Grid>
        )}
    </Grid>
}

export default ChartView;