<template>
  <div>
    <h1>Articles</h1>
    <br>
    <single-subject :articles="articles" @articleAdded="fetchArticles" @articleUpdated="fetchArticles" @articleDeleted="fetchArticles"></single-subject>
    <div v-if="showButtons" class="pagination-buttons">
      <button @click="prevPage" :disabled="page <= 1">⬅️</button>
      <button @click="nextPage" :disabled="!hasNextPage">➡️</button>
    </div>
  </div>
</template>

<script>
import SingleSubject from "@/components/SingleSubject.vue";

export default {
  name: "ArticlesForDestinationPage",
  components: { SingleSubject },
  data() {
    return {
      articles: [],
      page: 1,
      hasNextPage: true,
      showButtons: false,
    };
  },
  created() {
    this.fetchArticles();
  },
  methods: {
    nextPage() {
      if (this.hasNextPage) {
        this.page++;
        this.fetchArticles();
      }
    },
    prevPage() {
      if (this.page > 1) {
        this.page--;
        this.fetchArticles();
      }
    },

    fetchArticles() {
      this.$axios.get(`/api/articles/page/${this.page}`)
          .then(response => {
            this.articles = response.data;
            console.log("AllArticles:", this.articles);
            // Dohvati nazive destinacija i autora za svaki članak
            this.articles.forEach(article => {
              this.fetchDestinationName(article);
              this.fetchAuthorName(article);
              this.showButtons = true; // Postavljamo vrednost showArticles na true nakon što se destinacije učitaju

            });
          })
          .catch(error => {
            console.error("Error fetching articles:", error);
          });
    },
    fetchDestinationName(article) {
      this.$axios.get(`/api/destinations/${article.destinationId}`)
          .then(response => {
            this.$set(article, 'destinationName', response.data.name);
          })
          .catch(error => {
            console.error("Error fetching destination name:", error);
          });
    },
    fetchAuthorName(article) {
      this.$axios.get(`/api/users/${article.authorId}`)
          .then(response => {
            this.$set(article, 'authorName', response.data.name);
          })
          .catch(error => {
            console.error("Error fetching author name:", error);
          });
    }
  }
};
</script>

<style scoped>
/* Stilizacija specifična za ovu komponentu */
.pagination-buttons {
  display: flex;
  justify-content: center;
  margin-top: 20px; /* Razmak između kartica i dugmadi za paginaciju */
}

.pagination-buttons button {
  background-color: #007bff; /* Plava boja dugmadi */
  color: white; /* Bela boja teksta */
  border: none; /* Bez okvira */
  padding: 10px 20px; /* Unutrašnji razmak */
  margin: 0 10px; /* Spoljni razmak između dugmadi */
  cursor: pointer; /* Pokazivač za klik */
  border-radius: 5px; /* Zaobljeni uglovi */
}

.pagination-buttons button:disabled {
  background-color: #cccccc; /* Siva boja za onemogućeno dugme */
  cursor: not-allowed; /* Zabrana klika */
}
</style>

