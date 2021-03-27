// code below is adapted from tutorial found at source:
// https://bezkoder.com/react-material-ui-examples-crud/#Import_Material_UI

import React, { Component } from "react";
import UserDataService from "../services/user.service";

import { styles } from "../css-common"
import { TextField, Button, withStyles, Grid, Paper } from "@material-ui/core";

class User extends Component {
    constructor(props) {
        super(props);

        this.onChangeFirstName = this.onChangeFirstName.bind(this);
        this.onChangeLastName = this.onChangeLastName.bind(this);
        this.onChangePhoneNumber = this.onChangePhoneNumber.bind(this);
        this.onChangePassword = this.onChangePassword.bind(this);
        //this.onChangePriorityLevel = this.onChangePriorityLevel.bind(this);
        this.onChangeZones = this.onChangeZones.bind(this);
        this.onChangeUserType = this.onChangeUserType.bind(this);
        this.getUser = this.getUser.bind(this);
        // TODO going to convert to activate function
        //this.updatePublished = this.updatePublished.bind(this);
        this.updateUser = this.updateUser.bind(this);
        this.deleteUser = this.deleteUser.bind(this);

        this.state = {
          currentUser: {
            id: null,
            firstName: "",
            lastName: "",
            phoneNumber: "",
            password: "",
            zones: "",
            userType: "",
            submitted: false
          },
          message: ""
        };
    }


    componentDidMount() {
        this.getUser(this.props.match.params.id);
    }

    onChangeFirstName(e) {
        const firstName = e.target.value;
        this.setState(function (prevState) {
            return {
                currentUser: {
                    ...prevState.currentUser,
                    firstName: firstName
                }
            };
        });
    }

    onChangeLastName(e) {
        const lastName = e.target.value;
        this.setState(function (prevState) {
            return {
                currentUser: {
                    ...prevState.lastName,
                    lastName: lastName
                }
            };
        });
    }

    onChangeZones(e) {
        const zones = e.target.value;
        this.setState(function (prevState) {
            return {
                currentUser: {
                    ...prevState.zones,
                    zones: zones
                }
           };
        });
    }

    onChangePassword(e) {
        const password = e.target.value;
        this.setState(function (prevState) {
            return {
                currentUser: {
                    ...prevState.password,
                    password: password
                }
            };
        });
    }

    onChangePhoneNumber(e) {
        const phoneNumber = e.target.value;
        this.setState(function (prevState) {
            return {
                currentUser: {
                    ...prevState.phoneNumber,
                    phoneNumber: phoneNumber
                }
            };
        });
    }

    onChangeUserType(e) {
        const userType = e.target.value;
        this.setState(function (prevState) {
            return {
                currentUser: {
                    ...prevState.userType,
                    userType: userType
                }
            };
        });
    }

    getUser(id) {
        UserDataService.get(id)
            .then(response => {
                this.setState({
                    currentUser: response.data
                });
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    }
    updateUser() {
        UserDataService.update(
            this.state.currentUser.id,
            this.state.currentUser
        )
            .then(response => {
                console.log(response.data);
                this.setState({
                    message: "The user was updated successfully!"
                });
            })
            .catch(e => {
                console.log(e);
            });
    }

    deleteUser() {
        UserDataService.delete(this.state.currentUser.id)
            .then(response => {
                console.log(response.data);
                this.props.history.push('/users')
            })
            .catch(e => {
                console.log(e);
            });
    }

// TODO: Remove published and change to activation
    updatePublished(status) {
        var data = {
            id: this.state.currentTutorial.id,
            title: this.state.currentTutorial.title,
            description: this.state.currentTutorial.description,
            published: status
        };

        UserDataService.update(this.state.currentTutorial.id, data)
            .then(response => {
                this.setState(prevState => ({
                    currentTutorial: {
                        ...prevState.currentTutorial,
                        published: status
                    }
                }));
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    }

    render() {
        const { currentUser } = this.state;
        const { classes } = this.props

        return (
            <Paper className={classes.listAddUser} style={{marginTop:"15px"}}>
                {currentUser ? (
                    <div className={classes.form}>
                        <h2>Update User</h2>
                        <form>
                           <div>
                                <TextField
                                    className={classes.textField}
                                    label="First Name"
                                    name="firstName"
                                    value={currentUser.firstName}
                                    onChange={this.onChangeFirstName}
                                    required
                                />
                            </div>
                            <div>
                                <TextField
                                    className={classes.textField}
                                    label="Last Name"
                                    name="lastName"
                                    value={currentUser.lastName}
                                    onChange={this.onChangeLastName}
                                    required
                                />
                            </div>
                            <div>
                                <TextField
                                    className={classes.textField}
                                    label="Phone Number"
                                    name="phoneNumber"
                                    value={currentUser.phoneNumber}
                                    onChange={this.onChangePhoneNumber}
                                    required
                                />
                            </div>
                            <div>
                                <TextField
                                    className={classes.textField}
                                    label="Password"
                                    name="password"
                                    value={currentUser.password}
                                    onChange={this.onChangePassword}
                                    required
                                />
                            </div>
                            <div>
                                <TextField
                                    className={classes.textField}
                                    label="Zones"
                                    name="zones"
                                    value={currentUser.zones}
                                    onChange={this.onChangeZones}
                                    required
                                />
                            </div>
                            <div>
                                <TextField
                                    className={classes.textField}
                                    label="User Type"
                                    name="userType"
                                    value={currentUser.userType}
                                    onChange={this.onChangeUserType}
                                    required
                                />
                            </div>


                            <div className="form-group">
                                <label>
                                    <strong>Status: </strong>
                                </label>
                                Active
                            </div>
                        </form>
                        <div className={classes.buttonWrapper}>
                            {currentUser.published ? (
                                <Button disabled
                                    className={`${classes.publish} ${classes.button}`}
                                    onClick={() => this.updatePublished(false)}
                                >
                                    UnActivate</Button>
                            ) : (
                                    <Button disabled
                                        className={`${classes.publish} ${classes.button}`}
                                        onClick={() => this.updatePublished(true)}
                                    >
                                        Activate</Button>
                                )}
                            <Button
                                className={`${classes.delete} ${classes.button}`}
                                onClick={this.deleteUser}
                            >
                                Delete
                            </Button>

                            <Button
                                type="submit"
                                className={`${classes.update} ${classes.button}`}
                                onClick={this.updateUser}
                            >
                                Update
                            </Button>
                        </div>
                        <p>{this.state.message}</p>
                    </div>
                ) : (
                        <div>
                            <br />
                            <p>Please click on a User...</p>
                        </div>
                    )}
            </Paper>
        );
    }
}

export default withStyles(styles)(User)

