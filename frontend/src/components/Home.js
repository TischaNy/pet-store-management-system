import React from 'react'
import Header from './Header'
import MainContent from './MainContent'
import Footer from './Footer'
import auth from '../auth/Auth'
import {apiRequest} from '../helpers/apiRequest'

class Home extends React.Component{
  constructor(props){
    super(props);
    this.state = {
      route: '/pet',
      petData: []
    }

    this.handleSignOut = this.handleSignOut.bind(this);
  }

  componentDidMount(){
    apiRequest(this.state.route, 'GET').then(response => response.json()).then((petData) => {
        this.setState({petData: petData});
    });
  }

  handleSignOut(){
    if(auth.isAuthenticated()){
        auth.logOut();
        this.props.history.push('/login');
    }
}


  render(){
    return (
      <div className="App">
        <Header handleSignOut={this.handleSignOut}/>
        <MainContent petData={this.state.petData}/>
        <Footer />
      </div>
    );
  }
}


export default Home;
