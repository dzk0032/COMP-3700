CREATE TABLE "PRODUCT" (
	ID					INT,		
	Name				VARCHAR,
	Quantity			INT	,
	Price				DOUBLE,
	ExpirationDate		INT,
	SellerInfo			VARCHAR
);

CREATE TABLE "PURCHASE" (
	ID			INT,
	Name		VARCHAR,
	Quantity	INT,
	Price		DOUBLE
);

INSERT INTO PRODUCT (ID, Name, Quantity, Price, ExpirationDate, SellerInfo)
VALUES (1, 'Apple', 200, 2.00, 20171001, 'California Farm');

INSERT INTO PRODUCT (ID, Name, Quantity, Price, ExpirationDate, SellerInfo)
VALUES (2, 'Pencil', 400, 1.00, 00000000, 'BIG');

INSERT INTO PRODUCT (ID, Name, Quantity, Price, ExpirationDate, SellerInfo)
VALUES (3, 'Broom', 50, 15.00, 00000000, 'Boston Broom');

INSERT INTO PRODUCT (ID, Name, Quantity, Price, ExpirationDate, SellerInfo)
VALUES (4, 'Note', 200, 12.00, 00000000, 'Five Star');

INSERT INTO PRODUCT (ID, Name, Quantity, Price, ExpirationDate, SellerInfo)
VALUES (5, 'Pork Belly', 60, 15.00, 20171021, 'Sweethome Alabama');
