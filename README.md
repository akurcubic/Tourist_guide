# Tourist_guide

# Online Tourist Guide Platform

## Overview

This project is an online platform for a tourist guide, allowing users to explore and discover destinations worldwide. The platform consists of two main parts:
1. **Content Management System (CMS)**
2. **Public Platform for Reading Tourist Articles**

### Content Management System (CMS)

#### Authentication and Authorization

- **Authentication**: Required for accessing the CMS. Only active users with valid credentials can log in. Passwords are stored as hashed values.
- **User Roles**:
  - **Content Editor**: Can create, review, edit, and delete tourist articles.
  - **Administrator**: Has all the capabilities of a Content Editor, plus the ability to manage users (add new users, define their roles, view, and edit users).

#### Functionalities by User Type

- **Content Editor**:
  - **Destinations**:
    - View existing destinations.
    - Add new destinations (unique name required).
    - Edit and delete destinations (cannot delete if articles are associated).
  - **Articles**:
    - View all articles.
    - Create new articles (must be linked to an existing destination).
    - Edit and delete articles (deletes all associated comments).
- **Administrator**: All functionalities of a Content Editor plus:
  - **User Management**:
    - View all users.
    - Add new users (with role selection).
    - Edit user details.
    - Activate/deactivate Content Editors (Administrators are always active).

### Public Platform for Reading Tourist Articles

#### Features

- **Home Page**: Displays a list of the 10 latest articles.
- **Most Read**: Shows the 10 most-read articles from the last 30 days.
- **Destinations**: Lists all destinations, each linking to their associated articles.
- **Article View**: Displays full article content, author, creation date, associated activities, and reader comments (with functionality to add new comments).

### Entities

- **User**: Unique email, name, user type, status, and hashed password.
- **Destination**: General information including name and description.
- **Article**: Title, content, creation time, visit count, author, comments, and activities.
- **Activity**: Keywords describing tourist activities available at a destination.
- **Comment**: Author name, comment text, creation date.

### Additional Requirements

- **Data Storage**: Relational database for all entities and system state.
- **Error Handling**: Validation and error reporting for all operations.
- **Pagination**: Implemented for all tables and lists.

### Technologies

- **Frontend**: Vue.js
- **Backend**: JAX-RS 





