import React from 'react';
import { createPortal } from 'react-dom';
import {convertToCurrency} from '../helpers/convertToCurrency';
import {PropTypes} from 'react'

class Pet extends React.Component{
    constructor(){
        super();
        this.state = {
        
        }
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit(event){
        let key = this.props.opleiding.id;
        this.props.handleDeletion(key);
    }

    render(){
        return (
            <div className="card-overlay">
                <div className="second-section-card" style={{}}>
                    {/* <img src={require("../img/" + this.props.pet.imageUrl).default} width="64px"/> */}
                    <p>{this.props.pet.name}</p>
                </div>
            </div>
        );
    }
}

export default Pet;