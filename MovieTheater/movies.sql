create table movies(
	name 	varchar2(50),
	age 	number(5),
	url varchar2(200)
);

insert into MOVIES(name, age, url) values ('프랑스여자',  15, 'https://movie-phinf.pstatic.net/20200515_262/1589519519892tStin_JPEG/movie_image.jpg?type=f67');

alter table movies rename column imageURL to url;

update movies set url = 'https://movie-phinf.pstatic.net/20200622_287/1592789179904A3DTh_JPEG/movie_image.jpg' where name = '베트맨 비긴즈';


select * from MOVIES;

drop table movies purge;

create table theater(
	city		varchar2(20), 
	TheaterName	varchar2(30),
	movie 		varchar2(50),
	day			varchar2(12),
	startTime 	varchar2(10),
	auditorium	varchar2(5),
	sellSeats 	varchar2(600)
);

select * from theater;

alter table  theater add sellSeats varchar2(600);

update theater set sellSeats = null;

drop table theater purge;