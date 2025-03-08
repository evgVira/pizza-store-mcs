alter table if exists order_sc.order
add column created_at timestamp not null,
add column updated_at timestamp not null;