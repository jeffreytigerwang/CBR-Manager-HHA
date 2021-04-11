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

        this.getStatistics = this.getStatistics.bind(this);

        this.state = {
         stats: ""
        };
    }


    componentDidMount() {
        this.getStatistics();
    }

    getStatistics() {
      statsDataService.getRisks();
    }
    render() {
        const { stats } = this.state;
        const { classes } = this.props

        return (<div></div>);
    }
}

export default withStyles(styles)(Reports);

