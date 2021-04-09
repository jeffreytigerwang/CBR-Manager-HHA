import React, { Component } from "react";

import UserDataService from "../services/user.service"
import VisitDataService from "../services/visit.service"
import StatsDataService from "../services/stats.service"
import { LOADING, LOADING_CHART_OPTIONS } from "../Util/Constants";
import "../styles/lineHeader.css"

import { TextField, Button, withStyles, Grid, Paper, ListItem } from "@material-ui/core"
import { styles } from "../css-common"
import CanvasJSReact from './canvasjs.react';

var CanvasJS = CanvasJSReact.CanvasJS;
var CanvasJSChart = CanvasJSReact.CanvasJSChart;

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
    
    /**
     * @param {[]} riskStats Array of properties
     * @param {String} aspect Health, education, or social
     * @returns option property for CanvasJS or LOADING_CHART_OPTIONS
     */
    setupRiskChartOptions = (riskStats, aspect) => {
        return riskStats.length ? {
            interactivityEnabled: false,
            animationEnabled: true,
            animationDuration: 1000,
            theme: "light1",
            title: {
                text: aspect + " Risk Levels Of All Clients",
                wrap: true
            },
            subtitles: [{
                text: "" + (riskStats[0].percentage + riskStats[1].percentage)*100 + "% High " + (riskStats[2].percentage + riskStats[3].percentage)*100 + "% Low",
                verticalAlign: "center",
                fontSize: 24,
                dockInsidePlotArea: true,
                maxWidth: 166
            }, {
                //  TODO: get the very first and last date of visit info for this chart.
                // text: "${initialDate} ${lastDate}",
                fontSize: 18,
                fontWeight: "normal"
            }],
            data: [{
                type: "doughnut",
                indexLabel: "{name}: {y}",
                yValueFormatString: "###.##%",
                dataPoints: [
                    { name: "Critical Risk", y: riskStats[0].percentage, color: "red" },
                    { name: "High Risk", y: riskStats[1].percentage, color: "orange" },
                    { name: "Medium Risk", y: riskStats[2].percentage, color: "yellow" },
                    { name: "Low Risk", y: riskStats[3].percentage, color: "yellowgreen" },
                ]
            }]
        } : LOADING_CHART_OPTIONS;
    }
    
    render() {
        const currentUser = this.state.isLoading ? LOADING : this.state.currentUser;
        const { classes } = this.props;
        const { generalVisitData } = this.state;
        const { healthVisitData } = this.state;
        const { allAspectRiskStats } = this.state;
        const { healthRiskStats } = this.state;
        const { educationRiskStats } = this.state;
        const { socialRiskStats } = this.state;
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


        // const allAspectChartOptions = this.setupRiskChartOptions(allAspectRiskStats, "All Aspect");
        const healthChartOptions = this.setupRiskChartOptions(healthRiskStats, "Health");
        // const educationChartOptions = this.setupRiskChartOptions(educationRiskStats, "Education");
        // const socialChartOptions = this.setupRiskChartOptions(socialRiskStats, "Social");

        return (
            <div>
                <h1 class="decorated"><span>SWAG</span></h1>
                <div>
                    <ul>
                        <li>Number of visits: {numberOfVisits}</li>
                        <li>Number of CBR visits: {numberOfCBRVisits}</li>
                        <li>Number of Disability Centre referral visits: {numberOfDCRVisits}</li>
                        <li>Number of Disability Centre referral follow up visits: {numberOfDCRFUVisits}</li>
                        <li>Number of wheel chairs: {numberOfWheelChair}</li>
                    </ul>
                </div>
                <Grid container spacing={2}>
                    <Grid item sm={12}>
                        <div>
                            {
                                this.state.isLoading ? LOADING :
                                <CanvasJSChart options = {LOADING_CHART_OPTIONS} 
                                    onRef = {ref => this.chart = ref}
                                />
                            }
                            
                        </div>
                    </Grid>
                    <Grid item sm>
                        <div>
                            {
                                this.state.isLoading ? LOADING :
                                <CanvasJSChart options = {healthChartOptions} 
                                    onRef = {ref => this.chart = ref}
                                />
                            }
                            
                        </div>
                    </Grid>
                    <Grid item sm>
                        <div>
                            {
                                this.state.isLoading ? LOADING :
                                <CanvasJSChart options = {LOADING_CHART_OPTIONS} 
                                    onRef = {ref => this.chart = ref}
                                />
                            }
                            
                        </div>
                    </Grid>
                    <Grid item sm>
                        <div>
                            {
                                this.state.isLoading ? LOADING :
                                <CanvasJSChart options = {LOADING_CHART_OPTIONS} 
                                    onRef = {ref => this.chart = ref}
                                />
                            }
                            
                        </div>
                    </Grid>
                </Grid>
            </div>
        )
    }

}

export default withStyles(styles)(Report)