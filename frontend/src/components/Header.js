import { render } from '@testing-library/react'
import React from 'react'
import auth from '../auth/Auth'
function Header(props){
    console.log(auth.isAuthenticated());
    return (
        <nav className="navbar">
            <div>
                <h2 className="logo">Petstore.</h2>
            </div>
            {auth.isAuthenticated() && 
                <div>
                    <ul className="nav-items-one">
                    <li><a href="/home"><img src={require("../img/home-icon.svg").default} width="20px"/></a></li>
                    <li><a href="/catalog">Catalog</a></li>
                    </ul>
                    <ul className="nav-items-two">
                        <li><a href="#">Profile</a></li>
                        <li><a onClick={props.handleSignOut}>Sign out</a></li>
                    </ul>
                </div>
            }
         
        </nav>
    );
}

export default Header;
