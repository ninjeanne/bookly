import axios from 'axios'

const AXIOS = axios.create({
  timeout: 1000,
    baseURL: process.env.VUE_APP_BACKEND + "/api" || "http://localhost:8080/api",
    headers: {
        'Access-Control-Allow-Origin' : '*',
        'Access-Control-Allow-Methods' : 'GET,PUT,POST,DELETE,PATCH,OPTIONS',
    }

});

export default {
    register(firstName, lastName) {
        return AXIOS.post(`/user/` + firstName + '/' + lastName, {
            headers: {
                'Authorization' : 'Bearer ' + localStorage.getItem('vue-token'),
                'Access-Control-Allow-Origin' : '*'
            }
        });
    },
    getUser() {
        return AXIOS.get("/customers", {
            headers: {
                'Authorization' : 'Bearer ' + localStorage.getItem('vue-token'),
                'Access-Control-Allow-Origin' : '*'
            }
        })
    },
    getSecured(user, password) {
        return AXIOS.get(`/user/`,{
            auth: {
                username: user,
                password: password
            },
        headers: {
            'Authorization' : 'Bearer ' + localStorage.getItem('vue-token'),
            'Access-Control-Allow-Origin' : '*'
        }});
    },
    getBook(auth) {
        return AXIOS.get("/friendshipbook",{
            headers: {
                'Authorization' : 'Bearer ' + localStorage.getItem('vue-token'),
                'Access-Control-Allow-Origin' : '*'
            }
        });
    },
    editBook(auth, title) {
        return AXIOS.put("/friendshipbook" +
            "?title=" + title,
            {},
            { headers: {
                    'Authorization' : 'Bearer ' + localStorage.getItem('vue-token'),
                    'Access-Control-Allow-Origin' : '*'
            }
        });
    },
    getBookCover(auth) {
        return AXIOS.get("/friendshipbook/image",{
            headers: {
                'Authorization' : 'Bearer ' + localStorage.getItem('vue-token'),
                'Access-Control-Allow-Origin' : '*'
            }
        })
    },
    editBookCover(auth, image) {
        var formData = new FormData();
        formData.append("file", image);
        return AXIOS.post("/friendshipbook/image", formData,{
            headers: {
                'content-type': 'multipart/form-data',
                'Authorization' : 'Bearer ' + localStorage.getItem('vue-token'),
                'Access-Control-Allow-Origin' : '*'
            }
        });
    }
}


