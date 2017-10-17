# checkoutRestService Documentation

showBasket()

	Returns json data about a basket.

	URL /checkout/basket

	Method: GET

	URL Params Required: none

	Success Response: Code: 200 
	Sample Content: 
	
	
	[{"orderId":1,"quantity":3,"cost":70,"items":{"id":1,"name":"A","price":40,"specialPrice":70,
	"qtyToDiscount":3}}]	
========================================================

addItemToBasket()

	Returns json data about a basket.

	URL /checkout/basket/add/name/{name}/{qty}

	Method: GET

	URL Params Required: name=[String], qty=[integer]

	Success Response: Code: 200 
	Sample Content: 

	[{"orderId":1,"quantity":3,"cost":70,"items":{"id":1,"name":"A","price":40,"specialPrice":70,
	"qtyToDiscount":3}}]

	Error Response:
	Code: 404 NOT FOUND 
	Content: "Unable to add item with name: " + name + " not found."
========================================================

addItemToBasket()

	Returns json data about a basket.

	URL /checkout/basket/add/id/{id}/{qty}

	Method: GET

	URL Params Required: id=[long], qty=[integer]

	Success Response: Code: 200 
	Sample Content: 	
	
	[{"orderId":1,"quantity":3,"cost":70,"items":{"id":1,"name":"A","price":40,"specialPrice":70,
	"qtyToDiscount":3}}]

	Error Response:
	Code: 404 NOT FOUND 
	Content: "Unable to add item with id: " + id + " not found."
========================================================

removeItemFromBasket()

	Returns json data about a basket.
	
	URL /checkout/basket/remove/name/{name}
	
	Method: DELETE
	
	URL Params Required: name=[String]
	
	Success Response: Code: 200 
	Sample Content: 
	
	[{"orderId":1,"quantity":3,"cost":70,"items":{"id":1,"name":"A","price":40,"specialPrice":70,
	"qtyToDiscount":3}}]
	or: []
	
	Error Response:
	Code: 404 NOT FOUND 
	Content: "Unable to remove item with name: " + name + " not found."	
========================================================

removeItemFromBasket()

	Returns json data about a basket.
	
	URL /checkout/basket/remove/id/{id}
	
	Method: DELETE
	
	URL Params Required: id=[long]
	
	Success Response: Code: 200 
	Sample Content: 
	
	[{"orderId":1,"quantity":3,"cost":70,"items":{"id":1,"name":"A","price":40,"specialPrice":70,
	"qtyToDiscount":3}}]
	or: []
	
	Error Response:
	Code: 404 NOT FOUND 
	Content: "Unable to remove item with id: " + id + " not found."		
========================================================

modifyQtyItemFromBasket()

	Returns json data about a basket.
	
	URL /checkout/basket/change/name/{name}/{qty}
	Method: PUT
	
	URL Params Required: name=[String], qty=[integer]
	
	Success Response: Code: 200 
	Sample Content: 	
	
	[{"orderId":1,"quantity":3,"cost":70,"items":{"id":1,"name":"A","price":40,"specialPrice":70,
	"qtyToDiscount":3}}]
	or: []
	
	Error Response:
	Code: 404 NOT FOUND 
	Content: "Unable to modify item with name: " + name + " not found."	
========================================================

modifyQtyItemFromBasket()

	Returns json data about a basket.
	
	URL /checkout/basket/change/id/{id}/{qty}
	Method: PUT
	
	URL Params Required: id=[long], qty=[integer]
	
	Success Response: Code: 200 
	Sample Content: 	
	
	[{"orderId":1,"quantity":3,"cost":70,"items":{"id":1,"name":"A","price":40,"specialPrice":70,
	"qtyToDiscount":3}}]
	or: []
	
	Error Response:
	Code: 404 NOT FOUND 
	Content: "Unable to modify item with id: " + id + " not found."	
========================================================

removeAllItemsFromBasket()

	Returns json data about a basket.
	
	URL /checkout/basket/remove/all
	Method: DELETE
	
	URL Params Required: none
	
	Success Response: Code: 200 
	Sample Content: 
	
	[]
========================================================

totalCostFromBasket()

	Returns json data about basket total cost.
	
	URL /checkout/basket/total
	Method: GET
	
	URL Params Required: none
	
	Success Response: Code: 200 
	Sample Content: 
	
	{515}
========================================================

totalCostFromBasket()

	Returns String about a basket.
	
	URL /checkout/basket/summary
	Method: GET
	
	URL Params Required: none
	
	Success Response: Code: 200 
	Sample Content: 
	
	Item name: A Price: 40 quantity: 10 Cost: 250
	Item name: B Price: 10 quantity: 15 Cost: 115
	Total Cost: 365
========================================================					
		


			
			
