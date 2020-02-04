--Put data to the tables
INSERT INTO public.cargo (id, name, weight, type, stored_temperature, expiration_date)
VALUES (1, 'APPLE', 100, 'FOOD', -50, '2020-11-11');
INSERT INTO public.cargo (id, name, weight, type, size, material)
VALUES (2, 'JEANS', 300, 'CLOTHERS', 'XXL', 'Cotton');
INSERT INTO public.cargo (id, name, weight, type, stored_temperature, expiration_date)
VALUES (3, 'ORANGE', 300, 'FOOD', -20, '2025-12-20');
INSERT INTO public.cargo (id, name, weight, type, size, material)
VALUES (4, 'Clothes_name_1', 150, 'CLOTHERS', 'L', 'Linen');
INSERT INTO public.cargo (id, name, weight, type, stored_temperature, expiration_date)
VALUES (5, 'BANANA', 500, 'FOOD', 10, '2020-12-20');
INSERT INTO public.cargo (id, name, weight, type, material)
VALUES (6, 'SOCKS', 800, 'CLOTHERS', 'Cotton');

INSERT INTO public.carrier (id, name, address, type)
VALUES (1, 'Carrier_Name', 'Spb, Nevskiy 800', 'SHIP');
INSERT INTO public.carrier (id, name, address, type)
VALUES (2, 'Carrier_Name', 'Sp, Prosveheniya 900', 'PLANE');

INSERT INTO public.transportation (description, billTo, begin_date, cargo_id, carrier_id)
VALUES ('Vip order', 'Ivanov', '2010-11-11', 1, 1);
INSERT INTO public.transportation (description, billTo, begin_date, cargo_id, carrier_id)
VALUES ('Fragile', 'Petrov', '2011-11-11', 2, 1);
INSERT INTO public.transportation (description, billTo, begin_date, cargo_id, carrier_id)
VALUES ('Fragile', 'Sidorov', '2017-01-01', 6, 2);
