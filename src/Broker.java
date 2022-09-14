/**
 * 
 * @author cl__nguyen
 *
 */
abstract class Broker {

	/**
	 * 
	 * @param name
	 */
	Broker(String name) {
	}

	/**
	 * 
	 * @param port
	 * @return
	 */
	abstract Channel accept(int port);

	/**
	 * 
	 * @param name
	 * @param port
	 * @return
	 */
	abstract Channel connect(String name, int port);

}
