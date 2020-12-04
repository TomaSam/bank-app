import React, { Component } from 'react';
import { Modal, ModalHeader, ModalBody, Button, Form, FormGroup, Label, Input } from 'reactstrap';
import UserService from '../services/UserService';

export default class TransferToAccountModal extends Component {
    constructor(props) {
        super(props);
        this.state = {
            isModalOpen: false,
            type: 'DEBIT',
            amount: 0.0,
            description: '',
            category: 'FROMTOOWNER',
            nextNumber: ''
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
        console.log("Next number: " + this.state.nextNumber);

        UserService.transferToAccount(transaction, this.props.number, this.state.nextNumber)
        .then(response => {this.props.refresh()})
        .then(response => {this.toggleModal()});
        console.log("userservice: " + transaction);   
    }

    render() {
        return(
            <div>
            <Button outline onClick={this.toggleModal}>Transfer to Account</Button>
            <Modal isOpen={this.state.isModalOpen} toggle={this.toggleModal}>
                <ModalHeader toggle={this.toggleModal}>Send Money</ModalHeader>
                <ModalBody>
                    <Form onSubmit={this.submitForm}>
                        <FormGroup>
                            <Label htmlFor="nextNumber">Recipient account</Label>
                            <Input type="text" id="nextNumber" name="nextNumber" onChange={this.inputChange} />
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