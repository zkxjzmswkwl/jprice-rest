package jprice.rest;

public interface I_CRUD {

	Response create(String _requestData);

	Response list();

	Response retrieve();

	Response retrieve(int _id);

	Response update(int _id, String _requestData);

	Response destroy(int _id);

}
