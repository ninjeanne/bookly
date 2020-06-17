<template>
    <div class="main">
        <h2>What about us</h2>
        <h3>
            Hey! We are team bookly and during our Softwareengineering class at university we decided to develop a digital friendshipbook.
        </h3>
        <h3>
            We are consisting of 3 team members:
            <table>
                <li>Jeanne Helm</li>
                <li>Nico Schinacher</li>
                <li>Alexandra Stober</li>
            </table>
        </h3>
        <h3>
            Our main goal is to learn and apply new technologies to our main idea.
            We achieved these goals by studying various papers and utilizing countless energy drinks.
        </h3>
        <h3>
            We hope you enjoy our (work in progress) application!
            If you have any questions or bugs feel free to contact us :)
        </h3>
        <h4>
            help@bookly.online
        </h4>
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
    h3, h4 {
        margin-top: 32px;
    }
</style>