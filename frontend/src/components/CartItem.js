import React from 'react';
import {convertToCurrency} from '../helpers/convertToCurrency';


class CartItem extends React.Component{
    constructor(){
        super();
        this.state = {
        
        }

        this.handleClick = this.handleClick.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }

    handleClick(event){
        let key = this.props.cartItem.id;
        this.props.deleteCartItem(key);
    }

    handleChange(event){
        const {name, value} = event.target;
        this.props.updateCartItem(name, value);
    }

    render(){
        return (
            <tr>
                <td><img src={require(`../img/api/products/no-image.png`).default} width="100px" /></td>
                <td>{this.props.cartItem.product.name}</td>
                <td>{this.props.cartItem.quantity}</td>
                <td>{convertToCurrency(this.props.cartItem.product.price)}</td>
                <td><input type="number" name={this.props.cartItem.id} defaultValue={this.props.cartItem.quantity} min="1" max="10" onChange={this.handleChange}/></td>
                <td>{convertToCurrency(parseFloat(this.props.cartItem.product.price)*parseInt(this.props.cartItem.quantity))}</td>
                <td><button className="remove-button" onClick={this.handleClick}>Remove item</button></td>
            </tr>
        );
    }
}

export default CartItem;