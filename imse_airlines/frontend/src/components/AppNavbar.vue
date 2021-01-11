<template>
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <router-link class="navbar-brand" to="/userpage">IMSE AIRLINES </router-link>
    <img alt="Vue logo" src="../assets/logo32.png" />
    <button
      class="navbar-toggler"
      type="button"
      data-toggle="collapse"
      data-target="#navbarSupportedContent"
      aria-controls="navbarSupportedContent"
      aria-expanded="false"
      aria-label="Toggle navigation"
    >
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav mr-auto">
        <li class="nav-item">
          <router-link class="nav-link" to="/userpage">Home</router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" to="/flightsPage">Flights</router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" to="/mytickets">My Tickets</router-link>
        </li>
         <li class="nav-item">
          <router-link class="nav-link" to="/companiesPage">Companies</router-link>
        </li>
        
      </ul>
      <form class="form-inline my-2 my-lg-0">
        <h5>welcome {{user}}</h5>
        <router-link class="nav-link" to="/"><button type="button"  class="btn btn-danger" @click="logout">Logout</button></router-link>
        
        
      </form>
    </div>
  </nav>
</template>

<script>
import Vue from "vue";
import axios from "axios";
import VueAxios from "vue-axios";

Vue.use(VueAxios, axios);
export default {
  name: "AppNavbar",
  data() {
    return {
      user:""
    }
  },
  created: function() {
    this.loadUser();
  },

  methods: {

    loadUser(){
      axios.get("http://localhost:8085/users/getLogi")
        .then(response => {
          console.log("GET_USER");
          console.log(response.data);
          this.user = response.data.username;
      })
        .catch(function(error) { console.log(error);})
        .then(function() {});

      
    },


    logout(){
        axios.get("http://localhost:8085/users/logout")
        .then(response => { console.log(response.data);})
        .catch(function(error) { console.log(error); })
        .then(function() {});
      }
  },
};
</script>
