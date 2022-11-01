package v3;

public class Test {

	public static void main(String[] args) {
				
		Manager manager = new Manager();
		TaskB server = new TaskB("server", 8080, manager);
		TaskA client = new TaskA("client", "server", 8080, manager);
		
		client.start();
		server.start();
		
		try {
			client.join();
			server.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
