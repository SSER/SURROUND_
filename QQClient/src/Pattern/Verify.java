package Pattern;
import Common.*;
public class Verify {
	public boolean checkUser(User u) {
		return new Connection().login(u);
	}
}
