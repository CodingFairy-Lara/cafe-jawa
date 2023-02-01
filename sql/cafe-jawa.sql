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
-- cafejawa 계정
--================================

CREATE TABLE member (
	id	varchar(100)		NOT NULL,
	grade	varchar2(100)		NULL,
	name	varchar(100)		NOT NULL,
	password	varchar(100)		NOT NULL,
	email	varchar(100)		NOT NULL,
	phone	varchar(100)		NOT NULL,
	birthday	date		NULL,
	enroll_date	date	DEFAULT sysdate	NOT NULL,
	order_count	number	DEFAULT 0	NOT NULL,
	memberRole	char(1)		NULL,
	withdrawal	char(1)	DEFAULT 'N'	NULL
);

CREATE TABLE order_tb (
	id	number		NOT NULL,
	member_id	varchar2(50)		NOT NULL,
	product_id	number		NOT NULL,
	store_id	varchar2(50)		NOT NULL,
	order_status	number		NOT NULL,
	order_date	date	DEFAULT sysdate	NOT NULL,
	price	number		NOT NULL
);

CREATE TABLE product (
	id	number		NOT NULL,
	sub_category_id	CHAR(5)		NOT NULL,
	name	varchar2(100)		NOT NULL,
	describe	number		NULL,
	unit_price	number	DEFAULT 0	NOT NULL,
	enroll_date	date	DEFAULT sysdate	NULL,
	enabled	char(1)		NULL
);

CREATE TABLE product_images (
	id	number		NOT NULL,
	product_id	number		NOT NULL,
	original_filename	varchar2(300)		NULL,
	renamed_filename	varchar2(200)		NULL,
	enroll_date	date	DEFAULT sysdate	NULL
);

CREATE TABLE pay (
	payment_id	char(6)		NOT NULL,
	order_id	number		NOT NULL,
	payment_method	varchar2(50)		NULL,
	status	number		NOT NULL
);

CREATE TABLE coupon (
	id	number		NOT NULL,
	member_id	varchar2(50)		NOT NULL,
	earned_date	date	DEFAULT sysdate	NOT NULL,
	quantity	number	DEFAULT 0	NOT NULL
);

CREATE TABLE grade (
	grade	varchar2(100)		NULL,
	standard	number		NULL
);

CREATE TABLE inquiry (
	id	number		NULL,
	member_id	varchar2(50)		NOT NULL,
	is_answered	char(1)		NULL,
	category	varchar2(20)		NOT NULL,
	title	varchar2(30)		NOT NULL,
	content	varchar2(2000)		NOT NULL,
	inquiry_date	Date	DEFAULT sysdate	NULL
);

CREATE TABLE store (
	store_id	varchar2(50)		NOT NULL,
	store_name	varchar2(50)		NOT NULL,
	address	varchar2(100)		NOT NULL,
	url	varchar2(100)		NULL,
	phone	number		NULL
);

CREATE TABLE notice (
	id	number		NOT NULL,
	title	varchar2(30)		NOT NULL,
	content	varchar2(2000)		NOT NULL,
	notice_date	Date	DEFAULT sysdate	NULL
);

CREATE TABLE cart (
	cart_id	varchar(10)		NOT NULL,
	member_id	varchar2(50)		NOT NULL,
	product_id	number		NOT NULL,
	quantity	number	DEFAULT 0	NOT NULL,
	cup	varchar2(10)		NOT NULL
);

CREATE TABLE main_category (
	main_category_id	CHAR(5)		NOT NULL,
	product_menu	varchar(100)		NULL
);

CREATE TABLE sub_category (
	sub_category_id	CHAR(5)		NOT NULL,
	main_category_id	CHAR(5)		NOT NULL,
	detail	varchar(100)		NULL
);

CREATE TABLE store_images (
	id	number		NOT NULL,
	store_id	varchar2(50)		NOT NULL,
	original_filename	varchar2(300)		NULL,
	renamed_filename	varchar2(200)		NULL,
	enroll_date	date	DEFAULT sysdate	NULL
);

CREATE TABLE inquiry_images (
	id	number		NOT NULL,
	inquiry_number	number		NULL,
	original_filename	varchar2(300)		NULL,
	renamed_filename	varchar2(200)		NULL,
	enroll_date	date	DEFAULT sysdate	NULL
);

CREATE TABLE notice_images (
	id	number		NOT NULL,
	notice_number	number		NOT NULL,
	original_filename	varchar2(300)		NULL,
	renamed_filename	varchar2(200)		NULL,
	enroll_date	date	DEFAULT sysdate	NULL
);

CREATE TABLE ordered_product (
	id	number		NOT NULL,
	order_id	number		NOT NULL,
	product_id	number		NOT NULL,
	amount	varchar(100)	DEFAULT 0	NOT NULL,
	cup	varchar(100)		NOT NULL
);

ALTER TABLE member ADD CONSTRAINT PK_MEMBER PRIMARY KEY (
	id
);

ALTER TABLE order_tb ADD CONSTRAINT PK_ORDER PRIMARY KEY (
	id
);

ALTER TABLE product ADD CONSTRAINT PK_PRODUCT PRIMARY KEY (
	id
);

ALTER TABLE product_images ADD CONSTRAINT PK_PRODUCT_IMAGES PRIMARY KEY (
	id
);

ALTER TABLE pay ADD CONSTRAINT PK_PAY PRIMARY KEY (
	payment_id
);

ALTER TABLE coupon ADD CONSTRAINT PK_COUPON PRIMARY KEY (
	id
);

ALTER TABLE grade ADD CONSTRAINT PK_GRADE PRIMARY KEY (
	grade
);

ALTER TABLE inquiry ADD CONSTRAINT PK_INQUIRY PRIMARY KEY (
	id
);

ALTER TABLE store ADD CONSTRAINT PK_STORE PRIMARY KEY (
	store_id
);

ALTER TABLE notice ADD CONSTRAINT PK_NOTICE PRIMARY KEY (
	id
);

ALTER TABLE cart ADD CONSTRAINT PK_CART PRIMARY KEY (
	cart_id
);

ALTER TABLE main_category ADD CONSTRAINT PK_MAIN_CATEGORY PRIMARY KEY (
	main_category_id
);

ALTER TABLE sub_category ADD CONSTRAINT PK_SUB_CATEGORY PRIMARY KEY (
	sub_category_id
);

ALTER TABLE store_images ADD CONSTRAINT PK_STORE_IMAGES PRIMARY KEY (
	id
);

ALTER TABLE inquiry_images ADD CONSTRAINT PK_INQUIRY_IMAGES PRIMARY KEY (
	id
);

ALTER TABLE notice_images ADD CONSTRAINT PK_NOTICE_IMAGES PRIMARY KEY (
	id
);

ALTER TABLE ordered_product ADD CONSTRAINT PK_ORDERED_PRODUCT PRIMARY KEY (
	id
);

ALTER TABLE member ADD CONSTRAINT FK_grade_TO_member_1 FOREIGN KEY (
	grade
)
REFERENCES grade  (
	grade
);

ALTER TABLE order_tb ADD CONSTRAINT FK_member_TO_order_1 FOREIGN KEY (
	member_id
)
REFERENCES member (
	id
);

ALTER TABLE order_tb ADD CONSTRAINT FK_product_TO_order_1 FOREIGN KEY (
	product_id
)
REFERENCES product (
	id
);

ALTER TABLE order_tb ADD CONSTRAINT FK_store_TO_order_1 FOREIGN KEY (
	store_id
)
REFERENCES store (
	store_id
);

ALTER TABLE product ADD CONSTRAINT FK_sub_category_TO_product_1 FOREIGN KEY (
	sub_category_id
)
REFERENCES sub_category (
	sub_category_id
);

ALTER TABLE product_images ADD CONSTRAINT FK_product_TO_product_images_1 FOREIGN KEY (
	product_id
)
REFERENCES product (
	id
);

ALTER TABLE payMENT ADD CONSTRAINT FK_order_TO_pay_1 FOREIGN KEY (
	order_id
)
REFERENCES order_tb (
	id
);

ALTER TABLE coupon ADD CONSTRAINT FK_member_TO_coupon_1 FOREIGN KEY (
	member_id
)
REFERENCES member (
	id
);

ALTER TABLE Inquiry ADD CONSTRAINT FK_member_TO_Inquiry_1 FOREIGN KEY (
	member_id
)
REFERENCES member (
	id
);

ALTER TABLE cart ADD CONSTRAINT FK_member_TO_cart_1 FOREIGN KEY (
	member_id
)
REFERENCES member (
	id
);

ALTER TABLE cart ADD CONSTRAINT FK_product_TO_cart_1 FOREIGN KEY (
	product_id
)
REFERENCES product (
	id
);

ALTER TABLE sub_category ADD CONSTRAINT FK_main_category_TO_sub_category_1 FOREIGN KEY (
	main_category_id
)
REFERENCES main_category (
	main_category_id
);

ALTER TABLE store_images ADD CONSTRAINT FK_store_TO_store_images_1 FOREIGN KEY (
	store_id
)
REFERENCES store (
	store_id
);

ALTER TABLE inquiry_images ADD CONSTRAINT FK_Inquiry_TO_inquiry_images_1 FOREIGN KEY (
	inquiry_number
)
REFERENCES inquiry (
	id
);

ALTER TABLE notice_images ADD CONSTRAINT FK_notice_TO_notice_images_1 FOREIGN KEY (
	notice_number
)
REFERENCES notice (
	id
);

ALTER TABLE ordered_product ADD CONSTRAINT FK_order_TO_ordered_product_1 FOREIGN KEY (
	order_id
)
REFERENCES order_tb (
	id
);

ALTER TABLE ordered_product ADD CONSTRAINT FK_product_TO_ordered_product_1 FOREIGN KEY (
	product_id
)
REFERENCES product (
	id
);

ALTER TABLE ordered_product ADD CONSTRAINT FK_member_TO_ordered_product_1 FOREIGN KEY (
	member_id
)
REFERENCES member (
	id
);



create sequence seq_product_id;
create sequence seq_product_images;


select * from member;
insert into member values ('hera', 'Silver', 'hhhera', 'fff', 'nmamama', '01012341234', to_date('19920714','yyyymmdd'), default, default, 'A', default);
insert into member values ('hera', default, 'hera', 'hera', 'hera', 'hera', to_date('19920714','yyyymmdd'), default, 0, default, default);


insert into main_category values ('DRINK', '메인 카테고리 - 음료메뉴');
insert into main_category values ('FOOD', '메인 카테고리 - 푸드메뉴');
select * from main_category;

insert into sub_category values ('CBR','DRINK', '콜드 브루 커피');
insert into sub_category values ('ESP','DRINK', '에스프레소');
insert into sub_category values ('FRP','DRINK', '프라푸치노');
insert into sub_category values ('BLD','DRINK', '블렌디드');
insert into sub_category values ('TEA','DRINK', '티');
insert into sub_category values ('BRD','FOOD', '브레드');
insert into sub_category values ('CAK','FOOD', '케이크');
insert into sub_category values ('SND','FOOD', '샌드위치 & 샐러드');
insert into sub_category values ('ICR','FOOD', '아이스크림');
select * from sub_category;
SET DEFINE On;

insert into product values (seq_product_id.nextval, 'CBR', '돌체 콜드 브루', '무더운 여름철, 동남아 휴가지에서 즐기는 커피를 떠오르게 하는 베스트 x 베스트 조합인 돌체 콜드 브루를 만나보세요!', default, default, 'Y');
insert into product values (seq_product_id.nextval, 'CBR', '나이트로 콜드 브루', '나이트로 커피 정통의 캐스케이딩과 부드러운 콜드 크레마! 부드러운 목 넘김과 완벽한 밸런스에 커피 본연의 단맛을 경험할 수 있습니다.', default, default, 'Y');
insert into product values (100, 'ESP', '아이스 카페 아메리카노', '진한 에스프레소에 시원한 정수물과 얼음을 더하여 스타벅스의 깔끔하고 강렬한 에스프레소를 가장 부드럽고 시원하게 즐길 수 있는 커피', default, default, 'Y');
select * from product;
select * from product_images;

insert into product_images values (seq_product_images.nextval, 3, 'dolce_colde_brew.jpeg', 'dolce_colde_brew.jpeg', default);
insert into product_images values (3, 100, 'iced_caffe_americano.jpeg', 'Iced Caffe Americano', default);

select * from product_images;

create sequence seq_ordered_product_id;
create sequence seq_order_id;
create sequence seq_order_num;
create sequence seq_cart_id;
create sequence seq_payment_id;

SELECT seq_order_id.CURRVAL FROM DUAL; 

insert into ordered_product values (seq_ordered_product_id.nextval, null, 202, 2, 'venti', '일회용컵');
--insert into ordered_product values (seq_ordered_product_id.nextval, null, ?, ?, ?, ?)
--select * from ordered_product where order_id is null and product_id = 403 and quantity = 3 and member_id = 'abcd12345';
--insert into cart values (seq_cart_id.nextval, 'abcd12345', 10, 3);
--select * from cart where member_id = 'abcd12345';
--delete from cart where cart_id = 23;
--delete from ordered_product where id = 43;
--delete from cart where member_id = 'abcd12345';
--UPDATE cart SET quantity = 3 WHERE cart_id = 22;

--select * from ordered_product where member_id = ? and product_id = ? and cup = ? and cup_size = ?
select * from store;
--cart 11 / op 14/ od 38

select * from ordered_product;
select * from cart;
select * from payment;
select * from order_tb;
select * from ordered_product where order_id is not null;

--LISTAGG( 합쳐지는 컬럼 [,'구분자']) WITHIN GROUP (ORDER BY 정렬하고 싶은 컬럼 ) [OVER (PARTITION BY  그룹을 나누고 싶은 컬럼)]
select ot.order_num,
REGEXP_REPLACE(LISTAGG(ot.member_id, ',') WITHIN GROUP (ORDER BY ot.member_id DESC ), '([^,]+)(,\1)*(,|$)', '\1\3') as member_id,
REGEXP_REPLACE(LISTAGG(ot.ID, ',') WITHIN GROUP (ORDER BY ot.ID DESC ), '([^,]+)(,\1)*(,|$)', '\1\3') as ID,
REGEXP_REPLACE(LISTAGG(ot.STORE_ID, ',') WITHIN GROUP (ORDER BY ot.STORE_ID DESC ), '([^,]+)(,\1)*(,|$)', '\1\3') as STORE_ID,
REGEXP_REPLACE(LISTAGG(ot.ORDER_STATUS, ',') WITHIN GROUP (ORDER BY ot.member_id DESC ), '([^,]+)(,\1)*(,|$)', '\1\3') as ORDER_STATUS,
REGEXP_REPLACE(LISTAGG(ot.ORDER_DATE, ',') WITHIN GROUP (ORDER BY ot.ORDER_DATE DESC ), '([^,]+)(,\1)*(,|$)', '\1\3') as ORDER_DATE,
REGEXP_REPLACE(LISTAGG(ot.TOTAL_PRICE, ',') WITHIN GROUP (ORDER BY ot.TOTAL_PRICE DESC ), '([^,]+)(,\1)*(,|$)', '\1\3') as TOTAL_PRICE,
REGEXP_REPLACE(LISTAGG(p.PAYMENT_METHOD, ',') WITHIN GROUP (ORDER BY p.PAYMENT_METHOD DESC ), '([^,]+)(,\1)*(,|$)', '\1\3') as PAYMENT_METHOD
from order_tb ot, payment p
where p.order_num = ot.order_num 
group by ot.order_num;

--SELECT ( CASE WHEN RANK = 1 THEN order_num ELSE '' END ) AS order_num,
--STORE_ID,ORDER_STATUS,TOTAL_PRICE,ORDER_DATE

--SELECT DISTINCT [중복제거할 컬럼] FROM [TABLE_NAME];
SELECT DISTINCT member_id, order_num, STORE_ID, TOTAL_PRICE, ORDER_STATUS FROM Order_tb;

--SELECT DISTINCT member_id, order_num, STORE_ID, TOTAL_PRICE, ORDER_STATUS FROM Order_tb where member_id='qwerty' ORDER BY order_status DESC;



--select ot.order_num,
--LISTAGG(ot.STORE_ID, ',') WITHIN GROUP (ORDER BY ot.STORE_ID DESC ) as STORE_ID,
--LISTAGG(ot.ORDER_STATUS, ',') WITHIN GROUP (ORDER BY ot.member_id DESC ) as ORDER_STATUS,
--LISTAGG(ot.ORDER_DATE, ',') WITHIN GROUP (ORDER BY ot.ORDER_DATE DESC ) as ORDER_DATE,
--LISTAGG(ot.TOTAL_PRICE, ',') WITHIN GROUP (ORDER BY ot.TOTAL_PRICE DESC ) TOTAL_PRICE,
--LISTAGG(p.PAYMENT_METHOD, ',') WITHIN GROUP (ORDER BY p.PAYMENT_METHOD DESC ) as PAYMENT_METHOD
--from order_tb ot, payment p
--where p.order_num = ot.order_num 
--group by ot.order_num;

select * from payment where order_num = 10010;
SELECT * FROM order_tb where member_ID = 'abcd12345';

UPDATE ordered_product SET order_id = 30 WHERE id = 19;
--select * from ordered_product where member_id = ? and product_id = ? and cup = ? and cup_size = ?
select * from store;
select MAX(order_num) from order_tb;

create table board (
    no number,
    title varchar2(200) not null,
    writer varchar2(20),
    content varchar2(4000) not null,
    read_count number default 0,
    reg_date date default sysdate,
    constraint pk_board_no primary key(no),
    constraint fk_board_writer foreign key(writer) references member(id)
        on delete set null
);
create sequence seq_board_no;

create table attachment (
    no number,
    board_no number not null,
    original_filename varchar2(255) not null, -- 업로드한 파일명
    renamed_filename varchar2(255) not null, -- 실제 저장된 파일명
    reg_date date default sysdate,
    constraint pk_attachment_no primary key(no),
    constraint fk_attachment_board_no foreign key(board_no) references board(no) 
      on delete cascade
);

create sequence seq_attachment_no;