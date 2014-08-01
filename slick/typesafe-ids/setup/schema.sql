create table buy (
  company_id bigint unsigned not null,
  trader_id bigint unsigned not null,
  value decimal(10, 4) not null,
  reference varchar(10) not null
);

create table company (
  company_id bigint unsigned not null,
  name varchar(50) not null
);

create table trader (
  trader_id bigint unsigned not null,
  email varchar(50) not null
);