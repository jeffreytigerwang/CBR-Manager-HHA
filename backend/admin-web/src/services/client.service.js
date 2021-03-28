import http from "../http-common";

class ClientDataService {
  getAll() {
    return http.get("/clients");
  }

  get(id) {
    return http.get(`/clients/${id}`);
  }

  getAllVisitsFromId(id) {
    return http.get(`/visits?clientId=${id}`)
  }

  create(data) {
    return http.post("/clients", data);
  }

  update(id, data) {
    return http.put(`/clients/${id}`, data);
  }

  delete(id) {
    return http.delete(`/clients/${id}`);
  }

  deleteAll() {
    return http.delete(`/clients`);
  }

  findByName(name) {
    var name_obj = name.split(" ");
    var firstName = name_obj[0];
    var lastName = name_obj[1];

    console.log("Name object: " + name_obj)
    console.log("First Name: " + firstName)
    console.log("Last Name: " + lastName)

    if (firstName && lastName) { return http.get(`/clients?firstName=${firstName}
                                                 &lastName=${lastName}`);}
    else return http.get(`/clients?name=${firstName}`);
  }
}

export default new ClientDataService();