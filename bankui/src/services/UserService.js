import axios from 'axios';
import AuthHeader from './AuthHeader';


class UserService {

  getUserAccounts() {
    return axios.get(`http://localhost:8080/api/account/all`, { headers: AuthHeader() });
  }

  getTransactions(number) {
    return axios.get(`http://localhost:8080/api/transaction/${number}`, { headers: AuthHeader() })
  }

  getAccountByNumber(number) {
    return axios.get(`http://localhost:8080/api/account/${number}`, { headers: AuthHeader() })
  }

}

export default new UserService();