<template>
   <div>
     <DefaultNavbar/>
      <br/>
      <br/>
      <br/>
      <br/>
      <router-link to="/admin"><button v-b-modal.tickets type="button"  class="btn btn-primary">ADMIN PANEL</button></router-link>
      <button v-b-modal.tickets type="button" @click="fill" class="btn btn-primary">FILL DATABASE</button>
      <button v-b-modal.tickets type="button" @click="migrate" class="btn btn-primary">MYSQL TO MONGODB</button>
      <br/>
      <br/>
      <br/>
      <br/>
  <div>
  <b-card-group deck>
    <b-card
      header="IMSE WS20"
      header-tag="header"
      footer="IMSE AIRLINES APPLICATION"
      footer-tag="footer"
      title="Veljko Radunovic"
    >
      <b-card-text> email: a01528243@unet.univie.ac.at</b-card-text>
      
    </b-card>

   <b-card
      header="IMSE WS20"
      header-tag="header"
      footer="IMSE AIRLINES APPLICATION"
      footer-tag="footer"
      title="Jelena Mikic"
    >
     <b-card-text> email: a1329687@unet.univie.ac.at</b-card-text>
    
    </b-card>
  </b-card-group>
</div>


   
  </div>
</template>

<script>
import DefaultNavbar from "../components/DefaultNavbar"
import Vue from "vue";
import axios from "axios";
import VueAxios from "vue-axios";

Vue.use(VueAxios, axios);
export default {
  components:{DefaultNavbar},
  methods:{
  
  migrate(){
    this.axios.get("http://localhost:8000/database/migrate")
            .then(response => { console.log(response)})
            .catch(function(error) { console.log(error); })
            .then(function() {});
  },

    fill(){
       this.axios.get("http://localhost:8000/database/fill")
            .then(response => { console.log(response)})
            .catch(function(error) { console.log(error); })
            .then(function() {});
    }
  }


}
</script>

<style>

</style>