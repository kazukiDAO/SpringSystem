■テーブル作成
psql -h <hostname> -U postgres

postgres=>
create database testdb;

postgres=>
create role testuser with login password 'testuser';

postgres=>
\q

psql -h <hostname> -U testuser -d testdb

testuser=>
CREATE TABLE employee( employee_id integer primary key, employee_name VARCHAR(50), age integer ) ;

testuser=>
CREATE TABLE IF NOT EXISTS m_user (
user_id VARCHAR(50) PRIMARY KEY,
password VARCHAR(100),
user_name VARCHAR(50),
birthday DATE,
age INT,
marriage int,
role VARCHAR(50)
);

■レコード作成
testuser=>
INSERT INTO employee( employee_id, employee_name, age ) VALUES( 1, 'yamada taro', 30 ) ;

testuser=>
INSERT INTO m_user (user_id, password, user_name, birthday, age, marriage, role)
VALUES('yamada@xxx.co.jp', '$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa', 'yamada', '1990-01-01', 28, 1, 'ROLE_ADMIN');

testuser=>
INSERT INTO m_user (user_id, password, user_name, birthday, age, marriage, role)
VALUES('tamura@xxx.co.jp', '$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa', 'tamura', '1986-11-05', 31, 1, 'ROLE_GENERAL');

■セッションテーブル作成
https://github.com/spring-projects/spring-session/blob/main/spring-session-jdbc/src/main/resources/org/springframework/session/jdbc/schema-postgresql.sql