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

  // addTransaction(transaction, number) {
  //   return axios.post(`http://localhost:8080/api/transaction/${number}`, transaction, { headers: AuthHeader() });
  // }

  transferBetweenAccounts(transaction, senderNumber, recipientNumber) {
    return axios.post(`http://localhost:8080/api/transaction/${senderNumber}/${recipientNumber}`, transaction, { headers: AuthHeader() });
  }

}

export default new UserService();