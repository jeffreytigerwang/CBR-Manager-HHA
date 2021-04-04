// code below is adapted from tutorial found at source:
// https://bezkoder.com/react-material-ui-examples-crud/#Import_Material_UI

import React, { Component } from "react";
import UserDataService from "../services/user.service";

import { styles } from "../css-common"
import { TextField, Button, withStyles, Grid, Paper } from "@material-ui/core";

class Reports extends Component {
    constructor(props) {
        super(props);

        //this.onChangeFirstName = this.onChangeFirstName.bind(this);

        this.state = {
         stats: ""
        };
    }


    componentDidMount() {
        //this.getUser(this.props.match.params.id);
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
*/

   render() {
        const { currentUser } = this.state;
        const { classes } = this.props

        return (<div></div>);
    }
}

export default withStyles(styles)(Reports);

