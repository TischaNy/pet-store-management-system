import React from 'react'
import Header from './Header'
import Footer from './Footer'
import Product from './Product'
import {apiRequest} from '../helpers/apiRequest'

class Catalog extends React.Component{

    constructor(props){
        super();
        this.state = {
            productData: []
        }
    }

    renderProductComponents(){
        return this.state.productData.map(product => {
            return <Product key={product.code} product={product} />
        })
    }

    componentDidMount(){
        apiRequest('/product', 'GET').then(response => response.json()).then((productData) => {
            this.setState({productData: productData});
        });
    }

    render(){
        return (
            <div className="App">
                    <div>Catalog</div>
                    <table className="styled-table">
                        <thead>
                            <tr>
                                {/* Shows product image */}
                                <th></th> 
                                <th></th>
                            </tr>
                            {this.renderProductComponents()}
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
            </div>
        );
    }
}

export default Catalog;