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

