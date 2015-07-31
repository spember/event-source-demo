create table event (
  id uuid primary key,
  aggregate_id uuid, --references aggregate(id),
  revision int not null,
  date timestamp with time zone not null,
  date_effective timestamp with time zone not null,
  dtype varchar(31) not null,
  user_id varchar(50),
  data text not null
);

create index e_agg_id_idx on event (aggregate_id);
create index e_date_effective_idx on event (date_effective);
create index e_dtype_idx on event (dtype);