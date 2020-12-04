import React, { Component } from 'react';
import UserService from '../services/UserService';
import { Jumbotron, Table, Button, ButtonGroup } from 'reactstrap';
import AuthService from '../services/AuthService';
import AddMoneyModal from './AddMoneyModal';
import SendMoneyModal from './SendMoneyModal';
import TransferToAccountModal from './TransferToAccountModal';

class AccountDetails extends Component {
    constructor(props) {
        super(props);
        this.state={
            transactions: [],
            account: {}
        }
        this.refresh = this.refresh.bind(this);
    }

    componentDidMount() {
        const number = this.props.match.params.number;
        this.getTransactions(number);
        this.getAccount(number);
    }

    refresh() {
        this.componentDidMount();
    }

    getTransactions(number) {  
        UserService.getTransactions(number)
            .then(response => {
                console.log("UserService get transactions: ", response.data);
                this.setState({
                    transactions: response.data
                });
            }
        )
    }

    getAccount(number) {
        UserService.getAccountByNumber(number)
        .then(response => {
            this.setState({
                account: response.data
            });
        })
    }

    logoutHandler =(e) => {
        AuthService.logout();
        this.props.history.push('/login')
    }

    render() {
        console.log("Transactions render this.state.transactions: ", this.state.transactions); 
        const accountNumber = this.props.match.params.number;
        const balance = this.state.account.balance;
     
        return (
        <div className="container">
            <div className="m-4">
                <Button color="secondary" size="lg" onClick={e=>this.logoutHandler(e)}>LogOut</Button>
            </div>  
            <Jumbotron className="m-3">    
                <div className="row">
                    <div className="col-12 col-sm-6 m-t-3">
                        <h3>Account {accountNumber} details</h3>
                    </div>
                    <div className="col-12 col-sm-6 m-t-3">
                        <p className="text-success">Account balance: {balance}</p>    
                    </div>
                </div>
            </Jumbotron>
            <div className="m-2">
                <ButtonGroup>
                    <AddMoneyModal number={accountNumber} refresh={this.refresh} />
                    <SendMoneyModal number={accountNumber} refresh={this.refresh} />
                    <TransferToAccountModal number={accountNumber} refresh={this.refresh} />
                </ButtonGroup>
            </div>
            <Table>
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Date</th>
                        <th>Title</th>
                        <th>Category</th>
                        <th>Amount</th>
                    </tr>
                </thead>
                    <DisplayTransactions transactions={this.state.transactions} />    
            </Table>
         </div>           
        )  
    }
}

function DisplayTransactions({transactions}) {

    const addSign = (type) => {
        if (type === 'DEBIT') {
            return "-";
        }
        else return "+"
    }

    if (transactions.length > 0) {
        const transactionsList = transactions.map((transaction, i) => {
            return(
            <tr key={transaction.id}>
                <th scope="row">{i + 1}</th>    
                <td>{transaction.transactionDate}</td> 
                <td>{transaction.description}</td>
                <td>{transaction.category}</td>
                <td className={transaction.type === 'DEBIT'?"text-danger": "text-success"}>{addSign(transaction.type)}{transaction.amount}</td> 
            </tr>
            )
        });    
    return (
        <tbody>
            {transactionsList}
        </tbody>
    )}
    else {
        return(
            <tbody>
                <tr>
                    <td colSpan="5">Transactions not found</td>
                </tr>
            </tbody> 
        )
    }
}

export default AccountDetails