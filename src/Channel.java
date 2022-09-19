
/**
 * Channel allows tasks to communicate between each other and share data
 * 
 * @author cl__nguyen
 * 
 * 
 */
public class Channel {
	/**
	 * This method allows a task to read the content of a buffer
	 * 
	 * @param bytes  : buffer of byte
	 * @param offset : beginning index
	 * @param length : length of the buffer
	 * @return int indicating how many bytes have been reading sucessfully
	 */
	public int read(byte[] bytes, int offset, int length) {
		return 0;
	}

	/**
	 * This method allows a task to write a content in a buffer
	 * 
	 * @param bytes  : buffer of byte
	 * @param offset : beginning index
	 * @param length : length of the buffer
	 * @return int indicating how many bytes have been writing sucessfully
	 */
	public int write(byte[] bytes, int offset, int length) {
		return 0;
	}

	/**
	 * This method allows a channel to disconnect
	 */

	public void disconnect() {
	}

	/**
	 * This method indicates if the channel is disconnected
	 * 
	 * @return
	 */
	public boolean disconnected() {
		return false;
	}
}
