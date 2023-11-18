# Project Implementation

### Reference Documentation

19-10-2023

1. Created the Model
2. Created the DTOs
3. Created the Controller
   -  Implemented Field Injection
4. Created the Service Layer
   - Implemented RestTemplate
   - Calling 3rd party [FakeStoreAPI] 
   - Return the Response to Controller

23-10-2023

1. Constructor Injection
2. Calling 3rd party API using 4 CRUD APIs 
   - getProduct(id)
   - getAllProducts()
   - createProduct()
   - deleteProduct(id)
3. Service Layer Implementation
4. Controller Advice

26-10-2023

1. Implemented FakeStoreAPIClient Class
2. Implemented Global Exception Handler
3. Implemented Mapper
4. Updated the FakeStoreProductServiceImpl
5. Did the Fake Store Configuration - Added the URLs in Application.properties

03-11-2023

1. Data Insertion – The Initialization Service runs through the Command Line and Inserts Data in the Table
2. New Entities - Price