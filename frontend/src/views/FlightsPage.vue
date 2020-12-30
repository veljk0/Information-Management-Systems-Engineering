<template>
  <div>
    <AppNavbar/>
    <div class="main">
    <div class="input-group">
    <input type="text" class="form-control" placeholder="Search city">
    <div class="input-group-append">
      <b-button  variant="success" class="mb-2" id="links">
      <b-icon icon="search" aria-hidden="true"></b-icon>
      </b-button>
    </div>
  </div>
  </div>
    <table class="table table-bordered">
      <thead>
        <tr>
          <th scope="col">FlightID</th>
          <th scope="col">Departure City</th>
          <th scope="col">Arrival City</th>
          <th scope="col">Departure Date</th>
          <th scope="col">Arrival Date</th>
          <th scope="col">Tickets left</th>
          <th scope="col">See tickets</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="flight in flights" :key="flight.flightID">
          <th scope="row">{{ flight.flightID }}</th>
          <td>{{ flight.departureCity }}</td>
          <td>{{ flight.arrivalCity }}</td>
          <td>{{ flight.departureDate }}</td>
          <td>{{ flight.arrivalDate }}</td>
          <td>{{ flight.placeLeft }}</td>
          <td><button v-b-modal.tickets type="button" @click="showTickets(flight.flightID)" class="btn btn-primary">tickets</button></td>
        </tr>
      </tbody>
    </table>

    <!--MODAL TICKET BUY-->
     <div>
      <b-modal  scrollable id="tickets" title="AVAILABLE TICKETS" ok-only>
         <table class="table table-dark table-hover">
          <thead>
            <tr>
              <th scope="col">ID</th>
              <th scope="col">Price</th>
              <th scope="col">buy ticket</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="ticket in tickets" :key="ticket.ticketID">
               <td>{{ ticket.ticketID }}</td>
               <td>{{ ticket.price }} €</td>
               <td><button v-b-modal.modal-scrollable1 type="button" @click="buy(ticket.ticketID)" class="btn btn-success">buy</button></td>
          </tr>
          </tbody>
        </table>  
      </b-modal>
    </div>
  </div>
</template>

<script>
import Vue from "vue";
import axios from "axios";
import VueAxios from "vue-axios";
Vue.use(VueAxios, axios);
import AppNavbar from "../components/AppNavbar"

export default {
  name: "flightssspage",
  data() {
    return {
      user : "",
      flights:[],
      tickets:[]
    };
  },
  components:{ AppNavbar },
  created: function() { this.loadFlights(); },
  methods:{
    loadFlights(){
       axios
        .get("http://localhost:8085/flights/getAll")
        .then(response => {
          console.log("GET_FLIGHTS");
          console.log(response.data);
          this.flights = response.data;
      })
        .catch(function(error) {
          console.log(error);
        })
        .then(function() {});
    },

    showTickets(id){
      console.log(id)
      axios.get("http://localhost:8085/tickets/getFlightAvailableTickets", { params: { flightId: id } })
        .then((response) => {
         console.log(response.data);
         this.tickets = response.data;  
      })
        .catch(function(error) {
          console.log(error);
        })
        .then(function() {});
        
    },

    buy(tiketID){
      console.log(tiketID)
      this.axios.get("http://localhost:8085/users/getLogi")
      .then(response => {
        this.user = response.data.username
        console.log("KURCINAAAAA " +this.user)
      })
      .catch(function(error) {console.log(error);})
      .then(function() {});
     // getUser

    }

  }
}
  
</script>

<style>
/* Styles for wrapping the search box */

.main {
    width: 50%;
    margin: 50px auto;
}

/* Bootstrap 4 text input with search icon */

.has-search .form-control {
    padding-left: 2.375rem;
}

.has-search .form-control-feedback {
    position: absolute;
    z-index: 2;
    display: block;
    width: 2.375rem;
    height: 2.375rem;
    line-height: 2.375rem;
    text-align: center;
    pointer-events: none;
    color: #aaa;
}
</style>