<template>
  <div>
  <DefaultNavbar/>
  <br/>
  <br/>
  <RegisterBox/>
</div>
</template>

<script>
import DefaultNavbar from "../components/DefaultNavbar"
import RegisterBox from "../components/RegisterBox"

export default {
  name:"RegisterPage",
  components:{
    DefaultNavbar, RegisterBox
  }

}
</script>

<style>

</style>