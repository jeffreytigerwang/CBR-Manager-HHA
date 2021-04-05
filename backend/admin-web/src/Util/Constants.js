
/**
 * Loading placeholder string as a placeholder when 
 * API calls are slow
 */
export const LOADING = "Loading...";

/**
 * Loading placeholder chart as a placeholder when 
 * API calls are slow
 */
export const LOADING_CHART_OPTIONS = {
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
};