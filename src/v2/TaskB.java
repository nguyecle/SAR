package v2;

public class TaskB extends Task {

	Broker broker;
	String name2;

	public TaskB(String name, String name2) {
		this.name2 = name2;
		this.broker = new Broker(name);
	}

	public void run() {
		Channel channel;
		try {
			channel = broker.connect(name2, 5555);

			byte[] bytes = new byte[1024];

			int length = channel.read(bytes, 0, 5);

			System.out.print("Message received : ");
			for (int i = 0; i < length; i++) {
				System.out.print((char) bytes[i]);
			}
			channel.disconnect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
