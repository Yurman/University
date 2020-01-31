# University

This is an educational project. It allows me to train my skills in UML diagram composition and designing application for university management.

## Getting Started
To start working with project you need to clone repository into your local workspace.

## Running the tests
Use `mvn test` to run tests and `mvn integration-test` to run integration tests
## Deployment
Open project in your IDE as Maven project.
Run pgAdmin to manage your database.

1.Use sql script `university_db_Init.sql` from `src\main\resources\sql` to create schema and user.
2.Use sql script `test_university_db_Init.sql` from `src\test\resources\` to create schema and user for integration tests.
2.Execute `mvn package` command. While executing, Flyway migrations will be performed.
3.Use `insert_university_data.sql` to insert data into the database.
4.Run application on your local server.
## Built With
Maven
Tomcat
PostgreSQL
## Contributing
Fork it.
Create your feature branch: `git checkout -b my-new-feature`
Commit your changes: `git commit -m 'Add some feature'`
Push to the branch: `git push origin my-new-feature`
Submit a pull request :D
## History
branch `task-9` Java project was created  based on UML diagram.
branch `task-10` DAO layer was created.
branch `task-11` DAO layer was rewrote using Spring JDBC. Checkstyle and surefire maven plugins were added.
branch `task-12` Service layer was created.
branch `task-13` Logging and exception handling was added

## Author

* Opanasenko Yurii




