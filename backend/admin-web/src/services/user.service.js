import http from "../http-common";

class UserDataService {
  getAll() {
    return http.get("/users");
  }

  get(id) {
    return http.get(`/users/${id}`);
  }

  create(data) {
    return http.post("/users", data);
  }

  update(id, data) {
    return http.put(`/users/${id}`, data);
  }

  delete(id) {
    return http.delete(`/users/${id}`);
  }

  deleteAll() {
    return http.delete(`/users`);
  }

  findByName(name) {
    var name_obj = name.split(" ");
    var firstName = name_obj[0];
    var lastName = name_obj[1];
    return http.get(`/users?firstName=${firstName}&lastName=${lastName}`);
  }
}
/*
  // TODO
  findByZone(zone) {
    return http.get(`/users?zone=${zone}`);
  }
}
*/

export default new UserDataService();

