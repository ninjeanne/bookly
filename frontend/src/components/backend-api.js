import axios from 'axios'

const AXIOS = axios.create({
  baseURL: `/api`,
  timeout: 1000
});


export default {
    getUser(auth) {
        return AXIOS.get("/user/", {
            headers: {
                "authorization": "Basic " + auth
            }
        })
    },
    createUser(firstName, lastName) {
        return AXIOS.post(`/user/` + firstName + '/' + lastName);
    },
    getSecured(user, password) {
        return AXIOS.get(`/user/`,{
            auth: {
                username: user,
                password: password
            }});
    },
}


