import http from "../http-common";

class VisitDataService {
  getAll() {
    return http.get("/visits");
  }

  get(id) {
    return http.get(`/visits/${id}`);
  }

  getAllVisitsFromId(id) {
    return http.get(`/visits?clientId=${id}`)
  }
}

export default new VisitDataService();