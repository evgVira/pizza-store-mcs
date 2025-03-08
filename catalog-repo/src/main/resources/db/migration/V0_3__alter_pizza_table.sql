alter table if exists catalog_sc.pizza
add column created_at timestamp not null,
add column updated_at timestamp not null,
add column available_status varchar not null;
