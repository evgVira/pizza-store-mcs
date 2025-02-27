create table if not exists order_sc.order
(
    id UUID primary key not null,
    user_id UUID not null,
    pizza_id UUID not null,
    total_amount decimal(10, 2) not null,
    status varchar not null
);