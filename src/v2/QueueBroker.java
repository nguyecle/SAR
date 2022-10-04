package v2;

/**
 * QueueBroker allows us to create MessageQueue objects, with a name and a port.
 * These objects will be a communication canal between two tasks.
 * 
 * @author cl__nguyen
 *
 */
abstract class QueueBroker {

	QueueBroker(String name) {
	}

	/*
	 * Accept allows to accept a connection on a specific port. This is a blocking
	 * method.
	 * 
	 * @param port
	 * 
	 * @return MessageQueue
	 */
	abstract MessageQueue accept(int port);

	/*
	 * Connect allows to ask for a connection on a specific port and name. This is a
	 * blocking method.
	 * 
	 * @param name, this is the name of a QueueBroker
	 * 
	 * @param port, this is the specific sport
	 * 
	 * @return MessageQueue
	 */
	abstract MessageQueue connect(String name, int port);
}