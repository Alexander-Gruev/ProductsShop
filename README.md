
# ProductsShop
This is a console Spring Data application, built using the Code First Approach, that I made for the SoftUni Java-DB - June 2021 course.

## Technologies
* Java, Spring Boot, Spring Data, Hibernate
* MySQL, JAXB, ModelMapper


## Structure
### Packages
* config
* constants
* models
* repositories
* services
* util

## Database tables
* users - id, age, first_name, last_name
* products - id, name, price, buyer_id, seller_id
* categories - id, name
* products_categories - product_id, category_id

## Functionality
* The application reads the data from .xml files from the "resources" folder, maps it to the corresponding
  DTOs using JAXB, validates them and then maps them to objects and saves them to the database.
* The application can execute four queries and can export the data to .xml files in the "out" folder.
### Query 1: Products in range:
Get all the products in a specified price range, which have no buyer. Order them by price
(from lowest to highest). Select only the product name, price, and the full name of the seller.
### Query 2: Successfully sold products:
Get all the users that have at least 1 item sold. Order them by first name, then by last name
Select the person's first and last name. For each of the products sold, select the product's
name, price and the buyer's first and last name.
### Query 3: Categories by products count:
Get all categories, order them by the number of products in each one(a product can be in many
categories). For each category select its name, the number of products, the average price of those
products and the total revenue(total price sum) of those products(regardless if they have a buyer
or not).
### Query 4: UsersAndProducts
Get all users that have at least 1 product sold. Order them by the number of products sold(from
highest to lowest), then by last name (ascending). Select their first name, last name and age,
and for each product - their name and price.

## Usage
* Enter your MySQL username and password in the application.properties file.
* In the console, enter exercise numbers, or "end" to stop the program.