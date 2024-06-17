<template>
  <div class="subjects">
    <h1 class="mt-4">Destinations</h1>
    <br>

    <div class="row">
      <div class="col-12">
        <table class="table" v-if="subjects.length">
          <thead>
          <tr>
            <th scope="col">Title</th>
            <th scope="col">Content</th>
            <th scope="col">Actions</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="subject in subjects" :key="subject.id" @click="navigateToDestinationArticles(subject.id)">
            <td>{{ subject.name | capitalize }}</td>
            <td>{{ subject.description | shortText }}</td>
            <td>
              <button class="btn btn-primary mr-1" @click.stop="editSubject(subject)">Edit</button>
              <span style="margin-right: 10px;"></span>
              <button class="btn btn-danger" @click.stop="deleteSubject(subject.id)">Delete</button>
            </td>
          </tr>
          </tbody>
        </table>
        <div class="pagination-buttons" v-if="subjects.length">
          <button @click="prevPage" :disabled="page <= 1">⬅️</button>
          <span style="margin-right: 10px;"></span>
          <button @click="nextPage">➡️</button>
        </div>
        <br>
      </div>
    </div>

    <!-- Button to create a new destination -->
    <div class="row" v-if="subjects.length">
      <div class="col-12">
        <button @click="openNewSubjectForm" class="btn btn-success">Create New Destination</button>
      </div>
    </div>

    <!-- Form to create a new destination -->
    <div v-if="showNewSubjectForm" class="row">
      <div class="col-12">
        <br>
        <h2>Create New Destination</h2>
        <form @submit.prevent="saveNewSubject">
          <div class="form-group">
            <label for="newSubjectName">Name</label>
            <input type="text" class="form-control" id="newSubjectName" v-model="newSubjectName" required>
          </div>
          <div class="form-group">
            <label for="newSubjectDescription">Description</label>
            <textarea class="form-control" id="newSubjectDescription" v-model="newSubjectDescription" required></textarea>
          </div>
          <br>
          <button type="submit" class="btn btn-primary">Save</button>
          <span style="margin-right: 10px;"></span>
          <button @click.prevent="cancelNewSubject" class="btn btn-secondary">Cancel</button>
        </form>
      </div>
    </div>

    <!-- Form to edit a destination -->
    <div v-if="showEditSubjectForm" class="row">
      <div class="col-12">
        <br>
        <h2>Edit Destination</h2>
        <form @submit.prevent="updateSubject">
          <div class="form-group">
            <label for="editSubjectName">Name</label>
            <input type="text" class="form-control" id="editSubjectName" v-model="selectedSubject.name" required>
          </div>
          <div class="form-group">
            <label for="editSubjectDescription">Description</label>
            <textarea class="form-control" id="editSubjectDescription" v-model="selectedSubject.description" required></textarea>
          </div>
          <br>
          <button type="submit" class="btn btn-primary">Save Changes</button>
          <span style="margin-right: 10px;"></span>
          <button @click.prevent="cancelEditSubject" class="btn btn-secondary">Cancel</button>
        </form>
      </div>
    </div>

    <!-- Error message -->
    <div v-if="error" class="row">
      <div class="col-12">
        <div class="alert alert-danger" role="alert">
          {{ error }}
        </div>
      </div>
    </div>
  </div>
</template>

<script>


export default {
  name: "AllDestinations",

  filters: {
    shortText(value) {
      if (value.length < 30) {
        return value;
      }
      return value.slice(0, 30) + '...'
    }
  },
  data() {
    return {
      selectedSubject: null,
      subjects: [],
      showNewSubjectForm: false,
      showEditSubjectForm: false,
      newSubjectName: '',
      newSubjectDescription: '',
      error: '',
      page: 1,
      totalPages: 0,
    }
  },
  created() {
    this.fetchSubjects();
  },
  methods: {
    nextPage() {
      this.page++;
      this.fetchSubjects();

    },
    prevPage() {
      if (this.page > 1) {
        this.page--;
        this.fetchSubjects();
      }
    },
    fetchSubjects() {
      this.$axios.get(`/api/destinations/page/${this.page}`).then((response) => {
        this.subjects = response.data;
      });
    },
    editSubject(subject) {
      this.selectedSubject = { ...subject };
      this.showEditSubjectForm = true;
    },
    deleteSubject(subjectId) {
      this.$axios.delete(`http://localhost:8080/api/destinations/${subjectId}`)
          .then(response => {
            console.log(response);
            if (response.data.statusCode === 200) {

              alert('Destination successfully deleted.');
              this.fetchSubjects();
            } else{
              alert("Deleting the destination is not possible because there are articles related to it");
            }
          });
    },
    openNewSubjectForm() {
      this.showNewSubjectForm = true;
    },
    saveNewSubject() {
      const data = {
        name: this.newSubjectName,
        description: this.newSubjectDescription
      };

      if (!this.newSubjectName || !this.newSubjectDescription) {
        this.error = "Both name and description are required.";
        return;
      }

      this.$axios.post('/api/destinations', data)
          .then(response => {
            if (response.status === 200) {
              alert(response.data.message);
              console.log('New destination created:', response.data);
              this.newSubjectName = '';
              this.newSubjectDescription = '';
              this.showNewSubjectForm = false;
              this.fetchSubjects();
            } else if (response.status === 500) {
              alert(response.data.message);
              console.error('Error creating new destination:', response.data);
            }
          })
          .catch(error => {
            console.error('Error creating new destination:', error);
            if (error.response && error.response.status === 500) {
              alert(error.response.data.message);
            }
          });
    },
    cancelNewSubject() {
      this.newSubjectName = '';
      this.newSubjectDescription = '';
      this.showNewSubjectForm = false;
      this.error = '';
    },
    updateSubject() {
      const data = {
        id: this.selectedSubject.id,
        name: this.selectedSubject.name,
        description: this.selectedSubject.description
      };

      this.$axios.put(`/api/destinations`, data)
          .then(response => {
            console.log('Destination updated:', response.data);
            this.showEditSubjectForm = false;
            this.fetchSubjects();
          })
          .catch(error => {
            console.error('Error updating destination:', error);
          });
    },
    cancelEditSubject() {
      this.showEditSubjectForm = false;
      this.error = '';
    },
    navigateToDestinationArticles(subjectId) {
      this.$router.push({ name: 'articles-for-destination', params: { id: subjectId } });
    }
  }
}
</script>

<style scoped>
.subjects {
  padding: 20px;
}
.table {
  margin-top: 20px;
}
.mt-4 {
  margin-top: 1.5rem;
}
.pagination-buttons {
  display: flex;
  justify-content: center;
  margin-top: 20px;
}
</style>








