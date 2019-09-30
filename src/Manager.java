import java.awt.EventQueue;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Manager {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PictHub window = new PictHub();
					window.login(window);
					Registry registry = LocateRegistry.getRegistry();
		            registry.bind("SharedCanvas", window);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}