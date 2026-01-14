INSERT INTO recipe (recipe_id, name, time_in_seconds, portion, notes)
VALUES (100, 'Spaghetti Bolognese', 1800, 4, 'Klassisches Rezept');

INSERT INTO ingredient (id, unit, amount, name, recipe_id)
VALUES (11, 'g', 400, 'Spaghetti', 100),
       (21, 'g', 300, 'Hackfleisch', 100);

INSERT INTO instruction (id, order_number, step, recipe_id)
VALUES (11, 1, 'Wasser zum Kochen bringen.', 100),
       (21, 2, 'Spaghetti kochen.', 100),
       (31, 3, 'Sauce zubereiten.', 100);


INSERT INTO recipe (recipe_id, name, time_in_seconds, portion, notes)
VALUES (200, 'Pasta Arrabiata', 1800, 4, 'Simples Rezept');

INSERT INTO ingredient (id, unit, amount, name, recipe_id)
VALUES (31, 'g', 400, 'Nudeln', 200),
       (41, 'g', 300, 'Tomatensauce', 200);

INSERT INTO instruction (id, order_number, step, recipe_id)
VALUES (41, 1, 'Wasser zum Kochen bringen.', 200),
       (51, 2, 'Nudeln kochen.', 200),
       (61, 3, 'Sauce zubereiten.', 200);
