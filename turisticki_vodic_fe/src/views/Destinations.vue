<template>
  <div class="page-container">
    <h1 class="page-title">DESTINATIONS</h1>
    <br>
    <destination-for-reading
        :destinations="destinations"
    ></destination-for-reading>


    <div v-if="showButtons" class="pagination-buttons">
      <button @click="prevPage" :disabled="page <= 1">⬅️</button>
      <button @click="nextPage" :disabled="!hasNextPage">➡️</button>
    </div>
  </div>
</template>

<script>
import DestinationForReading from "@/components/DestinationForReading.vue";

export default {
  name: "DestinationsP",
  components: { DestinationForReading}, // Dodajemo komponentu u listu komponenti
  data() {
    return {
      destinations: [],
      showButtons: false,
      page: 1,
      hasNextPage: true,
    };
  },
  created() {
    this.fetchDestinations();
  },
  methods: {
    fetchDestinations() {
      this.$axios
          .get(`/api/destinations/page/${this.page}`)
          .then((response) => {
            console.log("API Response:", response.data);
            this.destinations = response.data || [];
            console.log("Destinations:", this.destinations);
            this.showButtons = true; // Postavljamo vrednost showArticles na true nakon što se destinacije učitaju
          })
          .catch((error) => {
            console.error("Error fetching destinations:", error);
            this.destinations = [];
          });
    },
    nextPage() {
      if (this.hasNextPage) {
        this.page++;
        this.fetchDestinations();
      }
    },
    prevPage() {
      if (this.page > 1) {
        this.page--;
        this.fetchDestinations();
      }
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
