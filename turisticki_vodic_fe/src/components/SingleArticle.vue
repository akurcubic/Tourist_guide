<template>
  <div v-if="article">
    <h1>{{ article.title }}</h1>
    <p>{{ article.content }}</p>
    <p><strong>Date:</strong> {{ formatDate(article.date) }}</p>
    <p v-if="authorName"><strong>Author Name:</strong> {{ authorName }}</p>
    <p v-else>Loading author...</p>
    <p v-if="destinationName"><strong>Destination:</strong> {{ destinationName }}</p>
    <p v-else>Loading destination...</p>
    <h2>Activities</h2>
    <ul>
      <!-- Use router-link to navigate to ArticlesForActivity -->
      <router-link v-for="activity in activities" :key="activity.id" :to="'/articles-for-activity/' + activity.id">
        <li>{{ activity.name }}</li>
      </router-link>
    </ul>
    <h2>Comments</h2>
    <br>
    <button @click="showForm = true">Add Comment</button>
    <div v-if="showForm">
      <br>
      <h2>Add Comment</h2>
      <form @submit.prevent="addComment">
        <div class="form-group">
          <label for="author">Author:</label>
          <input type="text" class="form-control" id="author" v-model="newComment.author" required>
        </div>
        <div class="form-group">
          <label for="content">Content:</label>
          <textarea class="form-control" id="content" v-model="newComment.content" required></textarea>
        </div>
        <br>
        <button type="submit" class="btn btn-primary">Create</button>
        <span style="margin-right: 10px;"></span>
        <button type="button" @click="showForm = false" class="btn btn-secondary">Close</button>
      </form>
    </div>

    <br>
    <br>
    <ul>
      <li v-for="comment in comments" :key="comment.id" class="card">
        <div class="card-header">{{ comment.author }}</div>
        <div class="card-body">{{ comment.content }}</div>
        <div class="card-footer">{{ formatDate(comment.date) }}</div>
      </li>
    </ul>
    <div class="pagination-buttons">
      <button @click="prevPage" :disabled="page <= 1">⬅️</button>
      <span style="margin-right: 10px;"></span>
      <button @click="nextPage" :disabled="page >= totalPages">➡️</button>
    </div>
  </div>
  <div v-else>
    <p>Loading article...</p>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  props: {
    articleId: {
      type: Number,
      required: true
    }
  },
  data() {
    return {
      article: null,
      authorName: null,
      destinationName: null,
      activities: [],
      comments: [],
      newComment: {
        author: '',
        content: ''
      },
      page: 1,
      totalPages: 0,
      showForm: false
    };
  },
  mounted() {
    this.fetchArticle();
    this.fetchActivities();
    this.fetchComments();
  },
  methods: {
    fetchArticle() {
      axios.get(`http://localhost:8080/api/articles/${this.articleId}`)
          .then(response => {
            this.article = response.data;

            // Fetch author name
            return axios.get(`http://localhost:8080/api/users/${this.article.authorId}`);
          })
          .then(userResponse => {
            this.authorName = userResponse.data.name;

            // Fetch destination name
            return axios.get(`http://localhost:8080/api/destinations/${this.article.destinationId}`);
          })
          .then(destinationResponse => {
            this.destinationName = destinationResponse.data.name;
          })
          .catch(error => {
            console.error('Error fetching article or related data:', error);
          });
    },
    fetchActivities() {
      axios.get(`http://localhost:8080/api/articlesactivities/article/${this.articleId}`)
          .then(response => {
            this.activities = response.data;
          })
          .catch(error => {
            console.error('Error fetching activities:', error);
          });
    },
    fetchComments() {
      axios.get(`http://localhost:8080/api/comments/article/${this.articleId}/${this.page}`)
          .then(response => {
            this.comments = response.data;
            console.log(this.comments);
            this.totalPages = response.data.totalPages;
          })
          .catch(error => {
            console.error('Error fetching comments:', error);
          });
    },
    formatDate(dateString) {
      const options = { year: 'numeric', month: 'long', day: 'numeric' };
      // return new Date(dateString).toLocaleDateString('en-US', options);
      return new Date(new Date(dateString).setDate(new Date(dateString).getDate() + 1)).toLocaleDateString('en-US', options);

    },
    addComment() {
      this.newComment.date = new Date().toISOString();
      this.newComment.articleId = this.articleId;
      axios.post(`http://localhost:8080/api/comments`, this.newComment)
          .then(response => {
            console.log('Comment added successfully:', response.data);
            this.showForm = false;
            this.newComment.author = '';
            this.newComment.content = '';
            this.fetchComments();
          })
          .catch(error => {
            console.error('Error adding comment:', error);
          });
    },
    nextPage() {
      this.page++;
      this.fetchComments();

    },
    prevPage() {
      if (this.page > 1) {
        this.page--;
        this.fetchComments();
      }
    }
  }
}
</script>

<style scoped>
h1 {
  font-size: 2em;
  margin-bottom: 0.5em;
}

p {
  margin: 0.5em 0;
}

ul {
  list-style-type: none;
  padding: 0;
}

.card {
  border: 1px solid #ccc;
  border-radius: 5px;
  margin-bottom: 10px;
}

.card-header,
.card-footer {
  background-color: #f0f0f0;
  padding: 5px;
}

.card-body {
  padding: 10px;
}
</style>
