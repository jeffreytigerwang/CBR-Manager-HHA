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
      //return json;
      //return jsonAggregate.create(json).match({ clientId: 1234 }).exec();
  }

  getAllVisitsFromId(id) {
  }

  create(data) {
  }

  update(id, data) {
  }

  delete(id) {
  }

  deleteAll() {
  }
}

const data = statsDataService.test();
console.log(data);

export default new statsDataService();
