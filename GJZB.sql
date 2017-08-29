--用户表序列
CREATE SEQUENCE userseq
INCREMENT BY 1
START WITH 1 
MINVALUE 1
NOMAXVALUE
NOCYCLE;
--类型表序列
CREATE SEQUENCE typeseq
INCREMENT BY 1
START WITH 1 
MINVALUE 1
NOMAXVALUE
NOCYCLE;
--工程表序列
CREATE SEQUENCE projectseq
INCREMENT BY 1
START WITH 1
MINVALUE 1
NOMAXVALUE
NOCYCLE;
--投标表序列
CREATE SEQUENCE relationseq
INCREMENT BY 1
START WITH 1 
MINVALUE 1
NOMAXVALUE
NOCYCLE;
--中标表序列
CREATE SEQUENCE winseq
INCREMENT BY 1
START WITH 1
MINVALUE 1
NOMAXVALUE
NOCYCLE;
--导航表序列
CREATE SEQUENCE navseq
INCREMENT BY 1
START WITH 1
MINVALUE 1
NOMAXVALUE
NOCYCLE;
--导航表
create table gjzb_nav(
  id int primary key,
  text varchar2(20) not null,
  state varchar2(6) not null,
  url varchar2(20),
  nid int
);

--用户表
create table gjzb_user(
    userId NUMBER(30) PRIMARY KEY not null,
    userName varchar2(60) not null,
    userPw varchar2(60) not null,
    phone varchar2(60) not null,
    email varchar2(60) not null,
    introduction varchar2(600),
    profilePic  varchar2(65),
    userlevel varchar2(20) default 0,
    winCount number(30) default 0,
    money number(30,2) default 0
);
--项目类型表
create table gjzb_type(
    typeId number(30) PRIMARY KEY not null,
    projectType varchar2(60)  
);

--项目表
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
--投标表
create table gjzb_relation(
    relationId number(30) PRIMARY KEY not null,
    fkputId number(30) not null,
    fkprojectId number(30) not null,
    foreign key(fkputId) references gjzb_user(userId),
    foreign key(fkprojectId) references gjzb_project(projectId)
);
--中标表
create table gjzb_win(
    winId number(30) PRIMARY KEY not null,
    fkgetId number(30) not null,
    fkprojectId number(30) not null,
    foreign key(fkgetId) references gjzb_user(userId),
    foreign key(fkprojectId) references gjzb_project(projectId)
);

//触发器，用于自动修改user表的winCount 和 money 字段
create trigger updateUserMoneyWinCount
after insert on gjzb_win
declare
    max_ID number(30);
    user_ID number(30);
    project_Id number(30);
    P_price NUMBER(30,2);
begin
    select max(winId ) into max_ID from gjzb_win;
    select fkgetId into user_ID  from gjzb_win where winId = max_ID;
    select fkprojectId into project_Id from gjzb_win where winId = max_ID;
    select price into P_price from gjzb_project where projectId =project_Id;
    update gjzb_user set money = money + P_price where userId = user_ID;
    update gjzb_user set winCount = winCount + 1 where userId = user_ID;
end;
--插入一条用户数据
insert into gjzb_user 
values(userseq.nextval,'laoweiling','admin','13437869863','2489077903@qq.com','自我介绍是很重要的事情喔，听说而已啦','1.jpg','0',0,0);
commit;
--插入项目类型数据
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
select * from gjzb_type;
select * from gjzb_user;
select * from gjzb_project;
--插入项目表数据
insert into gjzb_project
values(projectseq.nextval,1,1,'我们一起共建阳江家园', to_date('2007-7-20','YYYY-MM-DD'),to_date('2007-12-20','YYYY-MM-DD'),'共建家园.docx',10000);
insert into gjzb_project
values(projectseq.nextval,1,2,'我们要做一个招标网站', to_date('2007-7-20','YYYY-MM-DD'),to_date('2007-12-20','YYYY-MM-DD'),'招标网站.docx',20000);
insert into gjzb_project
values(projectseq.nextval,2,3,'我们要做一个培训班级', to_date('2007-7-20','YYYY-MM-DD'),to_date('2007-12-20','YYYY-MM-DD'),'共建家园.docx',30000);
insert into gjzb_project
values(projectseq.nextval,2,5,'我们开发一台宇宙飞船', to_date('2007-7-20','YYYY-MM-DD'),to_date('2007-12-20','YYYY-MM-DD'),'宇宙飞船.docx',40000);
insert into gjzb_project
values(projectseq.nextval,1,3,'我们要做长生不老神药', to_date('2007-7-20','YYYY-MM-DD'),to_date('2007-12-20','YYYY-MM-DD'),'长生不老.docx',50000);
insert into gjzb_project
values(projectseq.nextval,1,1,'test', to_date('2007-7-20','YYYY-MM-DD'),to_date('2007-12-20','YYYY-MM-DD'),'长生不老.docx',50000);


--插入投标表
insert into gjzb_relation
values(relationseq.nextval,2,1);
insert into gjzb_relation
values(relationseq.nextval,1,3);
commit;
--插入中标表
insert into gjzb_win
values(winseq.nextval,2,3);
commit;

--插入导航条数据
insert into gjzb_nav values(navseq.nextval,'投标/招标管理模块','closed',null,0);
--注意，先插入第一条数据，再插入后面的数据以及修改后面的nid
--tender 招标
--bid 投标
insert into gjzb_nav values(navseq.nextval,'投标管理','open','bid/bid',1);
insert into gjzb_nav values(navseq.nextval,'招标管理','open','tender/tender',1);
insert into gjzb_nav values(navseq.nextval,'已中标','open','tender/istendered',3);
insert into gjzb_nav values(navseq.nextval,'未中标','open','tender/nottendered',3);
insert into gjzb_nav values(navseq.nextval,'添加项目','open','tender/addTender',3);
commit;

select * from gjzb_nav;
insert into gjzb_nav values(navseq.nextval,'用户管理模块','closed',null,0);
insert into gjzb_nav values(navseq.nextval,'用户管理','open','user/user',2);
insert into gjzb_nav values(navseq.nextval,'个人信息管理','closed',null,0); 
insert into gjzb_nav values(navseq.nextval,'修改个人信息','open','user/personal',3);
 

