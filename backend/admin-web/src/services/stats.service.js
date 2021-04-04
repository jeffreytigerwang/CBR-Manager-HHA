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
        return sum;
      }

     const res = await http.get(`/healthAspect`)
                            .catch(err => { console.log(err); });
      const healthData = jsonAggregate.create(JSON.stringify(res.data));

      var statsArray = [];

      // Filter by Risk & Sum
      var healthCriticalCount = healthData
                                  .match({ rateHealth: 'critical risk' })
                                  .group({ id: 'rateHealth',
                                           count: { $sum: 1 } })
                                  .exec();

      var healthHighCount = healthData
                                  .match({ rateHealth: 'high risk' })
                                  .group({ id: 'rateHealth',
                                           count: { $sum: 1 } })
                                  .exec();

      var healthMediumCount = healthData
                                  .match({ rateHealth: 'medium risk' })
                                  .group({ id: 'rateHealth',
                                           count: { $sum: 1 } })
                                  .exec();


      var healthLowCount = healthData
                                  .match({ rateHealth: 'low risk' })
                                  .group({ id: 'rateHealth',
                                           count: { $sum: 1 } })
                                  .exec();

      var healthRiskStats = [healthCriticalCount, healthMediumCount,
                        healthHighCount, healthLowCount];
      console.log('aggregated to: ');
      console.log(healthRiskStats);
      console.log('data test: ');
      console.log(calcPercentage(healthRiskStats));


      return healthRiskStats;
  }

}

export default new statsDataService();
