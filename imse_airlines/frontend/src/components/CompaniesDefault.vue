<template>

  <div class="container mt-4">
 <table class="table table-bordered">
      <tbody>
        <tr v-for="company in companies" :key="company.companyId">
          <td>{{ company.companyName }}</td>
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
      companies: [],
  };
  },

  created: function() {
    this.loadCompanies();
  },

  methods: {
    loadCompanies() {
      axios
        .get("http://localhost:8085/companies/getAll")
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
