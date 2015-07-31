set timezone='UTC';

-- while we inherit on each of the tables, we also specify the id as primary key again in order to keep the unique constraint and index

create table aggregate (
  id uuid primary key,
  revision int not null,
  active boolean DEFAULT true
);


create table product (
  id uuid primary key,
  sku varchar(20) not null,
  name varchar(200) not null
) INHERITS (aggregate);
