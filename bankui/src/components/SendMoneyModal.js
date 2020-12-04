import React, { Component } from 'react';
import { Modal, ModalHeader, ModalBody, Button, Form, FormGroup, Label, Input } from 'reactstrap';
import UserService from '../services/UserService';

export default class SendMoneyModal extends Component {
    constructor(props) {
        super(props);
        this.state = {
            isModalOpen: false,
            type: 'DEBIT',
            amount: 0.0,
            description: '',
            category: ''
        }
        this.toggleModal = this.toggleModal.bind(this);
    }

    toggleModal() {
        this.setState({
            isModalOpen: !this.state.isModalOpen
        });
    }

    inputChange = (event) => {
        this.setState({[event.target.name]: event.target.value});
    };

    submitForm = (event) => {
        event.preventDefault();
    
        const transaction = {
            type: this.state.type,
            amount: this.state.amount,
            description: this.state.description,
            category: this.state.category
        };
        console.log("Transaction: " + transaction);
        console.log("Account number: " + this.props.number);

        UserService.addTransaction(transaction, this.props.number)
        .then(response => {this.props.refresh()})
        .then(response => {this.toggleModal()});   
    }

    render() {
        return(
            <div>
            <Button outline onClick={this.toggleModal}>Send Money</Button>
            <Modal isOpen={this.state.isModalOpen} toggle={this.toggleModal}>
                <ModalHeader toggle={this.toggleModal}>Send Money</ModalHeader>
                <ModalBody>
                    <Form onSubmit={this.submitForm}>
                            <FormGroup>
                                <Label htmlFor="category">Category</Label>
                                <Input type="select" id="category" name="category" required defaultValue={'DEFAULT'}
                                onChange={this.inputChange}>
                                    <option value="DEFAULT" disabled>Choose Priority</option>
                                    <option value="RESTAURANTS">Restaurants</option>
                                    <option value="UTILITIES">Utilities</option>
                                    <option value="TRANSPORT">Transport</option>
                                    <option value="GROCERIES">Groceries</option>
                                    <option value="SERVICES">Services</option>
                                    <option value="HEALTH">Health</option>
                                    <option value="SHOPPING">Shopping</option>
                                    <option value="FROMCARD">Cash withdrawal</option>
                                </Input>
                            </FormGroup>
                            <FormGroup>
                                <Label htmlFor="amount">Amount</Label>
                                <Input type="number" id="amount" name="amount"
                               value={this.state.amount} onChange={this.inputChange} />
                            </FormGroup>
                            <FormGroup>
                                <Label htmlFor="description">Description</Label>
                                <Input type="text" id="description" name="description"
                                value={this.state.description} onChange={this.inputChange} />
                            </FormGroup>
                            <Button type="submit" value="submit" color="primary">
                                Send
                            </Button>
                    </Form>
                </ModalBody>
            </Modal>
            </div>
        )
    }
}