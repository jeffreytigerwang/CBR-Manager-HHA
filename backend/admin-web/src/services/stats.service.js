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
            obj[i][j].percentage = obj[i][j].count / sum;
          }
        }
        return obj;
      }

      function removeDoubleNestArry(obj) {
        var i, j;
        var newArray = [];
        for (i in obj) {
          newArray.push(obj[i][0]);
        }
        return newArray;
      }

      async function getAspectRiskStats(aspect) {
          const rateAspect = 'rate' + aspect.charAt(0).toUpperCase() + aspect.slice(1);

          const res = await http.get(`/${aspect}Aspect`)
                                .catch(err => { console.log(err); });
          const data = jsonAggregate.create(JSON.stringify(res.data));

          // create match objects
          const jsonTest = '{ "' + rateAspect + '": "critical risk" }'
          console.log(jsonTest);
          const criticalJson = JSON.parse();

          // Filter by Risk & Sum
          var criticalCount = data
                                      .match(jsonTest)
                                      .group({ id: rateAspect,
                                               count: { $sum: 1 } })
                                      .exec();

          var highCount = data
                                      .match({ rateHealth: 'high risk' })
                                      .group({ id: rateAspect,
                                               count: { $sum: 1 } })
                                      .exec();

          var mediumCount = data
                                      .match({ rateHealth: 'medium risk' })
                                      .group({ id: rateAspect,
                                               count: { $sum: 1 } })
                                      .exec();


          var lowCount = data
                                      .match({ rateHealth: 'low risk' })
                                      .group({ id: rateAspect,
                                               count: { $sum: 1 } })
                                      .exec();

          // Format Data
          var riskStats = [criticalCount, mediumCount,
                            highCount, lowCount];
          var stats_percentage = calcPercentage(riskStats);
          var statsData = removeDoubleNestArry(stats_percentage);
          console.log('aggregated to: ');
          console.log(statsData);
          //console.log('data test: ');


          return statsData;
      }

      const statsData = getAspectRiskStats('health');
      return statsData;
  }

}

export default new statsDataService();
