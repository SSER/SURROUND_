package Pattern;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Common.*;

public class GetOnline {
	public boolean getOnlineUser(User u) {
		return new Connection().login(u);
	}
}
