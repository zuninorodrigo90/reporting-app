# reporting-app
User Reporting application which allows user to save report configurations

## Running the app
### Pre requirements:
* Docker or MySQL
* IDE (recommended: IntelliJ IDEA)
* Web browser (recommended: Google Chrome)
* Postman (if you want to test requests)
* DB client (Workbench, DataGrip 2, etc)

## Starting app
To run this application you should run this command to start mysql:

```
docker-compose -f docker/docker-compose.yml up -d
```

If you don't want to use docker-compose, you can use your MySQL

After docker-compose is executed you should run `database-setup/schema.sql` in some client for DB like Workbench or DataGrip 2 to create schema, tables and insert some necessary data
 
You can check docker-compose configuration file in the docker folder
 
If you want to use your MySQL you should change user and password in application.yml file

Once container is up and `database-setup/schema.sql` is executed, run the project in your IDE (IntelliJ is recommended)

To see the front end open `web/index.html` with your browser (Google Chrome recommended)

## Stopping app

To stop container, run:

```
docker-compose -f docker/docker-compose.yml down
```
 
## Postman
The project has a postman folder which contains a json with postman request so you can open it in your postman to execute and test all requests

The requests are:
* Create report
* Update report
* List reports
* Get by id report
* List KPIs

