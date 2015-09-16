create table orders (order_id bigint not null, created_at datetime, description varchar(255), status varchar(255), user_user_id bigint, primary key (order_id))
create table users (user_id bigint not null, is_admin bit not null, last_name varchar(50) not null, name varchar(50) not null, nickname varchar(32) not null, password varchar(32) not null, primary key (user_id))
alter table users add constraint UK_2ty1xmrrgtn89xt7kyxx6ta7h  unique (nickname)
alter table orders add constraint FK_dl1ku1sdw7q0x9xt1ie2ahd8f foreign key (user_user_id) references users (user_id)
