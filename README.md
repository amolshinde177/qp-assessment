This project is about Grocery Booking API:

Roles:
- Admin
- User

API endpoints
1. Admin Responsibilities:
   a. Add new grocery items to the system
   - api endpoint: POST localhost:9090/api/admin/grocery
   - api request:
     {
    "name": "Rice",
    "description": "pure basamti Rice",
    "price": 60,
    "inventory": 500
     }
	 
   - api response:
     {
    "id": 1,
    "name": "Rice",
    "description": "pure basamti Rice",
    "price": 60.0,
    "inventory": 500
    }
	
   b. View existing grocery items
         - api endpoint: GET localhost:9090/api/admin/grocery (To get all the items) (api response will be similar to Add new grocery items api response)
                         GET localhost:9090/api/admin/grocery?itemId=1 (api response will be similar to Add new grocery items api response)
                         GET localhost:9090/api/admin/grocery?name=Rice (api response will be similar to Add new grocery items api response)
            
   c. Remove grocery items from the system
        - api endpoint: DELETE localhost:9090/api/admin/grocery (To delete all the items)
		        DELETE localhost:9090/api/admin/grocery/{itemId}  (To delete specific item by itemId)
                        e.g. DELETE localhost:9090/api/admin/grocery/1
		
   d. Update details (e.g., name, price) of existing grocery items
         - api endpoint: PUT localhost:9090/api/admin/grocery/{item_id}
                         e.g. PUT localhost:9090/api/admin/grocery/103 (api response will be similar to Add new grocery items api response)
   
   e. Manage inventory levels of grocery items 
      - api endpoint: localhost:9090/api/admin/grocery/inventory
	    - api request:
	    {
            "itemId": "103",
             "inventory": 150
             }
	  
	  - api response:
	    {
             "itemId": 103,
             "newInventory": 150,
             "responseMessage": "Inventory updated successfully."
             }
     
2. User Responsibilities:
   a. View the list of available grocery items
      - api endpoint: GET localhost:9090/api/user/grocery (To get all the items) (api response will be similar to Add new grocery items api response)
                      GET localhost:9090/api/user/grocery?itemId=1 (api response will be similar to Add new grocery items api response)
                      GET localhost:9090/api/user/grocery?name=Rice (api response will be similar to Add new grocery items api response) 
      
   b. To book multiple grocery items in a single order
      - api endpoint: POST localhost:9090/api/user/grocery/order
	  - api request:
	  {
        "userId": 1,
        "bookedItemDetails": 
	    [
         {
          "itemId": 102,
          "quantity": 5
         },
         {
         "itemId": 103,
         "quantity": 2
         }
        ]
      }  
	   
Advanced Thing:
- We containerized the application using Docker for ease of deployment and scaling.
- Please generate .jar file for project by running project as Maven install
Database:
- Postgres.
