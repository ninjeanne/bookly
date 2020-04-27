import axios from 'axios'

const AXIOS = axios.create({
  timeout: 2000,
    baseURL: process.env.VUE_APP_BACKEND + "/api" || "http://localhost:8080/api",
    headers: { }
});

export default {
    getUser() {
        console.log(localStorage.getItem('vue-token'))
        return AXIOS.get("/user", {
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem('vue-token'),
            }
        })
    },
    getBook() {
        return AXIOS.get("/friendshipbook", {
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem('vue-token'),
            }
        });
    },
    editBook(title) {
        return AXIOS.put("/friendshipbook" +
            "?title=" + title,
            {},
            {
                headers: {
                    'Authorization': 'Bearer ' + localStorage.getItem('vue-token'),
                }
            });
    },
    getBookCover() {
        return AXIOS.get("/friendshipbook/image", {
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem('vue-token'),
            }
        })
    },
    editBookCover(image) {
        var formData = new FormData();
        formData.append("file", image);
        return AXIOS.post("/friendshipbook/image", formData, {
            headers: {
                'content-type': 'multipart/form-data',
                'Authorization': 'Bearer ' + localStorage.getItem('vue-token'),
            }
        });
    },
    getPage() {
        return AXIOS.get("/page", {
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem('vue-token'),
            }
        })
    },
}