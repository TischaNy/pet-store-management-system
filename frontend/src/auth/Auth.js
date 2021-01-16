class Auth{
    constructor(){
        this.authenticated = false;
        this.token = {};
    }

    setAuth(auth){
        this.authenticated = auth.authenticated;
        this.token = auth.token;
    }

    logIn(token){
        this.token = token;
        this.authenticated = true;
    }

    logOut(){
        this.authenticated = false;
        this.token = {}
        localStorage.removeItem('auth');
    }
    
    isAuthenticated(){
        return this.authenticated;
    }

    setToken(token){
        this.token = token;
        localStorage.setItem('auth', JSON.stringify(this));
    }

    getToken(){
        return this.token;
    }
}

export default new Auth();