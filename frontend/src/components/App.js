import React from 'react'
import '../App.css';
import Home from './Home'
import Catalog from './Catalog'
import Header from './Header'
import Footer from './Footer'
import Login from './Login'
import auth from '../auth/Auth'
import { ProtectedRoute } from './ProtectedRoute';
import { Redirect } from 'react-router';

import {
  BrowserRouter as Router,
  Route,
  Switch,
  withRouter
} from "react-router-dom";



class App extends React.Component{
  constructor(props){
    super();
    this.handleSignOut = this.handleSignOut.bind(this);
  }

  
  handleSignOut(){
    if(auth.isAuthenticated()){
        auth.logOut();
        this.props.history.push('/login');
    }
  }


  render(){
      console.log(window.location.pathname);
  
      return (
        <Router>
            <Header handleSignOut={this.handleSignOut}/>
            <Switch>
              <ProtectedRoute exact path={["/", "/home"]} component={Home}  />
              <ProtectedRoute  path="/catalog" component={Catalog}/>
              <Route path="/login" component={Login}  />
              <Route path='*' exact={true} render={() => (<Redirect to="/home" />)} />
            </Switch>
    
            <Footer />
        </Router>
       
      );
    }
}


export default withRouter(App);
