import React, { Component } from "react";
import UserDataService from "../services/user.service";

import { TextField, Button, withStyles, Grid, Paper } from "@material-ui/core"
import { styles } from "../css-common"

class AddUser extends Component {
    constructor(props) {
        super(props);
        this.onChangeTitle = this.onChangeTitle.bind(this);
        this.onChangeDescription = this.onChangeDescription.bind(this);
        this.saveTutorial = this.saveTutorial.bind(this);
        this.newTutorial = this.newTutorial.bind(this);

        this.onChangePhoneNumber = this.onChangePhoneNumber.bind(this);
        this.onChangePassword = this.onChangePassword.bind(this);
        this.saveUser = this.saveUser.bind(this);
        this.newUser = this.newUser.bind(this);
        this.onChangeFirstName = this.onChangeFirstName.bind(this);
        this.onChangeLastName = this.onChangeLastName.bind(this);
        this.onChangePriorityLevel = this.onChangePriorityLevel.bind(this);
        this.onChangeZones = this.onChangeZones.bind(this);
        this.onChangeUserType = this.onChangeUserType.bind(this);


        this.state = {
            id: null,
            firstName: "",
            lastName: "",
            phoneNumber: "",
            password: "",
            priorityLevel: "",
            userType: "",
            zones: "",
            submitted: false
        };
    }

    onChangeZones(e) {
        this.setState({
            zones: e.target.value
        });
    }

    onChangeUserType(e) {
        this.setState({
            userType: e.target.value
        });
    }

    onChangePriorityLevel(e) {
        this.setState({
            priorityLevel: e.target.value
        });
    }

    onChangeLastName(e) {
        this.setState({
            lastName: e.target.value
        });
    }

    onChangeFirstName(e) {
        this.setState({
            firstName: e.target.value
        });
    }

    onChangePassword(e) {
        this.setState({
            password: e.target.value
        });
    }

    onChangePhoneNumber(e) {
        this.setState({
            phoneNumber: e.target.value
        });
    }

    onChangeTitle(e) {
        this.setState({
            title: e.target.value
        });
    }

    onChangeDescription(e) {
        this.setState({
            description: e.target.value
        });
    }
    saveUser() {
        var data = {
            firstName: this.state.firstName,
            lastName: this.state.lastName,
            phoneNumber: this.state.phoneNumber,
            password: this.state.password,
            priorityLevel: this.state.priorityLevel,
            zones: this.state.zones,
            userType: this.state.userType
        };

        UserDataService.create(data)
            .then(response => {
                this.setState({
                    firstName: response.data.firstName,
                    lastName: response.data.lastName,
                    phoneNumber: response.data.phoneNumber,
                    password: response.data.password,
                    priorityLevel: response.data.priorityLevel,
                    zones: response.data.zones,
                    id: response.data.id,
                    userType: response.userType,

                    submitted: true
                });
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    }

    newUser() {
        this.setState({
            id: null,
            firstName: "",
            lastName: "",
            phoneNumber: "",
            password: "",
            priorityLevel: "",
            zones: "",
            userType: "",

            submitted: false
        });
    }

    saveTutorial() {
        var data = {
            title: this.state.title,
            description: this.state.description
        };

        UserDataService.create(data)
            .then(response => {
                this.setState({
                    id: response.data.id,
                    title: response.data.title,
                    description: response.data.description,
                    published: response.data.published,

                    submitted: true
                });
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    }

    newTutorial() {
        this.setState({
            id: null,
            title: "",
            description: "",
            published: false,

            submitted: false
        });
    }

    render() {
        const { classes } = this.props

        return (
            <React.Fragment>
            <Paper className={classes.listAddUser} style={{marginTop:"15px"}}>
                {this.state.submitted ? (
                    <div className={classes.form}>
                        <h4>You submitted successfully!</h4>
                        <Button
                            size="small"
                            color="primary"
                            variant="contained"
                            onClick={this.newUser}>
                            Add
                        </Button>
                    </div>
                ) : (
                        <div className={classes.form}>
                            <h2>Add a User</h2>
                            <div className={classes.textField}>
                                <TextField
                                    label="First Name"
                                    name="firstName"
                                    value={this.state.firstName}
                                    onChange={this.onChangeFirstName}
                                    required
                                />
                            </div>

                            <div className={classes.textField}>
                                <TextField
                                    label="Last Name"
                                    name="lastName"
                                    value={this.state.lastName}
                                    onChange={this.onChangeLastName}
                                    required
                                />
                            </div>

                            <div className={classes.textField}>
                                <TextField
                                    label="Phone Number"
                                    name="phoneNumber"
                                    value={this.state.phoneNumber}
                                    onChange={this.onChangePhoneNumber}
                                    required
                                />
                            </div>

                            <div className={classes.textField}>
                                <TextField
                                    label="Password"
                                    name="password"
                                    value={this.state.password}
                                    onChange={this.onChangePassword}
                                    required
                                />
                            </div>

                            <div className={classes.textField}>
                                <TextField
                                    label="User Type"
                                    name="userType"
                                    value={this.state.userType}
                                    onChange={this.onChangeUserType}
                                    required
                                />
                            </div>

                            <div className={classes.textField}>
                                <TextField
                                    label="Zones"
                                    name="zones"
                                    value={this.state.zones}
                                    onChange={this.onChangeZones}
                                    required
                                />
                            </div>





                            <Button
                                size="small"
                                color="primary"
                                variant="contained"
                                className={classes.searchBar}
                                onClick={this.saveUser}>
                                Submit
                            </Button>
                        </div>
                    )}
            </Paper>
            </React.Fragment>
        );
    }
}

export default withStyles(styles)(AddUser)

