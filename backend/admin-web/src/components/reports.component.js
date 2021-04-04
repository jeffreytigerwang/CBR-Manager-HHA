// code below is adapted from tutorial found at source:
// https://bezkoder.com/react-material-ui-examples-crud/#Import_Material_UI

import React, { Component } from "react";
import UserDataService from "../services/user.service";
import statsDataService from "../services/stats.service";

import { styles } from "../css-common"
import { TextField, Button, withStyles, Grid, Paper } from "@material-ui/core";

class Reports extends Component {
    constructor(props) {
        super(props);

        //this.onChangeFirstName = this.onChangeFirstName.bind(this);
        //this.onChangeLastName = this.onChangeLastName.bind(this);
        this.getStatistics = this.getStatistics.bind(this);

        this.state = {
         stats: ""
        };
    }


    componentDidMount() {
        //this.getUser(this.props.match.params.id);
        this.getStatistics();
    }

    getStatistics() {
      statsDataService.getRisks();
    }
/*
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
*/
    render() {
        const { stats } = this.state;
        const { classes } = this.props

        return (<div></div>);
    }
}

export default withStyles(styles)(Reports);

