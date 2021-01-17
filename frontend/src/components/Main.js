import React from 'react'
import Cart from './Cart'
import Pet from './Pet'
import banner from '../img/pets-banner.png'


class Main extends React.Component{
    constructor(props){
        super();
        this.state =  {

        }

        this.renderPetComponents = this.renderPetComponents.bind(this);
    }

 
    renderPetComponents() {

        return this.props.petData.map(pet => {
            return <Pet key={pet.id} pet={pet} />
        })
    }

    render(){
        return (
            <main>
                <div className="first-section">
                    <div className="first-section-one">
                    <img src={banner}/>     
                    </div>
                    <div className="first-section-two">
                        <h1>Give your pet a <span>healthy treat</span></h1>
                        <p>We offer pet food, supplies and more at affordable prices.</p>
                        <a href="/catalog"><button className="search-button">Search in catalog</button></a>
                    </div>
                </div>
                <div className="second-section">
                    <h1>Pet Categories</h1>
                    <hr/>
                    {this.renderPetComponents()}
                </div>
            </main>
        );
    }
}


export default Main;