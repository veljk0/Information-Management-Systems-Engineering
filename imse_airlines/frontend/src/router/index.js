import Vue from "vue";
import VueRouter from "vue-router";
import LandingPage from "../views/LandingPage.vue";
import LoginPage from "../views/LoginPage.vue"
import RegisterPage from "../views/RegisterPage.vue";
import UserPage from "../views/UserPage.vue"
import AdminLoginPage from "../views/AdminLoginPage.vue"
import AdminCompanies from "../components/admin/AdminCompanies.vue"
import AdminFlights from "../components/admin/AdminFlights.vue"
import AdminTickets from "../components/admin/AdminTickets.vue"
import CompaniesPage from "../views/CompaniesPage.vue"
import FlightsPage from "../views/FlightsPage.vue"
import MyTicketsPage from "../views/MyTicketsPage.vue"

import AdminUsers from "../components/admin/AdminUsers.vue"

Vue.use(VueRouter);

const routes = [
  {
    path: "/adminUsers",
    name: "AdminUsers",
    component: AdminUsers
  },

  {
    path: "/companiesPage",
    name: "CompaniesPage",
    component: CompaniesPage
  },
  {
    path: "/flightsPage",
    name: "FlightsPage",
    component: FlightsPage
  },
  {
    path: "/mytickets",
    name: "MyTicketsPage",
    component: MyTicketsPage
  },
  {
    path: "/adminCompanies",
    name: "AdminCompanies",
    component: AdminCompanies
  },
  
  {
    path: "/adminFlights",
    name: "AdminFlights",
    component: AdminFlights
  },
  {
    path: "/adminTickets",
    name: "AdminTickets",
    component: AdminTickets
  },
  {
    path: "/",
    name: "LandingPage",
    component: LandingPage
  },
 
  {
    path: "/admin",
    name: "AdminLoginPage",
    component: AdminLoginPage
  },
  {
    path: "/login",
    name: "LoginPage",
    component: LoginPage
  },
  {
    path: "/userpage",
    name: "Userpage",
    component: UserPage
  },
  {
    path: "/register",
    name: "RegisterPage",
    component: RegisterPage
  },
  
  {
    path: "/about",
    name: "About",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/About.vue")
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;
