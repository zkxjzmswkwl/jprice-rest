package jprice.rest;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public interface Request {

	APIConnection getAPIConnection();
	
	HttpURLConnection getHttpConnection();
	
	String getRequestURL();

	String getRequestMethod();

	String getContentType();
	
	String getRequestData();
	
	public static void writeBytes(HttpURLConnection connection, String data) {
		try {
			OutputStream os = connection.getOutputStream();
			os.write(data.getBytes());
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
