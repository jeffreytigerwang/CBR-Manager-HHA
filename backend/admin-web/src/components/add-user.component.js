import React, { Component } from "react";
import UserDataService from "../services/user.service";

import { TextField, Button, withStyles } from "@material-ui/core"
import { styles } from "../css-common"

class AddUser extends Component {
    constructor(props) {
        super(props);
        this.onChangeTitle = this.onChangeTitle.bind(this);
        this.onChangeDescription = this.onChangeDescription.bind(this);
        this.saveTutorial = this.saveTutorial.bind(this);
        this.newTutorial = this.newTutorial.bind(this);

        this.onChangePhone = this.onChangePhone.bind(this);
        this.onChangePassword = this.onChangePassword.bind(this);
        this.saveUser = this.saveUser.bind(this);
        this.newUser = this.newUser.bind(this);
        this.onChangeFirstName = this.onChangeFirstName.bind(this);
        this.onChangeLastName = this.onChangeLastName.bind(this);
        this.onChangePriorityLevel = this.onChangePriorityLevel.bind(this);
        this.onChangeZone = this.onChangeZone.bind(this);


        this.state = {
            id: null,
            firstName: "",
            lastName: "",
            phone: "",
            password: "",
            priorityLevel: "",
            zone: "",
            submitted: false
        };
    }

    onChangeZone(e) {
        this.setState({
            zone: e.target.value
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

    onChangePhone(e) {
        this.setState({
            phone: e.target.value
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
            phone: this.state.phone,
            password: this.state.password,
            priorityLevel: this.state.priorityLevel,
            zone: this.state.zone
        };

        UserDataService.create(data)
            .then(response => {
                this.setState({
                    firstName: response.data.firstName,
                    lastName: response.data.lastName,
                    phone: response.data.phone,
                    password: response.data.password,
                    priorityLevel: response.data.priorityLevel,
                    zone: response.data.zone,
                    id: response.data.id,

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
            phone: "",
            password: "",
            priorityLevel: "",
            zone: "",

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
                                    name="phone"
                                    value={this.state.phone}
                                    onChange={this.onChangePhone}
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
                                    label="Priority Level"
                                    name="priorityLevel"
                                    value={this.state.priorityLevel}
                                    onChange={this.onChangePriorityLevel}
                                    required
                                />
                            </div>

                            <div className={classes.textField}>
                                <TextField
                                    label="Zone"
                                    name="zone"
                                    value={this.state.zone}
                                    onChange={this.onChangeZone}
                                    required
                                />
                            </div>





                            <Button
                                size="small"
                                color="primary"
                                variant="contained"
                                onClick={this.saveUser}>
                                Submit
                            </Button>
                        </div>
                    )}
            </React.Fragment>
        );
    }
}

export default withStyles(styles)(AddUser)

