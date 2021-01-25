<template>
<div>
    <AdminNavbar/>
<div class="container mt-4">
    <h1>Creator options</h1>
    <div class="container mt-3">
        <button type="button" @click="flightCard" class="btn btn-success">add new fllight</button>
        <button type="button" @click="ticketCard" class="btn btn-success">create new ticket</button>
        
  </div>
  <div class="container mt-5"> <button type="button" @click="refresh" class="btn btn-primary">Refresh table</button></div>
    <br/>
    <br/>
   
    <table class="table table-bordered">
      <thead>
        <tr>
          <th scope="col">FlightID</th>
          <th scope="col">Departure City</th>
          <th scope="col">Arrival City</th>
          <th scope="col">Departure Date</th>
          <th scope="col">Arrival Date</th>
          <th scope="col">Tickets left</th>
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
        </tr>
      </tbody>
    </table>

     <!--MODALS-->

      <modal name="modal-addNewFlight" :adaptive="true" :height="650" :width="650">
      <div class="px-10 py-8">
        <h2 class="mb-4 uppercase tracking-wide text-xl">Create flight</h2><br/>
        <div class="form-group mb-4">
            <v-form>
                <v-text-field  v-model="companyName" name="name" label="company name ..." type="text"></v-text-field>
                <v-text-field  v-model="flight.departureCity" name="name" label="departure city ...." type="text"></v-text-field>
                <v-text-field  v-model="flight.arrivalCity" name="name" label="arrival city ...." type="text"></v-text-field>
                <br/><br/>
                    <v-form>
                        <label for="departure date">departure date :</label>
                        <input type="date" v-model="flight.departureDate" name="departure date ">
                    </v-form>
                    <br/>
                    <v-form>    
                        <label for="arrival date">arrival date :</label>
                        <input type="date" v-model="flight.arrivalDate" name="arrival date ">
                    </v-form>
            </v-form>
            <br/><br/>
            <button type="button" @click="createMongoFlight" class="btn btn-success">create flight</button>
        </div>
        </div>
    </modal>

     <modal name="modal-addNewTicket" :adaptive="true" :height="400" :width="400">
      <div class="px-10 py-8">
        <h2 class="mb-4 uppercase tracking-wide text-xl">create ticket</h2><br/>
        <div class="form-group mb-4">
            <v-form>
                <v-text-field v-model="flightId"   name="name" label="flight id ...." type="text"></v-text-field>
                <v-text-field v-model="price"  name="name" label="price ...." type="text"></v-text-field>
                <br/><br/>
                    
            </v-form>
            <br/><br/>
            <button type="button" @click="mongoTicket" class="btn btn-success">create ticket</button>
        </div>
        </div>
    </modal>

  </div>
</div>

</template>

<script>
import AdminNavbar from "../AdminNavbar"
import Vue from "vue";
import axios from "axios";
import VueAxios from "vue-axios";
Vue.use(VueAxios, axios);

export default {
components:{AdminNavbar},
  name: "dashboard",
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
      
      fakeCompany:null,
      fakeCompany2:{
          companyId:"",
          companyName:""
      },
      
      companyName:"",
      
      flight:{
        departureCity:"",
        arrivalCity:"",
        departureDate:"",
        arrivalDate:"",
        company : null  
    },
      
      flights: [],
  };
  },

  created: function() {
    this.loadFlights();
  },

  methods: {

    refresh(){ window.location.reload() },

    createMongoFlight(){
      this.axios
            .get("http://localhost:8000/flights/addNewFlight/" + this.companyName + "/" + this.flight.departureCity + "/" + 
            this.flight.departureDate + "/"+this.flight.arrivalCity +"/"+this.flight.arrivalDate)
            .then(response => { console.log("FLIGHT CREATED: "+ response.data) })
            .catch(function(error) { console.log(error); })
            .then(function() {});
      
    },

    mongoTicket(){
      this.axios
            .get("http://localhost:8000/tickets/addNewTicket/" + this.flightId + "/" + this.price)
            .then(response => { console.log("TICKET CREATED: "+ response.data) })
            .catch(function(error) { console.log(error); })
            .then(function() {});
    },

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
           this.axios.post("http://localhost:8000/tickets/addTicket" + this.flightId, this.fakeFlight2, {headers: {}})
                    .then(res => { console.log(res);})
                        .catch(err => { console.log(err.response);});

         })
        .catch(function(error) {
          console.log(error);
        })
        .then(function() {});
        setTimeout(() => {  console.log("CREATING BREE!"); }, 10000);
        this.$modal.hide('modal-addNewTicket')
       
    },


    

    createFlight(){
         console.log(this.flight);
         console.log(this.companyName);
         axios.get("http://localhost:8000/companies/getCompany", {
          params: {
            name: this.companyName
          }
        })
        .then((response) => {
             console.log("getting company");
             console.log(response.data);
            this.fakeCompany = response.data
            console.log("Company for flight");
            console.log(this.fakeCompany);
            console.log("Final Product");
            this.fakeCompany2.companyId = this.fakeCompany.companyId;
            this.fakeCompany2.companyName = this.fakeCompany.companyName;
            console.log(this.fakeCompany2);
            this.flight.company = this.fakeCompany2
            console.log("Double CHECK");
            console.log(this.flight);
            this.axios.post("http://localhost:8000/flights/addFlight", this.flight, {headers: {}})
                    .then(res => { console.log(res);})
                        .catch(err => { console.log(err.response);});
            
            })
                .catch(function(error) { console.log(error); })
                    .then(function() {});
                    this.$modal.hide('modal-addNewFlight')
                    

    },
    
    flightCard(){ this.$modal.show('modal-addNewFlight')},
    ticketCard(){ this.$modal.show('modal-addNewTicket')},

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
    }
  }
};
</script>
