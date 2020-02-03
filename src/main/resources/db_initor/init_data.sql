--Put data to the tables
INSERT INTO public.cargo_type (id, name) VALUES (1, 'FOOD');
INSERT INTO public.cargo_type (id, name) VALUES (2, 'CLOTHERS');

INSERT INTO public.clothes_size (id, name) VALUES (1, 'XXL');
INSERT INTO public.clothes_size (id, name) VALUES (2, 'L');

INSERT INTO public.clothes_material (id, name) VALUES (1, 'Cotton');
INSERT INTO public.clothes_material (id, name) VALUES (2, 'Linen');

INSERT INTO public.cargo (id, name, weight, type_id, stored_temperature, expiration_date)
VALUES (1, 'APPLE', 100, 1, -50, '2020-11-11');
INSERT INTO public.cargo (id, name, weight, type_id, size_id, material_id)
VALUES (2, 'JEANS', 300, 2, 1, 1);
INSERT INTO public.cargo (id, name, weight, type_id, stored_temperature, expiration_date)
VALUES (3, 'ORANGE', 300, 1, -20, '2025-12-20');
INSERT INTO public.cargo (id, name, weight, type_id, size_id, material_id)
VALUES (4, 'Clothes_name_1', 150, 2, 2, 2);
INSERT INTO public.cargo (id, name, weight, type_id, stored_temperature, expiration_date)
VALUES (5, 'BANANA', 500, 1, 10, '2020-12-20');
INSERT INTO public.cargo (id, name, weight, type_id, material_id)
VALUES (6, 'SOCKS', 800, 2, 1);


INSERT INTO public.carrier_type (id, name) VALUES (1, 'SHIP');
INSERT INTO public.carrier_type (id, name) VALUES (2, 'PLANE');
INSERT INTO public.carrier_type (id, name) VALUES (3, 'CAR');

INSERT INTO public.country (id, name) VALUES (1, 'Russia');

INSERT INTO public.city (id, name) VALUES (1, 'Saint-Petersburg');

INSERT INTO public.street (id, name) VALUES (1, 'Nevskiy');
INSERT INTO public.street (id, name) VALUES (2, 'Prosvesheniya');

INSERT INTO public.address (id, country_id, city_id, street_id, address_line)
VALUES (1, 1, 1, 1, '800');
INSERT INTO public.address (id, country_id, city_id, street_id, address_line)
VALUES (2, 1, 1, 2, '900');

INSERT INTO public.carrier (id, name, address_id, type_id)
VALUES (1, 'Carrier_Name', 1, 1);
INSERT INTO public.carrier (id, name, address_id, type_id)
VALUES (2, 'Carrier_Name', 2, 2);

INSERT INTO public.payer (id, name) VALUES (1, 'Ivanov');
INSERT INTO public.payer (id, name) VALUES (2, 'Petrov');
INSERT INTO public.payer (id, name) VALUES (3, 'Sidorov');

INSERT INTO public.transportation (description, payer_id, begin_date, cargo_id, carrier_id)
VALUES ('Vip order', 1, '2010-11-11', 1, 1);
INSERT INTO public.transportation (description, payer_id, begin_date, cargo_id, carrier_id)
VALUES ('Fragile', 2, '2011-11-11', 2, 1);
INSERT INTO public.transportation (description, payer_id, begin_date, cargo_id, carrier_id)
VALUES ('Fragile', 3, '2017-01-01', 6, 2);
