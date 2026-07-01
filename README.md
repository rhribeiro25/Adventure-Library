# Adventure-Library

<details>
  <summary><strong><span style="font-size: 1.1em;">
    💼 Challenge Description
  </span></strong></summary>

###### Goal

Develop an interactive **Adventure Book** application using **Angular** for the frontend and **Java with Spring Boot** for the backend. The application should allow players to navigate through interactive books where each decision leads to a different section of the story. :contentReference[oaicite:0]{index=0}

---

###### Scenario

Adventure books immerse the reader in a branching story composed of numbered sections. At the end of each section, the player chooses an option that determines the next section to read. The journey continues until the player reaches an ending or dies. :contentReference[oaicite:1]{index=1}

The application must allow users to:

- Browse available books
- Search and filter books
- Start and play an adventure
- Navigate between sections
- Handle game consequences (health, death, victory)
- Save and resume game progress (extra feature)

---

###### Book Validation Rules

A book is considered **invalid** if any of the following conditions are met:

- It contains no **BEGIN** section or more than one **BEGIN** section.
- It contains no **END** section.
- Any option references an invalid next section.
- Any non-ending section contains no navigation options.

---

###### Gameplay Rules

- Every player starts with 10 Health Points (HP).
- Choosing an option may trigger a consequence.
- Consequences can increase or decrease HP.
- If HP reaches 0, the player dies.
- The game ends when:
    - the player reaches an END section, or
    - the player's HP reaches 0.

---

###### Backend Objectives (Spring Boot)

###### Core Features

- Browse, list and search books by title, author, category and difficulty.
- View detailed book information, including categories.
- Add and remove book categories.
- Start and navigate through interactive adventure books.
- Apply gameplay consequences and manage the player's health system.
- Display the current book title and player health during gameplay.
- Allow players to choose among the available options to progress through the story.
- Pause, stop and save game progression.

###### Extra Features

- Support multiple players, each with their own independent game progress.
- Add new adventure books to the collection.

---

###### Technical Notes

- Objectives should be implemented in the specified order.
- Extra objectives are evaluated only after the mandatory requirements are completed.
- AI-assisted development is allowed, provided its usage is documented in the project README, including prompts and the parts implemented manually.

---

</details>

<details>
  <summary><strong><span style="font-size: 1.1em;">
    🌐 Swagger UI
  </span></strong></summary>

###### Local Swagger Access
```text
http://localhost:9090/swagger-ui/index.html
```

###### RESTful APIs exposed through Swagger UI
```text
* GET   /api/v1/books?query=&title=&author=&difficulty=&category=&page=0&size=10&sort=title&direction=asc
* GET   /api/v1/books/{bookId}
* PATCH /api/v1/books/{bookId}
* POST  /api/v1/books
* POST  /api/v1/categories
* PATCH /api/v1/games/{gameId}?status=PAUSED
* PATCH /api/v1/games/{gameId}/choices?optionId=20
```
</details>

<details>
  <summary><strong><span style="font-size: 1.1em;">
    🏗️ My Contributions
  </span></strong></summary>

######
* Designed the application architecture following Clean Architecture principles.
* Modeled the domain layer and implemented the core business rules.
* Designed and implemented the REST API contracts.
* Implemented the persistence layer using JPA, PostgreSQL and Flyway.
* Created validation facades for adventure book rules.
* Applied the Data Transfer Object pattern for API requests and responses.
* Implemented centralized exception handling.
* Designed the solution structure and selected the main technologies.
* Added Redis cache for book details and paginated book search.
* Separated domain models from persistence entities.
* Implemented optimistic locking support for concurrent updates.
* Created PlantUML diagrams to document the main application flows.
</details>

<details>
  <summary><strong><span style="font-size: 1.1em;">
    🤖 AI Support
  </span></strong></summary>

######
```text
1. Using this PDF interview test, suggest the initial domain model for the Adventure Book application.
```
```text
2. Based on this domain model, generate JPA entities using Spring Boot, JPA and PostgreSQL.
```
```text
3. Review these validation classes against the PDF rules: one BEGIN section, at least one END section, valid next section ids, and options for non-ending sections.
```
```text
4. Generate the Spring Boot use case, service, port, adapter and repository structure for navigating a game choice.
```
```text
5. Help me separate game navigation, consequence handling and game result validation using clean architecture principles.
```
```text
6. Generate a PostgreSQL Flyway migration for books, categories, sections, options, consequences, players and games.
```
```text
7. Generate PlantUML sequence diagrams for search books, get book details, create book, navigate game, update game status and create category.
```
```text
8. Review Redis cache usage for paginated book search and book details, including cache key strategy and eviction after writes.
```
```text
9. Review this Spring Boot 4 exception handling error and suggest a compatible implementation: "ErrorResponse is abstract; cannot be instantiated".
```
```text
10. Review Java package naming and SonarQube issues for package naming conventions.
```
</details>

<details>
  <summary><strong><span style="font-size: 1.1em;">
    ⚙️ Setup Essentials
  </span></strong></summary>

###### Tools
- *Git*
- *Docker 24 +*
- *Docker Compose*
</details>

<details>
  <summary><strong><span style="font-size: 1.1em;">
    ▶️ How to Run
  </span></strong></summary>

###### Commands
```bash
git clone https://github.com/rhribeiro25/Adventure-Library.git
```
```bash
cd Adventure-Library
```
```bash
docker compose up --build
```
</details>

<details>
  <summary><strong><span style="font-size: 1.1em;">
    🗄️ Accessing data
  </span></strong></summary>

###### PostgreSQL Conection
```text
JDBC URL: jdbc:postgresql://localhost:5432/adventure-library
Username: postgres
Password: postgres
```
</details>

<details>
  <summary><strong><span style="font-size: 1.1em;">
    🧩 Sequence Diagrams
  </span></strong></summary>

###### Create Book – Success
![Create Card – Success](src/main/resources/static/docs/diagrams/sequence/images/add-book.png)

---
###### Update Book – Success
![Create Card – Success](src/main/resources/static/docs/diagrams/sequence/images/update-book.png)

---
###### Get Book Details – Success
![Create Card – Success](src/main/resources/static/docs/diagrams/sequence/images/get-book-details.png)

---
###### Search Book – Success
![Create Card – Success](src/main/resources/static/docs/diagrams/sequence/images/search-books-pageable.png)

---
###### Create Category – Success
![Create Card – Success](src/main/resources/static/docs/diagrams/sequence/images/add-category.png)

---
###### Game Navigate – Success
![Create Card – Success](src/main/resources/static/docs/diagrams/sequence/images/game-navigate.png)

---
###### Update Game – Success
![Create Card – Success](src/main/resources/static/docs/diagrams/sequence/images/update-game.png)

---
</details>

<details>
  <summary><strong><span style="font-size: 1.1em;">
    🧪 Testing
  </span></strong></summary>

###### Coverage
</details>

#

