CREATE TABLE IF NOT EXISTS routes (id SERIAL PRIMARY KEY, path VARCHAR(255), method VARCHAR(255), consumer VARCHAR(255));
insert  into routes(path,method,consumer) values('/consumer2','GET','consumer2');
insert  into routes(path,method,consumer) values('/consumer1','GET','consumer1');