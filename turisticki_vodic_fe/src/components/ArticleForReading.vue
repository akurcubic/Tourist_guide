<template>
  <div class="articles">
    <br>
    <!-- Dodajemo event listener za klik na karticu artikla -->
    <div v-for="article in articles" :key="article.id" class="article-card" @click="openArticle(article.id)">
      <h2>{{ article.title }}</h2>
      <p>{{ truncateText(article.content, 100) }}</p>
      <p><strong>Destination:</strong> {{ article.destinationName }}</p>
      <p><strong>Creation Date:</strong> {{ formatDate(article.date) }}</p>
      <p><strong>Visits:</strong> {{ article.visits }}</p>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  props: {
    articles: {
      type: Array,
      required: true
    }
  },
  methods: {
    // Function to truncate text to first 100 characters
    truncateText(text, maxLength) {
      if (text.length > maxLength) {
        return text.slice(0, maxLength) + '...';
      }
      return text;
    },
    // Function to format date
    formatDate(dateString) {
      const options = { year: 'numeric', month: 'long', day: 'numeric' };
      return new Date(dateString).toLocaleDateString('en-US', options);
    },

    async openArticle(articleId) {
      if (!articleId) {
        console.error("Invalid articleId:", articleId);
        return;
      }
      await axios.put(`http://localhost:8080/api/articles/increment_visits/${articleId}`);

      const url = this.$router.resolve({ path: `/single-article/${articleId}` }).href;
      window.open(url, '_blank');
    }

  }
}
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
}

.article-card:hover {
  background-color: #e9ecef; /* Boja pozadine pri hover-u */
}

/* Postavljamo boju pozadine za celu stranicu */
body {
  background-color: #f0f0f0; /* Boja pozadine */
}
</style>

