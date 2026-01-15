INSERT INTO recipe (recipe_id, name, time_in_seconds, portion, notes)
VALUES (10, 'Spaghetti Bolognese', 1800, 4, 'Klassisches Rezept');

INSERT INTO ingredient (id, unit, amount, name, recipe_id)
VALUES (10, 'g', 400, 'Kartoffeln', 10),
       (20, 'g', 300, 'Hackfleisch', 10);

INSERT INTO instruction (id, order_number, step, recipe_id)
VALUES (10, 1, 'Wasser zum Kochen bringen.', 10),
       (20, 2, 'Spaghetti kochen.', 10),
       (30, 3, 'Sauce zubereiten.', 10);


INSERT INTO recipe (recipe_id, name, time_in_seconds, portion, notes)
VALUES (20, 'Pasta Arrabiata', 1800, 4, 'Simples Rezept');

INSERT INTO ingredient (id, unit, amount, name, recipe_id)
VALUES (30, 'g', 400, 'Nudeln', 20),
       (40, 'g', 300, 'Tomatensauce', 20);

INSERT INTO instruction (id, order_number, step, recipe_id)
VALUES (40, 1, 'Wasser zum Kochen bringen.', 20),
       (50, 2, 'Nudeln kochen.', 20),
       (60, 3, 'Sauce zubereiten.', 20);
