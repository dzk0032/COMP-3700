CREATE TABLE database {
	Name				VARCHAR
	ID					VARCHAR
	BarcodeID			INT
	DateOfProduction	DATE
	Quantity			INT
	OrderQuantity		INT
	Price				FLOAT
	ExpirationDate		DATE
	SellerInfo			VARCHAR
}

INSERT INTO database (Name, ID, BarcodeID, DateOfProduction, Quantity, OrderQuantity, Price, ExpirationDate, SellerInfo)
VALUES ('Apple', '00001-A1', 1231658478, 2017-09-21, 200, 150, 2.00, 10302017, 'California farm');

INSERT INTO database (Name, ID, BarcodeID, DateOfProduction, Quantity, OrderQuantity, Price, ExpirationDate, SellerInfo)
VALUES('Pencil', '00002-P8', 4567897643, 2016-02-20, 400, 30, 1.00, null, 'BIG');

INSERT INTO database (Name, ID, BarcodeID, DateOfProduction, Quantity, OrderQuantity, Price, ExpirationDate, SellerInfo)
VALUES ('Broom', '04134-B3', 7894566548, 2016-05-02, 50, 5, 15.00, null, 'Boston broom');

INSERT INTO database (Name, ID, BarcodeID, DateOfProduction, Quantity, OrderQuantity, Price, ExpirationDate, SellerInfo)
VALUES ('Note', '13041-N3', 8521479630, 2015-02-25, 200, 10, 12.00, null, 'Five Star');

INSERT INTO database (Name, ID, BarcodeID, DateOfProduction, Quantity, OrderQuantity, Price, ExpirationDate, SellerInfo)
VALUES ('PorkBelly', '10403-P2', 984265130, 2017-09-22, 60, 20, 15.00, 10012017, 'Sweethome Alabama');

SELECT *
FROM database
WHERE Quantity > 0; 


CREATE TABLE OrderDatabase {
	Name				VARCHAR
	ID					VARCHAR
	BarcodeID			INT
	DateOfProduction	DATE
	Quantity			INT
	OrderQuantity		INT
	SellerInfo			VARCHAR
	OrderID				INT
}

INSERT INTO OrderDatabase (Name, ID, BarcodeID, DateOfProduction, Quantity, OrderQuantity, SellerInfo, OrderID)
VALUES ('Apple', '00001-A1', 1231658478, 2017-09-21, 200, 150, 'California farm', 010);

INSERT INTO OrderDatabase (Name, ID, BarcodeID, DateOfProduction, Quantity, OrderQuantity, SellerInfo, OrderID)
VALUES('Pencil', '00002-P8', 4567897643, 2016-02-20, 400, 30, 'BIG', 028);

INSERT INTO OrderDatabase (Name, ID, BarcodeID, DateOfProduction, Quantity, OrderQuantity, SellerInfo, OrderID)
VALUES ('Broom', '04134-B3', 7894566548, 2016-05-02, 50, 5, 'Boston broom', 098);
INSERT INTO OrderDatabase (Name, ID, BarcodeID, DateOfProduction, Quantity, OrderQuantity, SellerInfo, OrderID)
VALUES ('Note', '13041-N3', 8521479630, 2015-02-25, 200, 10, 'Five Star', 001);

INSERT INTO OrderDatabase (Name, ID, BarcodeID, DateOfProduction, Quantity, OrderQuantity, SellerInfo, OrderID)
VALUES ('PorkBelly', '10403-P2', 984265130, 2017-09-22, 60, 20, 'Sweethome Alabama', 077);

SELECT *
FROM OrderDatabase
WHERE OrderID <> null;