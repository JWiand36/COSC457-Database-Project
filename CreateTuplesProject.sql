SET FOREIGN_KEY_CHECKS=0;

INSERT INTO account (a_id, ssn, password, username) 
VALUES (1,1,12345,'John');

INSERT INTO account (a_id, ssn, password, username) 
VALUES (2,2,12345,'Hanna');

INSERT INTO account (a_id, ssn, password, username) 
VALUES (3,3,12345,'Zach');

INSERT INTO account (a_id, ssn, password, username) 
VALUES (4,4,12345,'Leon');

INSERT INTO customer (c_id, fname, lname, sex, address, balance) 
VALUES (1, 'Brad', 'Smoke', 'M', 'Towson', 0.0);

INSERT INTO customer (c_id, fname, lname, sex, address, balance) 
VALUES (2, 'Jessica', 'High', 'F', 'Baltimore', 5.0);

INSERT INTO customer (c_id, fname, lname, sex, address, balance) 
VALUES (3, 'Brittany', 'Lean', 'M', 'Washington DC', 3.0);

INSERT INTO employee (ssn, fname, lname, address, sex, age, super_ssn) 
VALUES (1, 'John', 'Smith', 'Frederick', 'M', 43, null);

INSERT INTO employee (ssn, fname, lname, address, sex, age, super_ssn) 
VALUES (2, 'Hanna', 'Bring', 'Towson', 'F', 25, null);

INSERT INTO employee (ssn, fname, lname, address, sex, age, super_ssn) 
VALUES (3, 'Zach', 'Tight', 'Towson', 'M', 32, null);

INSERT INTO employee (ssn, fname, lname, address, sex, age, super_ssn) 
VALUES (4, 'Leon', 'Jones', 'Baltimore', 'M', 24, null);

INSERT INTO employee (ssn, fname, lname, address, sex, age, super_ssn) 
VALUES (5, 'Casey', 'Wrong', 'Frederick', 'M', 19, 1);

INSERT INTO employee (ssn, fname, lname, address, sex, age, super_ssn) 
VALUES (6, 'Mary', 'Right', 'Towson', 'M', 22, 2);

INSERT INTO employee (ssn, fname, lname, address, sex, age, super_ssn) 
VALUES (7, 'Mike', 'Low', 'Towson', 'M', 43, 3);

INSERT INTO employee (ssn, fname, lname, address, sex, age, super_ssn) 
VALUES (8, 'Suzy', 'Grain', 'Baltimore', 'F', 34, 4);

INSERT INTO products (p_id, name, description, amt_left, price) 
VALUES (1,'Harry Potter', 'Book', 10, 5.99);

INSERT INTO products (p_id, name, description, amt_left, price) 
VALUES (2,'God of War', 'Game', 20, 59.99);

INSERT INTO products (p_id, name, description, amt_left, price) 
VALUES (3,'Game of Thrones', 'Book', 15, 5.99);

INSERT INTO products (p_id, name, description, amt_left, price) 
VALUES (4,'Far Cry 5', 'Book', 10, 59.99);

INSERT INTO sale (s_id, total_cost,date) 
VALUES (1,15.0, 'March 3 2018');

INSERT INTO sale (s_id, total_cost,date) 
VALUES (2,120.0, 'April 16 2018');

INSERT INTO sale (s_id, total_cost,date) 
VALUES (3,30.0, 'March 28 2018');

INSERT INTO sale (s_id, total_cost,date) 
VALUES (4,45.0, 'April 10 2018');

INSERT INTO store (store_id, location) 
VALUES (1,'Towson');

INSERT INTO store (store_id, location) 
VALUES (2,'Baltimore');

INSERT INTO vendor (v_id, name, location, contact) 
VALUES (1,'Games R Us','Baltimore',3011115465);

INSERT INTO vendor (v_id, name, location, contact) 
VALUES (2,'Epic Books','Washington DC',3014585465);

INSERT INTO warehouse (w_id, location) 
VALUES (1,'Towson');

INSERT INTO warehouse (w_id, location) 
VALUES (2, 'Baltimore');

INSERT INTO contains (w_id, p_id) 
VALUES (1, 2);

INSERT INTO contains (w_id, p_id) 
VALUES (1, 1);

INSERT INTO contains (w_id, p_id) 
VALUES (1, 3);

INSERT INTO contains (w_id, p_id) 
VALUES (2, 4);

INSERT INTO contains (w_id, p_id) 
VALUES (2, 3);

INSERT INTO has (store_id, p_id) 
VALUES (1,4);

INSERT INTO has (store_id, p_id) 
VALUES (1,3);

INSERT INTO has (store_id, p_id) 
VALUES (2,1);

INSERT INTO has (store_id, p_id) 
VALUES (2,2);

INSERT INTO has (store_id, p_id) 
VALUES (2,3);

INSERT INTO has (store_id, p_id) 
VALUES (2,4);

INSERT INTO items_sold (p_id, s_id) 
VALUES (1,1);

INSERT INTO items_sold (p_id, s_id) 
VALUES (3,1);

INSERT INTO items_sold (p_id, s_id) 
VALUES (2,2);

INSERT INTO items_sold (p_id, s_id) 
VALUES (1,3);

INSERT INTO items_sold (p_id, s_id) 
VALUES (1,4);

INSERT INTO items_sold (p_id, s_id) 
VALUES (4,4);

INSERT INTO items_sold (p_id, s_id) 
VALUES (2,4);

INSERT INTO makes_sale (c_id, s_id) 
VALUES (1,1);

INSERT INTO makes_sale (c_id, s_id) 
VALUES (2,2);

INSERT INTO makes_sale (c_id, s_id) 
VALUES (2,3);

INSERT INTO makes_sale (c_id, s_id) 
VALUES (3,4);

INSERT INTO shop_at (c_id, store_id) 
VALUES (1,1);

INSERT INTO shop_at (c_id, store_id) 
VALUES (2,2);

INSERT INTO shop_at (c_id, store_id) 
VALUES (3,2);

INSERT INTO sold_at (s_id, store_id) 
VALUES (1,1);

INSERT INTO sold_at (s_id, store_id) 
VALUES (2,2);

INSERT INTO sold_at (s_id, store_id) 
VALUES (3,2);

INSERT INTO sold_at (s_id, store_id) 
VALUES (4,2);

INSERT INTO supplies (p_id, v_id) 
VALUES (2,1);

INSERT INTO supplies (p_id, v_id) 
VALUES (4,1);

INSERT INTO supplies (p_id, v_id) 
VALUES (1,2);

INSERT INTO supplies (p_id, v_id) 
VALUES (3,2);

INSERT INTO works_at (store_id, ssn) 
VALUES (2,1);

INSERT INTO works_at (store_id, ssn) 
VALUES (2,4);

INSERT INTO works_at (store_id, ssn) 
VALUES (1,2);

INSERT INTO works_at (store_id, ssn) 
VALUES (1,3);

INSERT INTO works_at (store_id, ssn) 
VALUES (2,5);

INSERT INTO works_at (store_id, ssn) 
VALUES (1,6);

INSERT INTO works_at (store_id, ssn) 
VALUES (1,7);

INSERT INTO works_at (store_id, ssn) 
VALUES (2,8);

INSERT INTO events(event_id, name, host_name, date)
VALUES (1, "Magic", "Brad", "Nov, 6");

INSERT INTO take_place(event_id, store_id)
VALUES (1,1);

INSERT INTO promotions(promo_id, date, discount)
VALUES (1,"Nov 6", 5);

INSERT INTO promoted(promo_id, P_id)
VALUES (1,1);
INSERT INTO orders(O_Id)
VALUES (1);

INSERT INTO Ordered(C_id, O_id)
VALUES (1,1);

INSERT INTO Invoice(Invoice_id, cost)
VALUES (1,100.0);

INSERT INTO Processed(Invoice_id, O_id)
VALUES (1,1);

INSERT INTO ordered_items(Invoice_id, P_id)
VALUES (1,2);

INSERT INTO ordered_items(Invoice_id, P_id)
VALUES (1,3);

INSERT INTO shipment(Shipment_Id, Confirm_num, shipping_address)
VALUES (1,45645, "Here");

INSERT INTO ship(O_id, shipment_id)
VALUES (1,1);

INSERT INTO shipping_company(company_id, name, price)
VALUES (1,"UPS",5.99);

INSERT INTO shipping_company(company_id, name, price)
VALUES (2,"USPS",4.99);

INSERT INTO shipping_company(company_id, name, price)
VALUES (3,"Fed-Ex",6.99);

INSERT INTO shipping(shipment_id, company_id)
VALUES (1,1);

INSERT INTO ordered(c_id, o_id)
VALUES (1,1);

SET FOREIGN_KEY_CHECKS=1;