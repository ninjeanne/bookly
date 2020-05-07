import axios from 'axios'

let baseURL;
if(location.hostname === "localhost"){
    baseURL = "http://localhost:8080/api/public"
}
else {
    baseURL = "https://" + location.hostname + "/api/public"
}

const AXIOS = axios.create({
    timeout: 2000,
    baseURL: baseURL,
    headers: { }
});

export default {
    getPage(uuid) {
        return AXIOS.get("/page?uuid=" + uuid, {
            headers: { }
        })
    },
    updatePage(json) {
        return AXIOS.post("/page", json, {
            headers: {
                'Content-Type': 'application/json',
            }
        })
    },
    getPageImage(uuid) {
        return AXIOS.get("/page/image?uuid=" + uuid);
    },
    updatePageImage(uuid, image) {
        var formData = new FormData();
        formData.append("file", image);
        formData.append("uuid", uuid);
        return AXIOS.post("/page/image", formData, {
            headers: {
                'uuid': uuid,
                'content-type': 'multipart/form-data'
            }
        });
    }
}