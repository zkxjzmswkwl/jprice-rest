

import jprice.rest.APIConnection;
import jprice.rest.ModelService;

public class PersonService extends ModelService {

	public PersonService(APIConnection _api) {
		super(_api);
	}

	@Override
	public String getEndpoint() {
		return "/people/";
	}

}
