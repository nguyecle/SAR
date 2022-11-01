package v3;

/**
 * Broker established the connection between two task, and creates the channels
 * 
 * @author cl__nguyen
 *
 */
public abstract class Broker {

	/**
	 * This method give a name to a broker
	 * 
	 * @param name
	 */
	Broker(String name) {
	}

	/**
	 * This method accept the connection, accept is a blocking method
	 * 
	 * @param port port number of the connection
	 * @return the channel allowing communication between the two brokers
	 * @throws InterruptedException
	 */
	abstract Channel accept(int port) throws InterruptedException;

	/**
	 * This method established the connection to another brokers, this method is
	 * blocking
	 * 
	 * @param name, name of the broker you want to connect with
	 * @param port, port number of the broker
	 * @return the channel
	 * @throws InterruptedException
	 */
	abstract Channel connect(String name, int port) throws InterruptedException;
}
