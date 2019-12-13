CREATE TABLE IF NOT EXISTS faculties
    (
        id INT NOT NULL
            GENERATED ALWAYS AS IDENTITY UNIQUE
            PRIMARY KEY,
        title VARCHAR (20) NOT NULL
    );

CREATE TABLE IF NOT EXISTS departments
    (
        id INT NOT NULL 
            GENERATED ALWAYS AS IDENTITY UNIQUE
            PRIMARY KEY,
        title VARCHAR (20) NOT NULL,
        faculty_id INT  NOT NULL
            REFERENCES faculties(id)
            ON UPDATE CASCADE
            ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS groups
    (
        id INT NOT NULL 
            GENERATED ALWAYS AS IDENTITY UNIQUE
            PRIMARY KEY,
        year INT NOT NULL,
        title VARCHAR (50) NOT NULL,
        department_id INT  NOT NULL
            REFERENCES departments(id) 
            ON UPDATE CASCADE
            ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS students
    (
        id INT NOT NULL 
            GENERATED ALWAYS AS IDENTITY UNIQUE
            PRIMARY KEY,
        first_name VARCHAR (20) NOT NULL,
        last_name VARCHAR (50) NOT NULL,
        group_id INT NOT NULL
            REFERENCES groups(id) 
            ON UPDATE CASCADE
            ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS professors
    (
        id INT NOT NULL 
            GENERATED ALWAYS AS IDENTITY 
            UNIQUE PRIMARY KEY,
        first_name VARCHAR (20) NOT NULL,
        last_name VARCHAR (50) NOT NULL,
        position VARCHAR(45) NOT NULL,
        degree VARCHAR (45) NOT NULL,
        age INT NOT NULL,
        department_id INT  
            REFERENCES faculties(id) 
            ON UPDATE CASCADE
            ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS rooms
    (
        id INT NOT NULL 
            GENERATED ALWAYS AS IDENTITY UNIQUE
            PRIMARY KEY,
        number INT NOT NULL
    );

CREATE TABLE IF NOT EXISTS lections
    (
        id INT NOT NULL 
            GENERATED ALWAYS AS IDENTITY UNIQUE 
            PRIMARY KEY,
        title VARCHAR (20) NOT NULL,
        start_time TIME NOT NULL,
        end_time TIME NOT NULL
    );

CREATE TABLE IF NOT EXISTS schedule_items
    (
        id INT NOT NULL 
            GENERATED ALWAYS AS IDENTITY UNIQUE 
            PRIMARY KEY,
        lection_id INT NOT NULL 
            REFERENCES lections(id) 
            ON UPDATE CASCADE
            ON DELETE CASCADE, 
        room_id INT NOT NULL 
            REFERENCES rooms(id)
            ON UPDATE CASCADE
            ON DELETE CASCADE,
        professor_id INT NOT NULL
            REFERENCES professors(id)
            ON UPDATE CASCADE
            ON DELETE CASCADE,
        item_date DATE NOT NULL
    );

CREATE TABLE IF NOT EXISTS schedule_items_groups
    (
        schedule_items_id INT NOT NULL UNIQUE 
            PRIMARY KEY 
            REFERENCES schedule_items(id) 
            ON UPDATE CASCADE
            ON DELETE CASCADE,
        group_id INT NOT NULL UNIQUE 
            REFERENCES groups(id)
            ON UPDATE CASCADE
            ON DELETE CASCADE
    );
