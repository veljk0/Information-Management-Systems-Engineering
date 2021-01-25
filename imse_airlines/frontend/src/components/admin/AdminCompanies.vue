<template>
<div>
<AdminNavbar/>
<div class="container mt-4">
<h2>Current companies</h2>
    <table class="table table-bordered">
      <tbody>
        <tr v-for="company in companies" :key="company.companyId" >
          <td> {{ company.companyName }}</td>
        </tr>
      </tbody>
    </table>
  </div>
  <button type="button" @click="showAdd" class="btn btn-success">add company</button>
  <button type="button" @click="showDelete" class="btn btn-danger">delete company</button>
  <br/><br/><br/><br/>
  
  
  <h1>Company Report</h1>
  <button  v-b-modal.report type="button" class="btn btn-primary" @click="report">Generate Report</button>
  <b-form-group label-cols="4" label-cols-lg="2" label-size="md" label="Report for company: " label-for="input-lg">
  <b-form-input v-model="companyname" id="input-md" size="sm"></b-form-input>
  </b-form-group> 
  
  <b-card-group deck>
    <b-card
      footer="IMSE AIRLINES"
      footer-tag="footer"
      title="FROM DATE"
    >
      <div>
       <b-form-datepicker form="yyyy/mm/dd" id="dateFrom" v-model="dateFrom" class="mb-2"></b-form-datepicker>
     </div>
    </b-card>

    <b-card title="TO DATE" header-tag="header" footer-tag="footer"  footer="FLY WITH US">
    <div>
      <b-form-datepicker form="yyyy/mm/dd" id="dateTo" v-model="dateTo" class="mb-2"></b-form-datepicker>
     </div>
    </b-card>
  </b-card-group>




  
  <!-- MODALS -->
  
  <!-- REPORT -->
   <b-modal  scrollable id="report" title="Flights of company" ok-only size="lg">
     <h2>time period between {{dateFrom}} - {{dateTo}}</h2>
     
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
        <tr v-for="flight in myflights" :key="flight.flightID">
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
    <button  type="button"  v-b-modal.soldtickets @click="soldTickets" class="btn btn-primary">See all sold ticket in this period</button>
    <button  type="button"  v-b-modal.profit @click="profit" class="btn btn-primary">See all profit from selling tickets</button>
      </b-modal>
      
      
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


  <modal name="modal-addCompany" :adaptive="true" :height="440" :width="400">
      <div class="px-10 py-8">
        <h2 class="mb-4 uppercase tracking-wide text-xl">Add Company</h2><br/>
        <div class="form-group mb-4">
            <v-form>
                <v-text-field v-model="company.companyId" name="name" label="company id ...." type="text"></v-text-field>
                <v-text-field v-model="company.companyName" name="name" label="company name ...." type="text"></v-text-field>
            </v-form>
        </div>
        <button type="button" @click="addCompany" class="btn btn-success">add company</button>
      </div>
    </modal>
    <modal name="modal-deleteCompany" :adaptive="true" :height="440" :width="400">
      <div class="px-10 py-8">
        <h2 class="mb-4 uppercase tracking-wide text-xl">remove company</h2><br/>
        <div class="form-group mb-4">
            <v-form>
                <v-text-field v-model="company.companyName" name="name" label="company name ...." type="text"></v-text-field>
            </v-form>
        </div>
        <button type="button" @click="deleteCompany" class="btn btn-success">delete</button>
      </div>
    </modal>
    
    <!--sold tickets-->
    <div>
      <b-modal  scrollable id="soldtickets" title="Sold tickets" ok-only>
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

     <!--profit-->
     <div>
      <b-modal  scrollable id="profit" title="PROFIT" ok-only>
        <h1>{{sum}} € from sold tickets</h1>
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
  name: "dashboard",
  data() {
    return {
      sum : "",
      companyTickets:[],
      tickets:[],
      //DATEPICKERS VAR
      dateFrom : "",
      dateTo: ""  ,
      companyname: "",
      mycompany : null,
      myflights : [],

      //COMPANY VAR
      company:{
        companyId:2,
        companyName :""
      },
      
      companies: [],
  };
  },

  created: function() {
    this.loadCompanies();
  },

  methods: {

    soldTickets(){
      this.sum = 0
      this.companyTickets = [];
      var i
      var j
      for (i = 0; i < this.myflights.length; i++) {
       for (j = 0; j < this.myflights[i].tickets.length; j++) {
         if(this.myflights[i].tickets[j].sold == true) this.companyTickets.push(this.myflights[i].tickets[j])
       }
      }
        
    },

    profit(){
    this.sum = 0
    this.companyTickets = [];
      var i
      var j
      for (i = 0; i < this.myflights.length; i++) {
       for (j = 0; j < this.myflights[i].tickets.length; j++) {
         if(this.myflights[i].tickets[j].sold == true) this.sum+= this.myflights[i].tickets[j].price
       }
      }
      
    },

    report(){
      console.log("DATE FROM: " + this.dateFrom)
      console.log("DATE TO: " + this.dateTo)
      console.log("FOR COMPANY: " + this.companyname)
      axios.get("http://localhost:8000/companies/getCompany", { params: { name: this.companyname }})
      .then((response) => { 
          console.log(response.data)
          this.mycompany = response.data
          console.log("babala: " + this.mycompany.companyId)
          axios.get("http://localhost:8000/flights/getFlightsDate", {
            params: { companyId: this.mycompany.companyId, from : this.dateFrom, to: this.dateTo }
          })
          .then((response) => { 
            this.myflights = JSON.parse(JSON.stringify(response.data))
            console.log("KURAC" + this.myflights)
          
            })
            .catch(function(error) { console.log(error); })
              .then(function() {});
        })
        ///
        .catch(function(error) { console.log(error); }).then(function() {});

    },

    addCompany(){
      this.axios.post("http://localhost:8000/companies/addCompany", this.company, {headers: {}})
                    .then(res => { console.log(res);})
                        .catch(err => { console.log(err.response);});
                        window.location.reload()
                        
    },

    deleteCompany(){
      axios.get("http://localhost:8000/companies/deleteCompany", {
                    params: { name: this.company.companyName }
                    })
                    .then(function(response) {
                    console.log(response.data);
                    })
                    .catch(function(error) {
                    console.log(error);
                    })
                    .then(function() {});
                    window.location.reload()
    },

    showAdd() {
      this.$modal.show('modal-addCompany')
      this.hideDelete();
    },

    showDelete() {
      this.$modal.show('modal-deleteCompany')
      this.hideAdd();
    },
    
    hideDelete() {
      this.$modal.hide('modal-deleteCompany')
    },


    hideAdd() {
      this.$modal.hide('modal-addCompany')
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

    loadCompanies() {
      axios
        .get("http://localhost:8000/companies/getAll")
        .then(response => {
          console.log("GET_COMPANIES");
          console.log(response.data);
          this.companies = response.data;
      })
        .catch(function(error) {
          console.log(error);
        })
        .then(function() {});
    }
  }
};
</script>


<style>
</style>