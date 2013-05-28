# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table address_book (
  id                        bigint not null,
  name                      varchar(255),
  constraint pk_address_book primary key (id))
;

create table contact (
  id                        bigint not null,
  last_name                 varchar(255),
  first_name                varchar(255),
  phone_number              varchar(255),
  mobile_number             varchar(255),
  address_book_id           bigint,
  constraint pk_contact primary key (id))
;

create sequence address_book_seq;

create sequence contact_seq;

alter table contact add constraint fk_contact_addressBook_1 foreign key (address_book_id) references address_book (id) on delete restrict on update restrict;
create index ix_contact_addressBook_1 on contact (address_book_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists address_book;

drop table if exists contact;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists address_book_seq;

drop sequence if exists contact_seq;

