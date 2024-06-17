<template>
  <div>
    <table v-if="tableLoaded" class="table">
      <thead>
      <tr>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Email</th>
        <th>User Type</th>
        <th>Actions</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="user in users" :key="user.id">
        <td>{{ user.name }}</td>
        <td>{{ user.surname }}</td>
        <td>{{ user.email }}</td>
        <td>{{ user.type }}</td>
        <td>
          <button @click="editUser(user)" class="btn btn-primary">Edit</button>
          <span style="margin-right: 10px;"></span>
          <button v-if="user.type !== 'ADMIN'" @click="toggleUserActivation(user)" class="btn" :class="{ 'btn-success': isUserActive(user), 'btn-danger': !isUserActive(user) }">
            {{ isUserActive(user) ? 'Deactivate' : 'Activate' }}
          </button>
        </td>
      </tr>
      </tbody>
    </table>

    <div v-if="users.length === 0">
      <p>No users found.</p>
    </div>

    <!-- Button to create a new user -->
    <button v-if="tableLoaded" @click="showCreateUserForm = true" class="btn btn-success mb-3">Create New User</button>

    <!-- Form to create a new user -->
    <div v-if="showCreateUserForm" class="create-user-form">
      <h2>Create New User</h2>
      <form @submit.prevent="createUser" class="form">
        <div class="form-group">
          <label for="firstName">First Name</label>
          <input type="text" id="firstName" v-model="newUser.name" class="form-control" required>
        </div>
        <div class="form-group">
          <label for="lastName">Last Name</label>
          <input type="text" id="lastName" v-model="newUser.surname" class="form-control" required>
        </div>
        <div class="form-group">
          <label for="email">Email</label>
          <input type="email" id="email" v-model="newUser.email" class="form-control" required>
        </div>
        <div class="form-group">
          <label for="userType">User Type</label>
          <select id="userType" v-model="newUser.type" class="form-control" required>
            <option value="admin">ADMIN</option>
            <option value="CONTENT_CREATOR">CONTENT_CREATOR</option>
          </select>
        </div>
        <div class="form-group">
          <label for="password">Password</label>
          <input type="password" id="password" v-model="newUser.password" class="form-control" required>
        </div>
        <div class="form-group">
          <label for="confirmPassword">Confirm Password</label>
          <input type="password" id="confirmPassword" v-model="confirmPassword" class="form-control" required>
        </div>
        <br>
        <button type="submit" class="btn btn-primary">Save</button>
        <span style="margin-right: 10px;"></span>
        <button @click="showCreateUserForm = false" class="btn btn-secondary">Cancel</button>
      </form>
    </div>

    <!-- Form to edit a user -->
    <div v-if="showEditUserForm" class="edit-user-form">
      <h2>Edit User</h2>
      <form @submit.prevent="updateUser" class="form">
        <div class="form-group">
          <label for="editFirstName">First Name</label>
          <input type="text" id="editFirstName" v-model="currentUser.name" class="form-control" required>
        </div>
        <div class="form-group">
          <label for="editLastName">Last Name</label>
          <input type="text" id="editLastName" v-model="currentUser.surname" class="form-control" required>
        </div>
        <div class="form-group">
          <label for="editEmail">Email</label>
          <input type="email" id="editEmail" v-model="currentUser.email" class="form-control" required>
        </div>
        <div class="form-group">
          <label for="editUserType">User Type</label>
          <select id="editUserType" v-model="currentUser.type" @change="convertToUpperCase" class="form-control" required>
            <option value="admin">ADMIN</option>
            <option value="CONTENT_CREATOR">CONTENT_CREATOR</option>
          </select>
        </div>
        <br>
        <button type="submit" class="btn btn-primary">Save Changes</button>
        <span style="margin-right: 10px;"></span>
        <button @click="showEditUserForm = false" class="btn btn-secondary">Cancel</button>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    users: {
      type: Array,
      required: true
    }
  },
  data() {
    return {
      showCreateUserForm: false,
      showEditUserForm: false,
      confirmPassword: '',
      newUser: {
        name: '',
        surname: '',
        email: '',
        type: 'CONTENT_CREATOR',
        password: ''
      },
      currentUser: {
        id: null,
        name: '',
        surname: '',
        email: '',
        type: ''
      },
      tableLoaded: false
    };
  },
  watch: {
    users(newUsers) {
      if (newUsers.length > 0) {
        this.tableLoaded = true;
      }
    }
  },
  methods: {
    convertToUpperCase() {
      this.newUser.type = this.newUser.type.toUpperCase();
    },
    async createUser() {
      if (this.newUser.password !== this.confirmPassword) {
        alert("Passwords do not match.");
        return;
      }
      try {
        const userToCreate = {
          name: this.newUser.name,
          surname: this.newUser.surname,
          email: this.newUser.email,
          type: this.newUser.type,
          password: this.newUser.password
        };

        const response = await this.$axios.post('/api/users/register', userToCreate);
        console.log('User created:', response.data);
        const statusCode = response.data.statusCode;
        if (statusCode === 200) {
          alert('User successfully created.');
          this.showCreateUserForm = false;
          this.newUser = {
            name: '',
            surname: '',
            email: '',
            type: 'CONTENT_CREATOR',
            password: ''
          };
          this.confirmPassword = ''; // Resetovanje confirmPassword polja
          this.$emit('userAdded');
        } else if (statusCode === 400) {
          alert('User with this email already exists.');
        } else {
          alert('Unexpected status code: ' + statusCode);
        }
      } catch (error) {
        console.error('Error creating user:', error);
        alert('Error creating user.');
      }
    },
    editUser(user) {
      this.currentUser = { ...user };
      this.showEditUserForm = true;
    },
    async updateUser() {
      try {
        console.log(this.currentUser);

        // Pretvaranje tipa korisnika u velika slova
        this.currentUser.type = this.currentUser.type.toUpperCase();

        const response = await this.$axios.put(`/api/users`, this.currentUser);
        console.log('User updated:', response.data);
        alert('User successfully updated.');
        this.showEditUserForm = false;
        this.currentUser = {
          id: null,
          name: '',
          surname: '',
          email: '',
          type: ''
        };
        // Emitovanje događaja za obaveštavanje roditeljske komponente o ažuriranju
        this.$emit('userUpdated');
      } catch (error) {
        console.error('Error updating user:', error);
        alert('Error updating user.');
      }
    },
    async toggleUserActivation(user) {
      try {
        const userStatus = user.status;
        if (userStatus !== 'ACTIVE' && userStatus !== 'INACTIVE') {
          console.error('Invalid user status:', userStatus);
          alert('Invalid user status.');
          return;
        }

        const newStatus = userStatus === 'ACTIVE' ? 'INACTIVE' : 'ACTIVE';
        const response = await this.axios.put(`/api/users/change_status/${user.id}/${newStatus}`);
        console.log('User activation status updated:', response.data);
        alert('User activation status successfully updated.');
        // Emit event to notify parent component about the status change
        this.$emit('userActivationToggled');
      } catch (error) {
        console.error('Error toggling user activation status:', error);
        alert('Error toggling user activation status.');
      }
    },
    isUserActive(user) {
      return user.status === 'ACTIVE';
    }
  }
};
</script>

<style scoped>
.create-user-form, .edit-user-form {
  margin-top: 20px;
  margin-bottom: 20px;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
}
</style>
