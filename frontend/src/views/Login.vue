<template>
  <div class="container">
    <h2>Login to IMSE Airlines</h2>
    <img
      src="/img/icons/apple-touch-icon-60x60.png"
      class="img-fluid"
      alt="Responsive image"
    />
    <br />
    <br />
    <div class="row">
      <div class="col-md-3">LEFT</div>
      <div class="col-md-6">
        <form>
          <div class="form-group">
            <input
              type="email"
              class="form-control"
              id="email"
              required
              v-model="email"
              aria-describedby="emailHelp"
              placeholder="your email adress"
            />
          </div>
          <div class="form-group">
            <input
              type="password"
              class="form-control"
              id="password"
              required
              v-model="password"
              placeholder="your password"
            />
           
          </div>

          <button @click="login" class="btn btn-primary" method="post">login</button>
        </form>
      </div>
      <div class="col-md-3">RIGHT</div>
    </div>
  </div>
</template>

<script>
export default {
  name: "login",
  data() {
    return {
      email: "",
      password: "",
    };
  },

  methods: {
    login: function () {
      console.log("Emali is" + this.email);
      console.log("Password is" + this.password);
    },
  },
};
</script>

<style scoped>
</style>

