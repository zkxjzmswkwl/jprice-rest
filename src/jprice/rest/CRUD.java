package jprice.rest;

public interface CRUD {
	
	Response create(String _requestData);
	
	Response list();
	
	Response retrieve(int _pk);
	
	Response update(int _pk, String _requestData);
	
	Response destroy(int _pk);

}
