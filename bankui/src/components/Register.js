import React, { Component } from 'react';
import SideNav from './SideNav';
import { Form, FormGroup, Label, Input, Col, Button } from 'reactstrap';
import AuthService from '../services/AuthService';


class Register extends Component {
    constructor(props) {
        super(props);
        this.state = {
            username: '',
            fullName: '',
            password: '',
            confirmPassword: '',
            message: ""
        }
    }

    handleInputChange = (event) => {
        this.setState({[event.target.name]: event.target.value});
    }

    handleSubmit = (event) => {
        event.preventDefault();
        const registerRequest = {
            username: this.state.username,
            fullName: this.state.fullName, 
            password: this.state.password,
            confirmPassword: this.state.confirmPassword
        };
        console.log("Register request: ", registerRequest);
        AuthService.register(registerRequest).then(
            () => {
                this.props.history.push("/login");
                window.location.reload();
            })
            .catch(error => {
                const resMessage = (error.response 
                    && error.response.data 
                    && error.response.data.message) ||
                    error.message ||
                    error.toString();
                    this.setState({
                        message: resMessage
                    });
            })
    }

    render() {
        return (
            <div className="container">
                <div className="row row-content">   
                    <div className="col-12 col-md-8">
                        <div className="col-12 text-center">
                            <h2>Create new Account</h2>
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
                                    <Label htmlFor="fullName" className="col-6" align="left">Full Name</Label>
                                    <Col md={{size: 6}}>
                                    <Input type="text" id="fullName" name="fullName" placeholder="Enter Full Name"
                                    value={this.state.fullName}
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
                                    <Label htmlFor="confirmPassword" className="col-6" align="left">Confirm Password</Label>
                                    <Col md={{size: 6}}>
                                    <Input type="password" id="confirmPassword" name="confirmPassword" placeholder="Confirm Password"
                                    value={this.state.confirmPassword}
                                    onChange={this.handleInputChange} />
                                    </Col>
                                </FormGroup>
                                <FormGroup>
                                <Col md={{size:6}}>
                                    <Button type="submit" color="primary" size="lg" block>Sign Up</Button>
                                </Col>  
                                </FormGroup>
                            </Form>
                            {this.state.message && (
                            <div className="alert alert-danger" role="alert">
                            {this.state.message}
                          </div>
                        )}
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

export default Register