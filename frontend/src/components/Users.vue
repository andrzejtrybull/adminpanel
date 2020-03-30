<template>
    <div>
        <h1>Users</h1>
        <p>
            {{info}}
        </p>
        <table>
            <thead>
                <tr>
                    <th>UserName</th>
                    <th>FirstName</th>
                    <th>LastName</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="user in users" :key="user.id">
                    <td>{{user.username}}</td>
                    <td>{{user.firstname}}</td>
                    <td>{{user.lastname}}</td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
    import axios from 'axios';
    export default {
        name: "Users",
        data: () => {
            return {
                info: 'Hello from vue Users component',
                users: []
            }
        },
        mounted() {
            axios
                .get('/api/user')
                .then( response => this.users = response.data );
        }
    }
</script>

<style scoped>

</style>