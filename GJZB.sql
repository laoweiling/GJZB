--�û�������
CREATE SEQUENCE userseq
INCREMENT BY 1
START WITH 1 
MINVALUE 1
NOMAXVALUE
NOCYCLE;
--���ͱ�����
CREATE SEQUENCE typeseq
INCREMENT BY 1
START WITH 1 
MINVALUE 1
NOMAXVALUE
NOCYCLE;
--���̱�����
CREATE SEQUENCE projectseq
INCREMENT BY 1
START WITH 1
MINVALUE 1
NOMAXVALUE
NOCYCLE;
--Ͷ�������
CREATE SEQUENCE relationseq
INCREMENT BY 1
START WITH 1 
MINVALUE 1
NOMAXVALUE
NOCYCLE;
--�б������
CREATE SEQUENCE winseq
INCREMENT BY 1
START WITH 1
MINVALUE 1
NOMAXVALUE
NOCYCLE;
--����������
CREATE SEQUENCE navseq
INCREMENT BY 1
START WITH 1
MINVALUE 1
NOMAXVALUE
NOCYCLE;
--������
create table gjzb_nav(
  id int primary key,
  text varchar2(20) not null,
  state varchar2(6) not null,
  url varchar2(20),
  nid int
);

--�û���
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
--��Ŀ���ͱ�
create table gjzb_type(
    typeId number(30) PRIMARY KEY not null,
    projectType varchar2(60)  
);

--��Ŀ��
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
--Ͷ���
create table gjzb_relation(
    relationId number(30) PRIMARY KEY not null,
    fkputId number(30) not null,
    fkprojectId number(30) not null,
    foreign key(fkputId) references gjzb_user(userId),
    foreign key(fkprojectId) references gjzb_project(projectId)
);
--�б��
create table gjzb_win(
    winId number(30) PRIMARY KEY not null,
    fkgetId number(30) not null,
    fkprojectId number(30) not null,
    foreign key(fkgetId) references gjzb_user(userId),
    foreign key(fkprojectId) references gjzb_project(projectId)
);

//�������������Զ��޸�user���winCount �� money �ֶ�
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
--����һ���û�����
insert into gjzb_user 
values(userseq.nextval,'laoweiling','admin','13437869863','2489077903@qq.com','���ҽ����Ǻ���Ҫ������ร���˵������','1.jpg','0',0,0);
commit;
--������Ŀ��������
insert into gjzb_type
values(typeseq.nextval,'���ز�');
insert into gjzb_type
values(typeseq.nextval,'������');
insert into gjzb_type
values(typeseq.nextval,'��ѯ��ѵ');
insert into gjzb_type
values(typeseq.nextval,'��е�豸');
insert into gjzb_type
values(typeseq.nextval,'ҽҩ');
commit;
select * from gjzb_type;
select * from gjzb_user;
select * from gjzb_project;
--������Ŀ������
insert into gjzb_project
values(projectseq.nextval,1,1,'����һ�𹲽�������԰', to_date('2007-7-20','YYYY-MM-DD'),to_date('2007-12-20','YYYY-MM-DD'),'������԰.docx',10000);
insert into gjzb_project
values(projectseq.nextval,1,2,'����Ҫ��һ���б���վ', to_date('2007-7-20','YYYY-MM-DD'),to_date('2007-12-20','YYYY-MM-DD'),'�б���վ.docx',20000);
insert into gjzb_project
values(projectseq.nextval,2,3,'����Ҫ��һ����ѵ�༶', to_date('2007-7-20','YYYY-MM-DD'),to_date('2007-12-20','YYYY-MM-DD'),'������԰.docx',30000);
insert into gjzb_project
values(projectseq.nextval,2,5,'���ǿ���һ̨����ɴ�', to_date('2007-7-20','YYYY-MM-DD'),to_date('2007-12-20','YYYY-MM-DD'),'����ɴ�.docx',40000);
insert into gjzb_project
values(projectseq.nextval,1,3,'����Ҫ������������ҩ', to_date('2007-7-20','YYYY-MM-DD'),to_date('2007-12-20','YYYY-MM-DD'),'��������.docx',50000);
insert into gjzb_project
values(projectseq.nextval,1,1,'test', to_date('2007-7-20','YYYY-MM-DD'),to_date('2007-12-20','YYYY-MM-DD'),'��������.docx',50000);


--����Ͷ���
insert into gjzb_relation
values(relationseq.nextval,2,1);
insert into gjzb_relation
values(relationseq.nextval,1,3);
commit;
--�����б��
insert into gjzb_win
values(winseq.nextval,2,3);
commit;

--���뵼��������
insert into gjzb_nav values(navseq.nextval,'Ͷ��/�б����ģ��','closed',null,0);
--ע�⣬�Ȳ����һ�����ݣ��ٲ������������Լ��޸ĺ����nid
--tender �б�
--bid Ͷ��
insert into gjzb_nav values(navseq.nextval,'Ͷ�����','open','bid/bid',1);
insert into gjzb_nav values(navseq.nextval,'�б����','open','tender/tender',1);
insert into gjzb_nav values(navseq.nextval,'���б�','open','tender/istendered',3);
insert into gjzb_nav values(navseq.nextval,'δ�б�','open','tender/nottendered',3);
insert into gjzb_nav values(navseq.nextval,'�����Ŀ','open','tender/addTender',3);
commit;

select * from gjzb_nav;
insert into gjzb_nav values(navseq.nextval,'�û�����ģ��','closed',null,0);
insert into gjzb_nav values(navseq.nextval,'�û�����','open','user/user',2);
insert into gjzb_nav values(navseq.nextval,'������Ϣ����','closed',null,0); 
insert into gjzb_nav values(navseq.nextval,'�޸ĸ�����Ϣ','open','user/personal',3);
 

