1. ȸ�������� ������ ������ �� �ִ� 'web_member'���̺��� ����ÿ�.
   �÷��� : email / pw / tel / address

select * from member;

create table message(

	num number,
	send varchar2(100),
	receive varchar2(100),
	message varchar2(500),
	message_data date

);

create sequence num start with 1 increment by 1;

insert into message values(num.nextval, '������', '����', '�̹��ֱݿ��Ͽ�������...', sysdate )

select * from message;





