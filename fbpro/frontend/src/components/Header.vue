<template>
    <header>
        <nav>
            <ul>
                <li><router-link to="/">HOME</router-link></li>
                <li><router-link to="/schedule">Schedule</router-link></li>
                <li><router-link to="/users">Users</router-link></li>
                <li><router-link to="/schedule-manager">schedule-manager</router-link></li>
            </ul>
        </nav>
    </header>
</template>

<script>

    export default {
        name : 'MainHeader'
    };

</script>
<style scoped>

    header{
        background-color: #2c3e50;
        padding : 10px;
        color: white;
    };

    nav ul {
        color: white;
        list-style-type : none;
        display: flex;
        justify-content: space-around;
    };

    nav a {
        color : white;
        text-decoration: none;
    };

    nav a:hover {
    color: #ecf0f1;  /* hover 시 색상 밝은 회색 */
};


</style>