
public class TaskA extends Task {

	Broker m_broker;
	Channel m_channel;

	public TaskA(Broker broker) {
		m_broker = broker;

	}

	@Override
	public void run() {
		m_channel = m_broker.accept(5555);
		byte[] bytes = new byte[1024];
		m_channel.read(bytes, 0, bytes.length);
		m_channel.disconnect();
	}

}
