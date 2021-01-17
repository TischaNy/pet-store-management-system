import React from 'react';
import { Redirect } from 'react-router-dom';
import auth from '../auth/Auth';


let info = [];

class Register extends React.Component{
    
    constructor(props){
        super();
        this.state = {
            firstName: '',
            lastName: '',
            userName: '',
            password: '',
            errorMessage: ''
        }

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.showMessages = this.showMessages.bind(this);
    }
    
    handleChange(event) {
        this.setState({[event.target.name]: event.target.value});
    }




    handleSubmit(event){
        event.preventDefault();

 
        fetch('http://localhost:3001/api/authentication/sign-up', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                'firstName': this.state.firstName,
                'lastName': this.state.lastName,
                'userName': this.state.userName,
                'password': this.state.password
            })
        }).then(res => {
            console.log(res);
            if(res.status == 400){
                return res.json().then((res) => {
                    this.setState({errorMessage: res.message || res.messages});
                    console.log(res);
                });
            }else{
                return res.json().then((res) => {
                    auth.logIn(res.data);
                    localStorage.setItem('auth', JSON.stringify(auth));
                    window.location.reload();
                })
            }
        });

    }

    showMessages(){
        if(this.state.errorMessage){
            if(Array.isArray(this.state.errorMessage)){
                return this.state.errorMessage.map(e => {
                    return <p>{e}</p>;
                })
            }
            return <p>{this.state.errorMessage}</p>
        }
    }


    render(){
        if(auth.isAuthenticated()){
            return <Redirect to="/home" />
        }

        return (
            <div className="register-section">
                <form method="POST" className="register-styled-form" onSubmit={this.handleSubmit}>
                    <h1>Register an Account</h1>
                    <label>Firstname</label><br/>
                    <input type="text" name="firstName" required value={this.state.firstName} placeholder="Enter first name" onChange={this.handleChange} /><br/>

                    <label>Lastname</label><br/>
                    <input type="text" name="lastName" required value={this.state.lastName} placeholder="Enter last name" onChange={this.handleChange} /><br/>

                    <label>Username</label><br/>
                    <input type="text" name="userName" required value={this.state.userName} placeholder="Enter username" onChange={this.handleChange} /><br/>

                    <label>Password</label><br/>
                    <input type="password" name="password" required value={this.state.password} placeholder="Enter password" onChange={this.handleChange} /><br/>
                    <button type="Submit">Register</button>
                    <div className="register-error-messages">
                        {this.showMessages()}
                    </div>
                </form>
        
            </div>
        );
    }
}

export default Register;