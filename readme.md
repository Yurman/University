# University

This is an educational project. It allows me to train my skills in UML diagram composition and designing application for university management.

## Getting Started
To start working with project you need to clone repository into your local workspace.

## Running the tests
Use `mvn test` to run tests and `mvn integration-test` to run integration tests
## Deployment
Open project in your IDE as Maven project.
Run pgAdmin to manage your database.

1. Use sql script `university_db_Init.sql` from `src\main\resources\sql` to create schema and user.
2. Use sql script `test_university_db_Init.sql` from `src\test\resources` to create schema and user for integration tests.
3. Execute `mvn package` command. While executing, Flyway migrations will be performed.
4. Use `insert_university_data.sql` to insert data into the database.
5. To run application, you need to run the following command in a terminal window (in the complete) directory:
`mvn spring-boot:run`

## Built With
Maven
PostgreSQL
## Contributing
- Fork it.
- Create your feature branch: `git checkout -b my-new-feature`
- Commit your changes: `git commit -m 'Add some feature'`
- Push to the branch: `git push origin my-new-feature`
- Submit a pull request :D

## History
- Branch `task-9` Java project was created  based on UML diagram.
- Branch `task-10` DAO layer was created.
- Branch `task-11` DAO layer was rewrote using Spring JDBC. Checkstyle and surefire maven plugins were added.
- Branch `task-12` Service layer was created.
- Branch `task-13` Logging and exception handling was added.
- Branch `task-14` Basic UI was added.
- Branch `task-15` Possibility to add, edit and remove entities was added.
- Branch `task-16` Data Source server configuration was added.
- Branch `task-17` DAO layer was rewrote using Hibernate.
- Branch `task-18` Spring Boot support was added.

## Author

* Opanasenko Yurii
