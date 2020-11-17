import axios from "axios";

class AuthService {

  login(loginRequest) {
    return axios.post(`http://localhost:8080/api/users/login`, loginRequest)
      .then(response => {
        console.log("AuthService Login response data", response.data.token);
        if (response.data.token) {
          localStorage.setItem("user", JSON.stringify(response.data));
        }
        console.log("AuthService Login response data", response.data);
        return response.data;
      });
  }

  logout() {
    localStorage.removeItem("user");
  }

  register(registerRequest) {
    return axios.post(`http://localhost:8080/api/users/register`, registerRequest);
  }

  getCurrentUser() {
    return JSON.parse(localStorage.getItem('user'));;
  }
  
}

export default new AuthService();