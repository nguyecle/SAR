package v2;

public class TaskA extends Task {

	Broker broker;
	MessageQueueImpl msgQueue;
	QueueBrokerImpl brokerQueue;
	Channel channel;

	public TaskA(String name) {
		this.broker = new Broker(name);
		this.msgQueue = new MessageQueueImpl(channel);
		this.brokerQueue = new QueueBrokerImpl(name);
	}

	public void run() {
		try {
			msgQueue = (MessageQueueImpl) brokerQueue.accept(5555);
			// channel = broker.accept(5555);
			String s = "Hello world";
			// byte[] content = s.getBytes();
			msgQueue.send(s.getBytes(), 0, s.length());
			// channel.write(content, 0, content.length);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
