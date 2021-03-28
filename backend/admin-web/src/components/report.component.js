import React, { Component } from "react";

import UserDataService from "../services/user.service"
import ClientDataService from "../services/client.service"
import VisitDataService from "../services/visit.service"
import statsDataService from "../services/stats.service"

import { TextField, Button, withStyles, Grid, Paper, ListItem } from "@material-ui/core"
import { styles } from "../css-common"

const LOADING = "Loading...";

class Report extends Component {
    constructor(props) {
        super(props);

        this.getAllClients = this.getAllClients.bind(this);
        this.getAllVisitsFromId = this.getAllVisitsFromId.bind(this);
        this.getAllVisits = this.getAllVisits.bind(this);
        this.getAllStats = this.getAllStats.bind(this);

        this.state = {
            isLoading: false,
            allClients: new Array(),
            visits: new Array(),
            stats: new Array()
        };
    }

    componentDidMount() {
        // this.getUser(this.props.match.params.id);
        this.getAllClients();
        this.getAllVisits();
        this.getAllStats(state.stats);
    }

    getAllStats(json) {
        statsDataService.test(json)
        .then(response => {
            console.log(response);
            this.setState({
                stats: response.data
            });
        })
        .catch(e => {
            console.log(e);
        });
    }

    getAllClients() {
        this.setState({isLoading: true})
        ClientDataService.getAll()
            .then(response => {
                console.log(response.data);
                this.setState({
                    isLoading: false,
                    allClients: response.data
                });
            })
            .catch(e => {
                console.log(e);
            });
    }

    getAllVisitsFromId(id) {
        this.setState({isLoading: true})
        ClientDataService.getAllVisitsFromId(id)
            .then(response => {
                console.log(response.data);
                this.setState({
                    isLoading: false,
                });
            })
            .catch(e => {
                console.log(e);
            });
    }

    getAllVisits(id) {
        this.setState({isLoading: true});
        VisitDataService.getAllVisitsFromId(id)
            .then(response => {
                console.log(response.data);
                this.setState({
                    isLoading: false,
                    visits: response
                });
            })
            .catch(e => {
                console.log(e);
            });
    }

    render() {
        const currentUser = this.state.isLoading ? LOADING : this.state.currentUser
        // const allClients = this.state.isLoading ? LOADING : this.state.allClients
        const {searchName, allClients, cuurentClient, currentIndex} = this.state;
        const { classes } = this.props;
        const {visits} = this.state;

        //var sumOfVisits = visits.group({ id: id, count: { $sum: 1 } }).exec()


        return (
            <div>
                <h1>SWAG sumOfVisits</h1>
                <Grid item sm={4}>
                    <Paper Grid container direction="column" justify="center"
                    alignItems="center">
                    <Grid item><h2>Users List</h2>
                    <div className="listGroup">
                        {allClients &&
                        allClients.map((user, index) => (
                            <ListItem
                            divider
                            button
                            key={index}>
                            {user.firstName + " " + user.lastName}
                            </ListItem>
                        ))}
                    </div>
                    </Grid>
                    </Paper>
                </Grid>
            </div>
        )
    }
}

export default withStyles(styles)(Report)
