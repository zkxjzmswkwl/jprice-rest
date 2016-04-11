import java.net.MalformedURLException;
import java.net.URL;

import jprice.rest.Request;
import jprice.rest.Response;

/**
 * An example of using the API programmatically, as opposed to using the
 * wrapper.
 * 
 * @author Jordan Price/shakedown-street
 */
public class APIUsageExample {

	/**
	 * I'm going to be hitting a localhost django database that I'm running
	 */
	private static String apiBase = "http://127.0.0.1:8000/api/posts";
	private static String pk = "";
	private static String params = "?format=json";

	public static void main(String[] args) {
		URL url = null;
		try {
			url = new URL(apiBase + pk + "/" + params);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		if (url == null) {
			return;
		}
		Response res = new Request(url) {

			@Override
			public String getContentType() {
				return "application/json";
			}
		}.post("{\"title\":\"New Post\",\"body\":\"Post this to the database!\"}");
	}

}
