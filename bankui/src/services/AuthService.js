import axios from "axios";

class AuthService {
  login(loginRequest) {
    return axios.post(`http://localhost:8080/api/users/login`, loginRequest)
      .then(response => {
        console.log("AuthService response data token", response.data.token);
        if (response.data.token) {
          localStorage.setItem("user", JSON.stringify(response.data));
        }
        console.log("AuthService response data", response.data);
        return response.data;
      });
  }

//   logout() {
//     localStorage.removeItem("user");
//   }

//   register(username, fullName, password) {
//     return axios
//     .post(API_URL + "register", {
//       username,
//       fullName,
//       password
//     });
//   }

  getCurrentUser() {
    return JSON.parse(localStorage.getItem('user'));;
  }
  
}

export default new AuthService();