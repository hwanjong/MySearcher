create table user(
user_id varchar(20) primary key,
pw varchar(30) not null,
cur_page int(1),
name varchar(20) not null);

create table catagory(
    catagory_name varchar(20) primary key,
    logo_img varchar(100)
);

create table user_set(
	user_id varchar(20),
	catagory_name varchar(20) not null,
    page_num int(1) not null,
    position_x int(5),
    position_y int(5),
    width int(5),
    height int(5),
    constraint usersetting foreign key(user_id) references user(user_id) on delete cascade on update restrict
);
create table scrap(
    user_id varchar(20),
    div_html varchar(200),
    scrap_no int(4) auto_increment primary key
    
);
    

alter table user add cur_page int(1);
alter table user_set add page_num int(1) not null;
alter table catagory add catagory_no int(3) auto_increment primary key;
alter table catagory drop column catagory_id;
alter table user_set change catagory_id catagory_name varchar(20);


select * FROM User WHERE user_id = 'hwan7287' and pw = '123';


insert into catagory(catagory_name,logo_img) values('Wikipedia','img/logo_icon/Wikipedia.png');

insert into user_set(user_id,catagory_name,page_num) values('hwan72872','Wikipedia',2);

select catagory.catagory_name, catagory.logo_img from user join user_set on user.cur_page = user_set.page_num join catagory on user_set.catagory_name = catagory.catagory_name where user.user_id='t1';
select c.catagory_name, c.logo_img from  User u join user_set s on (u.user_id = s.user_id) join catagory c on (s.catagory_name=c.catagory_name) where u.user_id ='hwan7287' and u.cur_page = s.page_num;

select cur_page from user where user_id='hwan7287';

update user set cur_page = 1 where user_id='hwan7287';

delete from user_set where user_id='hwan7287' and page_num=4 and catagory_name='CyworldBlog';
select c.catagory_name, c.logo_img, s.left, s.top from User u join user_set
		s on (u.user_id = s.user_id) join catagory c on
		(s.catagory_name=c.catagory_name) where u.user_id ='hwan7287' and
		u.cur_page = s.page_num;
truncate useruser;


update user_set set user_set.left = '40px', top='120px' where user_id='hwan7287' and catagory_name='CyworldBlog' and page_num='1';

insert into user_set(user_id,catagory_name,page_num,user_set.left)
		values('hwan7287','dfdfdf',4,'240px');
        
select set_no from user_set where user_id='hwan7287' and page_num=1 and z_index=4;

update user_set set z_index = z_index+1 where set_no=133;

update user_set set user_set.left = '40px', top='120px' where user_id='hwan7287' and catagory_name='CyworldBlog' and page_num='1';

insert into scrap(user_id,div_html,div_id) values('hwan7287','<div></div>',1);
select div_html, div_id from scrap where user_id='hwan7287';
delete from scrap where user_id = 'hwan7287' and div_id=2;