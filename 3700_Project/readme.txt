Sample data used:

ID		Name			Quantity		Price		ExpirationDate		SellerInfo

1		Apple			200				2.00		20171001			California Farm
2		Pencil			400				1.00		00000000			BIG
3		Broom			50				15.00		00000000			Boston Broom
4		Note			200				12.00		00000000			Five Star
5		Pork Belly		60				15.00		20171021			Sweethome Alabama


Scenario 1: purchase a product

select transaction
select look up item and type the id number of the product you wish to buy
Do this for how many times you wish to buy specific products
Select Finish and Pay
Select a payment method: Cash or Card
You will see the products you are going to purchase
Select pay

If you selected Cash button, it will ask for the amount of cash you will insert and then print the receipt.
If you selected the Card button, it will automatically show you the receipt when pay button is pressed.

Scenario 2: add a product

In the main menu, select Manage Products
Then, fill in the fields that show on the screen.
(Note that the expiration field should always be filled with number. If no corressponding
number exists, enter 0).
After you filled out all of the fields, click on Add product.
Select clear to empty the fields.
Select load and enter the newly added product's id to check it has been added.

Scenario 3: Edit a product information

In the main menu, select Manage Products
Then select Load product.
It will ask for the ID number of the product you wish to edit.
Then, the information for the product will be loaded onto the screen.
Then, simply edit the information for the particular product (except the ID number because it has to be unique for each product) and select Update product.
