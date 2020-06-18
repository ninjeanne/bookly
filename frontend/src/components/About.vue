<template>
    <div class="main">
        <div class="card">
        <h2 style="text-align: center">About us</h2>
            <v-list-item>
                <div style="text-align: center">
            <img src="../assets/bookly.jpg" alt="logo" width="450" height="250">
            <img src="../assets/logo.png" alt="logo" width="225" height="250">
                </div>
            </v-list-item>
            <br>
            <p>
            Hey! We are team bookly. We decided to develop a digital friendship book during our Softwareengineering class at university.
        </p>


            <p>
            Our team consists of three team members: </p>
            <table>
                <ul>
                <li>Jeanne Helm</li>
                <li>Nico Schinacher</li>
                <li>Alexandra Stober</li>
                </ul>
            </table>

        <p>
            Our main goal is to learn and apply new technologies to our idea.
            We achieved these goals by studying various papers and utilizing countless energy drinks as fuel.
        </p>
        <p>
            We hope you enjoy our (WIP) application!
            If you have any questions or bugs, feel free to contact us. 😄 </p>
            Our email address is:
        <h4 style="text-align: center">
            <strong>help@bookly.online</strong>
        </h4>
            <br>
            <div style="text-align: center">
            <img src="../assets/booksBanner.jpg" alt="banner" width="600" height="144">
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "about"
    }
</script>

<style scoped>

    .main {
        padding: 32px;
        margin-left: 18%;
        margin-right: 18%;
        text-align: justify;
    }
    .card{
        padding: 22px;
    }
    h3, h4 {
        margin-top: 32px;
    }
</style>