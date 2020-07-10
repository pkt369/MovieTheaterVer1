create table movies(
	name 	varchar2(50),
	age 	number(5)
);

select * from MOVIES;

drop table movies purge;

create table theater(
	city		varchar2(20), 
	TheaterName	varchar2(30),
	movie 		varchar2(50),
	day			varchar2(12),
	startTime 	varchar2(10),
	auditorium	varchar2(5)
);

select * from theater;

drop table theater purge;