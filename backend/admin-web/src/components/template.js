// code below is adapted from tutorial found at source:
// https://bezkoder.com/react-material-ui-examples-crud/#Import_Material_UI

import React, { Component } from "react";
import UserDataService from "../services/user.service";

import { styles } from "../css-common"
import { TextField, Button, withStyles, Grid, Paper } from "@material-ui/core";

class Reports extends Component {
    constructor(props) {
        super(props);


        this.state = {
         stats: ""
        };
    }


    componentDidMount() {
    }

   render() {
        const { currentUser } = this.state;
        const { classes } = this.props

        return (<div></div>);
    }
}

export default withStyles(styles)(Reports);

