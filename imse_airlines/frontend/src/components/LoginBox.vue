<template>
  <v-main>
      <v-container fluid fill-height>
        <v-layout align-center justify-center>
          <v-flex xs12 sm8 md4>
            <v-card class="elevation-12">
              <v-toolbar dark color="primary">
                <v-toolbar-title>IMSE AIRLINES</v-toolbar-title>
                <v-spacer></v-spacer>
               
            </v-toolbar>
              <v-card-text>
                <v-form>
                  <v-text-field v-model="username" name="login" label="type in your email adress .." type="text"></v-text-field>
                  <v-text-field v-model="password" id="password"  name="password" label="type in your password .." type="password"></v-text-field>
                </v-form>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="primary" @click="login">Login</v-btn>
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
    name: "loginBox",
    data() {
        return {
        username: "",
        password: ""
        };
    },

    methods: {
        login(e) {
        console.log("EMAIL BRE: " + this.username);

        this.axios
            .get("http://localhost:8085/users/getAll")
            .then(response => {
            console.log("ListOfUsers loaded...");
            console.log("KURCINELA819 " + response.data.length);
            for (let index = 0; index < response.data.length; index++) {
                if (
                response.data[index].email == this.username &&
                response.data[index].password == this.password
                ) {
                this.$router.replace("userpage");
                axios.get("http://localhost:8085/users/login", {
                    params: { username: this.username }
                    })
                    .then(function(response) {
                    console.log(response.data);
                    })
                    .catch(function(error) {
                    console.log(error);
                    })
                    .then(function() {});
                } //kurac
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
