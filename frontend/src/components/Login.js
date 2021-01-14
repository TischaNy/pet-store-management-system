import { render } from '@testing-library/react';
import React from 'react';
import auth from '../auth/Auth'
import { Redirect } from 'react-router-dom';

class Login extends React.Component{
    constructor(props){
        super();
        this.state = {
            username: '',
            password: '',
            errorMessage: ''
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {
        this.setState({[event.target.name]: event.target.value});
    }

    handleSubmit(event){
        fetch('http://localhost:3000/api/authentication/sign-in', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                'username': this.state.username,
                'password': this.state.password
            })
        }).then(res => {
            console.log(res);
            if(res.status == 400){
                return res.json().then((res) => {
                    this.setState({errorMessage: res.message});
                });
            }else{
                return res.json().then((res) => {
                    auth.logIn(res.data);
                    localStorage.setItem('auth', JSON.stringify(auth));
                    console.log(localStorage.getItem('auth'));
                    //this.props.history.push("/home");
                })
            }
        });
        event.preventDefault();
    }

    render(){
        return (
            <form method="POST" className="styled-form" onSubmit={this.handleSubmit}>
                <h1>Account Login</h1>
                <label>Username</label><br/>
                <input type="text" name="username" value={this.state.username} placeholder="Username" onChange={this.handleChange} /><br/>
                <label>Password</label><br/>
                <input type="password" name="password" value={this.state.password} placeholder="Password" onChange={this.handleChange}/><br/>
                <button type="submit">Log In</button>
                {this.state.errorMessage && <p>{this.state.errorMessage}</p>}
            </form>
        );
    }

}

export default Login;