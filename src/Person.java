
import jprice.rest.DataModel;
import jprice.rest.Response;

@SuppressWarnings({ "serial", "unchecked" })
public class Person extends DataModel {

	public Person(Response _res) {
		super(_res);
	}

	public long getId() {
		return (long) get("id");
	}

	public void setId(long _id) {
		replace("id", _id);
	}

	public String getFirstName() {
		return (String) get("first_name");
	}

	public void setFirstName(String _first) {
		replace("first_name", _first);
	}

	public String getLastName() {
		return (String) get("last_name");
	}

	public void setLastName(String _last) {
		replace("last_name", _last);
	}

	public long getAge() {
		return (long) get("age");
	}

	public void setAge(long _age) {
		replace("age", _age);
	}

	public String getGender() {
		return (String) get("gender");
	}

	public void setGender(String _gender) {
		replace("gender", _gender);
	}

	public String toString() {
		return serialize();
	}

}
