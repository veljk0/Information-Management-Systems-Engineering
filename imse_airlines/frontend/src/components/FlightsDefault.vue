<template>

<div class="container mt-4">
  <table scrollable class="table table-bordered">
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
      flights: [],
  };
  },

  created: function() {
    this.loadFlights();
  },

  methods: {
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
