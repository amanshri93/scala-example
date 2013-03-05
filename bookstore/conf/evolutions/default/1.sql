# --- Created by Slick DDL
# To stop Slick DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table "COFFEES" ("COF_NAME" VARCHAR NOT NULL PRIMARY KEY,"SUP_ID" INTEGER NOT NULL,"PRICE" DOUBLE NOT NULL,"SALES" INTEGER NOT NULL,"TOTAL" INTEGER NOT NULL);
create table "SUPPLIERS" ("SUP_ID" INTEGER NOT NULL PRIMARY KEY,"SUP_NAME" VARCHAR NOT NULL,"STREET" VARCHAR NOT NULL,"CITY" VARCHAR NOT NULL,"STATE" VARCHAR NOT NULL,"ZIP" VARCHAR NOT NULL);
create table "CAT" ("name" VARCHAR NOT NULL PRIMARY KEY,"color" VARCHAR NOT NULL);
alter table "COFFEES" add constraint "SUP_FK" foreign key("SUP_ID") references "SUPPLIERS"("SUP_ID") on update NO ACTION on delete NO ACTION;

# --- !Downs

alter table "COFFEES" drop constraint "SUP_FK";
drop table "COFFEES";
drop table "SUPPLIERS";
drop table "CAT";

