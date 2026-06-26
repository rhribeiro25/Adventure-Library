# Adventure-Library
Develop an interactive adventure book as an application using Angular and Java with Spring Boot for the backend, adhering to the design provided below.

## APIs:

GET    /api/v1/books?query=&author=&difficulty=&category=&page=0&size=10&sort=title,asc
GET    /api/v1/books/{bookId}

PUT    /api/v1/books/{bookId}/categories/{categoryName}
DELETE /api/v1/books/{bookId}/categories/{categoryName}

POST   /api/v1/books/{bookId}/games
GET    /api/v1/games/{gameId}

POST   /api/v1/games/{gameId}/choices
PATCH  /api/v1/games/{gameId}

POST   /api/v1/books
