<template>
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <router-link class="navbar-brand">ADMIN MONITORING </router-link>
    <img alt="Vue logo" src="../assets/logo32.png" />
    <button
      class="navbar-toggler"
      type="button"
      data-toggle="collapse"
      data-target="#navbarSupportedContent"
      aria-controls="navbarSupportedContent"
      aria-expanded="false"
      aria-label="Toggle navigation"
    >
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav mr-auto">
       <li class="nav-item">
          <router-link class="nav-link" to="/adminFlights">Flight and Tickets creator</router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" to="/adminTickets">Company reports</router-link>
        </li>
         <li class="nav-item">
          <router-link class="nav-link" to="/adminCompanies">Company creator</router-link>
        </li>
         <li class="nav-item">
          <router-link class="nav-link" to="/adminUsers">Customers reports</router-link>
        </li>
        
      </ul>
      <form class="form-inline my-2 my-lg-0">
        <router-link class="nav-link" to="/"><button type="button" to="/" class="btn btn-danger">Logout</button></router-link>
        
        
      </form>
    </div>
  </nav>
</template>

<script>
export default {
  name: "AdminNavbar"
};
</script>
