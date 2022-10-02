package v1;
public class TaskA extends Task {

	Broker broker;

	public TaskA(String name) {
		this.broker = new Broker(name);
	}

	public void run() {
		Channel channel;
		try {
			channel = broker.accept(5555);
			String s = "hello";
			byte[] content = s.getBytes();
			channel.write(content, 0, content.length);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
