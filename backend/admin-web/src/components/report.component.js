import React, { Component } from "react";

import UserDataService from "../services/user.service"
import VisitDataService from "../services/visit.service"
import StatsDataService from "../services/stats.service"

import { TextField, Button, withStyles, Grid, Paper, ListItem } from "@material-ui/core"
import { styles } from "../css-common"

import CanvasJSReact from './canvasjs.react';
var CanvasJS = CanvasJSReact.CanvasJS;
var CanvasJSChart = CanvasJSReact.CanvasJSChart;

const LOADING = "Loading...";

class Report extends Component {
    constructor(props) {
        super(props);

        this.getAllGeneralVisitData = this.getAllGeneralVisitData.bind(this);
        this.getAllHealthVisitData = this.getAllHealthVisitData.bind(this);
        this.getHealthRiskStats = this.getHealthRiskStats.bind(this);

        this.state = {
            isLoading: false,
            currentUser: new Array(),
            generalVisitData: new Array(),
            healthVisitData: new Array(),
            healthRiskStats: []
        };
    }

    componentDidMount() {
        // this.getUser(this.props.match.params.id);
        this.getAllGeneralVisitData();
        this.getAllHealthVisitData();
        this.getHealthRiskStats();
    }

    getAllGeneralVisitData = () => {
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
    
    getAllHealthVisitData = () => {
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
    
    getHealthRiskStats = () => {
        this.setState({isLoading: true})
        StatsDataService.getRisks()
            .then(result => {
                console.log(result.data);
                this.setState({
                    isLoading: false,
                    healthRiskStats: result
                })
            });
    }

    render() {
        const currentUser = this.state.isLoading ? LOADING : this.state.currentUser;
        const { classes } = this.props;
        const {generalVisitData} = this.state;
        const {healthVisitData} = this.state;
        const {healthRiskStats} = this.state;
        // Bug: when trying to access arrays or any data that
        // requires API calls here, you get TypeError.
        // React Lesson: You need to add condition because 
        // on initial render the healthRiskStats is empty array 
        // and doesn’t have any objects in it. E.g.,
        // healthRiskStats.length ? console.log(healthRiskStats[0].percentage) : console.log(LOADING);

        var numberOfVisits = generalVisitData.length;
        var numberOfCBRVisits = 0;
        var numberOfDCRVisits = 0;
        var numberOfDCRFUVisits = 0;
        var numberOfWheelChair = 0;

        generalVisitData.forEach(element => {
            if (element.isCBRChecked) {
                numberOfCBRVisits++;
            } else if (element.isDCRChecked) {
                numberOfDCRVisits++;
            } else {
                numberOfDCRFUVisits++;
            }
        });

        healthVisitData.forEach(element => {
            if (element.isWheelChairChecked) {
                numberOfWheelChair++;
            }
        });

        const dummyOptions = {
            animationEnabled: true,
			title: {
				text: "Dummy Graph"
			},
			subtitles: [{
				text: "Actual Graph has not loaded yet",
				verticalAlign: "center",
				fontSize: 24,
				dockInsidePlotArea: true
			}],
			data: [{
				type: "doughnut",
				showInLegend: true,
				indexLabel: "{name}: {y}",
				yValueFormatString: "#,###'%'",
				dataPoints: [
					{ name: "Unsatisfied", y: 5 },
					{ name: "Very Unsatisfied", y: 31 },
					{ name: "Very Satisfied", y: 40 },
					{ name: "Satisfied", y: 17 },
					{ name: "Neutral", y: 7 }
				]
			}]
        }

        const options = healthRiskStats.length ? {
            interactivityEnabled: false,
			animationEnabled: true,
            animationDuration: 1000,
            theme: "light1",
			title: {
				text: "Health Risk Levels"
			},
			subtitles: [{
				text: "71% Positive",
				verticalAlign: "center",
				fontSize: 24,
				dockInsidePlotArea: true
			}],
			data: [{
				type: "doughnut",
				showInLegend: true,
				indexLabel: "{name}: {y}",
				yValueFormatString: "###.##%",
				dataPoints: [
					{ name: "Critical Risk", y: healthRiskStats[0].percentage },
					{ name: "High Risk", y: healthRiskStats[1].percentage },
					{ name: "Medium Risk", y: healthRiskStats[2].percentage },
					{ name: "Low Risk", y: healthRiskStats[3].percentage },
				]
			}]
		} : dummyOptions;

        return (
            <div>
                <h1>SWAG</h1>
                <div>
                    <ul>
                        <li>Number of visits: {numberOfVisits}</li>
                        <li>Number of CBR visits: {numberOfCBRVisits}</li>
                        <li>Number of Disability Centre referral visits: {numberOfDCRVisits}</li>
                        <li>Number of Disability Centre referral follow up visits: {numberOfDCRFUVisits}</li>
                        <li>Number of wheel chairs: {numberOfWheelChair}</li>
                    </ul>
                </div>
                <div>
                    {
                        this.state.isLoading ? LOADING :
                        <CanvasJSChart options = {options} 
                            onRef = {ref => this.chart = ref}
                        />
                    }
                    
                </div>
            </div>
        )
    }
}

export default withStyles(styles)(Report)