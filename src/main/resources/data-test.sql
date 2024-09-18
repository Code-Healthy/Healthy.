-- Insertando datos en la tabla plans

INSERT INTO plans (user_id, name, description, start_date, end_date, plan_status)
VALUES
    (101, 'Fitness Plan', 'A 3-month workout routine designed to build strength and endurance.', '2024-01-01 08:00:00', '2024-04-01 08:00:00', 'ACTIVE'),
    (102, 'Healthy Eating Plan', 'A nutritional guide for healthy eating over a 6-month period.', '2024-02-01 08:00:00', '2024-08-01 08:00:00', 'COMPLETED'),
    (103, 'Reading Challenge', 'A plan to read 10 books in 3 months.', '2024-03-01 08:00:00', '2024-06-01 08:00:00', 'ABANDONED');
