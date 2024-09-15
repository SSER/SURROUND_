package Common;
import java.io.Serializable;
public class User implements Serializable {
	
	private String id;
	private String key;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
}
