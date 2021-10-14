import axios from 'axios';
import * as converter from 'xml-js';
import soapResponseToJson from './Xml2Json';

const instance = axios.create({
    baseURL: 'http://localhost:8080/',
    timeout: 1000,
    headers: { 'Access-Control-Allow-Origin': '*' }
});

const soapInstance = axios.create({
    baseURL: 'http://localhost:8080/soapWs',
    timeout: 1000,
    headers: {
        'Access-Control-Allow-Origin': '*',
        'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE,OPTIONS',
        'Access-Control-Allow-Headers': 'Content-Type',
        'Content-Type': 'text/xml',
        'soapAction': 'http://localhost:8080/soapWs/activity'
    }
});

class Api {

    getUsers = (type, callback) => {
        instance.get(`user/${type}`)
            .then((response) => callback(response.data));
    }

    getMeds = (callback) => {
        instance.get("med")
            .then((response) => callback(response.data));
    }

    getMonitoredDataByPatient = (patientId, callback) => {
        instance.get(`/monitored-data/all/${patientId}`)
            .then((response) => callback(response));
    }

    addUser = (type, id, firstName, lastName, birthDate, gender, address, email, password, callback) => {
        instance.post(`user/${type}`, {
            id: id,
            firstName: firstName,
            lastName: lastName,
            birthDate: birthDate,
            gender: gender,
            address: address,
            email: email,
            password: password,
        })
            .then((response) => callback(response.data));
    }

    addInterval = (medicationId, medId, startDate, endDate, callback) => {
        instance.post("interval", {
            startDate: startDate,
            endDate: endDate,
            medicationId: medicationId,
            medId: medId
        })
            .then((response) => callback(response.data));
    }

    addMedicalRecord = (patientId, disease, doctor, hospital, callback) => {
        instance.post("record", {
            patientId: patientId,
            disease: disease,
            hospital: hospital,
            doctor: doctor
        })
            .then((response) => callback(response.data));
    }

    updatePatient = (id, firstName, lastName, birthDate, gender, address, email, password, callback) => {
        instance.put(`user/patient/${id}`, {
            id: id,
            firstName: firstName,
            lastName: lastName,
            birthDate: birthDate,
            gender: gender,
            address: address,
            email: email,
            password: password
        })
            .then((response) => callback(response));
    }

    updateMedication = (id, startDate, endDate, callback) => {
        instance.put(`medication/${id}`, {
            id: id,
            startDate: startDate,
            endDate: endDate
        })
            .then((response) => callback(response.data));
    }

    updateInterval = (id, startDate, endDate, callback) => {
        instance.put(`interval/${id}`, {
            id: id,
            startDate: startDate,
            endDate: endDate
        })
            .then((response) => callback(response));
    }

    updatePatientCaregiver = (patientId, caregiverId, callback) => {
        instance.put(`patient/${patientId}`, {
            patientId: patientId,
            caregiverId: caregiverId,
        })
            .then((response) => callback(response.data));
    }

    deleteUser = (type, userId, callback) => {
        instance.delete(`user/${type}/${userId}`)
            .then((response) => callback(response));
    }

    deleteRecord = (id, callback) => {
        instance.delete(`record/${id}`)
            .then((response) => callback(response));
    }

    deleteInterval = (id, callback) => {
        instance.delete(`interval/${id}`, {
            id: id
        })
            .then((response) => callback(response));
    }

    getUserAccount = (type, userId, callback) => {
        instance.get(`${type}/${userId}`)
            .then((response) => callback(response.data));
    }

    login = (email, password, callback) => {
        instance.post("login", {
            email: email,
            password: password
        })
            .then((response) => callback(response.data));
    }

    soapGetActivities = (patientId, callback) => {
        let req = `<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
            <Body>\
                <ActivityRequest xmlns="activity">\
                    <patientId>${patientId}</patientId>\
                </ActivityRequest>\
            </Body>\
        </Envelope>`;
        soapInstance.post("", req)
            .then((response) => {
                let parser = new DOMParser();
                let xml = parser.parseFromString(response.data.replace(/ns2:/g, ""), "text/xml");

                let jsonifiedResponse = {};
                jsonifiedResponse.patientId = xml.getElementsByTagName("patientId");
                jsonifiedResponse.activities = [];

                let children = xml.getElementsByTagName("Activity");
                for (let i = 0; i < children.length; i++) {
                    let child = children[i];
                    let activity = {};
                    activity.activityLabel = child.getElementsByTagName("activity")[0].textContent;
                    activity.activityId = child.getElementsByTagName("activityId")[0].textContent;
                    activity.startTime = child.getElementsByTagName("startTime")[0].textContent;
                    activity.endTime = child.getElementsByTagName("endTime")[0].textContent;
                    activity.isProblem = child.getElementsByTagName("isProblem")[0].textContent;
                    activity.recommendation = child.getElementsByTagName("recommendation")[0].textContent;

                    jsonifiedResponse.activities.push(activity);
                }

                callback(jsonifiedResponse);
            });
    }

    soapInsertRecommendation = (monitoredDataId, recommendation) => {
        let req = `<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">\
            <Body>\
                <RecommendationRequest xmlns="activity">\
                    <monitoredDataId>${monitoredDataId}</monitoredDataId>\
                    <recommendation>${recommendation}</recommendation>\
                </RecommendationRequest>\
            </Body>\
        </Envelope>`;
        soapInstance.post("", req);
    }

    soapGetMedication = (patientId, callback) => {
        let req = `<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">\
            <Body>\
                <MedicationRequest xmlns="medication">\
                    <patientId>${patientId}</patientId>\
                </MedicationRequest>\
            </Body>\
        </Envelope>`;

        soapInstance.post("", req)
            .then((response) => {
                let parser = new DOMParser();
                let xml = parser.parseFromString(response.data.replace(/ns2:/g, ""), "text/xml");

                let jsonifiedResponse = {};
                jsonifiedResponse.patientId = xml.getElementsByTagName("patientId");
                jsonifiedResponse.medication = [];

                let children = xml.getElementsByTagName("Medication");
                for (let i = 0; i < children.length; i++) {
                    let child = children[i];
                    let med = {};
                    med.medId = child.getElementsByTagName("medId")[0].textContent;
                    med.medName = child.getElementsByTagName("medName")[0].textContent;
                    med.taken = child.getElementsByTagName("taken")[0].textContent;

                    if (med.taken === "true") {
                        med.date = child.getElementsByTagName("date")[0].textContent;
                    }
                    
                    jsonifiedResponse.medication.push(med);
                }

                callback(jsonifiedResponse);
            })
    }

}

export default Api;