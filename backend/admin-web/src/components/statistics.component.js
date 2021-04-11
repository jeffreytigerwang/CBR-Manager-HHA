import React, { Component } from "react";

import UserDataService from "../services/user.service"
import VisitDataService from "../services/visit.service"
import StatsDataService from "../services/stats.service"
import { LOADING, LOADING_CHART_OPTIONS } from "../Util/Constants";
import "../styles/lineHeader.css"

import { TextField, Button, withStyles, Grid, Paper, ListItem, Table as statsTable } from "@material-ui/core"
import { styles } from "../css-common"
import CanvasJSReact from './canvasjs.react';
import DataTable from './dataTable.component';

var CanvasJS = CanvasJSReact.CanvasJS;
var CanvasJSChart = CanvasJSReact.CanvasJSChart;

class Statistics extends Component {
    constructor(props) {
        super(props);

        this.state = {
            isLoading: false,
            currentUser: new Array(),
            generalVisitData: new Array(),
            healthVisitData: new Array(),
            allAspectRiskStats: [],
            healthRiskStats: [],
            educationRiskStats: [],
            socialRiskStats: [],
            visitsPerCBRWorker: [],
            allDisabilityCounts: [],
            bidibidiDisabilityCounts: [],
            palorinyaDisabilityCounts: [],
            allReferralByType: [],
            allReferralSum: [],
        };
    }

    componentDidMount() {
        this.getAllGeneralVisitData();
        this.getAllHealthVisitData();
        this.getAllAspectRiskStats();
        this.getNumberOfVisitsPerCBRWorker();
        this.getDisabilityData();
        this.getReferralsData();
    }

    getReferralsData = () => {
        this.setState({isLoading: true});
        StatsDataService.getReferralsStats()
            .then(response => {
                //console.log(response);
                this.setState({
                    isLoading: false,
                    allReferralByType: response.allReferralsByType,
                    allReferralSum: response.allReferralSum,
                });
            })
            .catch(e => {
                console.log(e);
            });
    }


    getDisabilityData = () => {
        this.setState({isLoading: true});
        StatsDataService.getDisabilityStats()
            .then(response => {
                console.log(response);
                this.setState({
                    isLoading: false,
                    allDisabilityCounts: response.allDisabilityCounts,
                    bidibidiDisabilityCounts: response.bidibidiDisabilityCounts,
                    palorinyaDisabilityCounts: response.palorinyaDisabilityCounts,
                });
            })
            .catch(e => {
                console.log(e);
            });
    }


    getAllGeneralVisitData = () => {
        this.setState({isLoading: true});
        VisitDataService.getAllGeneralData()
            .then(response => {
                //console.log(response.data);
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
        this.setState({isLoading: true});
        VisitDataService.getAllHealthData()
            .then(response => {
                //console.log(response.data);
                this.setState({
                    isLoading: false,
                    healthVisitData: response.data
                });
            })
            .catch(e => {
                console.log(e);
            });
    }

    getAllAspectRiskStats = () => {
        this.setState({isLoading: true});
        StatsDataService.getRisks()
            .then(result => {
                //console.log(result);
                this.setState({
                    isLoading: false,
                    allAspectRiskStats: result.allRisk,
                    healthRiskStats: result.healthRisk,
                    educationRiskStats: result.educationRisk,
                    socialRiskStats: result.socialRisk,
                })
            });
    }


    getNumberOfVisitsPerCBRWorker = () => {
        this.setState({isLoading: true});
        StatsDataService.getNumberOfVisitsPerCBRWorker()
            .then(result => {
                console.log(result);
                this.setState({
                    isLoading: false,
                    visitsPerCBRWorker: result
                })
            })
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
                maxWidth: 130
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

        const { visitsPerCBRWorker } = this.state;

        const { allDisabilityCounts } = this.state;
        const { bidibidiDisabilityCounts } = this.state;
        const { palorinyaDisabilityCounts } = this.state;

        const { allReferralsByType } = this.state;
        const { allReferralSum } = this.state;
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
        var numberOfHealthVisits = 0;
        var numberOfEducationVisits = 0;
        var numberOfSocialVisits = 0;
        var numberOfWheelChair = 0;

        generalVisitData.forEach(element => {
            if (element.isCBRChecked) {
                numberOfCBRVisits++;
            } else if (element.isDCRChecked) {
                numberOfDCRVisits++;
            } else {
                numberOfDCRFUVisits++;
            }
            if (element.isHealthChecked) {
                numberOfHealthVisits++;   
            }
            if (element.isEducationChecked) {
                numberOfEducationVisits++;   
            }
            if (element.isSocialChecked) {
                numberOfSocialVisits++;   
            }
        });

        healthVisitData.forEach(element => {
            if (element.isWheelChairChecked) {
                numberOfWheelChair++;
            }
        });

        var counts = [{id: "Number of CBR visits", count: numberOfCBRVisits},
                        {id: "Number of Disability Centre referral visits", count: numberOfDCRVisits},
                        {id: "Number of Disability Centre referral follow up visits", count: numberOfDCRFUVisits},
                        {id: "Number of visits for health", count: numberOfHealthVisits},
                        {id: "Number of visits for education", count: numberOfEducationVisits},
                        {id: "Number of visites for social", count: numberOfSocialVisits},
                        {id: "Number of wheel chairs", count: numberOfWheelChair}]

        const allAspectChartOptions = this.setupRiskChartOptions(allAspectRiskStats, "All Aspect");
        const healthChartOptions = this.setupRiskChartOptions(healthRiskStats, "Health");
        const educationChartOptions = this.setupRiskChartOptions(educationRiskStats, "Education");
        const socialChartOptions = this.setupRiskChartOptions(socialRiskStats, "Social");

        return (
            <div>
                <div>
                    <h1 className="decorated"><span>General Statistics</span></h1>
                    <div>
                        {
                            !generalVisitData.length ? LOADING :
                            <DataTable data={counts} headers={["Visits check list", "#Of times checked"]}></DataTable>
                        }
                    </div>
                    <h1 className="decorated"><span>Stats Per CBR Worker</span></h1>
                    <div>
                        {
                            !visitsPerCBRWorker.length ? LOADING :
                            <DataTable data={visitsPerCBRWorker} headers={["Worker name", "Visits recorded"]}></DataTable>
                        }
                    </div>
                </div>
                <div>
                <div>
                    <h1 className="decorated"><span>Stats Per Zone</span></h1>
                </div>
                <div>
                    <h1 className="decorated"><span>Stats for settlement as a whole</span></h1>
                    <span>
                        <Grid container spacing={2}>
                            <Grid item sm>
                                {
                                    !allDisabilityCounts.length ? LOADING :
                                    <DataTable data={allDisabilityCounts} headers={["Disabilities", "#Of times checked"]}></DataTable>
                                }
                            </Grid>
                            <Grid item sm>
                                {
                                    !bidibidiDisabilityCounts.length ? LOADING :
                                    <DataTable data={bidibidiDisabilityCounts} headers={["Disabilities", "#Of times checked"]}></DataTable>
                                }
                            </Grid>
                            <Grid item sm>
                                {
                                    !palorinyaDisabilityCounts.length ? LOADING :
                                    <DataTable data={palorinyaDisabilityCounts} headers={["Disabilities", "#Of times checked"]}></DataTable>
                                }
                            </Grid>
                        </Grid>
                    </span>
                </div>
                <div>
                    <h1 className="decorated"><span>Risk Level Charts</span></h1>

                </div>
                </div>
                <Grid container spacing={2}>
                    <Grid item sm={12}>
                        <div>
                            {
                                this.state.isLoading ? LOADING : 
                                <CanvasJSChart options = {allAspectChartOptions} 
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
                                <CanvasJSChart options = {educationChartOptions} 
                                    onRef = {ref => this.chart = ref}
                                />
                            }

                        </div>
                    </Grid>
                    <Grid item sm>
                        <div>
                            {
                                this.state.isLoading ? LOADING : 
                                <CanvasJSChart options = {socialChartOptions} 
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

export default withStyles(styles)(Statistics)
