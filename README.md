# checkoutRestService 
Market checkout component with readable API that calculates the total price of a number of items.

ver1.1
Added Hibernate as ORM, app is now connected to MySql.
Service layer has got now smaller interfaces.
There is new Model class (Basket).
Buisness logic is implemented to Hibernate.
Tests (unit and integration) are performed with h2 in-memory database.

ver 1.0
Service contains model Item class, RestController class, Service interface.
Also it has got unit tests of Service implementation, integration tests of Controller.
As a UAT i performed example implementation of service interface with ArrayList of four Item objects.

