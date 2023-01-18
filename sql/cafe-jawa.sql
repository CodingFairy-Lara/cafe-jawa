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

select * from grade;
