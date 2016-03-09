import java.net.MalformedURLException;
import java.net.URL;

import jprice.rest.JSONRequest;
import jprice.rest.Response;

public class Example {

	/**
	 * I'm going to be hitting a localhost django database that I'm running
	 */
	private static String apiBase = "http://127.0.0.1:8000/animations/";
	private static int pk = 3;
	private static String params = "?format=json";

	public static void main(String[] args) {
		try {
			URL url = new URL(apiBase + pk + "/" + params);
			
			Response res = new JSONRequest(url).get();
			System.out.println(res.asJSONString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

}
