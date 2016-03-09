import jprice.rest.APIConnection;
import jprice.rest.ModelService;

public class RandomUserService extends ModelService {

	public RandomUserService(APIConnection _api) {
		super(_api);
	}

	@Override
	public String getEndpoint() {
		return "/api/";
	}

}
