import React, { Component } from 'react';
import UserService from '../services/UserService';
import { Table, Button } from 'reactstrap';
import { Link } from 'react-router-dom';

class Dashboard extends Component {
    constructor(props) {
        super(props);
        this.state={
            accounts: []
        }
    }

    componentDidMount() {
        UserService.getUserAccounts().then(
            response => {
                console.log("UserService response.data:", response.data);
                this.setState({
                    accounts: response.data
                });
            }
        ) 
    }

    render() {
        console.log("Dashboard render this.state.accounts: ", this.state.accounts);
        const name = this.state.accounts.map(account => account.user.fullName)[0];
        console.log(name);
        return (
            <div className="container">    
                <div className="m-3">
                    <h3>Welcome, {name}, to dashboard!</h3>
                </div>
                <Table>
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Account number</th>
                            <th>Balance</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        {this.state.accounts.map((account, i) =>
                        <tr key={account.id}>
                            <th scope="row">{i + 1}</th>    
                            <td>{account.number}</td> 
                            <td>{account.balance}</td>
                            <td><Button><Link to={`/dashboard/${account.number}`} >Account details</Link></Button></td> 
                        </tr>
                        )}
                    </tbody>
                </Table>        
            </div>            
        )   
    }
}


export default Dashboard
