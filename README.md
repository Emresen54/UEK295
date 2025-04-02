# ÜK 295 – ProductStore

##  Default Users (auto-inserted at startup)

| Nr.   | Username | Password   | Role   |
|-----|----------|------------|--------|
| 1°  | `admin`  | `admin123` | ADMIN  |
| 2°  | `user`   | `123`      | USER   |

---

## How to Start the Project

> Make sure Docker and Docker Compose are installed.

1. Open a terminal in the project folder  
2. Run this command:

```bash
docker-compose up --build
```

This will:

- Start the **backend API** on `http://localhost:8080`
- Start the **Adminer database viewer** on `http://localhost:8081`
- Create the default users automatically if the database is empty

---

##  How to Access Everything

### 1. API Backend

Open your browser and go to:

[http://localhost:8080](http://localhost:8080)

This is the backend. You can test it using Postman or Swagger.

---

### 2. API Documentation (Swagger UI)

Interactive documentation where you can see all endpoints and test this.

[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

Example:
- Login via `POST /auth/login`
- Copy the token
- Click “Authorize” and paste it to unlock secured routes

---

### 3. Adminer – Database GUI

See the database and check the inserted users.

[http://localhost:8081](http://localhost:8081)

**Login credentials:**

- **System:** MariaDB  
- **Server:** `db`  
- **User:** `root`  
- **Password:** `123`  
- **Database:** `product_manager`
