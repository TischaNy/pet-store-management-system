import React from 'react';
import { Route } from 'react-router-dom';
import { Redirect } from 'react-router';
import auth from '../auth/Auth'

if(JSON.parse(localStorage.getItem('auth')) != undefined){ // if user is authenticated, get authentication informatopn
  auth.setAuth(JSON.parse(localStorage.getItem('auth')));
}

console.log(auth);

export const ProtectedRoute = ({ component: Component, ...rest }) => (
  <Route {...rest} render={(props) => (
      auth.isAuthenticated() === true
      ? <Component {...props} />
      : <Redirect to='/login' />
  )} />
)