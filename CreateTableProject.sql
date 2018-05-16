DROP TABLE `Promoted`;


DROP TABLE `Shipping`;


DROP TABLE `Ship`;


DROP TABLE `Processed`;


DROP TABLE `Ordered_Items`;


DROP TABLE `Ordered`;


DROP TABLE `Take_Place`;


DROP TABLE `Account`;


DROP TABLE `Sold_At`;


DROP TABLE `paid_with`;


DROP TABLE `contains`;


DROP TABLE `supplies`;


DROP TABLE `owner_of`;


DROP TABLE `Has`;


DROP TABLE `Works_at`;


DROP TABLE `Shop_At`;


DROP TABLE `Promotions`;


DROP TABLE `Shipping_Company`;


DROP TABLE `Shipment`;


DROP TABLE `Invoice`;


DROP TABLE `Orders`;


DROP TABLE `Events`;


DROP TABLE `Warehouse`;


DROP TABLE `Receipt`;


DROP TABLE `Vendor`;


DROP TABLE `Products`;


DROP TABLE `Employee`;


DROP TABLE `Store`;


DROP TABLE `Customer`;


DROP TABLE `Credit_Card`;


CREATE TABLE `Promotions`
(
 `Promo_Id` INT NOT NULL AUTO_INCREMENT,
 `Date`     VARCHAR(45) NOT NULL ,
 `Discount` DOUBLE NOT NULL ,

PRIMARY KEY (`Promo_Id`)
);

CREATE TABLE `Shipping_Company`
(
 `Company_Id` INT NOT NULL AUTO_INCREMENT,
 `Name`       VARCHAR(45) NOT NULL ,
 `Price`      DOUBLE NOT NULL ,

PRIMARY KEY (`Company_Id`)
);

CREATE TABLE `Shipment`
(
 `Shipment_Id`      INT NOT NULL AUTO_INCREMENT,
 `Confirm_Num`      INT NOT NULL ,
 `Shipping_Address` VARCHAR(45) NOT NULL ,

PRIMARY KEY (`Shipment_Id`)
);

CREATE TABLE `Invoice`
(
 `Invoice_Id` INT NOT NULL AUTO_INCREMENT,
 `Cost`       DOUBLE NOT NULL ,

PRIMARY KEY (`Invoice_Id`)
);

CREATE TABLE `Orders`
(
 `O_Id` INT NOT NULL AUTO_INCREMENT,

PRIMARY KEY (`O_Id`)
);

CREATE TABLE `Events`
(
 `Event_Id`  INT NOT NULL AUTO_INCREMENT,
 `Name`		 VARCHAR(45) NOT NULL ,
 `Host_Name` VARCHAR(45) NOT NULL ,
 `Date`      VARCHAR(45) NOT NULL ,

PRIMARY KEY (`Event_Id`)
);

CREATE TABLE `Warehouse`
(
 `W_id`     INT NOT NULL AUTO_INCREMENT,
 `Location` VARCHAR(45) NOT NULL ,

PRIMARY KEY (`W_id`)
);


CREATE TABLE `Receipt`
(
 `S_id`       INT NOT NULL AUTO_INCREMENT,
 `Total_cost` DOUBLE NOT NULL ,
 `Date`       VARCHAR(45) NOT NULL ,

PRIMARY KEY (`S_id`)
);


CREATE TABLE `Vendor`
(
 `V_id`     INT NOT NULL AUTO_INCREMENT,
 `Name`     VARCHAR(45) NOT NULL ,
 `Location` VARCHAR(45) NOT NULL ,
 `Contact`  BIGINT NOT NULL ,

PRIMARY KEY (`V_id`)
);


CREATE TABLE `Products`
(
 `P_id`        INT NOT NULL AUTO_INCREMENT,
 `Name`        VARCHAR(45) NOT NULL ,
 `Description` VARCHAR(45) NOT NULL ,
 `Amt_left`    INT NOT NULL ,
 `Price`       DOUBLE NOT NULL ,

PRIMARY KEY (`P_id`)
);


CREATE TABLE `Employee`
(
 `SSN`       INT NOT NULL ,
 `Fname`     VARCHAR(45) NOT NULL ,
 `Lname`     VARCHAR(45) NOT NULL ,
 `Address`   VARCHAR(45) NOT NULL ,
 `Sex`       CHAR NOT NULL ,
 `Age`       INT NOT NULL ,
 `Super_ssn` INT,

PRIMARY KEY (`SSN`)
);

CREATE TABLE `Store`
(
 `Store_id` INT NOT NULL AUTO_INCREMENT,
 `Location` VARCHAR(45) NOT NULL ,

PRIMARY KEY (`Store_id`)
);


CREATE TABLE `Customer`
(
 `C_id`    INT NOT NULL AUTO_INCREMENT,
 `Fname`   VARCHAR(45) NOT NULL ,
 `Lname`   VARCHAR(45) NOT NULL ,
 `Sex`     CHAR NOT NULL ,
 `Address` VARCHAR(45) NOT NULL ,
 `Balance` DOUBLE ,

PRIMARY KEY (`C_id`)
);

CREATE TABLE `Credit_Card`
(
 `Credit_id`    INT NOT NULL AUTO_INCREMENT,
 `Name`   VARCHAR(45) NOT NULL ,
 `Credit_num`   INT NOT NULL ,
 
PRIMARY KEY (`Credit_id`)
);

CREATE TABLE `Account`
(
 `A_id`     INT NOT NULL AUTO_INCREMENT,
 `SSN`      INT NOT NULL ,
 `Password` VARCHAR(100) NOT NULL ,
 `Username` VARCHAR(45) NOT NULL ,

PRIMARY KEY (`A_id`, `SSN`),
CONSTRAINT `Account-SSN` FOREIGN KEY (`SSN`) REFERENCES `Employee` (`SSN`)
);

CREATE TABLE `Sold_At`
(
 `o_id`     INT NOT NULL ,
 `Store_id` INT NOT NULL ,

PRIMARY KEY (`o_id`, `Store_id`),
CONSTRAINT `Sold_at-O_id` FOREIGN KEY (`o_id`) REFERENCES `Orders` (`o_id`),
CONSTRAINT `Sold_at-Store_id` FOREIGN KEY (`Store_id`) REFERENCES `Store` (`Store_id`)
);

CREATE TABLE `contains`
(
 `W_id` INT NOT NULL ,
 `P_id` INT NOT NULL ,

PRIMARY KEY (`W_id`, `P_id`),
CONSTRAINT `Contains-W_id` FOREIGN KEY (`W_id`) REFERENCES `Warehouse` (`W_id`),
CONSTRAINT `Contains-P_id` FOREIGN KEY (`P_id`) REFERENCES `Products` (`P_id`)
);

CREATE TABLE `supplies`
(
 `P_id` INT NOT NULL ,
 `V_id` INT NOT NULL ,

PRIMARY KEY (`P_id`, `V_id`),
CONSTRAINT `Supplies-P_id` FOREIGN KEY (`P_id`) REFERENCES `Products` (`P_id`),
CONSTRAINT `Supplies-V_id` FOREIGN KEY (`V_id`) REFERENCES `Vendor` (`V_id`)
);

CREATE TABLE `Has`
(
 `Store_id` INT NOT NULL ,
 `P_id`     INT NOT NULL ,

PRIMARY KEY (`Store_id`, `P_id`),
CONSTRAINT `Has-Store_id` FOREIGN KEY (`Store_id`) REFERENCES `Store` (`Store_id`),
CONSTRAINT `Has-P_id` FOREIGN KEY (`P_id`) REFERENCES `Products` (`P_id`)
);

CREATE TABLE `Works_at`
(
 `Store_id` INT NOT NULL ,
 `SSN`      INT NOT NULL ,

PRIMARY KEY (`Store_id`, `SSN`),
CONSTRAINT `Works_At-Store_id` FOREIGN KEY (`Store_id`) REFERENCES `Store` (`Store_id`),
CONSTRAINT `Works_At-SSN` FOREIGN KEY (`SSN`) REFERENCES `Employee` (`SSN`)
);

CREATE TABLE `Shop_At`
(
 `C_id`     INT NOT NULL ,
 `Store_id` INT NOT NULL ,

PRIMARY KEY (`C_id`, `Store_id`),
CONSTRAINT `Shop_At-C_id` FOREIGN KEY (`C_id`) REFERENCES `Customer` (`C_id`),
CONSTRAINT `Shop_At-Store_id` FOREIGN KEY (`Store_id`) REFERENCES `Store` (`Store_id`)
);

CREATE TABLE `Promoted`
(
 `P_id`     INT NOT NULL ,
 `Promo_Id` INT NOT NULL ,

PRIMARY KEY (`P_id`, `Promo_Id`),
KEY `Promoted-P_id` (`P_id`),
CONSTRAINT `Promoted-P_id` FOREIGN KEY `Promoted-P_id` (`P_id`) REFERENCES `Products` (`P_id`),
KEY `Promoted-Promo_Id` (`Promo_Id`),
CONSTRAINT `Promoted-Promo_Id` FOREIGN KEY `Promoted-Promo_Id` (`Promo_Id`) REFERENCES `Promotions` (`Promo_Id`)
);

CREATE TABLE `Shipping`
(
 `Company_Id`  INT NOT NULL ,
 `Shipment_Id` INT NOT NULL ,

PRIMARY KEY (`Company_Id`, `Shipment_Id`),
KEY `Shipping-Company_Id` (`Company_Id`),
CONSTRAINT `Shipping-Company_Id` FOREIGN KEY `Shipping-Company_Id` (`Company_Id`) REFERENCES `Shipping_Company` (`Company_Id`),
KEY `Shipping-Shipment_Id` (`Shipment_Id`),
CONSTRAINT `Shipping-Shipment_Id` FOREIGN KEY `Shipping-Shipment_Id` (`Shipment_Id`) REFERENCES `Shipment` (`Shipment_Id`)
);

CREATE TABLE `Ship`
(
 `O_Id`        INT NOT NULL ,
 `Shipment_Id` INT NOT NULL ,

PRIMARY KEY (`O_Id`, `Shipment_Id`),
KEY `Ship-O_Id` (`O_Id`),
CONSTRAINT `Ship-O_Id` FOREIGN KEY `Ship-O_Id` (`O_Id`) REFERENCES `Orders` (`O_Id`),
KEY `Ship-Shipment_Id` (`Shipment_Id`),
CONSTRAINT `Ship-Shipment_Id` FOREIGN KEY `Ship-Shipment_Id` (`Shipment_Id`) REFERENCES `Shipment` (`Shipment_Id`)
);

CREATE TABLE `Processed`
(
 `O_Id`       INT NOT NULL ,
 `Invoice_Id` INT NOT NULL ,

PRIMARY KEY (`O_Id`, `Invoice_Id`),
KEY `Processed-O_Id` (`O_Id`),
CONSTRAINT `Processed-O_Id` FOREIGN KEY `Processed-O_Id` (`O_Id`) REFERENCES `Orders` (`O_Id`),
KEY `Processed-Invoice_Id` (`Invoice_Id`),
CONSTRAINT `Processed-Invoice_Id` FOREIGN KEY `Processed-Invoice_Id` (`Invoice_Id`) REFERENCES `Invoice` (`Invoice_Id`)
);

CREATE TABLE `Ordered_Items`
(
 `Invoice_Id` INT NOT NULL ,
 `P_id`       INT NOT NULL ,

PRIMARY KEY (`Invoice_Id`, `P_id`),
KEY `Ordered_Items-Invoice_Id` (`Invoice_Id`),
CONSTRAINT `Ordered_Items-Invoice_Id` FOREIGN KEY `Ordered_Items-Invoice_Id` (`Invoice_Id`) REFERENCES `Invoice` (`Invoice_Id`),
KEY `Ordered_Items-P_Id` (`P_id`),
CONSTRAINT `Ordered_Items-P_Id` FOREIGN KEY `Ordered_Items-P_Id` (`P_id`) REFERENCES `Products` (`P_id`)
);

CREATE TABLE `Ordered`
(
 `C_id` INT NOT NULL ,
 `O_Id` INT NOT NULL ,

PRIMARY KEY (`C_id`, `O_Id`),
KEY `Ordered-C_id` (`C_id`),
CONSTRAINT `Ordered-C_id` FOREIGN KEY `Ordered-C_id` (`C_id`) REFERENCES `Customer` (`C_id`),
KEY `Ordered-O_id` (`O_Id`),
CONSTRAINT `Ordered-O_id` FOREIGN KEY `Ordered-O_id` (`O_Id`) REFERENCES `Orders` (`O_Id`)
);

CREATE TABLE `Take_Place`
(
 `Store_id` INT NOT NULL ,
 `Event_Id` INT NOT NULL ,

PRIMARY KEY (`Store_id`, `Event_Id`),
KEY `Take_Place-Store_id` (`Store_id`),
CONSTRAINT `Take_Place-Store_id` FOREIGN KEY `Take_Place-Store_id` (`Store_id`) REFERENCES `Store` (`Store_id`),
KEY `Take_Place-Event_id` (`Event_Id`),
CONSTRAINT `Take_Place-Event_id` FOREIGN KEY `Take_Place-Event_id` (`Event_Id`) REFERENCES `Events` (`Event_Id`)
);

CREATE TABLE `Paid_with`
(
 `o_id` INT NOT NULL ,
 `credit_Id` INT NOT NULL ,

PRIMARY KEY (`o_id`, `credit_Id`),
KEY `owner_of-o_id` (`o_id`),
CONSTRAINT `Owner_of-o_id` FOREIGN KEY `Owner_of-o_id` (`o_id`) REFERENCES `Orders` (`o_id`),
KEY `owner_of-Credit_id` (`credit_Id`),
CONSTRAINT `Owner_of-credit_id` FOREIGN KEY `Owner_of-credit_id` (`credit_Id`) REFERENCES `Credit_Card` (`credit_Id`)
);




