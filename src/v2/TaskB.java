package v2;

public class TaskB extends Task {

	Broker broker;
	QueueBrokerImpl brokerQueue;
	MessageQueueImpl msgQueue;
	String m_name;
	Channel channel;

	public TaskB(String name, String name2) {
		broker = new Broker(name);
		m_name = name2;
		this.msgQueue = new MessageQueueImpl(channel);
		this.brokerQueue = new QueueBrokerImpl(name);
	}

	public void run() {
		try {
			msgQueue = (MessageQueueImpl) brokerQueue.connect(m_name, 5555);
			// channel = broker.connect(m_name, 5555);

			byte[] tabTest = new byte[64];
			tabTest = msgQueue.receive();

			// int length = channel.read(tab, 0, 6);

			for (int i = 0; i < tabTest.length; i++) {
				System.out.print(tabTest[i]);
			}
			// channel.disconnect();
			msgQueue.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
