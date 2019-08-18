CREATE TABLE IF NOT EXISTS t_order_0 (
  order_id bigint(20) NOT NULL AUTO_INCREMENT,
  user_id int(11) NOT NULL,
  status varchar(50)  DEFAULT NULL,
  PRIMARY KEY (order_id)
);

CREATE TABLE IF NOT EXISTS t_order_1 (
  order_id bigint(20) NOT NULL AUTO_INCREMENT,
  user_id int(11) NOT NULL,
  status varchar(50) DEFAULT NULL,
  PRIMARY KEY (order_id)
);

CREATE TABLE IF NOT EXISTS t_order_item_0 (
  order_item_id bigint(20) NOT NULL AUTO_INCREMENT,
  order_id bigint(20) NOT NULL,
  user_id int(11) NOT NULL,
  PRIMARY KEY (order_item_id)
);

CREATE TABLE IF NOT EXISTS t_order_item_1 (
  order_item_id bigint(20) NOT NULL AUTO_INCREMENT,
  order_id bigint(20) NOT NULL,
  user_id int(11) NOT NULL,
  PRIMARY KEY (order_item_id)
);