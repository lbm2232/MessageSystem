1. 회원가입한 정보를 저장할 수 있는 'web_member'테이블을 만드시오.
   컬럼명 : email / pw / tel / address

select * from member;

create table message(

	num number,
	send varchar2(100),
	receive varchar2(100),
	message varchar2(500),
	message_data date

);

create sequence num start with 1 increment by 1;

insert into message values(num.nextval, '아이유', '보미', '이번주금요일에만나요...', sysdate )

select * from message;





