--================================
-- 관리자계정 system
--================================
alter session set "_oracle_script"  = true; -- c## 접두어 없이 계정 생성가능하도록 설정

create user cafejawa
identified by goffk132444A
default tablespace users;

alter user cafejawa quota unlimited on users;

grant connect, resource to cafejawa;


--================================
-- jawa 계정
--================================

alter table member add constraint memberRole_check check (memberRole in ('U', 'A'));
alter table member add constraint withdrawal_check check (withdrawal in ('Y', 'N'));
alter table product add constraint enabled_check check (enabled in ('Y', 'N'));
alter table inquiry add constraint is_answered_check check (is_answered in ('Y', 'N'));
alter table member modify memberRole default 'U';

insert into grade values ('Silver', 0);
insert into grade values ('Gold', 10);
insert into grade values ('VIP', 30);

alter table grade modify grade default 'Silver';
alter table grade modify grade default null;
alter table member modify grade default 'Silver';

select * from member;
update member set order_count = 31 where id = 'abc123';

select * from grade;

alter table member drop column withdrawal;
alter table store drop column url;

select * from store;

insert into store values('001', 'CAFE JAWA 잠실점', '서울 강남구 도곡로 457', '1522-3232');
insert into store values('002', 'CAFE JAWA 인천점', '인천 남동구 인하로 556', '1522-3232');
insert into store values('003', 'CAFE JAWA 동탄점', '경기 화성시 메타폴리스로 54', '1522-3232');