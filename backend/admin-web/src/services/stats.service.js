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
      const res = await http.get(`/healthAspect`)
                            .catch(err => { console.log(err); });
      const healthData = jsonAggregate.create(JSON.stringify(res.data));

      var statsArray = [];

      // Filter by Risk & Sum
      const healthCriticalCount = healthData
                                  .match({ rateHealth: 'critical risk' })
                                  .group({ id: 'rateHealth',
                                           count: { $sum: 1 } })
                                  .exec();

      const healthHighCount = healthData
                                  .match({ rateHealth: 'high risk' })
                                  .group({ id: 'rateHealth',
                                           count: { $sum: 1 } })
                                  .exec();

      const healthMediumCount = healthData
                                  .match({ rateHealth: 'medium risk' })
                                  .group({ id: 'rateHealth',
                                           count: { $sum: 1 } })
                                  .exec();


      const healthLowCount = healthData
                                  .match({ rateHealth: 'low risk' })
                                  .group({ id: 'rateHealth',
                                           count: { $sum: 1 } })
                                  .exec();

      var healthRiskStats = [healthCriticalCount, healthMediumCount,
                        healthHighCount, healthLowCount];
      console.log('aggregated to: ');
      console.log(healthRiskStats);

      return healthRiskStats;
  }

}

export default new statsDataService();
