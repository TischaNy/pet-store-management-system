class Auth{
    constructor(){
        this.authenticated = false;
        this.user = {};
    }

    setAuth(auth){
        this.authenticated = auth.authenticated;
        this.user = auth.user;
    }

    logIn(user){
        this.user = user;
        this.authenticated = true;
    }

    logOut(){
        this.authenticated = false;
        this.user = {}
        localStorage.removeItem('auth');
    }
    
    isAuthenticated(){
        return this.authenticated;
    }
}

export default new Auth();