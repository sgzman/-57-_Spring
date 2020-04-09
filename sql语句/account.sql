create table account(
			id int key auto_increment,
			name varchar(40),
			money FLOAT
)CHARACTER set UTF8 COLLATE utf8_general_ci;

insert into account(name ,money) VALUES('aaa',1000);
insert into account(name ,money) VALUES('bbb',1000);
insert into account(name ,money) VALUES('ccc',1000);
