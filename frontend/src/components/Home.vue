<template>
  <div class="hello">
    <div class="wrapper">
      <div class="left">
        <img src="./../assets/bookly.jpg">
      </div>
      <div class="right">
        <h3 class="suggestion">Take a look at your Friends Entries</h3>
        <b-btn class="float-right">View Entries (!)</b-btn>
        <br>
        <h3 class="suggestion">Design your own Book</h3>
        <b-btn class="float-right">View my Book (!)</b-btn>
        <br>
        <h3 class="suggestion">Ask your friend to add a new Entry</h3>
        <b-btn class="float-right">Send Invite (!)</b-btn>
      </div>
    </div>
    <div style="clear: both"></div>
    <div class="separator" style="margin-bottom: 16px"></div>
    <h1>{{ hellomsg }}</h1>
    <h3>Do you still remember the friendship book from your childhood days?
      <br>
      Whoever had the most pages and the greatest entries was highly regarded.
      <br>
      It was handcrafted, painted, drawn and designed.</h3>
    <br>
    <h4>As books become less important with digitalization,
      <br>
      we want to maintain the old charm of friendship books and bring back the fun of designing and collecting.
    </h4>
  </div>
</template>

<script>
export default {
  name: 'hello',
  props: { hellomsg: { type: String, required: true } }
}

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  img {
    width: 100%;
  }
  button {
    width: 80%;
  }
  .separator {
    width: 100%;
    height: 2px;
    background: black;
  }
  .left {
    width: 50%;
    float: left;
  }
  .right {
    width: 50%;
    float: right;
    padding: 64px;
  }
  .suggestion {
    margin-top: 16px;
    text-align: right;
  }
</style>