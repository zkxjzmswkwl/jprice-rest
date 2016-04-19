package jprice.rest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

/**
 * A small wrapper for making quick requests to a REST database.
 * 
 * Usage:
 * 
 * cd jprice-rest/bin/
 * JPRest <method> <url> $<data>
 * 
 * Accepted Methods:
 * 	GET
 * 	POST $ - data required
 * 	PUT $ - data required
 * 	DELETE
 * 
 * Returns a response such as:
 * 		GET http://127.0.0.1:8000/api/posts/ 200
 * 		application/json
 * 		// JSON OBJECT
 * 
 * @author Jordan Price/shakedown-street
 */
public class JPRest {

	public static void main(String[] args) {
		String method = args[0].toUpperCase();
		URL url = null;
		try {
			url = new URL(args[1]);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		Response res = null;

		switch (method) {
		case "GET":
			res = new Request(url) {

				@Override
				public String getContentType() {
					return "application/json";
				}

				@Override
				public HashMap<String, String> customRequestHeaders() {
					return null;
				}
			}.get();
			break;
		case "POST":
			res = new Request(url) {

				@Override
				public String getContentType() {
					return "application/json";
				}

				@Override
				public HashMap<String, String> customRequestHeaders() {
					return null;
				}
			}.post(args[2]);
			break;
		case "PUT":
			res = new Request(url) {
				@Override
				public String getContentType() {
					return "application/json";
				}

				@Override
				public HashMap<String, String> customRequestHeaders() {
					return null;
				}
			}.put(args[2]);
			break;
		case "DELETE":
			res = new Request(url) {
				@Override
				public String getContentType() {
					return "application/json";
				}

				@Override
				public HashMap<String, String> customRequestHeaders() {
					// TODO Auto-generated method stub
					return null;
				}
			}.delete();
			break;
		default:
			res = null;
			System.out.println("Operation: '" + method + "' not supported");
			break;
		}
		if (res == null) {
			return;
		}
		System.out.println(res.getRequestMethod() + " " + url + " " + res.getResponseCode());
		System.out.println(res.getContentType());
		System.out.println(res.toJSONString());
	}

}
