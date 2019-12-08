import axios from 'axios'

const AXIOS = axios.create({
  baseURL: `/api`,
  timeout: 1000
});


export default {
    register(firstName, lastName) {
        return AXIOS.post(`/user/` + firstName + '/' + lastName);
    },
    getUser(auth) {
        return AXIOS.get("/user/", {
            headers: {
                "authorization": "Basic " + auth
            }
        })
    },
    getSecured(user, password) {
        return AXIOS.get(`/user/`,{
            auth: {
                username: user,
                password: password
            }});
    },
    getBook(auth) {
        return AXIOS.get("/friendshipbook",{
            headers: {
                "authorization": "Basic " + auth
            }
        });
    },
    editBook(auth, title) {
        return AXIOS.put("/friendshipbook" +
            "?title=" + title,
            {},
            { headers: {
                "authorization": "Basic " + auth
            }
        });
    },
    getBookCover(auth) {
        return AXIOS.get("/friendshipbook/image",{
            headers: {
                "authorization": "Basic " + auth
            }
        })
    },
    editBookCover(auth, image) {
        var formData = new FormData();
        formData.append("file", image);
        return AXIOS.post("/friendshipbook/image", formData,{
            headers: {
                'content-type': 'multipart/form-data',
                "authorization": "Basic " + auth
            }
        });
    }
}


