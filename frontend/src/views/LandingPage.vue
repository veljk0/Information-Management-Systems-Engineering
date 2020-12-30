<template>
  <div>
    <DefaultNavbar/>
    <WelcomeBox/>
    <br/>
     <v-container grid-list-xl>
        <v-layout >
        <v-flex>
            <v-card dark color="accent">
            <v-card-text></v-card-text>
             <h1>Current flights</h1>
             <FlightsDefault/>
           </v-card>
        </v-flex>
        <v-flex>
            <v-card dark color="accent">
                <v-card-text></v-card-text>
                <h1>Our partners</h1>
                <CompaniesDefault/>
            </v-card>
        </v-flex>
       
        </v-layout>
    </v-container>
  </div>
</template>

<script>
import DefaultNavbar from "../components/DefaultNavbar"
import WelcomeBox from "../components/WelcomeBox"
import FlightsDefault from "../components/FlightsDefault"
import CompaniesDefault from "../components/CompaniesDefault"
export default {
  name: "LandingPage",
  components:{ DefaultNavbar, WelcomeBox, FlightsDefault, CompaniesDefault }
};
</script>
