package v2;

import java.util.HashMap;

public class QueueBrokerImpl extends QueueBroker {

	private HashMap<Integer, MessageQueueImpl> map;
	private Broker broker;

	QueueBrokerImpl(String name) {
		super(name);
		this.map = new HashMap<>();
		this.broker = new Broker(name);
	}

	@Override
	MessageQueue accept(int port) {
		try {
			Channel channel = this.broker.accept(port);
			MessageQueueImpl message = new MessageQueueImpl(channel);
			map.put(port, message);
			return message;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	MessageQueue connect(String name, int port) {
		try {
			Channel channel = this.broker.connect(name, port);
			MessageQueueImpl message = new MessageQueueImpl(channel);
			map.put(port, message);
			return message;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}

}
