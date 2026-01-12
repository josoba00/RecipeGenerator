INSERT INTO recipe (recipe_id, name, time_in_seconds, portion, notes)
VALUES (1, 'Spaghetti Bolognese', 1800, 4, 'Klassisches Rezept');

INSERT INTO ingredient (id, unit, amount, name, recipe_id)
VALUES (1, 'g', 400, 'Spaghetti', 1),
       (2, 'g', 300, 'Hackfleisch', 1);

INSERT INTO instruction (id, order_number, step, recipe_id)
VALUES (1, 1, 'Wasser zum Kochen bringen.', 1),
       (2, 2, 'Spaghetti kochen.', 1),
       (3, 3, 'Sauce zubereiten.', 1);


INSERT INTO recipe (recipe_id, name, time_in_seconds, portion, notes)
VALUES (2, 'Pasta Arrabiata', 1800, 4, 'Simples Rezept');

INSERT INTO ingredient (id, unit, amount, name, recipe_id)
VALUES (3, 'g', 400, 'Nudeln', 2),
       (4, 'g', 300, 'Tomatensauce', 2);

INSERT INTO instruction (id, order_number, step, recipe_id)
VALUES (4, 1, 'Wasser zum Kochen bringen.', 2),
       (5, 2, 'Nudeln kochen.', 2),
       (6, 3, 'Sauce zubereiten.', 2);
