import React, { useEffect, useState } from 'react';
import { withStyles, Grid, Typography } from '@material-ui/core';
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
import ActivityList from '../../components/ActivityList';
import TakenList from '../../components/TakenList';

const styles = (theme) => {

}

const API = new Api();

const DoctorPatientView = ({ classes, id }) => {

    const [value, setValue] = useState(3);
    const [patient, setPatient] = useState({});
    const [caregiver, setCaregiver] = useState({});
    const [medication, setMedication] = useState({});
    const [meds, setMeds] = useState([]);
    const [medicalRecords, setMedicalRecords] = useState([]);
    const [activities, setActivities] = useState("");
    const [takenMedication, setTakenMedication] = useState([]);

    const tabOptions = ["caregiver", "medication", "record", "activity"];

    useEffect(() => API.getUserAccount("patient", id, (response) => {
        setPatient(response.patient);
        setCaregiver(response.caregiver);
        setMedication(response.medication);
        setMeds(response.medication.meds);
        setMedicalRecords(response.medicalRecords);

        API.soapGetActivities(id, (response) => setActivities(response.activities));

        API.soapGetMedication(id, (response) => setTakenMedication(response.medication));
    }), []);

    const handleTabChange = (event, newValue) => {
        setValue(newValue);
    };

    const handleRecordDelete = (id) => {
        setMedicalRecords(_.remove(medicalRecords, (item) => item.id != id));
    }

    const handleMedicationDelete = (id) => {
        let updatedMeds = _.remove(meds, (item) => item.intakeInterval.id != id);
        setMeds(updatedMeds);
    }

    const handleMedicationInsert = (data) => {
        let med = {
            id: data.medicationId,
            dosage: data.dosage,
            sideEffect: data.sideEffect,
            name: data.medName,
            intakeInterval: {
                id: data.id,
                startDate: data.startDate,
                endDate: data.endDate
            }
        }
        setMeds(meds.concat([med]));
    }

    const handleRecordInsert = (data) => {
        setMedicalRecords(medicalRecords.concat([data]));
    }

    return <Grid container>
        <Grid item xs={4} style={{ padding: 10 }}>
            <PatientDetailsForm patient={patient} />
        </Grid>
        <Grid item xs={4} style={{ padding: 10 }} style={{
            overflow: "overlay",
            height: "95vh"
        }}>
            <TabsHolder options={tabOptions} value={value} handleTabChange={handleTabChange} />
            <TabPanel value={value} index={0}>
                <CaregiverDetailsForm patientId={patient.id} caregiver={caregiver} />
            </TabPanel>
            <TabPanel value={value} index={1}>
                <MedicationList medication={medication} meds={meds} onDelete={handleMedicationDelete} />
                Current status
                <TakenList medication={takenMedication} />
            </TabPanel>
            <TabPanel value={value} index={2}>
                <MedicalRecordList medicalRecords={medicalRecords} onDelete={handleRecordDelete} />
            </TabPanel>
            <TabPanel value={value} index={3}>
                <ActivityList activities={activities} readOnly={false} />
            </TabPanel>
        </Grid>
        <Grid item xs={4} style={{ padding: 10 }}>
            <Typography variant="h5" align="center" style={{ marginBottom: 10 }}>prescribe a med</Typography>
            <MedicationForm medicationId={medication.id} onInsert={handleMedicationInsert} />
            <Typography variant="h5" align="center" style={{ margin: 10 }}>add record</Typography>
            <MedicalRecordForm patientId={id} onInsert={handleRecordInsert} />
        </Grid>
    </Grid >
}

export default withStyles(styles)(DoctorPatientView);