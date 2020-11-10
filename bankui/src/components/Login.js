import React, { Component } from 'react';
import SideNav from './SideNav';
import { Form, FormGroup, Label, Input, Col, Button } from 'reactstrap';
import AuthService from '../services/AuthService';

class Login extends Component {
    constructor(props) {
        super(props);
        this.state = {
            username: '',
            password: ''
        }
    }

    handleInputChange = (event) => {
        this.setState({[event.target.name]: event.target.value});
    }

    handleSubmit = (event) => {
        event.preventDefault();
        const loginRequest = {
            username: this.state.username, 
            password: this.state.password
        };
        console.log("Login", loginRequest.username);
        console.log("Login", loginRequest.password);
        AuthService.login(loginRequest).then(
            () => {
                this.props.history.push("/dashboard");
                window.location.reload();
            }
        )
    }
    
    render() {
        return (
            <div className="container">
                <div className="row row-content">
                    
                    <div className="col-12 col-md-8">
                    <div className="col-12 text-center">
                        <h2>Login to Your Account</h2>
                    </div>
                       <div align="center"> 
                        <Form onSubmit={this.handleSubmit}>
                            <FormGroup>
                                <Label htmlFor="username" className="col-6" align="left">Email address</Label>
                                <Col md={{size: 6}}>
                                <Input type="email" id="username" name="username" placeholder="Enter email"
                                value={this.state.username}
                                onChange={this.handleInputChange} />
                                </Col>
                            </FormGroup>
                            <FormGroup>
                                <Label htmlFor="password" className="col-6" align="left">Password</Label>
                                <Col md={{size: 6}}>
                                <Input type="password" id="password" name="password" placeholder="Password"
                                value={this.state.password}
                                onChange={this.handleInputChange} />
                                </Col>
                            </FormGroup>
                            <FormGroup>
                            <Col md={{size:6}}>
                                <Button type="submit" color="primary" size="lg" block>Login</Button>
                            </Col>  
                            </FormGroup>
                        </Form>
                        </div>
                    </div>
                    <div className="col-12 col-md-4 p-5">
                        <SideNav />
                    </div>
                </div>
            </div>
        );   
    }
}

export default Login