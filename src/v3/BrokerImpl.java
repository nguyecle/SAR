package v3;

import java.util.HashMap;

public class BrokerImpl extends Broker {

	private String name;
	private HashMap<Integer, ChannelImpl> openList;
	private Manager manager;

	BrokerImpl(String name, Manager manager) {
		super(name);
		this.name = name;
		this.openList = new HashMap<Integer, ChannelImpl>();
		this.manager = manager;
		this.manager.addBroker(name, this);
	}

	synchronized ChannelImpl accept(int port) throws InterruptedException {
		if (this.openList.containsKey(port)) { // port already used by another connection ?
			throw new InterruptedException("Port " + port + " already in use");
		}

		ChannelImpl channelaccept = manager.accept(this, port);

		this.openList.put(port, channelaccept); // add the connection on the broker

		System.out.println(this.name + " accepted connection on port " + port);

		return channelaccept;
	}

	synchronized ChannelImpl connect(String name, int port) throws InterruptedException {

		ChannelImpl channelconnect = manager.connect(this, name, port);

		this.openList.put(port, channelconnect); // add the connection on the broker

		System.out.println(this.name + " connected on port " + port);

		return channelconnect;
	}

}
