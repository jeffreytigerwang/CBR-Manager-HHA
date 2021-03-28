const jsonAggregate = require('json-aggregate')

class statsDataService {

  test(json) {
      return jsonAggregate.create(json).match({ clientId: 1234 }).exec();
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

export default new statsDataService();
