<template>
  <div>
    <AppNavbar/>
    <div class="main">
    <div class="input-group">
    <input type="text" class="form-control" placeholder="Search city" v-model="city">
    <div class="input-group-append">
      <b-button @click="searchCity" v-b-modal.cityFlightsModal variant="success" class="mb-2" id="links">
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
               <td><button v-b-modal.notify type="button" @click="buy(ticket.ticketID)" class="btn btn-success">buy</button></td>
          </tr>
          </tbody>
        </table>  
      </b-modal>
    </div>


     <div>
      <b-modal  scrollable id="notify" title="AVAILABLE TICKETS" ok-only>
       <b-card bg-variant="success" text-variant="white" header="Success" class="text-center">
        <b-card-text>Ticket successfully bought</b-card-text>
      </b-card>
      </b-modal>
    </div>
    
    <!--MODAL CITY SEARCH-->
    <div>
      <b-modal size="lg" scrollable id="cityFlightsModal" title="AVAILABLE FLIGHTS FOR" ok-only>
        <h2>{{city}}</h2>
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
        <tr v-for="flight in letovi" :key="flight.flightID">
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
      /*EMAIL OF USER*/
      city:"",
      username : "",
      flightID:null,
      /*******************/
      
      /*ORIGIGI*/
      ticket:{
        ticketID:null,
        price:null,
        sold:null,
        flight:null,
        user:null
      },

      user: {
        id:null,
        firstName: null,
        secondName: null,
        email: null,
        password: "",
      },
      
      flight :{
        flightID:"",
        departureCity: "",
        departureDate: "",
        arrivalCity: "",
        arrivalDate: ""
    },
      
    /*FAKES-HELPER*/
    fakeTicket:null,
    fakeUser:null,
    fakeFlight:null,
      
      /*TABLES*/
      letovi:[],
      flights:[],
      tickets:[]
      /*******************/
    };
  },
  components:{ AppNavbar },
  created: function() {
    setTimeout(() => { this.loadFlights(); }, 100);
    setTimeout(() => { this.loadUserName(); }, 100);
    

  },
  
  methods:{

    searchCity(){
       axios.get("http://localhost:8085/flights/findFlights", { params: { city: this.city} })
          .then((response) => {
            console.log("LOADING FLIGHTS FOR " + this.city);
            this.letovi = response.data
             console.log("RESPONSE DATE " + this.letovi);

            })
          .catch(function(error) {console.log(error);})
          .then(function() {});
    },
    
    loadUserName(){
      axios.get("http://localhost:8085/users/getLogi")
        .then(response => {this.username = response.data.username})
        .catch(function(error) {console.log(error);})
        .then(function() {});
      },
    
    loadFlights(){
       axios.get("http://localhost:8085/flights/getAll")
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
/////////////////////////////////////////////////////
buy(tiketa){
        console.log(this.ticketID);
        
        axios.get("http://localhost:8085/users/getUser", { params: {email: this.username }})
        .then((response) => {
          console.log("LOADING USER: ");
          this.fakeUser = response.data;
          this.user.id = this.fakeUser.id
          this.user.firstName = this.fakeUser.firstName
          this.user.secondName = this.fakeUser.secondName
          this.user.email = this.fakeUser.email
          this.user.password = this.fakeUser.password
          console.log(this.user);

          axios.get("http://localhost:8085/flights/getFlight", { params: { flightID: this.flightID} })
          .then((response) => {
            console.log("LOADING FLIGHT: ");
            this.fakeFlight = response.data;
            this.flight.flightID= this.fakeFlight.flightID
            this.flight.departureCity = this.fakeFlight.departureCity
            this.flight.departureDate = this.fakeFlight.departureDate
            this.flight.arrivalCity = this.fakeFlight.arrivalCity
            this.flight.arrivalDate = this.fakeFlight.arrivalDate
            console.log(this.flight);
           
          })
          .catch(function(error) {console.log(error);})
          .then(function() {});
          
          axios.get("http://localhost:8085/tickets/getTicket", { params: { ticketID: tiketa } })
          .then((response) => {
            console.log("LOADING TICKET: ");
            this.fakeTicket = response.data;
            this.ticket.ticketID = this.fakeTicket.ticketID
            this.ticket.price = this.fakeTicket.price
            this.ticket.sold = true
            this.ticket.flight = this.flight
            this.ticket.user = this.user
            console.log(this.ticket);
          })
          .catch(function(error) {console.log(error);})
          .then(function() {});
          
        console.log("Final Product");
        console.log(this.ticket);
        })
        .catch(function(error) {
          console.log(error);
        })
        .then(function() {});

        setTimeout(() => { this.doIt() }, 5000);
        
      },


      doIt(){
        this.axios.post("http://localhost:8085/tickets/updateTicket", this.ticket, {headers: {}})
                    .then(res => { console.log(res);})
                        .catch(err => { console.log(err.response);});

        this.axios.get("http://localhost:8085/flights/updateSeat", { params: { flightID: this.flightID }})
        .then((response) => {console.log(response.data)})
        .catch(function(error) { console.log(error); })
        .then(function() {});
        window.location.reload();
        this.showNotification();
      },
      
      ////////////////////////////////////////////////////////////////////////////////////////
    

    showNotification(){
      this.$modal.show('notify')
      this.$modal.hide('tickets')
      

    },
    
    showTickets(id){
      this.flightID = id
      console.log("FLIGHT: " + this.flightID)
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