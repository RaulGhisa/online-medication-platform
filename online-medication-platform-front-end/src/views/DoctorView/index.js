import React, { useEffect, useState, Fragment } from 'react';
import { withStyles, Grid, Divider } from '@material-ui/core';

import CustomCard from '../../components/CustomCard';

import Api from '../../Api';
import TabsHolder from '../../components/TabsHolder';
import PatientsPanel from './PatientsPanel';
import TabPanel from '../../components/TabPanel';
import CaregiversPanel from './CaregiversPanel';
import MedicalRecordsView from './MedicalRecordsList';
import MedsView from './MedsList';
import UserForm from './UserForm';
import Selector from '../../components/Selector';
import MedForm from './MedForm';

import _ from 'lodash';

const styles = (theme) => ({
    tab: {
        padding: 15,
        overflow: "overlay",
        height: "95vh"
    },
    meds: {
        padding: 15,
    },
    form: {
        padding: 10,
        overflow: "overlay",
        height: "95vh"
    },
    divider: {
        marginBottom: 15,
    }
});

const API = new Api();

const DoctorView = ({ classes }) => {

    const [value, setValue] = useState(0);
    const [selectValue, setSelectValue] = useState(0);
    const [id, setId] = useState(0);
    const [patients, setPatients] = useState([]);
    const [caregivers, setCaregivers] = useState([]);

    const options = ["user", "med"];
    const tabOptions = ["patients", "caregivers"];

    useEffect(() => {
        API.getUsers("patient", (response) => setPatients(response));
        API.getUsers("caregiver", (response) => setCaregivers(response));
    }, []);

    const handleTabChange = (event, newValue) => {
        setValue(newValue);
    };

    const handleUserInsert = (data) => {
        if (data != null) {
            if (data.type === "patient") {
                setPatients(patients.concat([data]));
            } else {
                setCaregivers(caregivers.concat([data]));

            }
        } else {
            alert("The id number or email already exists.")
        }
    }

    const handlePatientDelete = (id) => {
        setPatients(_.remove(patients, (item) => item.id != id));
    }

    const handleCaregiverDelete = (id) => {
        setCaregivers(_.remove(caregivers, (item) => item.id != id));
    }

    return <Grid container justify="space-evenly">
        <Grid item xs={6} className={classes.tab}>
            <TabsHolder options={tabOptions} value={value} handleTabChange={handleTabChange} />
            <TabPanel value={value} index={0}>
                <PatientsPanel setId={setId} patients={patients} onDelete={handlePatientDelete} />
            </TabPanel>
            <TabPanel value={value} index={1}>
                <CaregiversPanel setId={setId} caregivers={caregivers} onDelete={handleCaregiverDelete} />
            </TabPanel>
        </Grid>
        <Grid item xs={6} className={classes.form}>
            <Selector options={options} value={selectValue} setValue={setSelectValue} />
            <TabPanel value={selectValue} index={0}>
                <UserForm label="add patients or caregivers" icon={true} autoComplete={false} onInsert={handleUserInsert} />
            </TabPanel>
            <TabPanel value={selectValue} index={1}>
                <MedForm autoComplete={false} />
            </TabPanel>
        </Grid>
    </Grid>
}

export default withStyles(styles)(DoctorView);
