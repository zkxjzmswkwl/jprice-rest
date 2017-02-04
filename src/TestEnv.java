import org.json.simple.JSONObject;

import jprice.rest.Http;
import jprice.rest.Response;

public class TestEnv {

	private static final String DATABASE_URL = "http://127.0.0.1:8000/api/";
	private Http http = new Http();

	public TestEnv() {
		String params = "";
		Response res1 = http
				.get(DATABASE_URL + "subcategories/" + params);
		System.out.println(res1.getURL());
		System.out.println(res1.getRequestMethod());
		System.out.println(res1.getResponseCode());
		System.out.println(res1.getResponseMessage());
		System.out.println(res1.getContent());
		
		JSONObject obj = new JSONObject();
		obj.put("name", "REST Test");
		obj.put("description", "[b]This was added through a REST API[/b]");
		obj.put("order", 3);
		obj.put("admin_req", false);
		obj.put("auth_req", false);
		obj.put("parent", "c3399572-d681-4f41-ad63-2a44977efbcd");
		
		Response res2 = http.post(DATABASE_URL + "subcategories/", obj.toJSONString());
		System.out.println(res2.getURL());
		System.out.println(res2.getRequestMethod());
		System.out.println(res2.getResponseCode());
		System.out.println(res2.getResponseMessage());
		System.out.println(res2.getContent());
	}

	public static void main(String[] args) {
		new TestEnv();
	}

}
