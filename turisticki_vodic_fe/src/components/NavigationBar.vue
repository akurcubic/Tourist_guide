<!--<template>-->
<!--  <div>-->
<!--    <nav class="navbar navbar-expand-lg navbar-light bg-light">-->
<!--      <div class="container-fluid">-->
<!--        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">-->
<!--          <span class="navbar-toggler-icon"></span>-->
<!--        </button>-->
<!--        <div class="collapse navbar-collapse" id="navbarSupportedContent">-->
<!--          <ul class="navbar-nav me-auto mb-2 mb-lg-0">-->
<!--            <li class="nav-item">-->
<!--              <router-link to="/" tag="a" class="nav-link" :class="{active: this.$router.currentRoute.name === 'Home'}">Home</router-link>-->
<!--            </li>-->
<!--            <li class="nav-item">-->
<!--              <router-link :to="{name: 'MostPopular'}" tag="a" class="nav-link" :class="{active: this.$router.currentRoute.name === 'MostPopular'}">Most popular</router-link>-->
<!--            </li>-->
<!--            <li class="nav-item">-->
<!--              <router-link :to="{name: 'Destinations'}" tag="a" class="nav-link" :class="{active: this.$router.currentRoute.name === 'Destinations'}">Destinations</router-link>-->
<!--            </li>-->
<!--            <li class="nav-item" v-if="showDestinations">-->
<!--              <router-link :to="{name: 'AllDestinations'}" tag="a" class="nav-link" :class="{active: this.$router.currentRoute.name === 'AllDestinations'}">Edit destinations</router-link>-->
<!--            </li>-->
<!--            <li class="nav-item" v-if="showArticles">-->
<!--              <router-link :to="{name: 'AllArticles'}" tag="a" class="nav-link" :class="{active: this.$router.currentRoute.name === 'AllArticles'}">Edit articles</router-link>-->
<!--            </li>-->
<!--            <li class="nav-item" v-if="showUsers">-->
<!--              <router-link :to="{name: 'AllUsers'}" tag="a" class="nav-link" :class="{active: this.$router.currentRoute.name === 'AllUsers'}">Users</router-link>-->
<!--            </li>-->
<!--          </ul>-->
<!--          <div class="d-flex">-->
<!--            <button v-if="showLoginButton" class="btn btn-outline-secondary" @click="handleLoginClick">{{ isLoggedIn ? 'Logout' : 'Login' }}</button>-->
<!--          </div>-->
<!--        </div>-->
<!--      </div>-->
<!--    </nav>-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--export default {-->
<!--  name: "NavigationBar",-->
<!--  data() {-->
<!--    return {-->
<!--      loggedIn: false,-->
<!--      showLoginButton: true,-->
<!--      showUsers: false,-->
<!--      showDestinations: false,-->
<!--      showArticles: false-->
<!--    };-->
<!--  },-->
<!--  computed: {-->
<!--    isLoggedIn() {-->
<!--      return this.loggedIn;-->
<!--    }-->
<!--  },-->
<!--  methods: {-->
<!--    async checkLoginStatus() {-->
<!--      // Proveri da li postoji jwt u localStorage-u-->
<!--      this.loggedIn = !!localStorage.getItem('jwt');-->
<!--      // Sakrij dugme za login na login stranici-->
<!--      this.showLoginButton = this.$route.name !== 'LoginPage';-->

<!--      if (this.loggedIn) {-->
<!--        // Ako je korisnik prijavljen, proveri njegovu ulogu-->
<!--        try {-->
<!--          const jwt = localStorage.getItem('jwt');-->
<!--          const response = await this.$axios.get(`http://localhost:8080/api/users/jwt/type/${jwt}`);-->
<!--          if (response) {-->
<!--            const userType = response.data;-->
<!--            // Postavi odgovarajuće vrednosti za prikazivanje stavki u navigacionom baru u zavisnosti od uloge korisnika-->
<!--            this.showDestinations = userType === 'ADMIN' || userType === 'CONTENT_CREATOR';-->
<!--            this.showArticles = userType === 'ADMIN' || userType === 'CONTENT_CREATOR';-->
<!--            this.showUsers = userType === 'ADMIN';-->
<!--          } else {-->
<!--            console.error("Greška: Nije pronađen tip korisnika u odgovoru.");-->
<!--          }-->
<!--        } catch (error) {-->
<!--          console.error("Greška pri dobijanju tipa korisnika:", error);-->
<!--          if (error.response) {-->
<!--            console.log("HTTP status:", error.response.status);-->
<!--            console.log("Poruka greške:", error.response.data.message);-->
<!--          }-->
<!--        }-->
<!--      } else {-->
<!--        // Ako korisnik nije prijavljen, sakrij sve stavke osim prvih tri-->
<!--        this.showDestinations = false;-->
<!--        this.showArticles = false;-->
<!--        this.showUsers = false;-->
<!--      }-->
<!--    },-->
<!--    handleLoginClick() {-->
<!--      if (this.isLoggedIn) {-->
<!--        // Izloguj korisnika-->
<!--        localStorage.removeItem('jwt');-->
<!--        this.loggedIn = false;-->
<!--//        // Redirektuj na login stranicu-->
<!--        this.$router.push({ name: 'LoginPage' });-->
<!--      } else {-->
<!--        // Redirektuj na stranicu za login-->
<!--        this.$router.push({ name: 'LoginPage' });-->
<!--      }-->
<!--    }-->
<!--  },-->
<!--  created() {-->
<!--    // Proveri status pri kreiranju komponente-->
<!--    this.checkLoginStatus();-->
<!--  },-->
<!--  watch: {-->
<!--    // Prati promene u localStorage-u-->
<!--    '$route'() {-->
<!--      this.checkLoginStatus();-->
<!--    }-->
<!--  }-->
<!--};-->
<!--</script>-->

<!--<style scoped>-->
<!--/* Stilizacija */-->
<!--</style>-->

<!--}-->
<template>
  <div>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
      <div class="container-fluid">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item">
              <router-link to="/" tag="a" class="nav-link" :class="{active: this.$router.currentRoute.name === 'Home'}">Home</router-link>
            </li>
            <li class="nav-item">
              <router-link :to="{name: 'MostPopular'}" tag="a" class="nav-link" :class="{active: this.$router.currentRoute.name === 'MostPopular'}">Most popular</router-link>
            </li>
            <li class="nav-item">
              <router-link :to="{name: 'Destinations'}" tag="a" class="nav-link" :class="{active: this.$router.currentRoute.name === 'Destinations'}">Destinations</router-link>
            </li>
            <li class="nav-item" v-if="showDestinations">
              <router-link :to="{name: 'AllDestinations'}" tag="a" class="nav-link" :class="{active: this.$router.currentRoute.name === 'AllDestinations'}">Edit destinations</router-link>
            </li>
            <li class="nav-item" v-if="showArticles">
              <router-link :to="{name: 'AllArticles'}" tag="a" class="nav-link" :class="{active: this.$router.currentRoute.name === 'AllArticles'}">Edit articles</router-link>
            </li>
            <li class="nav-item" v-if="showUsers">
              <router-link :to="{name: 'AllUsers'}" tag="a" class="nav-link" :class="{active: this.$router.currentRoute.name === 'AllUsers'}">Users</router-link>
            </li>
          </ul>
          <div class="d-flex">
            <span v-if="userName" class="me-2">Welcome, {{ userName }}</span>
            <button v-if="showLoginButton" class="btn btn-outline-secondary" @click="handleLoginClick">{{ isLoggedIn ? 'Logout' : 'Login' }}</button>
          </div>
        </div>
      </div>
    </nav>
  </div>
</template>

<script>
export default {
  name: "NavigationBar",
  data() {
    return {
      loggedIn: false,
      showLoginButton: true,
      showUsers: false,
      showDestinations: false,
      showArticles: false,
      userName: ''
    };
  },
  computed: {
    isLoggedIn() {
      return this.loggedIn;
    }
  },
  methods: {
    async checkLoginStatus() {
      // Proveri da li postoji jwt u localStorage-u
      this.loggedIn = !!localStorage.getItem('jwt');
      // Sakrij dugme za login na login stranici
      this.showLoginButton = this.$route.name !== 'LoginPage';

      if (this.loggedIn) {
        // Ako je korisnik prijavljen, proveri njegovu ulogu
        try {
          const jwt = localStorage.getItem('jwt');
          const response = await this.$axios.get(`http://localhost:8080/api/users/jwt/type/${jwt}`);
          if (response) {
            const userType = response.data;
            // Postavi odgovarajuće vrednosti za prikazivanje stavki u navigacionom baru u zavisnosti od uloge korisnika
            this.showDestinations = userType === 'ADMIN' || userType === 'CONTENT_CREATOR';
            this.showArticles = userType === 'ADMIN' || userType === 'CONTENT_CREATOR';
            this.showUsers = userType === 'ADMIN';

            // Dobavi ime korisnika
            const userResponse = await this.$axios.get(`http://localhost:8080/api/users/jwt/${jwt}`);
            console.log(userResponse);
            if (userResponse && userResponse.data) {
              // Prikazi ime korisnika
              this.userName = userResponse.data;
            }
          } else {
            console.error("Greška: Nije pronađen tip korisnika u odgovoru.");
          }
        } catch (error) {
          console.error("Greška pri dobijanju tipa korisnika:", error);
          if (error.response) {
            console.log("HTTP status:", error.response.status);
            console.log("Poruka greške:", error.response.data.message);
          }
        }
      } else {

        // Ako korisnik nije prijavljen, sakrij sve stavke osim prvih tri
        this.showDestinations = false;
        this.showArticles = false;
        this.showUsers = false;
        this.userName = ''; // Resetuj ime korisnika
      }
    },
    handleLoginClick() {
      if (this.isLoggedIn) {
        // Izloguj korisnika
        localStorage.removeItem('jwt');
        this.loggedIn = false;
        this.userName = ''; // Resetuj ime korisnika
        // Redirektuj na login stranicu
        this.$router.push({ name: 'LoginPage' });
      } else {
        // Redirektuj na stranicu za login
        this.$router.push({ name: 'LoginPage' });
      }
    }
  },
  created() {
    // Proveri status pri kreiranju komponente
    this.checkLoginStatus();
  },
  watch: {

    '$route'() {
      this.checkLoginStatus();
    }
  }
};
</script>

<style scoped>
/* Stilizacija */
</style>

