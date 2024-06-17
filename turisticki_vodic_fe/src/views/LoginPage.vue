<template>
  <div class="pt-5">
    <form @submit.prevent="login">
      <div class="form-group">
        <label for="username">E-mail</label>
        <input v-model="email" type="text" class="form-control" id="username" aria-describedby="usernameHelp" placeholder="Enter e-mail">
        <small id="usernameHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
      </div>
      <div class="form-group">
        <label for="exampleInputPassword1">Password</label>
        <input v-model="password" type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
      </div>
      <button type="submit" class="btn btn-primary mt-2">Submit</button>
      <!-- Prikazivanje poruke o grešci -->
      <div v-if="error" class="alert alert-danger mt-2">{{ error }}</div>
    </form>
  </div>
</template>

<script>
export default {
  name: "LoginPage",
  data() {
    return {
      email: '',
      password: '',
      error: ''
    }
  },
  methods: {
    login() {
      this.$axios.post('/api/users/login', {
        email: this.email,
        password: this.password,
      }).then(response => {
        console.log(response.data)
        if (response.data.jwt) {
          localStorage.setItem('jwt', response.data.jwt)
          this.$router.push({name: 'AllDestinations'});
        } else if(response.data.statusCode === 401){
          // Ako server vrati grešku da korisnik nije uneo ispravne podatke
          // Prikazi odgovarajuće obaveštenje korisniku
          alert('Invalid email or password. Please try again.');
        }
        else if(response.data.statusCode === 402){
          alert('Your account is INACTIVE');
        }
      }).catch(error => {
        // Uhvati grešku i obradi je ovde
        console.error('Error logging in:', error);
        // Prikazi obaveštenje korisniku da je došlo do greške prilikom logovanja
        alert('An error occurred while logging in. Please try again later.');
      });
    }
  },
}
</script>

<style scoped>

</style>



