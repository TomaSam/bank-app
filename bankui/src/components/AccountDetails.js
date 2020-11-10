import React, { Component } from 'react';
import UserService from '../services/UserService';
import { Table } from 'reactstrap';


class AccountDetails extends Component {
    constructor(props) {
        super(props);
        this.state={
            transactions: []
        }
    }

    componentDidMount() {
        const number = this.props.match.params.number;
        console.log("Number " + number);
        UserService.getTransactions(number)
            .then(response => {
                console.log("UserService get transactions: ", response.data);
                this.setState({
                    transactions: response.data
                });
            }
        )
         
    }

    render() {
        console.log("Transactions render this.state.transactions: ", this.state.transactions); 
        const account = this.props.match.params.number;
        return (
        <div className="container">    
            <div className="m-3">
                <h3>Account {account} details</h3>
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
    if (transactions.length > 0) {
        const transactionsList = transactions.map((transaction, i) => {
            return(
            <tr key={transaction.id}>
                <th scope="row">{i + 1}</th>    
                <td>{transaction.transactionDate}</td> 
                <td>{transaction.description}</td>
                <td>{transaction.category}</td>
                <td>{transaction.amount}</td> 
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
           <h4>No found transactions</h4> 
        )
    }
}

export default AccountDetails