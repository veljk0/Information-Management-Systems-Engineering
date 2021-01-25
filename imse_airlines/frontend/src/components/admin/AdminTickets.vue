<template>
<div>
    <AdminNavbar/>
    <div class="container mt-4">
     <h2>Search company flights</h2>
    <b-form-group label-cols="4" label-cols-lg="2" label-size="lg" label="COMPANY NAME" label-for="input-lg">
    <b-form-input v-model="companyName" id="input-lg" size="lg"></b-form-input>
    </b-form-group> 
    <div>
      <button @click="cFlights(companyName)" v-b-modal.modal-scrollable2 type="button" class="btn btn-primary">Search company flights</button>
      <button @click="mongoSold()" v-b-modal.modal-scrollable3 type="button" class="btn btn-primary">See company sold tickets</button>
      <button @click="mongoProfit()" v-b-modal.modal-scrollable4  type="button" class="btn btn-primary">See company profit</button>
      <button type="button" @click="ticketCard" class="btn btn-primary">create new ticket</button>
    </div>
    <h2>Current flights</h2>
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
          <td><button v-b-modal.modal-scrollable1 type="button" @click="showTickets(flight.flightID)" class="btn btn-primary">tickets</button></td>
        </tr>
      </tbody>
    </table>

    <!--MODAL-->
    <div>
      <b-modal id="modal-scrollable1" scrollable title="Tickets">
        <table class="table table-dark table-hover">
          <thead>
            <tr>
              <th scope="col">ID</th>
              <th scope="col">Price</th>
              <th scope="col">Sold</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="ticket in tickets" :key="ticket.ticketID">
               <td>{{ ticket.ticketID }}</td>
               <td>{{ ticket.price }} €</td>
               <td>{{ ticket.sold }}</td>
             
            </tr>
          </tbody>
        </table>  
    </b-modal>
    </div>
    
    <!--MODAL-->
    <div>
      <b-modal  scrollable id="modal-scrollable2" title="Flights of company" ok-only size="lg">
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
        <tr v-for="flight in flightsOfCompany" :key="flight.flightID">
          <th scope="row">{{ flight.flightID }}</th>
          <td>{{ flight.departureCity }}</td>
          <td>{{ flight.arrivalCity }}</td>
          <td>{{ flight.departureDate }}</td>
          <td>{{ flight.arrivalDate }}</td>
          <td>{{ flight.placeLeft }}</td>
          <td><button v-b-modal.modal-scrollable1 type="button" @click="showTickets(flight.flightID)" class="btn btn-primary">tickets</button></td>
        </tr>
      </tbody>
    </table>
      </b-modal>
    </div>
    
    <!--MODAL-->
    <div>
      <b-modal  scrollable id="modal-scrollable3" title="Sold tickets" ok-only>
         <table class="table table-dark table-hover">
          <thead>
            <tr>
              <th scope="col">ID</th>
              <th scope="col">Price</th>
              <th scope="col">Sold</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="ticket in companyTickets" :key="ticket.ticketID">
               <td>{{ ticket.ticketID }}</td>
               <td>{{ ticket.price }} €</td>
               <td>{{ ticket.sold }}</td>
             
            </tr>
          </tbody>
        </table>  
      </b-modal>
    </div>

    <!--MODAL-->
    <div>
      <b-modal  scrollable id="modal-scrollable4" title="PROFIT" ok-only>
        <h1>{{sum}} € from sold tickets</h1>
      </b-modal>
    </div>

    <modal name="modal-createTicket" :adaptive="true" :height="400" :width="400">
      <div class="px-10 py-8">
        <h2 class="mb-4 uppercase tracking-wide text-xl">create ticket</h2><br/>
        <div class="form-group mb-4">
            <v-form>
                <v-text-field v-model="flightId"   name="name" label="flight id ...." type="text"></v-text-field>
                <v-text-field v-model="price"  name="name" label="price ...." type="text"></v-text-field>
                <br/><br/>
                    
            </v-form>
            <br/><br/>
            <button type="button" @click="createTicket" class="btn btn-success">create ticket</button>
        </div>
        </div>
    </modal>
    
    
   
    
    
    </div>
 </div>  

</template>

<script>
import Vue from "vue";
import axios from "axios";
import VueAxios from "vue-axios";
import AdminNavbar from "../AdminNavbar"
Vue.use(VueAxios, axios);
import VModal from 'vue-js-modal'
Vue.use(VModal, { dialog: true })

export default {
  components:{AdminNavbar},
    
  data() {
    return {
      fakeFlight : null,
    fakeFlight2 : {
      price:"",
      flight:null
    },
    
    fakeFlight3 :{
        flightID:"",
        departureCity: "",
        departureDate: "",
        arrivalCity: "",
        arrivalDate: ""
    },
    
    flightId :"",
    price:"",
        companyName : "",

        companyTickets:[],
        searchCompany:null,
        flightsOfCompany: [],
        sum:"",
        
        flights: [],
        tickets:[],
    };
  },

  created: function() {
    this.loadFlights();

  },

  
  methods: {

    createTicket(){
        console.log(this.flightId);
        console.log(this.price);
        axios
        .get("http://localhost:8000/flights/getFlight", {
          params: {
            flightID: this.flightId
          }
        })
        .then((response) => {
          console.log("FLIGA");
          console.log(response.data);
          this.fakeFlight = response.data;
          this.fakeFlight3.flightID = this.fakeFlight.flightID
          this.fakeFlight3.departureCity = this.fakeFlight.departureCity
          this.fakeFlight3.departureDate = this.fakeFlight.departureDate
          this.fakeFlight3.arrivalCity = this.fakeFlight.arrivalCity
          this.fakeFlight3.arrivalDate = this.fakeFlight.arrivalDate
          
          this.fakeFlight2.price = this.price;
          this.fakeFlight2.flight = this.fakeFlight3;
          console.log("Final Product");
          console.log(this.fakeFlight2);
           this.axios.post("http://localhost:8000/tickets/addTicket", this.fakeFlight2, {headers: {}})
                    .then(res => { console.log(res);})
                        .catch(err => { console.log(err.response);});

         })
        .catch(function(error) {
          console.log(error);
        })
        .then(function() {});
        window.location.reload();
    },
    

    cSum(pacov){
       console.log(pacov)
        axios
        .get("http://localhost:8000/companies/getCompany", {
          params: {
            name: pacov
          }
        })
        .then((response) => { 
          console.log("SUM LOADING");
          this.searchCompany = response.data 
          console.log(this.searchCompany);
          axios.get("http://localhost:8000/flights/getCompanyWin", {
          params: {
            companyId: this.searchCompany.companyId
          }
        })
        .then((response) => { 
          console.log("SUMIS");
          this.sum = response.data 
          console.log("sum final" + this.sum);
          
        })
        .catch(function(error) {
          console.log(error);
        })
        .then(function() {});
        })
        .catch(function(error) {
          console.log(error);
        })
        .then(function() {});


    },

    ////////////////////////////////////////////////////////////////////////////
    mongoSold(){
       this.axios
            .get("http://localhost:8000/flights/mongoSold/" + this.companyName)
            .then(response => { 
              console.log("SOLD TICKETS ARE MONGO FRUIT: "+ response.data)
              this.companyTickets = response.data 
            })
            .catch(function(error) { console.log(error); })
            .then(function() {});

    },

    mongoProfit(){
       this.axios
            .get("http://localhost:8000/flights/mongoProfit/" + this.companyName)
            .then(response => { 
              console.log("OUR PROFIT SELLING FRUIT: "+ response.data)
              this.sum = response.data 
            })
            .catch(function(error) { console.log(error); })
            .then(function() {});

    },
    ////////////////////////////////////////////////////////////////////////////

    cTickets(pacov){
      console.log(pacov)
        axios
        .get("http://localhost:8000/companies/getCompany", {
          params: {
            name: pacov
          }
        })
        .then((response) => { 
          console.log("SOLD TICKETS LOADING");
          this.searchCompany = response.data 
          console.log(this.searchCompany);
          axios.get("http://localhost:8000/flights/getCompanySoldTickets", {
          params: {
            companyId: this.searchCompany.companyId
          }
        })
        .then((response) => { 
          console.log("SOLD ARE");
          this.companyTickets = response.data 
          console.log(this.companyTickets.length);
          
        })
        .catch(function(error) {
          console.log(error);
        })
        .then(function() {});
        })
        .catch(function(error) {
          console.log(error);
        })
        .then(function() {});

    },

    cFlights(pacov){
        console.log(pacov)
        axios
        .get("http://localhost:8000/companies/getFlights/" + this.companyName)
        .then((response) => { 
          console.log("PACOV");
          console.log(response.data.length);
          this.flightsOfCompany = response.data
        })
        .catch(function(error) {
          console.log(error);
        })
        .then(function() {});

    },

    showTickets(id){
      console.log(id)
      axios.get("http://localhost:8000/tickets/getFlightTickets", { params: { flightId: id } })
        .then((response) => {
         console.log(response.data);
         this.tickets = response.data;  
      })
        .catch(function(error) {
          console.log(error);
        })
        .then(function() {});
        
    },
    
    
    loadFlights() {
      axios
        .get("http://localhost:8000/flights/getAll")
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
    ticketCard(){ this.$modal.show('modal-createTicket')},
  }
  
};
</script>

<style>
</style>