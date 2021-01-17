import React from 'react'
import '../App.css';
import Home from './Home'
import Catalog from './Catalog'
import Cart from './Cart'
import Header from './Header'
import Footer from './Footer'
import Login from './Login'
import Register from './Register'
import auth from '../auth/Auth'
import { ProtectedRoute } from './ProtectedRoute';
import { Redirect } from 'react-router';
import { apiRequest } from '../helpers/apiRequest';
import Checkout from './Checkout'
import Order from './Order'
import {
  BrowserRouter as Router,
  Route,
  Switch,
  withRouter
} from "react-router-dom";



class App extends React.Component{
  constructor(props){
    super();
    this.state = {
      cartItems: []
    }
    this.handleSignOut = this.handleSignOut.bind(this);
    this.deleteCartItem = this.deleteCartItem.bind(this);
    this.updateCartItem = this.updateCartItem.bind(this);
  }

  componentDidMount(){
    if(auth.isAuthenticated()){
        apiRequest('/cart-item?cart='+ auth.getToken().user.cart.id, 'GET').then(response => response.json()).then((result) => {
            this.setState({cartItems: result});
        });
    }
  }

  deleteCartItem(key){
    apiRequest('/cart-item?id=' + key, 'DELETE');
    window.location.reload();
  }

  updateCartItem(key, value){
    console.log(key);
    let cartItem = this.state.cartItems.filter(cartItem => cartItem.id == key)[0];
    cartItem.quantity = value;
    apiRequest('/cart-item/update', 'POST', cartItem);
    window.location.reload();
  }

  
  handleSignOut(){
    if(auth.isAuthenticated()){
        auth.logOut();
        this.props.history.push('/login');
    }
  }


  render(){
      return (
        <Router>
            <Header handleSignOut={this.handleSignOut} cartTotal={this.state.cartItems}/>
              <Switch>
                <ProtectedRoute exact path={["/", "/home"]} component={Home}  />
                <ProtectedRoute  path="/catalog" component={Catalog}/>
                <ProtectedRoute path="/cart"  component={props => <Cart cartItems={this.state.cartItems} deleteCartItem={this.deleteCartItem.bind(this)} updateCartItem={this.updateCartItem.bind(this)} />}  />
                <ProtectedRoute path="/checkout/address" component={props => <Checkout cartItems={this.state.cartItems} />}/>
                <ProtectedRoute path="/checkout/order" component={Order} />
                <Route path="/login" component={Login}  />
                <Route path="/register" component={Register}  />
                <Route path='*' exact={true} render={() => (<Redirect to="/home" />)} />
              </Switch>
            <Footer />
        </Router>
       
      );
    }
}


export default withRouter(App);
