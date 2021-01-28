Coverage: 56.3%
Inventory Management System Project

This application is a system created using the Java programming language and an embedded h2 plugin to connect with a MySQL database. A user would interact with the console to manage the inventory.

## Getting Started

Clone this repository as a local git repository. 

### Prerequisites

Git bash - to clone this repo
Java 11
Maven 

### Installing


## Running the tests

To run the JUnit tests in this Maven package, simply change the directory to ..\20DecSDET2-IMS-Starter and execute
    mvn test

### Unit Tests 

These tests confirm that each method returns the correct/expected value using assertions like assertEquals() statements. 

```
Example:
//Method in OrderDao
@Override
	public Order create(Order order) {
	    try (Connection connection = DatabaseUtilities.getInstance().getConnection();
                PreparedStatement statement = connection
                        .prepareStatement("INSERT INTO orders (fk_cid) VALUES (?);")){
            statement.setLong(1, order.getFkCid());
            statement.executeUpdate();
            return readLatest();
        } catch (Exception e) {
        	LOGGER.debug(e);
        	LOGGER.error(e.getMessage());
        }
        return null;
	}
//Test Method in OrderDaoTest
	 @Test
	    public void testCreate() {
		 final Order order=new Order(2L, 1L,0.0);
		 assertEquals(order,DAO.create(order));
	    }
```


### Integration Tests 
Explain what these tests test, why and how to run them

```
Give an example
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Deployment

In the target folder, there is a "ims-0.0.1-jar-with-dependencies.jar" file which is executable from your command line. 
Before doing anything, make sure you have the right version of Java by running in your command line:
    java -version

Open your command prompt and change your directory to the local git repository:
    cd ..\20DecSDET2-IMS-Starter\target
  
To execute the .jar file, run:
  java -jar ims-0.0.1-jar-with-dependencies.jar

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Chris Perrins** - *Initial work* - [christophperrins](https://github.com/christophperrins)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc
