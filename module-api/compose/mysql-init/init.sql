ALTER USER 'root'@'%' IDENTIFIED WITH mysql_native_password BY 'root1234';

CREATE USER 'devuser'@'%' IDENTIFIED WITH mysql_native_password BY 'devpass123';
GRANT ALL PRIVILEGES ON *.* TO 'devuser'@'%' WITH GRANT OPTION;
FLUSH PRIVILEGES;
