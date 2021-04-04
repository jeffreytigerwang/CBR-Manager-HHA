import http from "../http-common";

const jsonAggregate = require('json-aggregate')

class VisitDataService {
//   getAll() {
//     return jsonAggregate.create(http.get("/visits").data);
//   }

  get(id) {
    return http.get(`/visits/${id}`);
  }

  getAllVisitsFromId(id) {
    return http.get(`/visits?clientId=${id}`)
  }

  create(data) {
    return http.post("/visits", data);
  }

  update(id, data) {
    return http.put(`/visits/${id}`, data);
  }

  delete(id) {
    return http.delete(`/visits/${id}`);
  }

  deleteAll() {
    return http.delete(`/visits`);
  }
}

export default new VisitDataService();