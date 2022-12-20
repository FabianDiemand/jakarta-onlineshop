#!/bin/bash
set -e
export PGPASSWORD=$POSTGRES_PASSWORD;
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
  CREATE USER $APP_DB_USER WITH PASSWORD '$APP_DB_PASS';
  CREATE DATABASE $APP_DB_NAME;
  GRANT ALL PRIVILEGES ON DATABASE $APP_DB_NAME TO $APP_DB_USER;
  \connect $APP_DB_NAME $APP_DB_USER
  BEGIN;

  create schema if not exists onlineshop;

  create table onlineshop.place
  (
      place_id    serial
          primary key,
      place_name  varchar(255) not null,
      postal_code varchar(255) not null,
      version     bigint
  );

  alter table onlineshop.place
      owner to onlineshop_user;

  create table onlineshop.address
  (
      address_id  serial
          primary key,
      housenumber varchar(255) not null,
      streetname  varchar(255) not null,
      version     bigint,
      place       bigint
          unique
          constraint fk_address_place
              references onlineshop.place
  );

  alter table onlineshop.address
      owner to onlineshop_user;

  create table onlineshop.customer
  (
      customer_id serial
          primary key,
      email       varchar(255) not null,
      firstname   varchar(255) not null,
      lastname    varchar(255) not null,
      password    varchar(255) not null
          unique,
      version     bigint,
      address     bigint
          unique
          constraint fk_customer_address
              references onlineshop.address
  );

  alter table onlineshop.customer
      owner to onlineshop_user;

  create table onlineshop.product
  (
      product_id         serial
          primary key,
      description        varchar(255)          not null,
      image              bytea                 not null,
      name               varchar(255)          not null,
      price              double precision      not null,
      version            bigint,
      buyer_customer_id  bigint
          constraint fk_product_buyer_customer_id
              references onlineshop.customer,
      seller_customer_id bigint
          constraint fk_product_seller_customer_id
              references onlineshop.customer,
      sold               boolean default false not null
  );

  alter table onlineshop.product
      owner to onlineshop_user;

  create table onlineshop."order"
  (
      order_id     serial
          primary key,
      is_paid      boolean not null,
      order_status text    not null,
      ordered_at   date    not null,
      paid_at      date,
      customer     bigint  not null
          constraint "FK_order_customer"
              references onlineshop.customer
  );

  alter table onlineshop."order"
      owner to onlineshop_user;

  create table onlineshop.wishlist
  (
      customer_fk bigint not null
          constraint fk_wishlist_customer_fk
              references onlineshop.customer,
      product_fk  bigint not null
          constraint fk_wishlist_product_fk
              references onlineshop.product,
      primary key (customer_fk, product_fk)
  );

  alter table onlineshop.wishlist
      owner to onlineshop_user;

  create table onlineshop.order_product
  (
      "order" bigint not null
          constraint "FK_order_product_order"
              references onlineshop."order",
      product bigint not null
          constraint fk_order_product_product
              references onlineshop.product,
      primary key ("order", product)
  );

  alter table onlineshop.order_product
      owner to onlineshop_user;

  COMMIT;
EOSQL