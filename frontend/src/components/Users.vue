<template>
    <div class="container">
        <h1 class="title is-0">Użytkownicy</h1>
        <b-button class="is-primary is-pulled-right" tag="router-link" to="/newuser">Nowy</b-button>
        <b-table
            :data="users"
            :loading="loading"
            paginated
            backend-pagination
            :current-page.sync="current"
            :total="total"
            :per-page="pagesize"
            @page-change="onPageChange"
            :pagination-simple="false"
            :striped="true"
            aria-next-label="Next page"
            aria-previous-label="Previous page"
            aria-page-label="Page"
            aria-current-label="Current page"
        >
            <template slot-scope="props">
                <b-table-column field="id" label="ID" sortable numeric>
                    {{ props.row.id }}
                </b-table-column>
                <b-table-column field="username" label="User Name" sortable>
                    {{ props.row.username }}
                </b-table-column>
                <b-table-column field="firstname" label="First Name" sortable>
                    {{ props.row.firstname }}
                </b-table-column>
                <b-table-column field="lastname" label="Last Name" sortable>
                    {{ props.row.lastname }}
                </b-table-column>
                <b-table-column label="Action">
                    <b-button class="is-success is-small" icon-left="pen">Zmień</b-button>
                    <b-button class="is-warning is-small" icon-left="trash">Usuń</b-button>
                </b-table-column>
            </template>
        </b-table>
    </div>
</template>

<script>
    import userService from '../services/user.service';

    export default {
        name: "Users",
        data() {
            return {
                info: 'Hello from vue Users component',
                page: {},
                users: [],

                total: 0,
                current: 1,
                pagesize: 10,

                loading: false,
            }
        },
        methods: {
            onPageChange(newpage) {
                this.loadDataPage(newpage, this.pagesize);
            },
            loadDataPage: function(page, pagesize) {
                this.loading = true;
                console.log('params : ' + page + ' : ' + pagesize);
                userService.getAllUsersPaginated(page, pagesize)
                    .then( data => {
                        this.page = data;
                        this.users = data.content;
                        this.current = data.number + 1;
                        this.total = data.totalElements;
                        this.pagesize = data.size;
                        this.loading = false;
                    });
            }
        },
        mounted() {
            this.loadDataPage(this.current, this.pagesize);
        },
    }
</script>

<style scoped>
    .title {
        margin-top: 20px;
    }
    .button {
        margin: 2px;
    }
    .table {
        width: 100%;
    }
    .row {
        height: 16px;
    }
</style>