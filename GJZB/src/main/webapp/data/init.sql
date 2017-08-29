CREATE SEQUENCE userseq
INCREMENT BY 1
START WITH 0 
MINVALUE 0
NOMAXVALUE
NOCYCLE;

CREATE SEQUENCE typeseq
INCREMENT BY 1
START WITH 0 
MINVALUE 0
NOMAXVALUE
NOCYCLE;

CREATE SEQUENCE projectseq
INCREMENT BY 1
START WITH 0
MINVALUE 0
NOMAXVALUE
NOCYCLE;

CREATE SEQUENCE relationseq
INCREMENT BY 1
START WITH 0 
MINVALUE 0
NOMAXVALUE
NOCYCLE;

CREATE SEQUENCE winseq
INCREMENT BY 1
START WITH 0
MINVALUE 0
NOMAXVALUE
NOCYCLE;

create table gjzb_user(
    userId NUMBER(30) PRIMARY KEY not null,
    userName varchar2(60) not null,
    userPw varchar2(60) not null,
    phone varchar2(60) not null,
    email varchar2(60) not null,
    introduction clob,
    profilePic  varchar2(65) not null,
    userlevel varchar2(20) default 0,
    winCount number(30) default 0,
    money number(30,2) default 0  
);
create table gjzb_type(
    typeId number(30) PRIMARY KEY not null,
    projectType varchar2(60)  
);
create table gjzb_project(
    projectId number(30) PRIMARY KEY not null,
    fkuserId  NUMBER(30) not null,
    fktypeId number(30) not null,
    projectName varchar2(100) not null,
    ReleaseTime date not null,
    lastTime date not null,
    projectcontent varchar2(60),
    price NUMBER(30,2) not null,
    foreign key(fktypeId) references gjzb_type(typeId),
    foreign key(fkuserId) references gjzb_user(userId)
);
create table gjzb_relation(
    relationId number(30) PRIMARY KEY not null,
    fkputId number(30) not null,
    fkprojectId number(30) not null,
    foreign key(fkputId) references gjzb_user(userId),
    foreign key(fkprojectId) references gjzb_project(projectId)
);
create table gjzb_win(
    winId number(30) PRIMARY KEY not null,
    fkgetId number(30) not null,
    fkprojectId number(30) not null,
    foreign key(fkgetId) references gjzb_user(userId),
    foreign key(fkprojectId) references gjzb_project(projectId)
);


insert into gjzb_user 
values(userseq.nextval,'hhg','admin','13437869863','2489077903@qq.com','只知道是一个男的','1.jpg','1',1,20000);
insert into gjzb_user 
values(userseq.nextval,'hh','admin','110110110110','110110110@qq.com','黄卉知道是一个女的程序员','2.jpg','1',1,50000);
insert into gjzb_user 
values(userseq.nextval,'lwl','admin','120120120120','120120120@qq.com','伟玲道是一个女的程序员','3.jpg','1',1,10000);
insert into gjzb_user 
values(userseq.nextval,'lxp','admin','119119119119','119119119@qq.com','肖萍是一个女的程序员','4.jpg','1',1,30000);
insert into gjzb_user 
values(userseq.nextval,'lmy','admin','114114114114','114114114@qq.com','梦雅是一个女的程序员','5.jpg','1',1,40000);
commit;
insert into gjzb_type
values(typeseq.nextval,'房地产');
insert into gjzb_type
values(typeseq.nextval,'软件外包');
insert into gjzb_type
values(typeseq.nextval,'咨询培训');
insert into gjzb_type
values(typeseq.nextval,'机械设备');
insert into gjzb_type
values(typeseq.nextval,'医药');
commit;

insert into gjzb_project
values(projectseq.nextval,1,1,'我们一起共建阳江家园', to_date('2007-7-20','YYYY-MM-DD'),to_date('2007-12-20','YYYY-MM-DD'),'共建家园.docx',10000);
insert into gjzb_project
values(projectseq.nextval,2,2,'我们要做一个招标网站', to_date('2007-7-20','YYYY-MM-DD'),to_date('2007-12-20','YYYY-MM-DD'),'招标网站.docx',20000);
insert into gjzb_project
values(projectseq.nextval,3,3,'我们要做一个培训班级', to_date('2007-7-20','YYYY-MM-DD'),to_date('2007-12-20','YYYY-MM-DD'),'共建家园.docx',30000);
insert into gjzb_project
values(projectseq.nextval,4,4,'我们开发一台宇宙飞船', to_date('2007-7-20','YYYY-MM-DD'),to_date('2007-12-20','YYYY-MM-DD'),'宇宙飞船.docx',40000);
insert into gjzb_project
values(projectseq.nextval,3,3,'我们要做长生不老神药', to_date('2007-7-20','YYYY-MM-DD'),to_date('2007-12-20','YYYY-MM-DD'),'长生不老.docx',50000);

insert into gjzb_relation
values(relationseq.nextval,2,1);
insert into gjzb_relation
values(relationseq.nextval,3,1);
insert into gjzb_relation
values(relationseq.nextval,1,2);
insert into gjzb_relation
values(relationseq.nextval,4,3);
insert into gjzb_relation
values(relationseq.nextval,5,3);
insert into gjzb_relation
values(relationseq.nextval,5,4);
insert into gjzb_relation
values(relationseq.nextval,2,4);
insert into gjzb_relation
values(relationseq.nextval,2,5);
insert into gjzb_relation
values(relationseq.nextval,3,5);

insert into gjzb_win
values(winseq.nextval,2,5);
insert into gjzb_win
values(winseq.nextval,5,4);
insert into gjzb_win
values(winseq.nextval,1,2);
insert into gjzb_win
values(winseq.nextval,4,3);
insert into gjzb_win
values(winseq.nextval,3,1);

commit;


drop table gjzb_user;
drop table gjzb_type;
drop table gjzb_project;
drop table gjzb_relation;
drop table gjzb_win;