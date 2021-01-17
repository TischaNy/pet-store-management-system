import React from 'react'
import CartItem from './CartItem'

import {
    BrowserRouter as Router,
    Route,
    Switch,
    withRouter
  } from "react-router-dom";

  
class Cart extends React.Component{
    constructor(props){
        super();
        this.state = {
            checkOut: false,
            errorMessage: ''
        }

        this.renderCartItems = this.renderCartItems.bind(this);
        this.handleClick = this.handleClick.bind(this);
    }

    componentDidMount(){
        console.log(this.state.checkOut);
    }

    renderCartItems(){
        return this.props.cartItems.map(cartItem => {
            return  <CartItem key={cartItem.id} cartItem={cartItem} deleteCartItem={this.props.deleteCartItem} updateCartItem={this.props.updateCartItem}/>
        });
    }

    handleClick(){
        if(this.props.cartItems.length == 0){
            this.setState({
                errorMessage: "There are no items in your cart. Add to proceed."
            })
            return;
        }

        this.props.history.push("/checkout/address");
    }


    render(){
        return (
            <div className="styled-cart">
                <h1>Shopping Cart</h1>
                <p>Items in shopping cart</p>

                <table>
                    {this.renderCartItems()}
                </table>
                <button onClick={this.handleClick}>Checkout</button>
                <p>{this.state.errorMessage}</p>
            </div>
        );
    }
}

export default withRouter(Cart);