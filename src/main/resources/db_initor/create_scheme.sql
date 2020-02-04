--Creating cargo
CREATE TABLE public.cargo_type (
  id BIGSERIAL NOT NULL,
  name VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE public.cargo (
  id BIGSERIAL NOT NULL,
  name VARCHAR(100),
  weight INT,
  type_id BIGINT REFERENCES public.cargo_type(id),
  stored_temperature INT,
  expiration_date DATE,
  size VARCHAR(100),
  material VARCHAR(100),
  PRIMARY KEY (id)
);

--Creating carrier
CREATE TABLE public.carrier_type (
  id BIGSERIAL NOT NULL,
  name VARCHAR(100),
  PRIMARY KEY (id)
);

CREATE TABLE public.carrier (
  id BIGSERIAL NOT NULL,
  name VARCHAR(100),
  address VARCHAR(300),
  type_id BIGINT REFERENCES public.carrier_type(id),
  PRIMARY KEY (id)
);

--Create transportation
CREATE TABLE public.transportation (
  id BIGSERIAL NOT NULL,
  description VARCHAR(200),
  billTo VARCHAR(200),
  begin_date DATE,
  cargo_id BIGINT REFERENCES public.cargo(id),
  carrier_id BIGINT REFERENCES public.carrier(id),
  PRIMARY KEY (id)
);
