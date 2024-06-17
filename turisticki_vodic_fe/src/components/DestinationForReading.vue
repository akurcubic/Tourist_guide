<template>
  <div class="articles">
    <br>
    <div
        v-for="destination in destinations"
        :key="destination.id"
        class="article-card"
        @click="navigateToDestination(destination.id)"
    >
      <h2>{{ destination.name }}</h2>
      <p>{{ destination.description }}</p>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    destinations: {
      type: Array,
      required: true,
      default: () => []  // Default to an empty array
    },
  },
  methods: {
    navigateToDestination(destinationId) {
      // Dohvati podatke o destinaciji
      this.$axios.get(`/api/destinations/${destinationId}`)
          .then(destinationResponse => {
            const destination = destinationResponse.data;
            const destinationName = destination.name;

            // Dohvati listu artikala za odabranu destinaciju
            this.$axios.get(`/api/articles/destination/${destinationId}`)
                .then(response => {
                  const articles = response.data;
                  // Za svaki članak, postavi ime destinacije
                  articles.forEach(article => {
                    article.destinationName = destinationName;
                  });
                  // Navigiraj na rutu koja prikazuje artikle za odabranu destinaciju
                  this.$router.push({ name: 'ArticlesForReading', params: { articles: articles } });
                })
                .catch(error => {
                  console.error("Error fetching articles:", error);
                });
          })
          .catch(destinationError => {
            console.error("Error fetching destination:", destinationError);
          });
    },
  },
};
</script>

<style scoped>
.articles {
  display: flex;
  flex-direction: column; /* Postavlja raspored vertikalno */
  align-items: center; /* Centrira kartice horizontalno */
  padding-top: 30px; /* Odmak od vrha stranice */
}

.page-title {
  text-align: center; /* Centriranje naslova */
  margin-bottom: 20px; /* Razmak ispod naslova */
}

.article-card {
  width: 80%; /* Širina kartice */
  margin-bottom: 20px; /* Razmak između kartica */
  border: 1px solid #ccc;
  padding: 20px;
  border-radius: 5px;
  background-color: #f8f9fa; /* Boja pozadine */
  color: #212529; /* Boja teksta */
  cursor: pointer; /* Pokazivač miša kao ruka */
  transition: background-color 0.3s; /* Dodajemo tranziciju za hover efekat */
}

.article-card:hover {
  background-color: #e9ecef; /* Boja pozadine pri hover-u */
}

/* Postavljamo boju pozadine za celu stranicu */
body {
  background-color: #f0f0f0; /* Boja pozadine */
}
</style>
