/**
 * 
 * @author cl__nguyen
 *
 */
abstract class Channel {
	/**
	 * 
	 * @param bytes
	 * @param offset
	 * @param length
	 * @return
	 */
	abstract int read(byte[] bytes, int offset, int length);

	/**
	 * 
	 * @param bytes
	 * @param offset
	 * @param length
	 * @return
	 */
	abstract int write(byte[] bytes, int offset, int length);

	/**
	 * 
	 */

	abstract void disconnect();

	/**
	 * 
	 * @return
	 */
	abstract boolean disconnected();
}
