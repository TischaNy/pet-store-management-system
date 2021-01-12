import React from 'react'

class Cart extends React.Component{
    constructor(){
        super();
        this.state = {

        }
    }

    render(){
        return (
            <div className="styled-cart">
                <h1>Shopping Cart</h1>
                <h2>Total: 0$</h2><hr/>
                <p>Items in shopping cart</p>
                <ul>
                    <li>Item 1</li>
                    <li>Item 2</li>
                    <li>Item 3</li>
                </ul>
                <button className="checkout-btn">Checkout</button>
            </div>
        );
    }
}

export default Cart;