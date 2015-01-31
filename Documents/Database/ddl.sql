create table user(
user_id varchar(20) primary key,
pw varchar(30) not null,
cur_page int(1),
name varchar(20) not null);

create table catagory(
	catagory_id int(4) primary key auto_increment,
    catagory_name varchar(20) not null,
    request_url varchar(100) not null,
    logo_img varchar(100)
    );
    

create table user_set(
	user_id varchar(20),
	catagory_id int(4) not null,
    position_x int(5),
    position_y int(5),
    width int(5),
    height int(5),
    page_num int(1) not null,
    constraint usersetting foreign key(user_id) references user(user_id) on delete cascade on update restrict
);