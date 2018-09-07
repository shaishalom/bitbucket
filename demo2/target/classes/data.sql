insert into BANK (BANK_ID)
values(1);

insert into BRANCH (BRANCH_ID,ADDRESS,BANK_ID)
values(1,'ADDRESS 1',1);

insert into BRANCH (BRANCH_ID,ADDRESS,BANK_ID)
values(2,'ADDRESS 2',1);

insert into ACCOUNT(ACCOUNT_ID,BALANCE,BRANCH_ID) 
values(1,100,1);

insert into ACCOUNT(ACCOUNT_ID,BALANCE,BRANCH_ID) 
values(2,200,1);

insert into ACCOUNT(ACCOUNT_ID,BALANCE,BRANCH_ID) 
values(3,300,2);

insert into ACCOUNT(ACCOUNT_ID,BALANCE,BRANCH_ID) 
values(4,400,2);

insert into CUSTOMER (CUSTOMER_ID,FIRST_NAME,LAST_NAME,GENDER,ACCOUNT_ID)
values(10001,'shai', 'shalom','MALE',1);

insert into CUSTOMER (CUSTOMER_ID,FIRST_NAME,LAST_NAME,GENDER,ACCOUNT_ID)
values(10002,'shlomit','shalom','FEMALE',2);

insert into CUSTOMER (CUSTOMER_ID,FIRST_NAME,LAST_NAME,GENDER,ACCOUNT_ID)
values(10003,'guy', 'shalom','MALE',3);

insert into CUSTOMER (CUSTOMER_ID,FIRST_NAME,LAST_NAME,GENDER,ACCOUNT_ID)
values(10004,'carmel', 'shalom','FEMALE',4);
