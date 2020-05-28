import axios from 'axios'

let baseURL;
if(location.hostname === "localhost"){
    baseURL = "http://localhost:8080/api"
}
else {
    baseURL = "https://" + location.hostname + "/api"
}


const AXIOS = axios.create({
  timeout: 2000,
    baseURL: baseURL,
    headers: { }
});

export default {
    getUser() {
        return AXIOS.get("/user", {
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem('vue-token')
            }
        })
    },
    updateUser(username, mail, firstName, lastName) {
        var json = "{" +
            '"username":"' + username + '",' +
            '"mail":"' + mail + '",' +
            '"first_name":"' + firstName + '",' +
            '"last_name":"' + lastName + '"' +
            "}";
        return AXIOS.post("/user", json, {
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem('vue-token'),
                'Content-Type': 'application/json'
            }
        })
    },
    getBook() {
        return AXIOS.get("/friendshipbook", {
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem('vue-token')
            }
        });
    },
    editBook(title, subtitle) {
        return AXIOS.post("/friendshipbook/title" +
            "?title=" + title + "&subtitle=" + subtitle,
            { },
            {
                headers: {
                    'Authorization': 'Bearer ' + localStorage.getItem('vue-token')
                }
            });
    },
    getBookCover() {
        return AXIOS.get("/friendshipbook/image", {
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem('vue-token')
            }
        })
    },
    editBookCover(image) {
        var formData = new FormData();
        formData.append("file", image);
        return AXIOS.post("/friendshipbook/image", formData, {
            headers: {
                'content-type': 'multipart/form-data',
                'Authorization': 'Bearer ' + localStorage.getItem('vue-token')
            }
        });
    },
    getBookSticker(number) {
        return AXIOS.get("/friendshipbook/sticker/" + number, {
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem('vue-token')
            }
        })
    },
    editBookSticker(image, number) {
        var formData = new FormData();
        formData.append("file", image);
        return AXIOS.post("/friendshipbook/sticker/" + number, formData, {
            headers: {
                'content-type': 'multipart/form-data',
                'Authorization': 'Bearer ' + localStorage.getItem('vue-token')
            }
        });
    },
    getPages() {
        return AXIOS.get("/page", {
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem('vue-token')
            }
        })
    },
    newPage() {
        var formData = new FormData();
        return AXIOS.post("/page", formData, {
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem('vue-token')
            }
        })
    },
    updatePage() {
        var formData = new FormData();
        return AXIOS.post("/page", formData, {
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem('vue-token')
            }
        })
    },
    deletePage(uuid) {
        console.log(uuid);
        return AXIOS.delete("/page?uuid=" + uuid, {
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem('vue-token')
            }
        })
    }
}