import React, { Component } from "react";

import UserDataService from "../services/user.service"
import ClientDataService from "../services/client.service"
import VisitDataService from "../services/visit.service"

import { TextField, Button, withStyles, Grid, Paper, ListItem } from "@material-ui/core"
import { styles } from "../css-common"

const LOADING = "Loading...";

class Report extends Component {
    constructor(props) {
        super(props);

        this.getAllClients = this.getAllClients.bind(this);
        this.getAllVisitsFromId = this.getAllVisitsFromId.bind(this);
        this.getAllGeneralVisitData = this.getAllGeneralVisitData.bind(this);
        this.getAllHealthVisitData = this.getAllHealthVisitData.bind(this);

        this.state = {
            isLoading: false,
            allClients: new Array(),
            generalVisitData: new Array(),
            healthVisitData: new Array()
        };
    }

    componentDidMount() {
        // this.getUser(this.props.match.params.id);
        this.getAllClients();
        this.getAllGeneralVisitData();
        this.getAllHealthVisitData();
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

    getAllGeneralVisitData() {
        this.setState({isLoading: true})
        VisitDataService.getAllGeneralData()
            .then(response => {
                console.log(response.data);
                this.setState({
                    isLoading: false,
                    generalVisitData: response.data
                });
            })
            .catch(e => {
                console.log(e);
            });
    }

    getAllHealthVisitData() {
        this.setState({isLoading: true})
        VisitDataService.getAllHealthData()
            .then(response => {
                console.log(response.data);
                this.setState({
                    isLoading: false,
                    healthVisitData: response.data
                });
            })
            .catch(e => {
                console.log(e);
            });
    }

    render() {
        const currentUser = this.state.isLoading ? LOADING : this.state.currentUser;
        // const {searchName, allClients, cuurentClient, currentIndex} = this.state;
        const { classes } = this.props;
        const {generalVisitData} = this.state;
        const {healthVisitData} = this.state;

        var numberOfVisits = 0;
        var numberOfWheelChair = 0;

        generalVisitData.forEach(element => {
            numberOfVisits++;
        });

        healthVisitData.forEach(element => {
            if (element.isWheelChairChecked) {
                numberOfWheelChair++;
            }
        });

        return (
            <div>
                <h1>SWAG</h1>
                <div>
                    <ul>
                        <li>Number of visits: {numberOfVisits}</li>
                        <li>Number of wheel chairs: {numberOfWheelChair}</li>
                    </ul>
                </div>
                {/* <Grid item sm={4}>
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
                </Grid> */}
            </div>
        )
    }
}

export default withStyles(styles)(Report)