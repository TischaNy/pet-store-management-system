import React from 'react'
import Header from './Header'
import Main from './Main'
import Footer from './Footer'
import Catalog from './Catalog'
import auth from '../auth/Auth'
import {apiRequest} from '../helpers/apiRequest'

class Home extends React.Component{
  constructor(props){
    super(props);
    this.state = {
      petData: []
    }

  }

  componentDidMount(){
    apiRequest('/pet', 'GET').then(response => response.json()).then((petData) => {
        this.setState({petData: petData});
    });
  }



  render(){
    return (
      <div className="App">
        <Main petData={this.state.petData} />
      </div>
    );
  }
}


export default Home;
