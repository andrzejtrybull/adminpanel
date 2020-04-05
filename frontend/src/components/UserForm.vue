<template>
    <div class="container">
        <h1 class="title is-0">Użytkownik</h1>
        <p>
            User id : {{ id }}
        </p>
    </div>
</template>

<script>
    export default {
        name: "UserForm",
        data() {
            return {
                id: 0
            }
        },
        created() {
            this.id = this.$route.params.id;
        }
    }
</script>

<style scoped>
    .title {
        margin-top: 20px;
    }
</style>
