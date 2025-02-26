create table if not exists catalog_sc.pizza
(
    id UUID primary key not null,
    name varchar not null,
    description varchar not null,
    price decimal(10, 2)
);