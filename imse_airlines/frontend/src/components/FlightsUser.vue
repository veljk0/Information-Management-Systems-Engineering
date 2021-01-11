<template>
  <div class="container mt-4">
    <h3>Hi {{ token.username }}</h3>
    <div class="nav-link">
      <button type="button" class="btn btn-danger">Logout</button>
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
          <th scope="col">Buy Ticket</th>
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
          <td>
            <button type="button" class="btn btn-success">buy ticket</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import Vue from "vue";
import axios from "axios";
import VueAxios from "vue-axios";
Vue.use(VueAxios, axios);

export default {
  name: "dashboard",
  data() {
    return {
      token: {
        username: "marinac",
        flag: null
      },
      flights: [],
      companies: [],
      usertickets: []
    };
  },

  created: function() {
    this.loadFlights();
  },

  methods: {
    sendToken() {
      axios
        .get("http://localhost:8085/users/getLogi")
        .then(response => {
          console.log("GET_TOKEN");
          console.log(response.data.username);
          this.token.username = response.data.username;
        })
        .catch(function(error) {
          console.log(error);
        })
        .then(function() {});
    },

    loadFlights() {
      axios
        .get("http://localhost:8085/flights/getAll")
        .then(response => {
          console.log("GET_FLIGHTS");
          console.log(response.data);
          this.flights = response.data;
          //this.token.username = response.data.username;
        })
        .catch(function(error) {
          console.log(error);
        })
        .then(function() {});
    }
  }
};
</script>
