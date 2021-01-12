import { render } from '@testing-library/react'
import React from 'react'

function Header(){
    return (
        <nav className="navbar">
            <div>
                <h2 className="logo">Petstore.</h2>
            </div>
            <ul className="nav-items-one">
                <li><a href="#"><img src={require("../img/home-icon.svg").default} width="20px"/></a></li>
                <li><a href="#">Assortiment</a></li>
            </ul>
            <ul className="nav-items-two">
                <li><a href="#">Login</a></li>
                <li><a href="#">Sign Up</a></li>
            </ul>
        </nav>
    );
}

export default Header;
