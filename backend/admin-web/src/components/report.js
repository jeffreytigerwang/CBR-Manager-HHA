import React, { Component } from "react";

import UserDataService from "../services/user.service";

import { TextField, Button, withStyles } from "@material-ui/core"
import { styles } from "../css-common"

class Report extends Component {
    constructor(props) {
        super(props);

        this.getUser = this.getUser.bind(this);

        this.state = {
            isLoading: false,
            currentUser: null
        };
    }

    componentDidMount() {
        this.getUser(this.props.match.params.id);
    }

    getUser(id) {
        this.setState({isLoading: true})
        UserDataService.get(id)
            .then(response => {
                this.setState({
                    isLoading: false,
                    currentUser: response.data
                });
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    }

    render() {
        const currentUser = this.state.isLoading ? "Loading..." : this.state.currentUser
        const { classes } = this.props

        return (
            <div>
                <h1>SWAG {currentUser}</h1>
            </div>
        )
    }
}

export default withStyles(styles)(Report)