import React from 'react';
import {convertToCurrency} from '../helpers/convertToCurrency';


class Opleiding extends React.Component{
    constructor(){
        super();
        this.state = {
        }
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit(event){
    
    }

    render(){
        return (
            <tr>
                <td>Image</td>
                <td>Product</td>
{/*                
                <td>{convertToCurrency(this.props.opleiding.prijs)}</td> */}
                {/* <td>
                    <form onSubmit={this.handleSubmit}>
                        <input id={`myInput-${this.props.opleiding.id}`} value={this.props.opleiding.id} hidden readOnly />
                        <button type="submit" className="btn-delete">Verwijder</button>
                    </form>
                </td> */}
            </tr> 
        );
    }
}

export default Opleiding;