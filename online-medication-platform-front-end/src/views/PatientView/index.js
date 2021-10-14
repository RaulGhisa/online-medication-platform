import React, { useEffect, useState } from 'react';
import { withStyles, Grid } from '@material-ui/core';
import Api from '../../Api';
import UserForm from '../DoctorView/UserForm';
import TabsHolder from '../../components/TabsHolder';
import TabPanel from '../../components/TabPanel';
import MedicationList from '../../components/MedicationList';
import MedicalRecordList from '../../components/MedicalRecordList';
import MedicationForm from '../../components/MedicationForm';
import MedicalRecordForm from '../../components/MedicalRecordForm';
import PatientDetailsForm from '../../components/PatientDetailsForm';
import CaregiverDetailsForm from '../../components/CaregiverDetailsForm';

import _ from 'lodash';

const styles = (theme) => {

}

const API = new Api();

const PatientView = ({ classes, id, type }) => {

    const [value, setValue] = useState(0);
    const [patient, setPatient] = useState({});
    const [caregiver, setCaregiver] = useState({});
    const [medication, setMedication] = useState({});
    const [meds, setMeds] = useState([]);
    const [medicalRecords, setMedicalRecords] = useState([]);

    const tabOptions = ["caregiver info", "medication", "medical record"];

    useEffect(() => API.getUserAccount("patient", id, (response) => {
        setPatient(response.patient);
        setCaregiver(response.caregiver);
        setMedication(response.medication);
        setMeds(response.medication.meds);
        setMedicalRecords(response.medicalRecords);
    }), []);

    const handleTabChange = (event, newValue) => {
        setValue(newValue);
    };

    return <Grid container>
        <Grid item xs={4} sm={6}>
            <PatientDetailsForm patient={patient} readOnly={type == "patient"} />
        </Grid>
        <Grid item xs={4} sm={6} style={{
            overflow: "overlay",
            height: "95vh"
        }}>
            <TabsHolder options={tabOptions} value={value} handleTabChange={handleTabChange} />
            <TabPanel value={value} index={0}>
                <CaregiverDetailsForm patientId={patient.id} caregiver={caregiver} readOnly={type == "patient"} />
            </TabPanel>
            <TabPanel value={value} index={1}>
                <MedicationList medication={medication} meds={meds} readOnly={type == "patient"} />
            </TabPanel>
            <TabPanel value={value} index={2}>
                <MedicalRecordList medicalRecords={medicalRecords} readOnly={type == "patient"} />
            </TabPanel>
        </Grid>
        {type === "doctor" ?
            <Grid item xs={4}>
                <MedicationForm medicationId={medication.id} />
                <MedicalRecordForm patientId={id} />
            </Grid> :
            null
        }
    </Grid >
}

export default withStyles(styles)(PatientView);