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
            checkOut: false
        }

        this.renderCartItems = this.renderCartItems.bind(this);
    }

    componentDidMount(){
        console.log(this.state.checkOut);
    }

    renderCartItems(){
        return this.props.cartItems.map(cartItem => {
            return  <CartItem key={cartItem.id} cartItem={cartItem} deleteCartItem={this.props.deleteCartItem} updateCartItem={this.props.updateCartItem}/>
        });
    }


    render(){
        return (
            <div className="styled-cart">
                <h1>Shopping Cart</h1>
                <p>Items in shopping cart</p>

                <table>
                    {this.renderCartItems()}
                </table>
                <a href="/checkout/address"><button>Checkout</button></a>
            </div>
        );
    }
}

export default withRouter(Cart);