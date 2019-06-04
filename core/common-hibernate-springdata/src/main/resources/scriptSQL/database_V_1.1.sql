create table user
(
  user_id       int auto_increment
    primary key,
  login         varchar(100)  not null,
  password      varchar(100)  not null,
  user_name     varchar(100)  not null,
  user_surname  varchar(100)  not null,
  user_number   varchar(100)  null,
  register_date timestamp     null,
  user_block    int default 0 not null,
  constraint login
    unique (login),
  constraint user_number
    unique (user_number)
);

create table role
(
  user_role_id int auto_increment
    primary key,
  user_id      int          not null,
  user_role    varchar(100) not null,
  constraint user_id
    unique (user_id),
  constraint Role_fk0
    foreign key (user_id) references user (user_id)
);

create table telephones
(
  tel_id            int auto_increment
    primary key,
  tel_name          varchar(100)  not null,
  tel_surname       varchar(100)  not null,
  tel_number        varchar(100)  not null,
  tel_creation_date timestamp     not null,
  tel_block         int default 0 not null,
  user_id           int           null,
  constraint tel_number
    unique (tel_number),
  constraint Telephones_fk0
    foreign key (user_id) references user (user_id)
);

create table adress
(
  adress_id        int auto_increment
    primary key,
  country          varchar(100)      null,
  city             varchar(100)      null,
  street           varchar(100)      null,
  house_number     varchar(100)      null,
  floor            int               not null,
  apartment_number int               null,
  user_id          int               null,
  tel_id           int               null,
  adress_block     int     default 0 not null,
  users_adress     tinyint default 0 not null,
  constraint Adress_fk0
    foreign key (user_id) references user (user_id),
  constraint Adress_fk1
    foreign key (tel_id) references telephones (tel_id)
);

create table favorite
(
  favorite_id    int auto_increment
    primary key,
  user_id        int           not null,
  tel_id         int           not null,
  favorite_block int default 0 not null,
  constraint Favorite_fk0
    foreign key (user_id) references user (user_id),
  constraint Favorite_fk1
    foreign key (tel_id) references telephones (tel_id)
);

