create table beer (id varchar not null, created_date timestamp, last_modified_date timestamp, version bigint, beer_name varchar(255), beer_style integer, min_on_hand integer, price decimal(19,2), quantity_to_brew integer, upc varchar(255), primary key (id))
create table beer_inventory (id varchar not null, created_date timestamp, last_modified_date timestamp, version bigint, quantity_on_hand integer, beer_id varchar, primary key (id))
create table beer_order (id varchar not null, created_date timestamp, last_modified_date timestamp, version bigint, customer_ref varchar(255), order_status integer, order_status_callback_url varchar(255), customer_id varchar, primary key (id))
create table beer_order_line (id varchar not null, created_date timestamp, last_modified_date timestamp, version bigint, order_quantity integer, quantity_allocated integer, beer_id varchar, beer_order_id varchar, primary key (id))
create table brewery (id varchar not null, created_date timestamp, last_modified_date timestamp, version bigint, brewery_name varchar(255), primary key (id))
create table customer (id varchar not null, created_date timestamp, last_modified_date timestamp, version bigint, api_key varchar, customer_name varchar(255), primary key (id))
alter table beer add constraint UK_p9mb364xktkjqmprmg89u2etr unique (upc)
alter table beer_inventory add constraint FKrcn9po4vdekbhm8mpoj5f35d9 foreign key (beer_id) references beer
alter table beer_order add constraint FK5siih2e7vpx70nx4wexpxpji foreign key (customer_id) references customer
alter table beer_order_line add constraint FKslskqsf79v6iekvb6d3gcc1l4 foreign key (beer_id) references beer
alter table beer_order_line add constraint FKhkgofxhwx8yw9m3vat8mgtnxs foreign key (beer_order_id) references beer_order
create table beer (id varchar not null, created_date timestamp, last_modified_date timestamp, version bigint, beer_name varchar(255), beer_style integer, min_on_hand integer, price decimal(19,2), quantity_to_brew integer, upc varchar(255), primary key (id))
create table beer_inventory (id varchar not null, created_date timestamp, last_modified_date timestamp, version bigint, quantity_on_hand integer, beer_id varchar, primary key (id))
create table beer_order (id varchar not null, created_date timestamp, last_modified_date timestamp, version bigint, customer_ref varchar(255), order_status integer, order_status_callback_url varchar(255), customer_id varchar, primary key (id))
create table beer_order_line (id varchar not null, created_date timestamp, last_modified_date timestamp, version bigint, order_quantity integer, quantity_allocated integer, beer_id varchar, beer_order_id varchar, primary key (id))
create table brewery (id varchar not null, created_date timestamp, last_modified_date timestamp, version bigint, brewery_name varchar(255), primary key (id))
create table customer (id varchar not null, created_date timestamp, last_modified_date timestamp, version bigint, api_key varchar, customer_name varchar(255), primary key (id))
alter table beer add constraint UK_p9mb364xktkjqmprmg89u2etr unique (upc)
alter table beer_inventory add constraint FKrcn9po4vdekbhm8mpoj5f35d9 foreign key (beer_id) references beer
alter table beer_order add constraint FK5siih2e7vpx70nx4wexpxpji foreign key (customer_id) references customer
alter table beer_order_line add constraint FKslskqsf79v6iekvb6d3gcc1l4 foreign key (beer_id) references beer
alter table beer_order_line add constraint FKhkgofxhwx8yw9m3vat8mgtnxs foreign key (beer_order_id) references beer_order
create table beer (id varchar not null, created_date timestamp, last_modified_date timestamp, version bigint, beer_name varchar(255), beer_style integer, min_on_hand integer, price decimal(19,2), quantity_to_brew integer, upc varchar(255), primary key (id))
create table beer_inventory (id varchar not null, created_date timestamp, last_modified_date timestamp, version bigint, quantity_on_hand integer, beer_id varchar, primary key (id))
create table beer_order (id varchar not null, created_date timestamp, last_modified_date timestamp, version bigint, customer_ref varchar(255), order_status integer, order_status_callback_url varchar(255), customer_id varchar, primary key (id))
create table beer_order_line (id varchar not null, created_date timestamp, last_modified_date timestamp, version bigint, order_quantity integer, quantity_allocated integer, beer_id varchar, beer_order_id varchar, primary key (id))
create table brewery (id varchar not null, created_date timestamp, last_modified_date timestamp, version bigint, brewery_name varchar(255), primary key (id))
create table customer (id varchar not null, created_date timestamp, last_modified_date timestamp, version bigint, api_key varchar, customer_name varchar(255), primary key (id))
alter table beer add constraint UK_p9mb364xktkjqmprmg89u2etr unique (upc)
alter table beer_inventory add constraint FKrcn9po4vdekbhm8mpoj5f35d9 foreign key (beer_id) references beer
alter table beer_order add constraint FK5siih2e7vpx70nx4wexpxpji foreign key (customer_id) references customer
alter table beer_order_line add constraint FKslskqsf79v6iekvb6d3gcc1l4 foreign key (beer_id) references beer
alter table beer_order_line add constraint FKhkgofxhwx8yw9m3vat8mgtnxs foreign key (beer_order_id) references beer_order
