/**
 * Broker established the connection between two task, and creates the channels
 * 
 * @author cl__nguyen
 *
 */
public abstract class Broker {

	String m_name;

	/**
	 * This method give a name to a broker
	 * 
	 * @param name
	 */
	protected Broker(String name) {
		m_name = name;
	}

	/**
	 * This method accept the connection, accept is a blocking method
	 * 
	 * @param port port number of the connection
	 * @return the channel allowing communication between the two brokers
	 */
	abstract Channel accept(int port);

	/**
	 * This method established the connection to another brokers, this method is
	 * blocking
	 * 
	 * @param name, name of the broker you want to connect with
	 * @param port, port number of the broker
	 * @return the channel
	 */
	abstract Channel connect(String name, int port);

}
