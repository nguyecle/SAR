package v1;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Broker established the connection between two task, and creates the channels
 * 
 * @author cl__nguyen
 *
 */
public class Broker {

	private String name;
	private List<Integer> waitingList;
	private HashMap<Integer, Channel> openList;

	/**
	 * This method give a name to a broker
	 * 
	 * @param name
	 */
	public Broker(String name) {
		this.name = name;
		this.waitingList = new ArrayList<Integer>();
		this.openList = new HashMap<Integer, Channel>();
		Test.BrokerList.put(name, this);
	}

	/**
	 * This method accept the connection, accept is a blocking method
	 * 
	 * @param port port number of the connection
	 * @return the channel allowing communication between the two brokers
	 * @throws InterruptedException
	 */
	public synchronized Channel accept(int port) throws InterruptedException {
		if (this.openList.containsKey(port) || this.waitingList.contains(port)) {
			throw new InterruptedException("Port already used");
		}

		this.waitingList.add(port);

		while (waitingList.contains(port)) {
			wait();
		}

		Channel channel = this.openList.get(port);
		synchronized (channel) {
			channel.notifyAll();
		}

		System.out.println(this.name + " accepted connection on port " + port);

		return channel;
	}

	/**
	 * This method established the connection to another brokers, this method is
	 * blocking
	 * 
	 * @param name, name of the broker you want to connect with
	 * @param port, port number of the broker
	 * @return the channel
	 * @throws InterruptedException
	 */
	public synchronized Channel connect(String name, int port) throws InterruptedException {
		while (!Test.BrokerList.containsKey(name)) {
			Thread.sleep(200);
		}

		Broker broker = Test.BrokerList.get(name);

		Channel channel1 = new Channel();
		Channel channel2 = new Channel();
		channel1.channelConnectedTo(channel2);
		channel2.channelConnectedTo(channel1);

		if (broker.waitingList.contains(port)) {
			broker.openConnection(port, channel2);
		}

		synchronized (broker) {
			broker.notifyAll();
		}

		while (broker.waitingList.contains(port)) {
			synchronized (channel2) {
				channel2.wait();
			}
		}

		this.openList.put(port, channel1);

		System.out.println(this.name + " connected on port " + port);

		return channel1;
	}

	public void openConnection(int port, Channel external) {
		this.openList.put(port, external);
		this.waitingList.remove(this.waitingList.indexOf(port));
	}

}
