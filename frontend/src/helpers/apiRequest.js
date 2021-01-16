import auth from '../auth/Auth'


export const apiRequest = async (route, method, data) => {
    let url = "http://localhost:3000/api"
    let now = new Date().getTime();


    if(auth.getToken().expiryDate <= now){
        let authResponse = await fetch(url + '/authentication/token', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(auth.getToken())
        }).then((res) => {
            return res.json()
        }).then((res) => {
            console.log(res);
            auth.setToken(res.data);
        });
    }

    let response = await fetch(url + route, {
        method: method,
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + auth.getToken().token
        },
        body: JSON.stringify(data)
    });
    console.log(response);
    return response;
}
