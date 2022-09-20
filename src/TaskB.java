
public class TaskB extends Task {

	Broker m_broker;
	Channel m_channel;

	public TaskB(Broker broker) {
		m_broker = broker;

	}

	@Override
	public void run() {
		m_channel = m_broker.connect("Test", 5555);
		byte[] bytes = new byte[1024];
		String s = new String("Hello World");
		bytes = s.getBytes();
		m_channel.write(bytes, 0, bytes.length);
		m_channel.disconnect();
	}

}
