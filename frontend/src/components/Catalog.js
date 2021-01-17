import React from 'react'
import auth from '../auth/Auth'
import Product from './Product'
import {apiRequest} from '../helpers/apiRequest'

class Catalog extends React.Component{

    constructor(props){
        super();
        this.state = {
            productData: [],
            petData: [],
            categoryData: [],
            filters: {
                petFilter: '',
                categoryFilter: ''
            }
        }

        this.renderProductComponents = this.renderProductComponents.bind(this);
        this.renderPetComponents = this.renderPetComponents.bind(this);
        this.renderCategoryComponents = this.renderCategoryComponents.bind(this);
        this.handleFilter = this.handleFilter.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.addProductToCart = this.addProductToCart.bind(this);
    }

    renderProductComponents(){
        return this.state.productData.map(product => {
            return <Product key={product.code} product={product} addProductToCart={this.addProductToCart.bind(this)} />
        })
    }

    renderPetComponents(){
        return this.state.petData.map(pet => {
            return  <div key={pet.id}>
                        <input type="radio" id={pet.name} name="petFilter" value={pet.name}  />
                        <label htmlFor={pet.name}>{pet.name}</label><br/>
                    </div>
        })
    }

    renderCategoryComponents(){
        return this.state.categoryData.map(category => {
            return  <div key={category.id}>
                        <input type="radio" id={category.name} name="categoryFilter" value={category.name} />
                        <label htmlFor={category.name}>{category.name}</label><br/>
                    </div>
        })
    }

    componentDidMount(){
        apiRequest('/product', 'GET').then(response => response.json()).then((productData) => {
            this.setState({productData: productData});
        });

        apiRequest('/pet', 'GET').then(response => response.json()).then((petData) => {
            this.setState({petData: petData});
        });

        apiRequest('/category', 'GET').then(response => response.json()).then((categoryData) => {
            this.setState({categoryData: categoryData});
        });
    }

    handleFilter(event){
        const {name, value} = event.target;
        let prevState = this.state;
        prevState.filters[name] = value;
        this.setState({prevState});
    }

    handleSubmit(){
        apiRequest(`/product?category=${this.state.filters.categoryFilter}&pet=${this.state.filters.petFilter}`, 'GET')
        .then(response => response.json()).then((productData) => {
            this.setState({productData: productData});
        });
    }


    addProductToCart(key){
        let cartItem = {};
        cartItem['product'] = this.state.productData.filter(product => product.code = key)[0];
        cartItem['quantity'] = parseInt(document.getElementById("product-" + key).value);
        cartItem['cart'] = auth.getToken().user.cart;

        let cartTotal = document.getElementById("cartTotal");
        cartTotal.innerHTML = parseInt(cartTotal.innerHTML) + parseInt(document.getElementById("product-" + key).value);
        console.log(cartItem);

        apiRequest('/cart-item/create', 'POST', cartItem);
    }

    render(){
        return (
            <div className="App catalog">
                 <div className="catalog-filter">
                    <h1>Catalog</h1>
                    <table>
                        <tr>
                            <td onChange={this.handleFilter}>
                                <div>
                                    <input type="radio" id="allPets" name="petFilter" value="" defaultChecked />
                                    <label htmlFor="allPets">All</label><br/>
                                </div>
                                {this.renderPetComponents()}
                            </td>
                            <td onChange={this.handleFilter}>
                                <div>
                                    <input type="radio" id="allCategories" name="categoryFilter" value="" defaultChecked/>
                                    <label htmlFor="allCategories">All</label><br/>
                                </div>
                                {this.renderCategoryComponents()}
                            </td>      
                        </tr>
                    </table>
                    <button onClick={this.handleSubmit} >Filter</button>
                    </div>
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