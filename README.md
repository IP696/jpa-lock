# jpa-lock

В базе лежат записи типа
```
1	Tomato	1	  tomato brazilian	NEW	2018-09-14
5	Banana	12	banana africa	    NEW	2018-09-14
3	Potato	3	  potato russia	    NEW	2018-09-14
4	Mango	  9  	mango africa	    NEW	2018-09-14
6	Carrot	7 	carrot russia	    NEW	2018-09-14
```

к серверу обращается много клиентов, и каждый из них одновременно каждую секунду отправляет в 5 потоках запрос вида - дай мне продукт 
со статусом NEW, с самой старой датой. Если в базе есть 5 записей удовлетворяющих этому запросу, то ожидается такая логика:

`1 поток получит одну самую старую запись из этих 5 подходящих
 2 поток получит слудеющую самую старую запись из уже оставшихся 4 подходящих
 ...
 5 поток получит последнюю оставшуюся запись`

один поток - одна запись. Ни один другой поток не должен получить повторно запись.

когда сервис получает 5 запросов одновременно - они должны отработать паралельно. Тоесть все потоки не ждут пока отработает первый.


скрипт таблицы
```
create table if not exists products
(
	id bigserial not null
		constraint products_pkey
			primary key,
	name varchar(100),
	count integer,
	description varchar(500),
	status varchar(100),
	expiration_date date
);

create unique index if not exists products_id_uindex on products (id);
```

в данном примере реализована эта задача путем блокировки на базе, с использованием базы  PostgreSQL. 
