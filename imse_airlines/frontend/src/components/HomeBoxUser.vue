<template>
<div>

<div class="carousel slide"> 
  <b-carousel
    id="carousel-fade"
    style="text-shadow: 0px 0px 2px #000"
    fade
    indicators
    img-width="1024"
    img-height="480"
  >
    <b-carousel-slide
      img-src="https://miro.medium.com/max/6054/0*kp8rJzqHjagMj22J"
    ></b-carousel-slide>
    <b-carousel-slide
      caption="Second Slide"
      img-src="https://wallpaperaccess.com/full/254381.jpg"
    ></b-carousel-slide>
    <b-carousel-slide
      caption="Third Slide"
      img-src="https://www.teahub.io/photos/full/47-473124_airplane-wallpaper-4k.jpg"
    ></b-carousel-slide>
  </b-carousel>
  <br/>
  <div>
    
    <b-card-group deck>
      <b-card bg-variant="danger" text-variant="white" header="SEE OUR PARTNERS" class="text-center">
      <b-button @click="partners" variant="danger" class="mb-2">
         Our partners<b-icon icon="diagram3" aria-hidden="true"></b-icon>
        </b-button>
      </b-card>

  

      <b-card bg-variant="primary" text-variant="white" header="AVAILABLE FLIGHTS" class="text-center">
       <b-button  @click="flights" variant="primary" class="mb-2">
         Search flights<b-icon icon="binoculars" aria-hidden="true"></b-icon>
        </b-button>
      </b-card>

      <b-card  bg-variant="success" text-variant="white" header="MY TICKETS" class="text-center">
       <b-button @click="tickets" variant="success" class="mb-2" id="links">
         My Tickets<b-icon icon="stickies-fill" aria-hidden="true"></b-icon>
        </b-button>
      </b-card>
    </b-card-group>
  </div>
</div>
</div>

</template>

<script>
import Vue from "vue";
import axios from "axios";
import VueAxios from "vue-axios";
Vue.use(VueAxios, axios);
export default { 
  name:"HomeBoxUser",
  data() {
        return { user : "" }
  },
    
  created: function() {
    this.getToken();
  },

   methods: {
     partners(){ this.$router.replace("companiesPage"); },
     flights(){ this.$router.replace("flightsPage");},
     tickets(){ this.$router.replace("mytickets");},

     getToken(){
       axios
        .get("http://localhost:8085/users/getLogi")
        .then((response) => {
          console.log("Getting user token");
          this.user = response.data.username;
         })
        .catch(function(error) {
          console.log(error);
        })
        .then(function() {});
     }
   }
}

</script>

<style>
.tales {
  width: 100%;
}
.carousel-inner{
  width:100%;
  max-height: 550px !important;
   
}</style>