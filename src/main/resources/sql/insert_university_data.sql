INSERT  INTO faculties
    (title)
    VALUES
    ('Pysics'),
    ('Math');

INSERT  INTO departments
    (title, faculty_id)
    VALUES
    ('Math and Physics', '1'),
    ('Optic', '1'),
    ('Statistic', '2'),
    ('Geometry', '2');

INSERT  INTO groups
    (title, year, department_id)
    VALUES
    ('MP-11', '1', '1'),
    ('MS-21','2', '3');

INSERT  INTO STUDENTS
    (first_name, last_name, group_id)
    VALUES
    ('Anatoliy', 'Shyrokov', '1'),
    ('Ivan', 'Antonov', '1'),
    ('Dmitry', 'Petrov', '1'),
    ('Petr', 'Sidorov', '1'),
    ('Nikolai', 'Ivanov', '1'),
    ('Sergei', 'Ivanov', '1'),
    ('Alexander', 'Kovalev', '1'),
    ('Alexander', 'Lebedev', '1'),
    ('Semen', 'Pupkin', '1'),
    ('Sergei', 'Pugovkin', '1'),
    ('Nikolai', 'Prokhorov', '1'),
    ('Sergei', 'Denisov', '1'),
    ('Dmitri', 'Petrov', '1'),
    ('Alexei', 'Sidorov', '1'),
    ('Ivan', 'Medvedev', '1'),
    ('Sergei', 'Kuznetsov', '1'),
    ('Maxim', 'Sergeev', '1'),
    ('Alexander', 'Ivanov', '2'),
    ('Dmitri', 'Pupkin', '2'),
    ('Sergei', 'Pyatkin', '2'),
    ('Vasiliy', 'Petrov', '2'),
    ('Alexei', 'Morozov', '2'),
    ('Artem', 'Komov', '2');
