import React from "react";
import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link
} from "react-router-dom";
import LogIn from "./views/LogInForm";
import SignUp from "./views/RegistrationForm";
import Home from "./views/Home";
import DoctorView from "./views/DoctorView";
import DoctorPatientView from "./views/DoctorPatientView";
import PatientView from "./views/PatientView";
import CaregiverView from "./views/CaregiverView";
import ChartView from "./views/ChartView";


const CustomRoutes = () => {

    const hasPermission = (type) => {
        return type == sessionStorage.getItem("type");
    }

    const error = () => <div>You do not have the rights to access this page.</div>

    return <Router>
        <Switch>
            <Route exact path="/" component={Home} />
            <Route path="/log-in" component={LogIn} />
            <Route path="/sign-up" component={SignUp} />
            <Route exact path="/doctor" render={(props) => hasPermission("doctor") ? <DoctorView {...props.location.state} /> : error()} />
            <Route path="/doctor/patient" render={(props) => hasPermission("doctor") ? <DoctorPatientView {...props.location.state} /> : error()} />
            <Route path="/doctor/caregiver" render={(props) => hasPermission("doctor") ? <CaregiverView {...props.location.state} /> : error()} />
            <Route path="/patient" render={(props) => hasPermission("patient") ? <PatientView {...props.location.state} /> : error()} />
            <Route path="/caregiver" render={(props) => hasPermission("caregiver") ? <CaregiverView {...props.location.state} /> : error()} />
            <Route path="/chart" component={ChartView} />
        </Switch>
    </Router>
}

export default CustomRoutes;