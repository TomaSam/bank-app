import axios from 'axios';
import AuthHeader from './AuthHeader';


class UserService {

  getUserAccounts() {
    return axios.get(`http://localhost:8080/api/account/all`, { headers: AuthHeader() });
  }

  getAccountByNumber(number) {
    return axios.get(`http://localhost:8080/api/account/${number}`, { headers: AuthHeader() });
  }

  createNewAccount() {
    return axios.post(`http://localhost:8080/api/account/new`, { headers: AuthHeader() });
  }

  getTransactions(number) {
    return axios.get(`http://localhost:8080/api/transaction/${number}`, { headers: AuthHeader() });
  }

  addTransaction(transaction, number) {
    return axios.post(`http://localhost:8080/api/transaction/${number}`, transaction, { headers: AuthHeader() });
  }

  transferToAccount(transaction, number, nextNumber) {
    return axios.post(`http://localhost:8080/api/transaction/${number}/${nextNumber}`, transaction, { headers: AuthHeader() });
  }

}

export default new UserService();