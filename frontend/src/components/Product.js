import React from 'react';
import {convertToCurrency} from '../helpers/convertToCurrency';


class Product extends React.Component{
    constructor(){
        super();
        this.state = {
        }
        this.handleSubmit = this.handleSubmit.bind(this);
    }


        
    handleSubmit(event){
        let key = this.props.product.code;
        this.props.addProductToCart(key);

    }

    componentDidMount(){
        console.log(this.props.product.pet);
    }

    render(){
        return (
            <tr className="product-item">
                <td><img src={require(`../img/api/products/no-image.png`).default} width="100px" /></td>
                <td className="product-info">
                    <p>Name: {this.props.product.name}</p>
                    <p>Price: {convertToCurrency(this.props.product.price)}</p>
                    <p>Category: {this.props.product.category.name}</p>
                    <p>Pet: {this.props.product.pet.name}</p>
                
                    <input id={`product-${this.props.product.code}`} type="number" min="1" max="10" defaultValue="1" />
                    <button className="product-button" onClick={this.handleSubmit}>Add to cart</button>
                </td>
            </tr> 
        );
    }
}

export default Product;