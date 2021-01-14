import React, { useState } from 'react'
import '../App.css';
import Home from './Home';
import Login from './Login';
import { ProtectedRoute } from './ProtectedRoute';

import {
  BrowserRouter as Router,
  Route,
} from "react-router-dom";



class App extends React.Component{
  constructor(){
    super();
  }


  render(){
    return (
      <Router>
          <ProtectedRoute exact path={["/", "/home"]} component={Home}  />
          <Route path="/login" component={Login}  />
      </Router>
  
    );
  }
  
}


export default App;
