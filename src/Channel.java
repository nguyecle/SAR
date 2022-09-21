
/**
 * Channel allows tasks to communicate between each other and share data Broker
 * creates channel
 * 
 * @author cl__nguyen
 * 
 * 
 */
public abstract class Channel {
	/**
	 * This method allows a task to read the content of a buffer. Exception when
	 * there is an error. This is a blocking method, when it tries to read when
	 * there is nothing to read. The method waits for the sending of additional
	 * bytes.
	 * 
	 * @param bytes  : buffer of byte which will be read
	 * @param offset : beginning index
	 * @param length : length of the buffer
	 * @return int indicating how many bytes have been reading sucessfully
	 * @throws IOException if channel is disconnected during the method
	 */
	public abstract int read(byte[] bytes, int offset, int length);

	/**
	 * This method allows a task to write a content in a buffer.
	 * 
	 * @param bytes  : buffer of byte which will be write
	 * @param offset : beginning indexs
	 * @param length : length of the buffer
	 * @return int indicating how many bytes have been writing sucessfully
	 * @throws IOException if channel is disconnected during the method
	 */
	public abstract int write(byte[] bytes, int offset, int length);

	/**
	 * This method allows a channel to disconnect
	 */

	public abstract void disconnect();

	/**
	 * This method indicates if the channel is disconnected
	 * 
	 * @return
	 */
	public abstract boolean disconnected();
}
