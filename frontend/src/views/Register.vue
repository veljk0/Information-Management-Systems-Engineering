<template>
  <div class="container">
    <h3>Register to IMSE Airlines</h3>
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
        <div class="form-group">
          <input
            type="text"
            class="form-control"
            id="firstname"
            required
            v-model="user.firstName"
            aria-describedby="type in your firstname"
            placeholder="Type in your firstname ..."
          />
        </div>
        <div class="form-group">
          <input
            type="text"
            class="form-control"
            id="secondname"
            required
            v-model="user.secondName"
            aria-describedby="type in your secondname"
            placeholder="Type in your secondname ..."
          />
        </div>

        <div class="form-group">
          <input
            type="email"
            class="form-control"
            id="email"
            required
            v-model="user.email"
            aria-describedby="type in your email adress"
            placeholder="Type in your email adress..."
          />
        </div>
        <div class="form-group">
          <input
            type="password"
            class="form-control"
            id="password"
            required
            v-model="user.password"
            placeholder="Type in your password ..."
          />
          <div
            v-if="user.password.length >= 1 && user.password.length < 6"
            class="text-danger"
          >
            Password lenght should be greater then 6
          </div>
        </div>
        <div class="form-group">
          <input
            type="password"
            class="form-control"
            id="password2"
            required
            v-model="user.password2"
            placeholder="Repeat your password ..."
          />
          <div v-if="user.password != user.password2" class="text-danger">
            passwords do not match
          </div>
        </div>
        <br />

        <button @click="addUser" class="btn-lg btn-primary" method="post">
          register
        </button>
      </div>

      <div class="col-md-3">RIGHT</div>
    </div>
  </div>
</template>

<script>
import Vue from "vue";
import axios from "axios";
import VueAxios from "vue-axios";
Vue.use(VueAxios, axios);

export default {
  name: "register",
  data() {
    return {
      user: {
        firstName: null,
        secondName: null,
        email: null,
        password: "",
      },
      password2: "",
    };
  },

  methods: {
    addUser(e) {
      axios
        .get("http://localhost:8080/users/check", {
          params: {
          email: this.user.email,
          },
        })
        .then(function (response) {
         
          console.log("KARINA55555");
          if(response.data)
            alert("This user already exists, try to login");
          else
            alert("User successfully created!");
           
          console.log(response.data);
        })
        .catch(function (error) {
          console.log(error);
        })
        .then(function () {
          
        });
    
      this.axios
        .post("http://localhost:8080/users/addUser", this.user, {
          headers: {
            // remove headers
          },
        })
        .then((res) => {
          console.log(res);
        })
        .catch((err) => {
          console.log(err.response);
        });

      e.preventDefault();
      console.warn(this.user);
      console.warn("cekas");
      console.log("Firstname: " + this.user.firstName);
      console.log("Secondname: " + this.user.secondName);
      console.log("Emali is " + this.user.email);
      console.log("Password is " + this.user.password);
      console.log("Password2 is " + this.user.password2);
    },
  },
};
</script>

<style scoped>
</style>

