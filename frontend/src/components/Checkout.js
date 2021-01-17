import React from 'react'
import {apiRequest} from '../helpers/apiRequest'
import auth from '../auth/Auth'

import {
    withRouter
  } from "react-router-dom";

class Checkout extends React.Component{
    constructor(props){
        super();

        this.state = {
            street: '',
            houseNumber: '',
            city: '',
            zipCode: '',
            address: {}
        }

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    
    handleChange(event) {
        this.setState({[event.target.name]: event.target.value});
    }

    handleSubmit(event){
        apiRequest('/address/create', 'POST', this.state).then((response) => response.json()).then((result) => {
            const order = {
                orderDate: new Date(),
                user: auth.getToken().user,
                userAddress: result.data
            }
            this.props.history.push({
                pathname: "/checkout/order",
                orderData: order,
                cartItems: this.props.cartItems
            });
        });

        event.preventDefault();
    }


    render(){
        return (
            <div className="checkout">
                <h1>Checkout</h1>
                <p>Fill in adress form</p>
                <form onSubmit={this.handleSubmit}>
                    <table>
                        <tr>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>Street</td>
                            <td><input type="text" name="street" required value={this.state.street} placeholder="Enter street" onChange={this.handleChange} /></td>
                            <td>House Number</td>
                            <td><input type="number" name="houseNumber" required value={this.state.houseNumber} placeholder="Enter house number" onChange={this.handleChange} /></td>
                        </tr>
                        <tr>
                            <td>City</td>
                            <td><input type="text" name="city" required value={this.state.city} placeholder="Enter city" onChange={this.handleChange} /></td>
                            <td>ZipCode</td>
                            <td><input type="text" name="zipCode" required value={this.state.zipCode} placeholder="Enter zip code" onChange={this.handleChange} /></td>
                        </tr>
                    </table>
             
                    <button className="checkout-button">Continue Checkout</button>
                    {this.state.errorMessage && <p>{this.state.errorMessage}</p>}
                </form>
            </div>
        );
    }

}

export default withRouter(Checkout);