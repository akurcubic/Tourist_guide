<template>
  <div>
    <h1 class="page-title">NEWEST ARTICLES</h1>
    <br>
    <article-for-reading :articles="articles"></article-for-reading>
  </div>
</template>

<script>
import ArticleForReading from "@/components/ArticleForReading.vue";

export default {
  name: "HomePage",
  components: { ArticleForReading },
  data() {
    return {
      articles: []
    };
  },
  created() {
    this.fetchArticles();
  },
  methods: {
    fetchArticles() {
      this.$axios
          .get(`/api/articles/latest-articles`)
          .then((response) => {
            this.articles = response.data;
            console.log("Latest 10 articles:", this.articles);
            // Fetch destination and author names for each article
            this.articles.forEach((article) => {
              this.fetchDestinationName(article);
            });
          })
          .catch((error) => {
            console.error("Error fetching articles:", error);
          });
    },
    fetchDestinationName(article) {
      this.$axios
          .get(`/api/destinations/${article.destinationId}`)
          .then((response) => {
            this.$set(article, "destinationName", response.data.name);
          })
          .catch((error) => {
            console.error("Error fetching destination name:", error);
          });
    },
  },
};
</script>

<style scoped>
/* Stilizacija specifična za ovu komponentu */
.page-title {
  text-align: center; /* Centriranje naslova */
}

</style>

