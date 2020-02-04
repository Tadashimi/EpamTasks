--Put data to the tables
INSERT INTO public.cargo_type (id, name) VALUES (1, 'FOOD');
INSERT INTO public.cargo_type (id, name) VALUES (2, 'CLOTHERS');

INSERT INTO public.cargo (id, name, weight, type_id, stored_temperature, expiration_date)
VALUES (1, 'APPLE', 100, 1, -50, '2020-11-11');
INSERT INTO public.cargo (id, name, weight, type_id, size, material)
VALUES (2, 'JEANS', 300, 2, 'XXL', 'Cotton');
INSERT INTO public.cargo (id, name, weight, type_id, stored_temperature, expiration_date)
VALUES (3, 'ORANGE', 300, 1, -20, '2025-12-20');
INSERT INTO public.cargo (id, name, weight, type_id, size, material)
VALUES (4, 'Clothes_name_1', 150, 2, 'L', 'Linen');
INSERT INTO public.cargo (id, name, weight, type_id, stored_temperature, expiration_date)
VALUES (5, 'BANANA', 500, 1, 10, '2020-12-20');
INSERT INTO public.cargo (id, name, weight, type_id, material)
VALUES (6, 'SOCKS', 800, 2, 'Cotton');


INSERT INTO public.carrier_type (id, name) VALUES (1, 'SHIP');
INSERT INTO public.carrier_type (id, name) VALUES (2, 'PLANE');
INSERT INTO public.carrier_type (id, name) VALUES (3, 'CAR');

INSERT INTO public.carrier (id, name, address, type_id)
VALUES (1, 'Carrier_Name', 'Spb, Nevskiy 800', 1);
INSERT INTO public.carrier (id, name, address, type_id)
VALUES (2, 'Carrier_Name', 'Sp, Prosveheniya 900', 2);

INSERT INTO public.transportation (description, billTo, begin_date, cargo_id, carrier_id)
VALUES ('Vip order', 'Ivanov', '2010-11-11', 1, 1);
INSERT INTO public.transportation (description, billTo, begin_date, cargo_id, carrier_id)
VALUES ('Fragile', 'Petrov', '2011-11-11', 2, 1);
INSERT INTO public.transportation (description, billTo, begin_date, cargo_id, carrier_id)
VALUES ('Fragile', 'Sidorov', '2017-01-01', 6, 2);
