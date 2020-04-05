import axios from 'axios';

export default {
    getAllUsersPaginated: function (page, pagesize) {
        return axios
            .get('/api/user', {
                params: {
                    page: page - 1,
                    pagesize
                }
            })
            .then(getData);
    }
}

function getData(response) {
    return response.data;
}
