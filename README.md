# Adventure-Library
Develop an interactive adventure book as an application using Angular and Java with Spring Boot for the backend, adhering to the design provided below.

### APIs

- GET    /api/v1/books?query=&author=&difficulty=&category=&page=0&size=10&sort=title,asc
- GET    /api/v1/books/{bookId}
- POST    /api/v1/books/{bookId}/categories/{categoryId}
- DELETE /api/v1/books/{bookId}/categories/{categoryId}
- POST   /api/v1/books/{bookId}/games
- GET    /api/v1/games/{gameId}
- POST   /api/v1/games/{gameId}/choices
- PATCH  /api/v1/games/{gameId}
- POST   /api/v1/books

### My Contributions

- Application architecture (Clean Architecture).
- Domain modeling and business logic.
- REST API design.
- Persistence layer implementation.
- Facade Validations for adventure books.
- Exception handling implementation.
- Solution design and technology selection.

### AI support

- Generating initial domain models.
```text
Using this pdf interview test. give me the model layer.
```
- Generating JPA entity.
```text
Give me the entity layer.
```
- Creating PlantUML sequence diagrams.
```text
Using these examples generate diagrams of my test, of each sequence.
```
- Reviewing exception handling based on Spring Boot 4 best practices.
```text
How to solve this error: 'ErrorResponse' is abstract; cannot be instantiated
```



