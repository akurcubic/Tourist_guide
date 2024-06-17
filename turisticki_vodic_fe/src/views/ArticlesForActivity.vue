<template>
  <div class="page-container">
    <br>
    <article-for-reading
        :articles="articles"
        @show-articles="handleShowArticles"
    ></article-for-reading>

    <div v-if="showArticles" class="pagination-buttons">
      <button @click="prevPage" :disabled="page <= 1">⬅️</button>
      <button @click="nextPage" :disabled="!hasNextPage">➡️</button>
    </div>
  </div>
</template>

<script>
import ArticleForReading from "@/components/ArticleForReading.vue";

export default {
  name: "ArticleForActivity",
  components: { ArticleForReading }, // Dodajemo komponentu u listu komponenti
  data() {
    return {
      articleId: this.$route.params.activityId,
      articles: [],
      showArticles: false,
      page: 1,
      hasNextPage: true,
    };
  },
  created() {
    this.fetchArticles();
  },
  methods: {
    async fetchArticles() {
      try {
        const response = await this.$axios.get(`/api/articlesactivities/activity/${this.articleId}/${this.page}`);
        console.log("API Response:", response.data);
        let articles = response.data || [];

        // Fetch destination names for each article
        for (let article of articles) {
          const destinationResponse = await this.$axios.get(`/api/destinations/${article.destinationId}`);
          article.destinationName = destinationResponse.data.name;
        }

        this.articles = articles;
        console.log("Articles for activity with destination names:", this.articles);
        this.showArticles = true; // Postavljamo vrednost showArticles na true nakon što se destinacije učitaju
      } catch (error) {
        console.error("Error fetching destinations:", error);
        this.articles = [];
      }
    },
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
    handleShowArticles(articles) {
      console.log("Received articles:", articles);
      this.articles = articles;
      // this.showArticles = true; // Neophodno je da ovde postavimo showArticles na true kako bi se prikazali dugmici
    },
  },
};
</script>

<style scoped>
/* Stilizacija specifična za ovu komponentu */
.page-container {
  background-color: white; /* Boja u skladu sa bojom kartica */
  padding: 20px; /* Dodajemo malo prostora oko sadržaja */
}

.page-title {
  text-align: center; /* Centriranje naslova */
  margin-bottom: 20px; /* Razmak između naslova i kartica */
}

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
