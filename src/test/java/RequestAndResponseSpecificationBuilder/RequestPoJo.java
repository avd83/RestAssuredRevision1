package RequestAndResponseSpecificationBuilder;

public class RequestPoJo {
	
	String id;
	String location;
	String name;
	String phneno;
	String[] courses;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhneno() {
		return phneno;
	}
	public void setPhneno(String phneno) {
		this.phneno = phneno;
	}
	public String[] getCourses() {
		return courses;
	}
	public void setCourses(String[] courses) {
		this.courses = courses;
	}
}
