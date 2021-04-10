import http from "../http-common";
const jsonAggregate = require('json-aggregate')

class statsDataService {
  async test() {
      const visits = await http.get(`/visits`)
      .catch(err => { console.log(err); });
      const json = JSON.stringify(visits.data);
      const aggregate = jsonAggregate.create(json).match({ clientId: 1234 }).exec();
      console.log('aggregated to: ');
      console.log(aggregate);
      return aggregate;
  }

  async getRisks() {

      function calcPercentage(obj) {
        // get sum
        var sum = 0;
        var i, j;
        for (i in obj) {
          for (j in obj[i]) {
            sum += obj[i][j].count;
          }
        }
        // calc percentage
        for (i in obj) {
          for (j in obj[i]) {
            obj[i][j].percentage = parseFloat((obj[i][j].count / sum).toFixed(3));
          }
        }
        return obj;
      }

      function removeDoubleNestArry(a_obj) {
        var i, j;
        var newArray = [];
        for (i in a_obj) {
          newArray.push(a_obj[i][0]);
        }
        return newArray;
      }

      function setUndefinedCountToZero(obj) {
        var boolArray = [false, false, false, false];
        if (obj[0].length === 0) {
          boolArray[0] = true;
        }
        if (obj[1].length === 0) {
          boolArray[1] = true;
        }
        if (obj[2].length === 0) {
          boolArray[2] = true;
        }
        if (obj[3].length === 0) {
          boolArray[3] = true;
        }

        if (boolArray[0] == true) {
          obj[0] = [{id: "Critical risk", count: 0}];
        }
        if (boolArray[1] == true) {
          obj[1] = [{id: "Medium risk", count: 0}];
        }
        if (boolArray[2] == true) {
          obj[2] = [{id: "High risk", count: 0}];
        }
        if (boolArray[3] == true) {
          obj[3] = [{id: "Low risk", count: 0}];
        }


        return obj;
      }

      async function getHealthAspectRiskStats() {
          const rateAspect = 'healthAspect';
          const res = await http.get(rateAspect)
                                .catch(err => { console.log(err); });
          const data = jsonAggregate.create(JSON.stringify(res.data));

          // Filter by Risk & Sum
          var criticalCount = data
                                      .match({ rateHealth: 'Critical risk' })
                                      .group({ id: 'rateHealth',
                                               count: { $sum: 1 } })
                                      .exec();

          var highCount = data
                                      .match({ rateHealth: 'High risk' })
                                      .group({ id: 'rateHealth',
                                               count: { $sum: 1 } })
                                      .exec();

          var mediumCount = data
                                      .match({ rateHealth: 'Medium risk' })
                                      .group({ id: 'rateHealth',
                                               count: { $sum: 1 } })
                                      .exec();


          var lowCount = data
                                      .match({ rateHealth: 'Low risk' })
                                      .group({ id: 'rateHealth',
                                               count: { $sum: 1 } })
                                      .exec();


          // Format Data
          var riskStats = [criticalCount, mediumCount, highCount, lowCount];
          var riskStats = setUndefinedCountToZero(riskStats);
          var stats_percentage = calcPercentage(riskStats);
          var statsData = removeDoubleNestArry(stats_percentage);

          return statsData;
      }

      async function getEducationAspectRiskStats() {
          const rateAspect = 'educationAspect';
          const res = await http.get(rateAspect)
                                .catch(err => { console.log(err); });
          const data = jsonAggregate.create(JSON.stringify(res.data));

          // Filter by Risk & Sum
          var criticalCount = data
                                      .match({ rateEducation: 'Critical risk' })
                                      .group({ id: 'rateEducation',
                                               count: { $sum: 1 } })
                                      .exec();

          var highCount = data
                                      .match({ rateEducation: 'High risk' })
                                      .group({ id: 'rateEducation',
                                               count: { $sum: 1 } })
                                      .exec();

          var mediumCount = data
                                      .match({ rateEducation: 'Medium risk' })
                                      .group({ id: 'rateEducation',
                                               count: { $sum: 1 } })
                                      .exec();


          var lowCount = data
                                      .match({ rateEducation: 'Low risk' })
                                      .group({ id: 'rateEducation',
                                               count: { $sum: 1 } })
                                      .exec();

          // Format Data
          var riskStats = [criticalCount, mediumCount, highCount, lowCount];
          var filteredRiskStats = setUndefinedCountToZero(riskStats);
          var stats_percentage = calcPercentage(filteredRiskStats);
          var statsData = removeDoubleNestArry(stats_percentage);

          return statsData;
      }

      async function getSocialAspectRiskStats() {
          const rateAspect = 'socialAspect';
          const res = await http.get(rateAspect)
                                .catch(err => { console.log(err); });
          const data = jsonAggregate.create(JSON.stringify(res.data));

          // Filter by Risk & Sum
          var criticalCount = data
                                      .match({ rateSocialStatus: 'Critical risk' })
                                      .group({ id: 'rateSocialStatus',
                                               count: { $sum: 1 } })
                                      .exec();

          var highCount = data
                                      .match({ rateSocialStatus: 'High risk' })
                                      .group({ id: 'rateSocialStatus',
                                               count: { $sum: 1 } })
                                      .exec();

          var mediumCount = data
                                      .match({ rateSocialStatus: 'Medium risk' })
                                      .group({ id: 'rateSocialStatus',
                                               count: { $sum: 1 } })
                                      .exec();


          var lowCount = data
                                      .match({ rateSocialStatus: 'Low risk' })
                                      .group({ id: 'rateSocialStatus',
                                               count: { $sum: 1 } })
                                      .exec();

          // Format Data
          var riskStats = [criticalCount, mediumCount, highCount, lowCount];
          var filteredRiskStats = setUndefinedCountToZero(riskStats);
          var stats_percentage = calcPercentage(filteredRiskStats);
          var statsData = removeDoubleNestArry(stats_percentage);

          return statsData;
      }

      function getAllAspectRiskStats(riskData) {
          const data = jsonAggregate.create(JSON.stringify(riskData));

          // Filter by Risk & Sum
          var allRiskCounts = data
                                      .group({ id: 'id',
                                               count: { $sum: 'count' } })
                                      .exec();

          // Format Data
          var stats_percentage = calcPercentage([allRiskCounts]);

          return stats_percentage[0];
      }




      // get individual risk data
      const healthRiskData = await getHealthAspectRiskStats();
      const educationRiskData = await getEducationAspectRiskStats();
      const socialRiskData = await getSocialAspectRiskStats();

      var statsObj = {'healthRisk': healthRiskData,
                        'educationRisk': educationRiskData,
                        'socialRisk': socialRiskData
                       };
      // get all risk data
      const allRiskDataRaw = healthRiskData.concat(educationRiskData).concat(socialRiskData);
      const allRiskData = getAllAspectRiskStats(allRiskDataRaw);
      statsObj.allRisk = allRiskData;

      console.log('aggregated to: ');
      console.log(statsObj);


      return statsObj;
  }

}

export default new statsDataService();
