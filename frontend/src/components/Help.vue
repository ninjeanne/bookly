<template>
    <div class="main" style="text-align:left">
        <div class="card">
        <h1>Help</h1>
<br>
        <p>This page will give you an idea of what bookly can do. Here you can find a quick overview of our help topics. You can choose the topic that interests you.</p>
        <table>
            <ul>
            <li> <a href="#login">Log in to your account</a></li>
            <li> <a href="#inviteFriend">Invite friend to friendship book</a></li>
            <li> <a href="#register">Register for an account at bookly</a></li>
            </ul>
        </table>
            <p>The explanations are too hard to understand and you don't know where to start? Then watch our short bookly help video. You will be able to use bookly in no time.</p>
            <h5 style="text-align:center"><a href="https://www.youtube.com/watch?v=6JA0YSH2aXc">Watch our bookly help video</a></h5>

        <h3><a id="Login">Log in to your account</a></h3>
        <br>
        <p style="text-align:justify"> In order to log in to your account, click on "Book" in the header or on "Go to YOUR book" under the welcome message on the home website. Fill in your login data. Press login to see your friendship book. Once you are logged in you can edit your profile and invite friends to design a friendship book page for you.</p>
        <div style="text-align:center"> <img src="../assets/loginBookly.jpg" alt="Tax" width="300" height="250" >
        </div>
        <br>
        <h3><a id="inviteFriend">Invite friend to friendship book</a></h3>
        <br>
        <v-list-item>
        <img src="../assets/help_invite_friend_2.jpg" alt="Tax" width="400" height="250">
        <img src="../assets/help_invite_friend_3.jpg" alt="Tax" width="400" height="250">
        </v-list-item>
        <br>
        <br>

        <p style="text-align:justify">Click "Invite friend" to create a code for a new friendship book page. The new code will show up on the left side. All codes are listed on the right side. Give the code to your friend. Your friend can enter the code on the home page and create a new friendship book page.</p>

        <div style="text-align:center">
        <img src="../assets/help_invite_friend_4.jpg" alt="Tax" width="400" height="250">
        </div>
        <p>All the pages can be found in your profile on the right side. </p>
            <v-list-item>
            <img src="../assets/friendshipBookCodes.jpg" alt="Tax" width="450" height="250">
            <img src="../assets/help_invite_friend_5.jpg" alt="Tax" width="300" height="250">
            </v-list-item>
            <br>
        <br>
        <h3><a id="register">Register for an account at bookly</a></h3>
        <br>
        <p>Click register and fill in your personal data. If you are younger than 13 years, please ask your parents or an adult for permission before using this site.</p>
            <v-list-item>
            <img src="../assets/help_register.jpg" alt="register1" width="400" height="350">
            <img src="../assets/help_register_2.jpg" alt="register2" width="400" height="350">
            </v-list-item>
            <br>
            </div>
    </div>
</template>

<script>
    export default {
        name: "help"
    }


</script>

<style scoped>
    .main {
        padding: 28px;
        margin-left: 15%;
        margin-right: 15%;
        font-size:large;

    }
    .card {
        padding: 12px;

    }
</style>