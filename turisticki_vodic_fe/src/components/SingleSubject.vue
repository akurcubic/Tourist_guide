<!--<template>-->
<!--  <div class="articles">-->
<!--    <h1 class="mt-4">Articles</h1>-->
<!--    <br>-->

<!--    <div class="row">-->
<!--      <div class="col-12">-->
<!--        <table class="table">-->
<!--          <thead>-->
<!--          <tr>-->
<!--            <th scope="col">Title</th>-->
<!--            <th scope="col">Destination</th>-->
<!--            <th scope="col">Author</th>-->
<!--            <th scope="col">Date</th>-->
<!--            <th scope="col">Actions</th>-->
<!--          </tr>-->
<!--          </thead>-->
<!--          <tbody>-->
<!--          <tr v-for="article in articles" :key="article.id">-->
<!--            <td>{{ article.title }}</td>-->
<!--            <td>{{ article.destinationName || article.destinationId }}</td>-->
<!--            <td>{{ article.authorName || article.authorId }}</td>-->
<!--            <td>{{ formatDate(article.date) }}</td>-->
<!--            <td>-->
<!--              <button class="btn btn-primary mr-1" @click="editArticle(article)">Edit</button>-->
<!--              <span style="margin-right: 10px;"></span>-->
<!--              <button class="btn btn-danger" @click="deleteArticle(article.id)">Delete</button>-->
<!--            </td>-->
<!--          </tr>-->
<!--          </tbody>-->
<!--        </table>-->
<!--      </div>-->
<!--    </div>-->

<!--    &lt;!&ndash; Error message &ndash;&gt;-->
<!--    <div v-if="error" class="alert alert-danger" role="alert">-->
<!--      {{ error }}-->
<!--    </div>-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--export default {-->
<!--  props: {-->
<!--    articles: {-->
<!--      type: Array,-->
<!--      required: true-->
<!--    }-->
<!--  },-->
<!--  data() {-->
<!--    return {-->
<!--      error: ''-->
<!--    }-->
<!--  },-->
<!--  created() {-->
<!--    console.log("Artikli u SingleSubject", this.articles);-->
<!--  },-->
<!--  methods: {-->
<!--    formatDate(date) {-->
<!--      const options = { year: 'numeric', month: 'long', day: 'numeric' };-->
<!--      return new Date(date).toLocaleDateString(undefined, options);-->
<!--    },-->
<!--    editArticle(article) {-->
<!--      console.log('Edit article:', article);-->
<!--    },-->
<!--    deleteArticle(articleId) {-->
<!--      console.log('Delete article with ID:', articleId);-->
<!--    }-->
<!--  }-->
<!--}-->
<!--</script>-->

<!--<style scoped>-->
<!--.articles {-->
<!--  padding: 20px;-->
<!--}-->
<!--.table {-->
<!--  margin-top: 20px;-->
<!--}-->
<!--</style>-->

<!--<template>-->
<!--  <div>-->
<!--    <table class="table">-->
<!--      <thead>-->
<!--      <tr>-->
<!--        <th>Title</th>-->
<!--        <th>Content</th>-->
<!--        <th>Destination</th>-->
<!--        <th>Author</th>-->
<!--        <th>Creation date</th>-->
<!--        <th>Actions</th>-->
<!--      </tr>-->
<!--      </thead>-->
<!--      <tbody>-->
<!--      <tr v-for="article in articles" :key="article.id">-->
<!--        <td>{{ article.title }}</td>-->
<!--        <td>{{ article.content }}</td>-->
<!--        <td>{{ article.destinationName }}</td>-->
<!--        <td>{{ article.authorName }}</td>-->
<!--        <td>{{article.date}}</td>-->
<!--        <td>-->
<!--          <button @click="editArticle(article)" class="btn btn-primary">Edit</button>-->
<!--          <span style="margin-right: 10px;"></span>-->
<!--          <button @click="deleteArticle(article.id)" class="btn btn-danger">Delete</button>-->
<!--        </td>-->
<!--      </tr>-->
<!--      </tbody>-->
<!--    </table>-->
<!--    <div v-if="articles.length === 0">-->
<!--      <p>No articles found.</p>-->
<!--    </div>-->
<!--  </div>-->

<!--</template>-->

<!--<script>-->
<!--export default {-->
<!--  props: {-->
<!--    articles: {-->
<!--      type: Array,-->
<!--      required: true-->
<!--    }-->
<!--  },-->
<!--  methods: {-->
<!--    editArticle(article) {-->
<!--      // Implementacija funkcije za edit članka-->
<!--      console.log(article);-->
<!--    },-->
<!--    deleteArticle(articleId) {-->
<!--      // Implementacija funkcije za brisanje članka-->
<!--      console.log(articleId);-->
<!--    }-->
<!--  }-->
<!--};-->
<!--</script>-->

<!--<style scoped>-->
<!--/* Stilizacija specifična za ovu komponentu */-->
<!--</style>-->

<!--<template>-->
<!--  <div>-->
<!--    <table class="table">-->
<!--      <thead>-->
<!--      <tr>-->
<!--        <th>Title</th>-->
<!--        <th>Content</th>-->
<!--        <th>Destination</th>-->
<!--        <th>Author</th>-->
<!--        <th>Creation date</th>-->
<!--        <th>Actions</th>-->
<!--      </tr>-->
<!--      </thead>-->
<!--      <tbody>-->
<!--      <tr v-for="article in articles" :key="article.id">-->
<!--        <td>{{ article.title }}</td>-->
<!--        <td>{{ article.content }}</td>-->
<!--        <td>{{ article.destinationName }}</td>-->
<!--        <td>{{ article.authorName }}</td>-->
<!--        <td>{{ formatDate(article.date) }}</td>-->
<!--        <td>-->
<!--          <button @click="editArticle(article)" class="btn btn-primary">Edit</button>-->
<!--          <span style="margin-right: 10px;"></span>-->
<!--          <button @click="deleteArticle(article.id)" class="btn btn-danger">Delete</button>-->
<!--        </td>-->
<!--      </tr>-->
<!--      </tbody>-->
<!--    </table>-->
<!--    <div v-if="articles.length === 0">-->
<!--      <p>No articles found.</p>-->
<!--    </div>-->

<!--    &lt;!&ndash; Button to create a new article &ndash;&gt;-->
<!--    <button @click="showCreateArticleForm = true" class="btn btn-success mb-3">Create New Article</button>-->

<!--    &lt;!&ndash; Form to create a new article &ndash;&gt;-->
<!--    <div v-if="showCreateArticleForm" class="create-article-form">-->
<!--      <h2>Create New Article</h2>-->
<!--      <form @submit.prevent="createArticle" class="form">-->
<!--        <div class="form-group">-->
<!--          <label for="articleTitle">Title</label>-->
<!--          <input type="text" id="articleTitle" v-model="newArticle.title" class="form-control" required>-->
<!--        </div>-->
<!--        <div class="form-group">-->
<!--          <label for="articleDestination">Destination</label>-->
<!--          <select id="articleDestination" v-model="newArticle.destinationName" class="form-control" required>-->
<!--            <option v-for="destination in destinations" :key="destination.id" :value="destination.name">-->
<!--              {{ destination.name }}-->
<!--            </option>-->
<!--          </select>-->
<!--        </div>-->
<!--        <div class="form-group">-->
<!--          <label for="articleContent">Content</label>-->
<!--          <textarea id="articleContent" v-model="newArticle.content" class="form-control" required></textarea>-->
<!--        </div>-->
<!--        <div class="form-group">-->
<!--          <label for="articleActivities">Activities</label>-->
<!--          <textarea id="articleActivities" v-model="newArticle.activities" class="form-control" required></textarea>-->
<!--        </div>-->
<!--        <button type="submit" class="btn btn-primary">Save</button>-->
<!--        <span style="margin-right: 10px;"></span>-->
<!--        <button @click="showCreateArticleForm = false" class="btn btn-secondary">Cancel</button>-->
<!--      </form>-->
<!--    </div>-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--import jwt_decode from 'jwt-decode';-->

<!--export default {-->
<!--  props: {-->
<!--    articles: {-->
<!--      type: Array,-->
<!--      required: true-->
<!--    }-->
<!--  },-->
<!--  data() {-->
<!--    return {-->
<!--      showCreateArticleForm: false,-->
<!--      destinations: [],-->
<!--      newArticle: {-->
<!--        title: '',-->
<!--        destinationName: '',-->
<!--        content: '',-->
<!--        activities: ''-->
<!--      }-->
<!--    };-->
<!--  },-->
<!--  created() {-->
<!--    this.fetchDestinations();-->
<!--  },-->
<!--  methods: {-->
<!--    formatDate(date) {-->
<!--      const options = { year: 'numeric', month: 'long', day: 'numeric' };-->
<!--      return new Date(date).toLocaleDateString(undefined, options);-->
<!--    },-->
<!--    editArticle(article) {-->
<!--      // Implementacija funkcije za edit članka-->
<!--      console.log(article);-->
<!--    },-->
<!--    deleteArticle(articleId) {-->
<!--      // Implementacija funkcije za brisanje članka-->
<!--      console.log(articleId);-->
<!--    },-->
<!--    fetchDestinations() {-->
<!--      this.$axios.get('http://localhost:8080/api/destinations')-->
<!--          .then(response => {-->
<!--            this.destinations = response.data;-->
<!--          })-->
<!--          .catch(error => {-->
<!--            console.error('Error fetching destinations:', error);-->
<!--          });-->
<!--    },-->
<!--    createArticle() {-->
<!--      console.log(this.newArticle.destinationName);-->
<!--      // Fetch destinationId based on destination name-->
<!--      this.$axios.get(`http://localhost:8080/api/destinations/destination_name/${this.newArticle.destinationName}`)-->
<!--          .then(destinationResponse => {-->
<!--            const destinationId = destinationResponse.data.id;-->

<!--            // Fetch authorId based on user name from JWT-->
<!--            const token = localStorage.getItem('jwt'); // Assuming JWT is stored in localStorage-->
<!--            // const decodedToken = jwt_decode(token);-->
<!--            // const authorName = decodedToken.username;-->
<!--            const decodedToken = jwt_decode(token);-->
<!--            const authorName = decodedToken.name;-->
<!--            const splitName = authorName.split(' '); // Splitovanje po razmaku-->
<!--            const firstName = splitName[0]; // Uzimanje prvog elementa niza-->
<!--            this.$axios.get(`http://localhost:8080/api/users/${firstName}`)-->
<!--                .then(userResponse => {-->
<!--                  const authorId = userResponse.data.id;-->

<!--                  console.log(this.newArticle.title);-->
<!--                  console.log(this.newArticle.content);-->
<!--                  console.log(new Date().toISOString().slice(0, 10));-->
<!--                  console.log(this.newArticle.activities);-->
<!--                  console.log(authorId);-->
<!--                  console.log(destinationId);-->

<!--                  // Create article object-->
<!--                  const articleData = {-->
<!--                    title: this.newArticle.title,-->
<!--                    content: this.newArticle.content,-->
<!--                    date: new Date().toISOString().slice(0, 10), // Current date-->
<!--                    visits: 0,-->
<!--                    activities: this.newArticle.activities,-->
<!--                    authorId: authorId,-->
<!--                    destinationId: destinationId-->
<!--                  };-->

<!--                  // Send POST request to create article-->
<!--                  this.$axios.post('http://localhost:8080/api/articles', articleData)-->
<!--                      .then(response => {-->
<!--                        console.log('Article created:', response.data);-->
<!--                        alert('Article successfully created.');-->
<!--                        this.showCreateArticleForm = false;-->
<!--                        this.newArticle = {-->
<!--                          title: '',-->
<!--                          destinationName: '',-->
<!--                          content: '',-->
<!--                          activities: ''-->
<!--                        };-->
<!--                        this.$emit('articleCreated', response.data);-->
<!--                      })-->
<!--                      .catch(error => {-->
<!--                        console.error('Error creating article:', error);-->
<!--                        alert('Error creating article.');-->
<!--                      });-->
<!--                })-->
<!--                .catch(error => {-->
<!--                  console.error('Error fetching author:', error);-->
<!--                  alert('Error creating article.');-->
<!--                });-->
<!--          })-->
<!--          .catch(error => {-->
<!--            console.error('Error fetching destination:', error);-->
<!--            alert('Error creating article.');-->
<!--          });-->
<!--    }-->

<!--  }-->
<!--};-->
<!--</script>-->

<!--<style scoped>-->
<!--/* Stilizacija specifična za ovu komponentu */-->
<!--.create-article-form {-->
<!--  margin-top: 20px;-->
<!--}-->

<!--.form-group {-->
<!--  margin-bottom: 15px;-->
<!--}-->

<!--.form {-->
<!--  width: 100%;-->
<!--  max-width: 800px; /* Isto širina kao tabela */-->
<!--  margin-left: 0; /* Pomeranje forme levo */-->
<!--  padding: 20px;-->
<!--  border: 1px solid #ccc;-->
<!--  border-radius: 5px;-->
<!--  background-color: #f9f9f9;-->
<!--}-->
<!--</style>-->

<!--<template>-->
<!--  <div>-->
<!--    <table class="table">-->
<!--      <thead>-->
<!--      <tr>-->
<!--        <th>Title</th>-->
<!--&lt;!&ndash;        <th>Content</th>&ndash;&gt;-->
<!--        <th>Destination</th>-->
<!--        <th>Author</th>-->
<!--        <th>Creation date</th>-->
<!--        <th>Actions</th>-->
<!--      </tr>-->
<!--      </thead>-->
<!--      <tbody>-->
<!--      <tr v-for="article in articles" :key="article.id">-->
<!--        <td>{{ article.title }}</td>-->
<!--&lt;!&ndash;        <td>{{ article.content }}</td>&ndash;&gt;-->
<!--        <td>{{ article.destinationName }}</td>-->
<!--        <td>{{ article.authorName }}</td>-->
<!--        <td>{{ formatDate(article.date) }}</td>-->
<!--        <td>-->
<!--          <button @click="editArticle(article)" class="btn btn-primary">Edit</button>-->
<!--          <span style="margin-right: 10px;"></span>-->
<!--          <button @click="deleteArticle(article.id)" class="btn btn-danger">Delete</button>-->
<!--        </td>-->
<!--      </tr>-->
<!--      </tbody>-->
<!--    </table>-->
<!--    <div v-if="articles.length === 0">-->
<!--      <p>No articles found.</p>-->
<!--    </div>-->

<!--    &lt;!&ndash; Button to create a new article &ndash;&gt;-->
<!--    <button @click="showCreateArticleForm = true" class="btn btn-success mb-3">Create New Article</button>-->

<!--    &lt;!&ndash; Form to create a new article &ndash;&gt;-->
<!--    <div v-if="showCreateArticleForm" class="create-article-form">-->
<!--      <h2>Create New Article</h2>-->
<!--      <form @submit.prevent="createArticle" class="form">-->
<!--        <div class="form-group">-->
<!--          <label for="articleTitle">Title</label>-->
<!--          <input type="text" id="articleTitle" v-model="newArticle.title" class="form-control" required>-->
<!--        </div>-->
<!--        <div class="form-group">-->
<!--          <label for="articleDestination">Destination</label>-->
<!--          <select id="articleDestination" v-model="newArticle.destinationName" class="form-control" required>-->
<!--            <option v-for="destination in destinations" :key="destination.id" :value="destination.name">-->
<!--              {{ destination.name }}-->
<!--            </option>-->
<!--          </select>-->
<!--        </div>-->
<!--        <div class="form-group">-->
<!--          <label for="articleContent">Content</label>-->
<!--          <textarea id="articleContent" v-model="newArticle.content" class="form-control" required></textarea>-->
<!--        </div>-->
<!--        <div class="form-group">-->
<!--          <label for="articleActivities">Activities</label>-->
<!--          <textarea id="articleActivities" v-model="newArticle.activities" class="form-control" required></textarea>-->
<!--        </div>-->
<!--        <button type="submit" class="btn btn-primary">Save</button>-->
<!--        <span style="margin-right: 10px;"></span>-->
<!--        <button @click="showCreateArticleForm = false" class="btn btn-secondary">Cancel</button>-->
<!--      </form>-->
<!--    </div>-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->

<!--export default {-->
<!--  props: {-->
<!--    articles: {-->
<!--      type: Array,-->
<!--      required: true-->
<!--    }-->
<!--  },-->
<!--  data() {-->
<!--    return {-->
<!--      showCreateArticleForm: false,-->
<!--      destinations: [],-->
<!--      newArticle: {-->
<!--        title: '',-->
<!--        destinationName: '',-->
<!--        content: '',-->
<!--        activities: ''-->
<!--      }-->
<!--    };-->
<!--  },-->
<!--  created() {-->
<!--    this.fetchDestinations();-->
<!--  },-->
<!--  methods: {-->

<!--    formatDate(date) {-->
<!--      const options = { year: 'numeric', month: 'long', day: 'numeric' };-->
<!--      return new Date(date).toLocaleDateString(undefined, options);-->
<!--    },-->
<!--    editArticle(article) {-->
<!--      // Implementacija funkcije za edit članka-->
<!--      console.log(article);-->
<!--    },-->
<!--    deleteArticle(articleId) {-->
<!--      // Implementacija funkcije za brisanje članka-->
<!--      console.log(articleId);-->
<!--    },-->
<!--    fetchDestinations() {-->
<!--      this.$axios.get('http://localhost:8080/api/destinations')-->
<!--          .then(response => {-->
<!--            this.destinations = response.data;-->
<!--          })-->
<!--          .catch(error => {-->
<!--            console.error('Error fetching destinations:', error);-->
<!--          });-->
<!--    },-->

<!--    createArticle() {-->
<!--      console.log(this.newArticle.destinationName);-->
<!--      // Fetch destinationId based on destination name-->
<!--      this.$axios.get(`http://localhost:8080/api/destinations/destination_name/${this.newArticle.destinationName}`)-->
<!--          .then(destinationResponse => {-->
<!--            const destinationId = destinationResponse.data.id;-->

<!--            // Fetch username based on JWT from backend endpoint-->
<!--            const token = localStorage.getItem('jwt'); // Assuming JWT is stored in localStorage-->
<!--            this.$axios.get(`http://localhost:8080/api/users/jwt/${token}`, {-->
<!--              headers: { Authorization: `Bearer ${token}` }-->
<!--            })-->
<!--                .then(jwtResponse => {-->
<!--                  const username = jwtResponse.data;-->

<!--                  // Fetch user by username to get userId-->
<!--                  this.$axios.get(`http://localhost:8080/api/users/user_name/${username}`)-->
<!--                      .then(userResponse => {-->
<!--                        const authorId = userResponse.data.id;-->

<!--                        console.log(this.newArticle.title);-->
<!--                        console.log(this.newArticle.content);-->
<!--                        console.log(new Date().toISOString().slice(0, 10));-->
<!--                        console.log(this.newArticle.activities);-->
<!--                        console.log(authorId);-->
<!--                        console.log(destinationId);-->

<!--                        // Create article object-->
<!--                        const currentDate = new Date();-->
<!--                        currentDate.setDate(currentDate.getDate() + 1); // Dodajemo 1 dan-->
<!--                        const articleData = {-->
<!--                          title: this.newArticle.title,-->
<!--                          content: this.newArticle.content,-->
<!--                          date: currentDate.toISOString().slice(0, 10), // Current date-->
<!--                          visits: 0,-->
<!--                          activities: this.newArticle.activities,-->
<!--                          authorId: authorId,-->
<!--                          destinationId: destinationId-->
<!--                        };-->

<!--                        // Send POST request to create article-->
<!--                        this.$axios.post('http://localhost:8080/api/articles', articleData)-->
<!--                            .then(response => {-->
<!--                              console.log('Article created:', response.data);-->
<!--                              alert('Article successfully created.');-->
<!--                              this.showCreateArticleForm = false;-->
<!--                              this.newArticle = {-->
<!--                                title: '',-->
<!--                                destinationName: '',-->
<!--                                content: '',-->
<!--                                activities: ''-->
<!--                              };-->
<!--                              this.$emit('articleAdded');-->
<!--                            })-->
<!--                            .catch(error => {-->
<!--                              console.error('Error creating article:', error);-->
<!--                              alert('Error creating article.');-->
<!--                            });-->
<!--                      })-->
<!--                      .catch(error => {-->
<!--                        console.error('Error fetching user:', error);-->
<!--                        alert('Error creating article.');-->
<!--                      });-->
<!--                })-->
<!--                .catch(error => {-->
<!--                  console.error('Error fetching username from JWT:', error);-->
<!--                  alert('Error creating article.');-->
<!--                });-->
<!--          })-->
<!--          .catch(error => {-->
<!--            console.error('Error fetching destination:', error);-->
<!--            alert('Error creating article.');-->
<!--          });-->
<!--    }-->

<!--  }-->
<!--}-->
<!--</script>-->

<!--<style scoped>-->
<!--/* Stilizacija specifična za ovu komponentu */-->
<!--.create-article-form {-->
<!--  margin-top: 20px;-->
<!--}-->

<!--.form-group {-->
<!--  margin-bottom: 15px;-->
<!--}-->

<!--.form {-->
<!--  width: 100%;-->
<!--  max-width: 800px; /* Isto širina kao tabela */-->
<!--  margin-left: 0; /* Pomeranje forme levo */-->
<!--  padding: 20px;-->
<!--  border: 1px solid #ccc;-->
<!--  border-radius: 5px;-->
<!--  background-color: #f9f9f9;-->
<!--}-->
<!--</style>-->
<!--<template>-->
<!--  <div>-->
<!--    <table class="table">-->
<!--      <thead>-->
<!--      <tr>-->
<!--        <th>Title</th>-->
<!--        <th>Destination</th>-->
<!--        <th>Author</th>-->
<!--        <th>Creation date</th>-->
<!--        <th>Actions</th>-->
<!--      </tr>-->
<!--      </thead>-->
<!--      <tbody>-->
<!--      <tr v-for="article in articles" :key="article.id">-->
<!--        <td>-->
<!--          <button @click="openArticle(article.id)" class="btn btn-link">{{ article.title }}</button>-->
<!--        </td>-->
<!--        <td>{{ article.destinationName }}</td>-->
<!--        <td>{{ article.authorName }}</td>-->
<!--        <td>{{ formatDate(article.date) }}</td>-->
<!--        <td>-->
<!--          <button @click="editArticle(article)" class="btn btn-primary">Edit</button>-->
<!--          <span style="margin-right: 10px;"></span>-->
<!--          <button @click="deleteArticle(article.id)" class="btn btn-danger">Delete</button>-->
<!--        </td>-->
<!--      </tr>-->
<!--      </tbody>-->
<!--    </table>-->
<!--    <div v-if="articles.length === 0">-->
<!--      <p>No articles found.</p>-->
<!--    </div>-->

<!--    &lt;!&ndash; Button to create a new article &ndash;&gt;-->
<!--    <button @click="showCreateArticleForm = true" class="btn btn-success mb-3">Create New Article</button>-->

<!--    &lt;!&ndash; Form to create a new article &ndash;&gt;-->
<!--    <div v-if="showCreateArticleForm" class="create-article-form">-->
<!--      <h2>Create New Article</h2>-->
<!--      <form @submit.prevent="createArticle" class="form">-->
<!--        <div class="form-group">-->
<!--          <label for="articleTitle">Title</label>-->
<!--          <input type="text" id="articleTitle" v-model="newArticle.title" class="form-control" required>-->
<!--        </div>-->
<!--        <div class="form-group">-->
<!--          <label for="articleDestination">Destination</label>-->
<!--          <select id="articleDestination" v-model="newArticle.destinationName" class="form-control" required>-->
<!--            <option v-for="destination in destinations" :key="destination.id" :value="destination.name">-->
<!--              {{ destination.name }}-->
<!--            </option>-->
<!--          </select>-->
<!--        </div>-->
<!--        <div class="form-group">-->
<!--          <label for="articleContent">Content</label>-->
<!--          <textarea id="articleContent" v-model="newArticle.content" class="form-control" required></textarea>-->
<!--        </div>-->
<!--        <div class="form-group">-->
<!--          <label for="articleActivities">Activities</label>-->
<!--          <textarea id="articleActivities" v-model="newArticle.activities" class="form-control" required></textarea>-->
<!--        </div>-->
<!--        <button type="submit" class="btn btn-primary">Save</button>-->
<!--        <span style="margin-right: 10px;"></span>-->
<!--        <button @click="showCreateArticleForm = false" class="btn btn-secondary">Cancel</button>-->
<!--      </form>-->
<!--    </div>-->

<!--    &lt;!&ndash; Form to edit an article &ndash;&gt;-->
<!--    <div v-if="showEditArticleForm" class="edit-article-form">-->
<!--      <h2>Edit Article</h2>-->
<!--      <form @submit.prevent="updateArticle" class="form">-->
<!--        <div class="form-group">-->
<!--          <label for="editArticleTitle">Title</label>-->
<!--          <input type="text" id="editArticleTitle" v-model="currentArticle.title" class="form-control" required>-->
<!--        </div>-->
<!--        <div class="form-group">-->
<!--          <label for="editArticleDestination">Destination</label>-->
<!--          <select id="editArticleDestination" v-model="currentArticle.destinationName" class="form-control" required>-->
<!--            <option v-for="destination in destinations" :key="destination.id" :value="destination.name">-->
<!--              {{ destination.name }}-->
<!--            </option>-->
<!--          </select>-->
<!--        </div>-->
<!--        <div class="form-group">-->
<!--          <label for="editArticleContent">Content</label>-->
<!--          <textarea id="editArticleContent" v-model="currentArticle.content" class="form-control" required></textarea>-->
<!--        </div>-->
<!--        <div class="form-group">-->
<!--          <label for="editArticleActivities">Activities</label>-->
<!--          <textarea id="editArticleActivities" v-model="currentArticle.activities" class="form-control" required></textarea>-->
<!--        </div>-->
<!--        <br>-->
<!--        <button type="submit" class="btn btn-primary">Save Changes</button>-->
<!--        <span style="margin-right: 10px;"></span>-->
<!--        <button @click="showEditArticleForm = false" class="btn btn-secondary">Cancel</button>-->
<!--      </form>-->
<!--    </div>-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->


<!--import axios from "axios";-->

<!--export default {-->
<!--  props: {-->
<!--    articles: {-->
<!--      type: Array,-->
<!--      required: true-->
<!--    }-->
<!--  },-->
<!--  data() {-->
<!--    return {-->
<!--      showCreateArticleForm: false,-->
<!--      showEditArticleForm: false,-->
<!--      destinations: [],-->
<!--      newArticle: {-->
<!--        title: '',-->
<!--        destinationName: '',-->
<!--        content: '',-->
<!--        activities: ''-->
<!--      },-->
<!--      currentArticle: {-->
<!--        id: null,-->
<!--        title: '',-->
<!--        destinationName: '',-->
<!--        content: '',-->
<!--        activities: ''-->
<!--      }-->
<!--    };-->
<!--  },-->
<!--  created() {-->
<!--    this.fetchDestinations();-->
<!--  },-->
<!--  methods: {-->
<!--    async openArticle(articleId) {-->
<!--      if (!articleId) {-->
<!--        console.error("Invalid articleId:", articleId);-->
<!--        return;-->
<!--      }-->
<!--      await axios.put(`http://localhost:8080/api/articles/increment_visits/${articleId}`);-->

<!--      const url = this.$router.resolve({ path: `/single-article/${articleId}` }).href;-->
<!--      window.open(url, '_blank');-->
<!--    },-->
<!--    fetchDestinations() {-->
<!--      this.$axios.get('http://localhost:8080/api/destinations')-->
<!--         .then(response => {-->
<!--            this.destinations = response.data;-->
<!--         })-->
<!--         .catch(error => {-->
<!--           console.error('Error fetching destinations:', error);-->
<!--         });-->
<!--    },-->
<!--    formatDate(date) {-->
<!--      const options = { year: 'numeric', month: 'long', day: 'numeric' };-->
<!--      return new Date(date).toLocaleDateString(undefined, options);-->
<!--    },-->
<!--    editArticle(article) {-->
<!--      this.currentArticle = { ...article };-->
<!--      this.showEditArticleForm = true;-->
<!--    },-->
<!--    deleteArticle(articleId) {-->
<!--      this.$axios.delete(`http://localhost:8080/api/articles/${articleId}`)-->
<!--          .then(response => {-->
<!--            console.log('Article deleted:', response.data);-->
<!--            alert('Article successfully deleted.');-->
<!--            // Emit event to notify parent component about the deletion-->
<!--            this.$emit('articleDeleted', articleId);-->

<!--          })-->
<!--          .catch(error => {-->
<!--            console.error('Error deleting article:', error);-->
<!--            alert('Error deleting article.');-->
<!--          });-->
<!--    },-->
<!--    createArticle() {-->
<!--      console.log(this.newArticle.destinationName);-->
<!--      this.$axios.get(`http://localhost:8080/api/destinations/destination_name/${this.newArticle.destinationName}`)-->
<!--          .then(destinationResponse => {-->
<!--            const destinationId = destinationResponse.data.id;-->
<!--            const token = localStorage.getItem('jwt');-->
<!--            this.$axios.get(`http://localhost:8080/api/users/jwt/${token}`, {-->
<!--              headers: { Authorization: `Bearer ${token}` }-->
<!--            })-->
<!--                .then(jwtResponse => {-->
<!--                  const username = jwtResponse.data;-->
<!--                  this.$axios.get(`http://localhost:8080/api/users/user_name/${username}`)-->
<!--                      .then(userResponse => {-->
<!--                        const authorId = userResponse.data.id;-->
<!--                        const currentDate = new Date();-->
<!--                        currentDate.setDate(currentDate.getDate() + 1);-->
<!--                        const articleData = {-->
<!--                          title: this.newArticle.title,-->
<!--                          content: this.newArticle.content,-->
<!--                          date: currentDate.toISOString().slice(0, 10),-->
<!--                          visits: 0,-->
<!--                          activities: this.newArticle.activities,-->
<!--                          authorId: authorId,-->
<!--                          destinationId: destinationId-->
<!--                        };-->
<!--                        this.$axios.post('http://localhost:8080/api/articles', articleData)-->
<!--                            .then(response => {-->
<!--                              console.log('Article created:', response.data);-->
<!--                              alert('Article successfully created.');-->
<!--                              this.showCreateArticleForm = false;-->
<!--                              this.newArticle = {-->
<!--                                title: '',-->
<!--                                destinationName: '',-->
<!--                                content: '',-->
<!--                                activities: ''-->
<!--                              };-->
<!--                              this.$emit('articleAdded');-->
<!--                            })-->
<!--                            .catch(error => {-->
<!--                              console.error('Error creating article:', error);-->
<!--                              alert('Error creating article.');-->
<!--                            });-->
<!--                      })-->
<!--                      .catch(error => {-->
<!--                        console.error('Error fetching user:', error);-->
<!--                        alert('Error creating article.');-->
<!--                      });-->
<!--                })-->
<!--                .catch(error => {-->
<!--                  console.error('Error fetching username from JWT:', error);-->
<!--                  alert('Error creating article.');-->
<!--                });-->
<!--          })-->
<!--          .catch(error => {-->
<!--            console.error('Error fetching destination:', error);-->
<!--            alert('Error creating article.');-->
<!--          });-->
<!--    },-->
<!--    updateArticle() {-->
<!--      console.log(this.currentArticle.destinationName);-->
<!--      this.$axios.get(`http://localhost:8080/api/destinations/destination_name/${this.currentArticle.destinationName}`)-->
<!--          .then(destinationResponse => {-->
<!--            const destinationId = destinationResponse.data.id;-->
<!--            const token = localStorage.getItem('jwt');-->
<!--            this.$axios.get(`http://localhost:8080/api/users/jwt/${token}`, {-->
<!--              headers: { Authorization: `Bearer ${token}` }-->
<!--            })-->
<!--                .then(jwtResponse => {-->
<!--                  const username = jwtResponse.data;-->
<!--                  this.$axios.get(`http://localhost:8080/api/users/user_name/${username}`)-->
<!--                      .then(userResponse => {-->
<!--                        const authorId = userResponse.data.id;-->
<!--                        const articleData = {-->
<!--                          id: this.currentArticle.id,-->
<!--                          title: this.currentArticle.title,-->
<!--                          content: this.currentArticle.content,-->
<!--                          activities: this.currentArticle.activities,-->
<!--                          authorId: authorId,-->
<!--                          destinationId: destinationId-->
<!--                        };-->
<!--                        this.$axios.put(`http://localhost:8080/api/articles/`, articleData)-->
<!--                            .then(response => {-->
<!--                              console.log('Article updated:', response.data);-->
<!--                              alert('Article successfully updated.');-->
<!--                              this.showEditArticleForm = false;-->
<!--                              this.currentArticle = {-->
<!--                                id: null,-->
<!--                                title: '',-->
<!--                                destinationName: '',-->
<!--                                content: '',-->
<!--                                activities: ''-->
<!--                              };-->
<!--                              this.$emit('articleUpdated');-->
<!--                            })-->
<!--                            .catch(error => {-->
<!--                              console.error('Error updating article:', error);-->
<!--                              alert('Error updating article.');-->
<!--                            });-->
<!--                      })-->
<!--                      .catch(error => {-->
<!--                        console.error('Error fetching user:', error);-->
<!--                        alert('Error updating article.');-->
<!--                      });-->
<!--                })-->
<!--                .catch(error => {-->
<!--                  console.error('Error fetching username from JWT:', error);-->
<!--                  alert('Error updating article.');-->
<!--                });-->
<!--          })-->
<!--          .catch(error => {-->
<!--            console.error('Error fetching destination:', error);-->
<!--            alert('Error updating article.');-->
<!--          });-->
<!--    }-->
<!--  }-->
<!--};-->
<!--</script>-->

<!--<style scoped>-->
<!--.create-article-form, .edit-article-form {-->
<!--  margin-top: 20px;-->
<!--  margin-bottom: 20px;-->
<!--  padding: 20px;-->
<!--  border: 1px solid #ccc;-->
<!--  border-radius: 5px;-->
<!--}-->
<!--</style>-->

<template>
  <div>
    <table v-if="articlesLoaded" class="table">
      <thead>
      <tr>
        <th>Title</th>
        <th>Destination</th>
        <th>Author</th>
        <th>Creation date</th>
        <th>Actions</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="article in articles" :key="article.id">
        <td>
          <button @click="openArticle(article.id)" class="btn btn-link">{{ article.title }}</button>
        </td>
        <td>{{ article.destinationName }}</td>
        <td>{{ article.authorName }}</td>
        <td>{{ formatDate(article.date) }}</td>
        <td>
          <button @click="editArticle(article)" class="btn btn-primary">Edit</button>
          <span style="margin-right: 10px;"></span>
          <button @click="deleteArticle(article.id)" class="btn btn-danger">Delete</button>
        </td>
      </tr>
      </tbody>
    </table>
    <div v-if="articles.length === 0">
      <p>No articles found.</p>
    </div>

    <!-- Button to create a new article -->
    <button v-if="articlesLoaded" @click="showCreateArticleForm = true" class="btn btn-success mb-3">Create New Article</button>

    <!-- Form to create a new article -->
    <div v-if="showCreateArticleForm" class="create-article-form">
      <h2>Create New Article</h2>
      <form @submit.prevent="createArticle" class="form">
        <div class="form-group">
          <label for="articleTitle">Title</label>
          <input type="text" id="articleTitle" v-model="newArticle.title" class="form-control" required>
        </div>
        <div class="form-group">
          <label for="articleDestination">Destination</label>
          <select id="articleDestination" v-model="newArticle.destinationName" class="form-control" required>
            <option v-for="destination in destinations" :key="destination.id" :value="destination.name">
              {{ destination.name }}
            </option>
          </select>
        </div>
        <div class="form-group">
          <label for="articleContent">Content</label>
          <textarea id="articleContent" v-model="newArticle.content" class="form-control" required></textarea>
        </div>
        <div class="form-group">
          <label for="articleActivities">Activities</label>
          <textarea id="articleActivities" v-model="newArticle.activities" class="form-control" required></textarea>
        </div>
        <button type="submit" class="btn btn-primary">Save</button>
        <span style="margin-right: 10px;"></span>
        <button @click="showCreateArticleForm = false" class="btn btn-secondary">Cancel</button>
      </form>
    </div>

    <!-- Form to edit an article -->
    <div v-if="showEditArticleForm" class="edit-article-form">
      <h2>Edit Article</h2>
      <form @submit.prevent="updateArticle" class="form">
        <div class="form-group">
          <label for="editArticleTitle">Title</label>
          <input type="text" id="editArticleTitle" v-model="currentArticle.title" class="form-control" required>
        </div>
        <div class="form-group">
          <label for="editArticleDestination">Destination</label>
          <select id="editArticleDestination" v-model="currentArticle.destinationName" class="form-control" required>
            <option v-for="destination in destinations" :key="destination.id" :value="destination.name">
              {{ destination.name }}
            </option>
          </select>
        </div>
        <div class="form-group">
          <label for="editArticleContent">Content</label>
          <textarea id="editArticleContent" v-model="currentArticle.content" class="form-control" required></textarea>
        </div>
        <div class="form-group">
          <label for="editArticleActivities">Activities</label>
          <textarea id="editArticleActivities" v-model="currentArticle.activities" class="form-control" required></textarea>
        </div>
        <br>
        <button type="submit" class="btn btn-primary">Save Changes</button>
        <span style="margin-right: 10px;"></span>
        <button @click="showEditArticleForm = false" class="btn btn-secondary">Cancel</button>
      </form>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  props: {
    articles: {
      type: Array,
      required: true
    }
  },
  data() {
    return {
      showCreateArticleForm: false,
      showEditArticleForm: false,
      destinations: [],
      newArticle: {
        title: '',
        destinationName: '',
        content: '',
        activities: ''
      },
      currentArticle: {
        id: null,
        title: '',
        destinationName: '',
        content: '',
        activities: ''
      },
      articlesLoaded: false // Dodali smo articlesLoaded promenljivu
    };
  },
  created() {
    this.fetchDestinations();
  },
  watch: {
    articles(newArticles) {
      if (newArticles.length > 0) {
        this.articlesLoaded = true;
      }
    }
  },
  methods: {
    async openArticle(articleId) {
      if (!articleId) {
        console.error("Invalid articleId:", articleId);
        return;
      }
      await axios.put(`http://localhost:8080/api/articles/increment_visits/${articleId}`);

      const url = this.$router.resolve({ path: `/single-article/${articleId}` }).href;
      window.open(url, '_blank');
    },
    fetchDestinations() {
      this.$axios.get('http://localhost:8080/api/destinations')
          .then(response => {
            this.destinations = response.data;
          })
          .catch(error => {
            console.error('Error fetching destinations:', error);
          });
    },
    formatDate(date) {
      const options = { year: 'numeric', month: 'long', day: 'numeric' };
      return new Date(date).toLocaleDateString(undefined, options);
    },
    editArticle(article) {
      this.currentArticle = { ...article };
      this.showEditArticleForm = true;
    },
    deleteArticle(articleId) {
      this.$axios.delete(`http://localhost:8080/api/articles/${articleId}`)
          .then(response => {
            console.log('Article deleted:', response.data);
            alert('Article successfully deleted.');
            // Emit event to notify parent component about the deletion
            this.$emit('articleDeleted');

          })
          .catch(error => {
            console.error('Error deleting article:', error);
            alert('Error deleting article.');
          });
    },
    createArticle() {
      console.log(this.newArticle.destinationName);
      this.$axios.get(`http://localhost:8080/api/destinations/destination_name/${this.newArticle.destinationName}`)
          .then(destinationResponse => {
            const destinationId = destinationResponse.data.id;
            const token = localStorage.getItem('jwt');
            this.$axios.get(`http://localhost:8080/api/users/jwt/${token}`, {
              headers: { Authorization: `Bearer ${token}` }
            })
                .then(jwtResponse => {
                  const username = jwtResponse.data;
                  this.$axios.get(`http://localhost:8080/api/users/user_name/${username}`)
                      .then(userResponse => {
                        const authorId = userResponse.data.id;
                        const currentDate = new Date();
                        currentDate.setDate(currentDate.getDate() + 1);
                        const articleData = {
                          title: this.newArticle.title,
                          content: this.newArticle.content,
                          date: currentDate.toISOString().slice(0, 10),
                          visits: 0,
                          activities: this.newArticle.activities,
                          authorId: authorId,
                          destinationId: destinationId
                        };
                        this.$axios.post('http://localhost:8080/api/articles', articleData)
                            .then(response => {
                              console.log('Article created:', response.data);
                              alert('Article successfully created.');
                              this.showCreateArticleForm = false;
                              this.newArticle = {
                                title: '',
                                destinationName: '',
                                content: '',
                                activities: ''
                              };
                              this.$emit('articleAdded');
                            })
                            .catch(error => {
                              console.error('Error creating article:', error);
                              alert('Error creating article.');
                            });
                      })
                      .catch(error => {
                        console.error('Error fetching user:', error);
                        alert('Error creating article.');
                      });
                })
                .catch(error => {
                  console.error('Error fetching username from JWT:', error);
                  alert('Error creating article.');
                });
          })
          .catch(error => {
            console.error('Error fetching destination:', error);
            alert('Error creating article.');
          });
    },
    updateArticle() {
      console.log(this.currentArticle.destinationName);
      this.$axios.get(`http://localhost:8080/api/destinations/destination_name/${this.currentArticle.destinationName}`)
          .then(destinationResponse => {
            const destinationId = destinationResponse.data.id;
            const token = localStorage.getItem('jwt');
            this.$axios.get(`http://localhost:8080/api/users/jwt/${token}`, {
              headers: { Authorization: `Bearer ${token}` }
            })
                .then(jwtResponse => {
                  const username = jwtResponse.data;
                  this.$axios.get(`http://localhost:8080/api/users/user_name/${username}`)
                      .then(userResponse => {
                        const authorId = userResponse.data.id;
                        const articleData = {
                          id: this.currentArticle.id,
                          title: this.currentArticle.title,
                          content: this.currentArticle.content,
                          activities: this.currentArticle.activities,
                          authorId: authorId,
                          destinationId: destinationId
                        };
                        this.$axios.put(`http://localhost:8080/api/articles/`, articleData)
                            .then(response => {
                              console.log('Article updated:', response.data);
                              alert('Article successfully updated.');
                              this.showEditArticleForm = false;
                              this.currentArticle = {
                                id: null,
                                title: '',
                                destinationName: '',
                                content: '',
                                activities: ''
                              };
                              this.$emit('articleUpdated');
                            })
                            .catch(error => {
                              console.error('Error updating article:', error);
                              alert('Error updating article.');
                            });
                      })
                      .catch(error => {
                        console.error('Error fetching user:', error);
                        alert('Error updating article.');
                      });
                })
                .catch(error => {
                  console.error('Error fetching username from JWT:', error);
                  alert('Error updating article.');
                });
          })
          .catch(error => {
            console.error('Error fetching destination:', error);
            alert('Error updating article.');
          });
    }
  }
};
</script>

<style scoped>
.create-article-form, .edit-article-form {
  margin-top: 20px;
  margin-bottom: 20px;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
}
</style>










