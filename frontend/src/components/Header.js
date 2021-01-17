import { render } from '@testing-library/react'
import React from 'react'
import auth from '../auth/Auth'


function Header(props){
    let calculatedTotal = props.cartTotal.reduce((sum, val) => {
        return sum + val.quantity;
    }, 0);

    console.log(calculatedTotal);

    return (
        <nav className="navbar">
            <div>
                <a href="/home"><h2 className="logo">Petstore.</h2></a>
            </div>
            {auth.isAuthenticated() && 
                <div>
                    <ul className="nav-items-one">
                        <li><a href="/home"><img src={require("../img/home-icon.svg").default} width="20px"/></a></li>
                        <li><a href="/catalog">Catalog</a></li>
                        <li><a href="/cart">Cart (<span id="cartTotal">{calculatedTotal}</span>)</a></li>
                        <li><a onClick={props.handleSignOut}>Sign out</a></li>
                    </ul>
                </div>
            }
         
        </nav>
    );
}

export default Header;

