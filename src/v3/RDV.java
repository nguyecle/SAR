package v3;

public class RDV {

	BrokerImpl accept;
	BrokerImpl connect;
	ChannelImpl channel;

	public RDV(BrokerImpl accept) {
		this.accept = accept;
	}

	/*
	 * This method allows to create a channel and accept for the broker
	 */
	public synchronized ChannelImpl accept(int port) throws InterruptedException {
		while (connect == null) {
			wait();
		}

		while (channel == null) {
			wait();
		}

		return channel;
	}

	/*
	 * Create two channel interconnected
	 * This is synchronized because we dont want that 2 brokers interact together with connect and accept functions
	 */
	public synchronized ChannelImpl connect(int port) throws InterruptedException {
		this.channel = new ChannelImpl();
		ChannelImpl channelconnect = new ChannelImpl();

		this.channel.connectTo(channelconnect);
		channelconnect.connectTo(channel);

		notifyAll();

		return channelconnect;

	}

	/*
	 * Set another broker to the RDV
	 */
	public void setBrokerConnect(BrokerImpl b) {
		this.connect = b;
	}
}
