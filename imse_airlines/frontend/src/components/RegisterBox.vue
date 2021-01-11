<template>
  <v-main>
      <v-container fluid fill-height>
        <v-layout align-center justify-center>
          <v-flex xs12 sm8 md4>
            <v-card class="elevation-12">
              <v-toolbar dark color="primary">
                <v-toolbar-title>Create your account to fly with us</v-toolbar-title>
                <v-spacer></v-spacer>
                <img alt="Vue logo" src="../assets/logo32.png" />
               
            </v-toolbar>
              <v-card-text>
                <v-form>
                  <v-text-field v-model="user.firstName" name="firstname" label="type in your firstname .." type="text"></v-text-field>
                  <v-text-field v-model="user.secondName" name="secondname" label="type in your secondname .." type="text"></v-text-field>
                  <v-text-field v-model="user.email" name="email" label="type in your email adress .." type="text"></v-text-field>
                  <v-text-field v-model="user.password" id="password"  name="password" label="type in your password .." type="password"></v-text-field>
                  <v-text-field v-model="repeatPassword" id="repeatPassword"  name="repeatPassword" label="repeat in your password .." type="password"></v-text-field>
                </v-form>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="primary" @click="addUser">Register</v-btn>
                <v-spacer></v-spacer>
              </v-card-actions>
            </v-card>
          </v-flex>
        </v-layout>
      </v-container>
    </v-main>
</template>

<script>

import Vue from "vue";
import axios from "axios";
import VueAxios from "vue-axios";

Vue.use(VueAxios, axios);

export default {
  name: "RegisterBox",
  components: {},

  data() {
    return {
      user: {
        firstName: null,
        secondName: null,
        email: null,
        password: ""
      },
      repeatPassword: ""
    }
  },

  methods: {
    addUser(e) {
      axios.get("http://localhost:8085/users/check", { params: { email: this.user.email } })
        .then((response) => {
          console.log("DOES USER ALREADY EXISTS IN DB?");
          if (response.data == true) alert("This user already exists, try to login");
          else {
              alert("User successfully created!");
              console.log(response.data);
                this.axios.post("http://localhost:8085/users/addUser", this.user, {headers: {}})
                    .then(res => { console.log(res);})
                        .catch(err => { console.log(err.response);});
            }
        })
        .catch(function(error) {
          console.log(error);
        })
        .then(function() {});

     

      e.preventDefault();
      console.warn(this.user);
      console.warn("USER DETAILS");
      console.log("Firstname: " + this.user.firstName);
      console.log("Secondname: " + this.user.secondName);
      console.log("Emali is " + this.user.email);
      console.log("Password is " + this.user.password);
      console.log("Password2 is " + this.repeatPassword);
    }
  }
};
</script>

<style scoped></style>
