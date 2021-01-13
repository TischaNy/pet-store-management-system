import React from 'react'
import Header from './Header'
import MainContent from './MainContent'
import Footer from './Footer'


class Home extends React.Component{
  constructor(props){
    super(props);
    this.state = {
      path: 'http://localhost:3000/api/',
      petData: []
    }

  }

  componentDidMount(){
    fetch(this.state.path + 'pet')
    .then(response => response.json()).then((petData) => {
        this.setState({petData: petData});
    }) 
}

  render(){
    return (
      <div className="App">
        <Header />
        <MainContent petData={this.state.petData}/>
        <Footer />
      </div>
    );
  }
}


export default Home;
