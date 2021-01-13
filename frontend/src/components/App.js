import React, { useState } from 'react'
import '../App.css';
import Home from './Home';
import Login from './Login';

import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link
} from "react-router-dom";


function App(){

  const token = useState();
  
  if(!token){
    return <Login />
  }



  return (
    <Router>
      <Route exact path={["/", "/home"]}>
        <Home />
      </Route>
    <Switch>
      <Route path="/login">
        <Login />
      </Route>
    </Switch>
  </Router>
  );
  
}


export default App;
