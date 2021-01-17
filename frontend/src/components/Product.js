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
            <tr>
                <td>Image</td>
                <td>
                    <p>{this.props.product.name}</p>
                    <p>{this.props.product.price}</p>
                    <p>{this.props.product.category.name}</p>
                    <p>{this.props.product.pet.name}</p>
                
                    <input id={`product-${this.props.product.code}`} type="number" min="1" max="10" defaultValue="1" />
                    <button onClick={this.handleSubmit}>Add to cart</button>
                </td>

{/*              
                <td>{convertToCurrency(this.props.opleiding.prijs)}</td> */}
                {/* <td>
                    <form onSubmit={this.handleSubmit}>
                        <input id={`myInput-${this.props.opleiding.id}`} value={this.props.opleiding.id} hidden readOnly />
                        <button type="submit" className="btn-delete">Verwijder</button>
                    </form>
                </td> */}
            </tr> 
        );
    }
}

export default Product;