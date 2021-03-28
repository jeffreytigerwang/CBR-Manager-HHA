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

  findByName(name) {
    var name_obj = name.split(" ");
    var firstName = name_obj[0];
    var lastName = name_obj[1];

    console.log("Name object: " + name_obj)
    console.log("First Name: " + firstName)
    console.log("Last Name: " + lastName)

    if (firstName && lastName) { return http.get(`/visits?firstName=${firstName}
                                                 &lastName=${lastName}`);}
    else return http.get(`/visits?name=${firstName}`);
  }
}

export default new VisitDataService();