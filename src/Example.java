import jprice.rest.APIConnection;
import jprice.rest.Response;
import models.Animal;

public class Example {

	/**
	 * I'm going to be hitting a localhost django database that I'm running
	 */
	private static String apiBase = "http://127.0.0.1:8000";
	private static String params = "?format=json";

	public static void main(String[] args) {

		/**
		 * Create a new API connection to be able to communicate with the
		 * database In this case we'll be hitting the /animals/ endpoint on the
		 * api
		 */
		APIConnection connection = new APIConnection(apiBase, "/animals/", params, 5000);

		/**
		 * Create a java model which can be serialized to json
		 */
		Animal dog = new Animal(10, "Dog", 1, "M");
		System.out.println(dog.serialize());
		// output > {"id":10,"name":"Dog","type":1,"gender":"M","owner":null}

		/**
		 * List all the animals in the database
		 */
		Response animals = connection.list();
		System.out.printf("%s - %s", "" + animals.getResponseCode(), animals.getContent());
		// output > 200 -
		// ...
		// ...
		// {"id":1,"name":"Cat","type":2,"gender":"M","owner":null},
		// {"id":2,"name":"Horse","type":3,"gender":"F","owner":null},
		// {"id":3,"name":"Horse","type":3,"gender":"F","owner":null}
		// ...
		// ...

		/**
		 * Get a single animal off the database
		 */
		Response firstAnimal = connection.retrieve(1);
		System.out.printf("%s - %s", "" + firstAnimal.getResponseCode(), firstAnimal.getContent());
		// output > 200 -
		// {"id":1,"name":"Cat","type":2,"gender":"M","owner":null}

		/**
		 * Create an object and push it to the database and return a response
		 */
		Response horse = connection.create(new Animal(2, "Horse", 3, "F").serialize());
		System.out.printf("%s - %s", "" + horse.getResponseCode(), horse.getContent());
		// output > 201 -
		// {"id":3,"name":"Horse","type":3,"gender":"F","owner":null}
	}

}
