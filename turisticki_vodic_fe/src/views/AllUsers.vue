<template>
  <div>
    <h1>Users</h1>
    <br>
    <user-list :users="users" @userAdded="fetchUsers" @userUpdated="fetchUsers" @userActivationToggled="fetchUsers"></user-list>

    <div v-if="showButtons" class="pagination-buttons">
      <button @click="prevPage" :disabled="page <= 1">⬅️</button>
      <button @click="nextPage" :disabled="!hasNextPage">➡️</button>
    </div>
  </div>
</template>

<script>
import UserList from "@/components/UsersList.vue";


export default {
  name: "AllUsers",
  components: { UserList },
  data() {
    return {
      users: [],
      page: 1,
      hasNextPage: true,
      showButtons: false,
    };
  },
  created() {
    this.fetchUsers();
  },
  methods: {
    nextPage() {
      if (this.hasNextPage) {
        this.page++;
        this.fetchUsers();
      }
    },
    prevPage() {
      if (this.page > 1) {
        this.page--;
        this.fetchUsers();
      }
    },

    fetchUsers() {
      this.$axios.get(`/api/users/page/${this.page}`)
          .then(response => {
            this.users = response.data;
            console.log("AllUsers:", this.users);
            this.showButtons = true;

          })
          .catch(error => {
            console.error("Error fetching users:", error);
          });
    },
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

