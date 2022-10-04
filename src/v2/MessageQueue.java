package v2;

/*
 * This class allows to established a communication between two tasks.
 * We send message between two tasks by packets.
 * 
 */
abstract class MessageQueue {

	MessageQueue(Channel channel) {

	}

	/*
	 * Send allows to send a message on a canal. This is a blocking method.
	 * 
	 * @param bytes
	 * 
	 * @param offset
	 * 
	 * @param length
	 */
	abstract void send(byte[] bytes, int offset, int length);

	/*
	 * Receive allows to get the sending message. This is a blocking method.
	 * 
	 * @return byte, the message
	 * 
	 */
	abstract byte[] receive();

	/*
	 * This method close the communication
	 */
	abstract void close();

	/*
	 * This method tell us if the communication is closed
	 * 
	 * @return boolean, true or false
	 */
	abstract boolean closed();
}
