import http from "../http-common";

class VisitDataService {
  getAllGeneralData() {
    return http.get("/visits");
  }

  getAllHealthData() {
    return http.get("/healthprogress")
  }

  get(id) {
    return http.get(`/visits/${id}`);
  }

  getAllVisitsFromId(id) {
    return http.get(`/visits?clientId=${id}`)
  }
}

export default new VisitDataService();