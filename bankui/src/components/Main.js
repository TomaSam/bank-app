import React, { Component } from 'react';
import Login from './Login';
import Header from './Header';
import Footer from './Footer';
import {Switch, Route, Redirect} from 'react-router-dom';
import Dashboard from './Dashboard';
import AccountDetails from './AccountDetails';
import Register from './Register';

class Main extends Component {    

    render() { 
        
        return (
            <div>
                <Header /> 
                    <Switch>
                    <Route exact path="/login" component={Login} />
                    <Route exact path="/register" component={Register} />
                    <Route exact path="/dashboard" component={Dashboard} />
                    <Route path="/dashboard/:number" component={AccountDetails} />
                    <Redirect to="/login" />
                    </Switch>
                <Footer />
            </div>
        );
    }
}

export default Main;
