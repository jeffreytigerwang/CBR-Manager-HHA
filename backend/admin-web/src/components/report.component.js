import React, { Component } from "react";

import UserDataService from "../services/user.service";

import { TextField, Button, withStyles } from "@material-ui/core"
import { styles } from "../css-common"

class Report extends Component {
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

    render() {
        const { currentUser } = this.state;
        const { classes } = this.props

        return (
            <div>
                
            </div>
        )
    }
}

export default withStyles(styles)(AddUser)