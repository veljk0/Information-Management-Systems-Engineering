<template>
<div>
  <DefaultNavbar/>
  <br/>
  <br/>
  <LoginBox/>
</div>
</template>

<script>
import DefaultNavbar from "../components/DefaultNavbar"
import LoginBox from "../components/LoginBox"


export default {
  name : "LoginPage",
  components:{DefaultNavbar, LoginBox}

}
</script>

<style></style>