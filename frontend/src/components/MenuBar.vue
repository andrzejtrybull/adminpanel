<template>
    <div>
        <template>
            <b-navbar>
                <template slot="brand">
                    <b-navbar-item tag="router-link" :to="{ path: '/' }">
                        <img class="logos" alt="Vue logo" src="../assets/logo.png">
                        <div class="title is-5">AdminPanel</div>
                    </b-navbar-item>
                </template>
                <template slot="start">
                    <b-navbar-item href="/#/users">
                        <b-icon pack="fas" icon="users" size="is-huge" class="itemicon"/>
                        <span>Użytkownicy</span>
                    </b-navbar-item>
                </template>
                <template slot="end">
                    <b-navbar-item href="/#/about">
                        O Programie
                    </b-navbar-item>
                </template>
            </b-navbar>
        </template>
    </div>
</template>

<script>
    export default {
        name: "MenuBar"
    }
</script>

<style scoped>
    div .title {
        padding-left: 10px;
        margin-right: 20px;
    }
    .itemicon {
        margin-right: 8px;
    }
    .navbar {
        background-color: #ddffee;
    }
</style>