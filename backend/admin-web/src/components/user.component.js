// https://bezkoder.com/react-material-ui-examples-crud/#Import_Material_UI

import React, { Component } from "react";
import UserDataService from "../services/user.service";

import { styles } from "../css-common"
import { TextField, Button, withStyles } from "@material-ui/core";

class User extends Component {
    constructor(props) {
        super(props);
        this.onChangeTitle = this.onChangeTitle.bind(this);
        this.onChangeDescription = this.onChangeDescription.bind(this);
        this.getTutorial = this.getTutorial.bind(this);
        this.updatePublished = this.updatePublished.bind(this);
        this.updateTutorial = this.updateTutorial.bind(this);
        this.deleteTutorial = this.deleteTutorial.bind(this);

        this.onChangeFirstName = this.onChangeFirstName.bind(this);
        this.onChangeLastName = this.onChangeLastName.bind(this);
        this.onChangePhone = this.onChangePhone.bind(this);
        this.onChangePassword = this.onChangePassword.bind(this);
        this.onChangePriorityLevel = this.onChangePriorityLevel.bind(this);
        this.onChangeZone = this.onChangeZone.bind(this);
        this.getUser = this.getUser.bind(this);
        //this.updatePublished = this.updatePublished.bind(this);
        this.updateUser = this.updateUser.bind(this);
        this.deleteUser = this.deleteUser.bind(this);

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


    componentDidMount() {
        this.getUser(this.props.match.params.id);
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
/*
    updatePublished(status) {
        var data = {
            id: this.state.currentTutorial.id,
            title: this.state.currentTutorial.title,
            description: this.state.currentTutorial.description,
            published: status
        };

        TutorialDataService.update(this.state.currentTutorial.id, data)
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
*/

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


    onChangeTitle(e) {
        const title = e.target.value;

        this.setState(function (prevState) {
            return {
                currentTutorial: {
                    ...prevState.currentTutorial,
                    title: title
                }
            };
        });
    }

    onChangeDescription(e) {
        const description = e.target.value;

        this.setState(prevState => ({
            currentTutorial: {
                ...prevState.currentTutorial,
                description: description
            }
        }));
    }

    getTutorial(id) {
        UserDataService.get(id)
            .then(response => {
                this.setState({
                    currentTutorial: response.data
                });
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    }

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

    updateTutorial() {
        UserDataService.update(
            this.state.currentTutorial.id,
            this.state.currentTutorial
        )
            .then(response => {
                console.log(response.data);
                this.setState({
                    message: "The tutorial was updated successfully!"
                });
            })
            .catch(e => {
                console.log(e);
            });
    }

    deleteTutorial() {
        UserDataService.delete(this.state.currentTutorial.id)
            .then(response => {
                console.log(response.data);
                this.props.history.push('/tutorials')
            })
            .catch(e => {
                console.log(e);
            });
    }

    render() {
        const { currentUser } = this.state;
        const { classes } = this.props

        return (
            <div>
                {currentUser ? (
                    <div className={classes.form}>
                        <h2>User</h2>
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
                                    name="phone"
                                    value={currentUser.phone}
                                    onChange={this.onChangePhone}
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
                                    label="Priority Level"
                                    name="priorityLevel"
                                    value={currentUser.priorityLevel}
                                    onChange={this.onChangePriorityLevel}
                                    required
                                />
                            </div>
                            <div>
                                <TextField
                                    className={classes.textField}
                                    label="Zone"
                                    name="zone"
                                    value={currentUser.zone}
                                    onChange={this.onChangeZone}
                                    required
                                />
                            </div>

                            <div className="form-group">
                                <label>
                                    <strong>Status: </strong>
                                </label>
                                {currentUser.published ? "Published" : "Pending"}
                            </div>
                        </form>
                        <div className={classes.buttonWrapper}>
                            {currentUser.published ? (
                                <Button
                                    className={`${classes.publish} ${classes.button}`}
                                    onClick={() => this.updatePublished(false)}
                                >
                                    UnPublish
              </Button>
                            ) : (
                                    <Button
                                        className={`${classes.publish} ${classes.button}`}
                                        onClick={() => this.updatePublished(true)}
                                    >
                                        Publish
              </Button>
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
            </div>
        );
    }
}

export default withStyles(styles)(User)

