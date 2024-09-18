-- Llenar la tabla tracking_records con datos de prueba utilizando user_id del 101 al 110

-- Insertar datos de prueba
INSERT INTO tracking_records (user_id, goal_id, date, value, note)
VALUES
    (101, 1, '2024-09-01 10:00:00', 5.0, 'Running session - good pace'),
    (102, 2, '2024-09-02 11:30:00', 8.5, 'Weightlifting - intense workout'),
    (103, 3, '2024-09-03 07:15:00', 3.2, 'Morning walk - relaxed pace'),
    (104, 4, '2024-09-04 18:45:00', 7.0, 'Cycling - evening ride'),
    (105, 2, '2024-09-05 06:50:00', 6.4, 'Swimming - endurance training'),
    (106, 5, '2024-09-06 12:30:00', 4.8, 'Yoga session - flexibility focus'),
    (107, 1, '2024-09-07 09:00:00', 6.0, 'Running - speed training'),
    (108, 3, '2024-09-08 16:45:00', 9.1, 'Cycling - long distance'),
    (109, 4, '2024-09-09 10:15:00', 7.5, 'Hiking - mountain trail'),
    (110, 5, '2024-09-10 19:30:00', 5.9, 'Pilates session - core strength');

-- Nota: Ajusta los 'goal_id' según los valores reales en la tabla 'goals'.