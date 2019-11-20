<template>
  <div class="hello">
    <b-img src="bookly.jpg"></b-img>
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
      we want to maintain the old charm of friendship books and bring back the fun of designing and collecting.</h4>
    <br>
    <h5 style="margin-top: 64px">This website was developed by three students for a college project.
      <br>You can find out more about it here:<br>
      <a href="https://blog.bookly.online">Blog</a> <a href="https://gitlab.com/project_bookly/bookly">GitLab</a>
    </h5>
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
  .separator {
    width: 100%;
    height: 2px;
    background: black;
  }
</style>
