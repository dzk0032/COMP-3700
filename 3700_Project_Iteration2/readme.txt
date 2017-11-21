Below is the sql code used for the PRODUCT database.
There is only one account originially with the password of 1111.
This account is a manager account.
NetBeans IDE was used for this project




CREATE TABLE "PRODUCT" (
	ID					INT,		
	Name				VARCHAR,
	Quantity			INT,
	Price				DOUBLE,
	ExpirationDate		INT,
	SellerInfo			VARCHAR
);

CREATE TABLE "PURCHASE" (
	UserID		INT,
	ID			INT,	
	Name	VARCHAR,
	Quantity	INT,
	Price		DOUBLE
);


CREATE TABLE "USER" (
	ID			INT,	
	UserType	INT,
	Password	INT
);

CREATE TABLE "PICTURE" (
	UserID		INT,
	AcctPhoto	BLOB
);


INSERT INTO USER (ID, UserType, Password)
VALUES(1, 1, 1111);

INSERT INTO PRODUCT (ID, Name, Quantity, Price, ExpirationDate, SellerInfo)
VALUES (1, 'Apple', 200, 2.00, 20171001, 'California Farm');

INSERT INTO PRODUCT (ID, Name, Quantity, Price, ExpirationDate, SellerInfo)
VALUES(2, 'Pencil', 400, 1.00, 0, 'BIG');

INSERT INTO PRODUCT (ID, Name, Quantity, Price, ExpirationDate, SellerInfo)
VALUES(3, 'Broom', 5, 15.00, 0, 'Boston Broom');

INSERT INTO PRODUCT (ID, Name, Quantity, Price, ExpirationDate, SellerInfo)
VALUES(4, 'Note', 200, 12.00, 0, 'Five Star');

INSERT INTO PRODUCT (ID, Name, Quantity, Price, ExpirationDate, SellerInfo)
VALUES(5, 'Pork Belly', 60, 15.00, 20171021, 'Sweethome Alabama');

