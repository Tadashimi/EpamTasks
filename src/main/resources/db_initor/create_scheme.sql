--Creating cargo
CREATE TABLE public.cargo_type (
  id BIGSERIAL NOT NULL,
  name VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE public.clothes_size (
  id BIGSERIAL NOT NULL,
  name VARCHAR(100),
  PRIMARY KEY (id)
);

CREATE TABLE public.clothes_material (
  id BIGSERIAL NOT NULL,
  name VARCHAR(100),
  PRIMARY KEY (id)
);

CREATE TABLE public.cargo (
  id BIGSERIAL NOT NULL,
  name VARCHAR(100),
  weight INT,
  type_id BIGINT REFERENCES public.cargo_type(id),
  stored_temperature INT,
  expiration_date DATE,
  size_id BIGINT REFERENCES public.clothes_size(id),
  material_id BIGINT REFERENCES public.clothes_material(id),
  PRIMARY KEY (id)
);

--Creating carrier
CREATE TABLE public.carrier_type (
  id BIGSERIAL NOT NULL,
  name VARCHAR(100),
  PRIMARY KEY (id)
);

CREATE TABLE public.country (
  id BIGSERIAL NOT NULL,
  name VARCHAR(100),
  PRIMARY KEY (id)
);

CREATE TABLE public.city (
  id BIGSERIAL NOT NULL,
  name VARCHAR(100),
  PRIMARY KEY (id)
);

CREATE TABLE public.street (
  id BIGSERIAL NOT NULL,
  name VARCHAR(100),
  PRIMARY KEY (id)
);

CREATE TABLE public.address (
  id BIGSERIAL NOT NULL,
  country_id BIGINT REFERENCES public.country(id),
  city_id BIGINT REFERENCES public.city(id),
  street_id BIGINT REFERENCES public.street(id),
  address_line VARCHAR(200),
  PRIMARY KEY (id)
);

CREATE TABLE public.carrier (
  id BIGSERIAL NOT NULL,
  name VARCHAR(100),
  address_id BIGINT REFERENCES public.address(id),
  type_id BIGINT REFERENCES public.carrier_type(id),
  PRIMARY KEY (id)
);

--Create transportation
CREATE TABLE public.payer (
  id BIGSERIAL NOT NULL,
  name VARCHAR(100),
  PRIMARY KEY (id)
);

CREATE TABLE public.transportation (
  id BIGSERIAL NOT NULL,
  description VARCHAR(200),
  payer_id BIGINT REFERENCES public.payer(id),
  begin_date DATE,
  cargo_id BIGINT REFERENCES public.cargo(id),
  carrier_id BIGINT REFERENCES public.carrier(id),
  PRIMARY KEY (id)
);
