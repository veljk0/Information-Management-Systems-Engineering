<template>
  <div>
    <AppNavbar/>
    <div>
      <table class="table table-dark table-hover">
          <thead>
            <tr>
              <th scope="col">ID</th>
              <th scope="col">Price</th>
              <th scope="col">Details</th>
          </tr>
          </thead>
          <tbody>
            <tr v-for="ticket in tickets" :key="ticket.ticketID">
               <td>{{ ticket.ticketID }}</td>
               <td>{{ ticket.price }} €</td>
               <td><button v-b-modal.details type="button" @click="showDetails(ticket.ticketID)" class="btn btn-success">details</button></td>
            </tr>
          </tbody>
        </table>  
    </div>
    
    <div>
      <b-modal  scrollable id="details" title="DETAIL ABOUT FLIGHT" ok-only>
        <b-card bg-variant="primary" text-variant="white" header="FLIGHT" class="text-center">
         <b-card-text>from: {{flight.departureCity}}</b-card-text>
         <b-card-text>to: {{flight.arrivalCity}}</b-card-text>
         <b-card-text>departure date: {{flight.departureDate}}</b-card-text>
         <b-card-text>arrival date: {{flight.arrivalDate}}</b-card-text>
       
      </b-card>
      </b-modal>
    </div>
  </div>
</template>

<script>
import AppNavbar from "../components/AppNavbar"
import Vue from "vue";
import axios from "axios";
import VueAxios from "vue-axios";
Vue.use(VueAxios, axios);

export default {
  name: "myticketsPAge",
  data() {
    return {
      flight : {
        departureCity:"",
        arrivalCity:"",
        departureDate:"",
        arrivalDate:""
      },
      username:"",
      userID:null,
      tickets:[]
    };
  },
  components:{AppNavbar},
  created: function() {
  this.loadUser();
 
  },
  
  methods:{

    showDetails(id){
       axios.get("http://localhost:8085/flights/ticketFlight", {params: {ticketID: id }})
        .then((response) => {
          console.log("loading details" + response.data)
          this.flight.departureCity = response.data.departureCity
           this.flight.arrivalCity = response.data.arrivalCity
            this.flight.departureDate = response.data.departureDate
             this.flight.arrivalDate = response.data.arrivalDate

        })
        .catch(function(error) {console.log(error);})
        .then(function() {});
    },
      
      
      loadUser(){
      axios.get("http://localhost:8085/users/getLogi")
        .then(response => {
          this.username = response.data.username
          axios.get("http://localhost:8085/users/getUser", {params: {email: this.username }})
          .then((response) => { this.tickets = response.data.tickets})
          .catch(function(error) {console.log(error);})
          .then(function() {});
        })
        .catch(function(error) {console.log(error);})
        .then(function() {});
      },

  
  }
}
</script>

<style>
</style>