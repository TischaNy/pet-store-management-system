import React from 'react'
import {apiRequest} from '../helpers/apiRequest'

let grandTotal = 0;

class Order extends React.Component{
    constructor(props){
        super();
        this.state = {
            orderItems: [],
        }

        this.renderOrderItems = this.renderOrderItems.bind(this);
    }

    componentDidMount(){
        const order = this.props.location.orderData;
        const cartItems = this.props.location.cartItems;

        console.log(order);
        if(order === undefined) {
            this.props.history.push("/cart");
            return;
        }

        apiRequest('/cart-item/cart?id=' + cartItems[0].cart.id, 'DELETE').then((response) => response.json()).then((result) => {
            console.log(result);
        }) 

        apiRequest('/order/create', 'POST', order).then((response) => response.json()).then((result) => {
            cartItems.forEach(item => {
                const cartItem = {
                    order: result.data,
                    product: item.product,
                    quantity: item.quantity,
                    amount: parseFloat(item.quantity)*parseFloat(item.product.price)
                }
                grandTotal += parseFloat(cartItem.amount);

                apiRequest('/order-item/create', 'POST', cartItem).then((response) => response.json()).then((result) => {
                    this.setState(prevState => ({
                        orderItems: [...prevState.orderItems, result.data]
                    }));
                });
            });
        });
    }

    
    renderOrderItems(){
        return this.state.orderItems.map(orderItem => {
            console.log(orderItem);
            return (
                <tr>
                    <td>{orderItem.product.name}</td>
                    <td>{orderItem.quantity}</td>
                    <td>{orderItem.amount}</td>
                </tr>
            );
        })
    }

    render(){
        return (
            <div>
                <h1>Order Information</h1>
                <p>Your order has been proceeded</p>
                <table>
                    {this.renderOrderItems()}
                </table>
                <p>Grand total: {grandTotal}</p>
            </div>
        )
    }
}

export default Order;