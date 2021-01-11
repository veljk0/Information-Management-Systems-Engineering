<template>
<div>
    <AdminNavbar/>
<h1>Customer Report</h1>
 <table class="table table-bordered">
      <thead>
        <tr>
          <th scope="col">ID</th>
          <th scope="col">FIRSTNAME </th>
          <th scope="col">SECONDNAME</th>
          <th scope="col">EMAIL</th>
          <th scope="col">TICKETS</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="user in users" :key="user.id">
          <th scope="row">{{ user.id }}</th>
          <td>{{ user.firstName }}</td>
          <td>{{ user.secondName }}</td>
          <td>{{ user.email}}</td>
         <td><button v-b-modal.tickets type="button" @click="showTickets(user.email)" class="btn btn-primary">tickets</button></td>
        </tr>
      </tbody>
    </table>
    

    <!--MODALS-->
     <div>
      <b-modal  scrollable id="tickets" title="CUSTOMER TICKETS" ok-only>
         <table class="table table-dark table-hover">
          <thead>
            <tr>
              <th scope="col">ID</th>
              <th scope="col">Price</th>
              <th scope="col">details</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="ticket in tickets" :key="ticket.ticketID">
               <td>{{ ticket.ticketID }}</td>
               <td>{{ ticket.price }} €</td>
               <td><button v-b-modal.details type="button" @click="showDetails(ticket.ticketID)" class="btn btn-success">detalis</button></td>
          </tr>
          </tbody>
        </table>  
      </b-modal>
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
import Vue from "vue";
import axios from "axios";
import VueAxios from "vue-axios";
import AdminNavbar from "../AdminNavbar"
Vue.use(VueAxios, axios);
export default {
    components:{AdminNavbar},
      data() {
    return {
      flight : {
        departureCity:"",
        arrivalCity:"",
        departureDate:"",
        arrivalDate:""
      },
     users:[],
     tickets:[]
     
    };
  },

  created: function() {
    this.loadUsers();
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

    
      showTickets(id){
        console.log("KURAC" + id)
        axios.get("http://localhost:8085/users/getUser", {params: {email: id }})
          .then((response) => { this.tickets = response.data.tickets})
          .catch(function(error) {console.log(error);})
          .then(function() {});
      },
      
      
      loadUsers(){
          this.axios
            .get("http://localhost:8085/users/getAll")
            .then(response => { this.users = response.data})
            .catch(function(error) { console.log(error); })
            .then(function() {});
      }
     
  }


}
</script>

<style>
</style>