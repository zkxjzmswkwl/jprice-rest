import jprice.rest.APIConnection;

public class Example {

	/**
	 * I'm going to be hitting a localhost django database that I'm running
	 */
	private static String apiBase = "http://127.0.0.1:8000";
	private static String params = "?format=json";
	
	private static void printPerson(Person p) {
		System.out.printf("First Name: %s\n", p.getFirstName());
		System.out.printf("Last Name: %s\n", p.getLastName());
		System.out.printf("Age: %s\n", p.getAge());
		switch(p.getGender()) {
		case "M":
			System.out.println("Gender: Male");
			break;
		case "F":
			System.out.println("Gender: Female");
			break;
		}
	}

	public static void main(String[] args) {
		APIConnection api = new APIConnection(apiBase, params, 5000);
		PersonService people = new PersonService(api);
		Person jprice = new Person(people.retrieve(15));
		printPerson(jprice);
	}

}
