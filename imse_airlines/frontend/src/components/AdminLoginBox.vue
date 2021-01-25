<template>
  <v-main>
      <v-container fluid fill-height>
        <v-layout align-center justify-center>
          <v-flex xs12 sm8 md4>
            <v-card class="elevation-12">
              <v-toolbar dark color="primary">
                <v-toolbar-title>ADMINISTRATORS</v-toolbar-title>
                <v-spacer></v-spacer>
               
            </v-toolbar>
              <v-card-text>
                <v-form>
                  <v-text-field v-model="username" name="login" label="username" type="text"></v-text-field>
                  <v-text-field v-model="password" id="password"  name="password" label="password" type="password"></v-text-field>
                </v-form>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="primary" @click="login">Login</v-btn>
                <router-link to="/"><v-btn color="danger">exit</v-btn></router-link> 
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
    name: "adminloginBox",
    data() {
        return {
        username: "",
        password: ""
        };
    },

    methods: {
        login(e) {
        console.log("ADMIN: " + this.username);
        console.log("ADMIN_PW: " + this.password);

        this.axios
            .get("http://localhost:8000/admins/getAll")
            .then(response => {
            console.log("Admins loaded...");
            console.log("set size: " + response.data.length);
            for (let index = 0; index < response.data.length; index++) {
                if (response.data[index].username == this.username && response.data[index].password == this.password) {
                    this.$router.replace("adminFlights");
                } 
            }
            })
            .catch(function(error) {
            console.log(error);
            })
            .then(function() {});

        e.preventDefault();
        }
    }
};
</script>

<style scoped></style>
