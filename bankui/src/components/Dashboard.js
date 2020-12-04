import React, { Component } from 'react';
import UserService from '../services/UserService';
import { Table, Button } from 'reactstrap';
import { Link } from 'react-router-dom';
import AuthService from '../services/AuthService';

class Dashboard extends Component {
    constructor(props) {
        super(props);
        this.state={
            accounts: [],
            
        }
        this.refresh = this.refresh.bind(this);
    }

    componentDidMount() {
        UserService.getUserAccounts().then(
            response => {
                //console.log("UserService response.data:", response.data);
                this.setState({
                    accounts: response.data
                });
            }
        )
    }

    refresh() {
        this.componentDidMount();
    }

    logoutHandler = () => {
        AuthService.logout();
        this.props.history.push('/login')
    }

    addAccount() {
        UserService.createNewAccount()
        .then(response => {this.refresh()});
    }

    render() {
        // console.log("Dashboard render this.state.accounts: ", this.state.accounts);
        // console.log("Name ", this.props.location.state.username);
        return (
            <div className="container m-4">
                <div className="m-4">
                    <Button color="secondary" size="lg" onClick={() => this.logoutHandler()}>LogOut</Button>
                </div>    
                <div className="m-4">
                    <h3>Welcome, {this.props.location.state.username}, to dashboard!</h3>
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
                    <DisplayAccounts accounts={this.state.accounts} />
                </Table>
                <div>
                    <Button onClick={() => this.addAccount()}>Add new account</Button>
                </div>        
            </div>            
        )   
    }
}

function DisplayAccounts({accounts}) {
    if (accounts.length > 0) {
        const accountList = accounts.map((account, i) => {
            return (
                <tr key={account.id}>
                    <th scope="row">{i + 1}</th>    
                    <td>{account.number}</td> 
                    <td>{account.balance}</td>
                    <td><Button><Link to={`/dashboard/${account.number}`} >Account details</Link></Button></td> 
                </tr>
            )
        });
        return (
            <tbody>
                {accountList}
            </tbody>
        )
    }
    else {
        return(
            <tbody>
                <tr>
                    <td colSpan="5">Accounts not found</td>
                </tr>
            </tbody> 
        )
    }
}

export default Dashboard
