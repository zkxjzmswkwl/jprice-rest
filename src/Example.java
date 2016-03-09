import jprice.rest.APIConnection;
import jprice.rest.DataModel;

public class Example {

	/**
	 * I'm going to be hitting a localhost django database that I'm running
	 */
	private static String apiBase = "http://127.0.0.1:8000";
	private static String params = "?format=json";

	public static void main(String[] args) {
		APIConnection api = new APIConnection("http://127.0.0.1:8000", "?format=json", 5000);
		PersonService people = new PersonService(api);

		APIConnection randomUserAPI = new APIConnection("https://randomuser.me/", "", 5000);
		RandomUserService users = new RandomUserService(randomUserAPI);

		DataModel user = new DataModel(users.retrieve());
		user.listFields();
	}

}
