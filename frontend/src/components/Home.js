import React from 'react'
import Main from './Main'
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
