drop table if exists Grade;

create table Grade (
  ID integer not null, 
  CODE varchar(50) not null, 
  NBHEURESBASE decimal(10,3) not null, 
  TAUXBASE decimal(10,7) not null,
  primary key (Id)
) engine=innodb default charset=utf8;

set autocommit=1;