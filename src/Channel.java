import java.io.IOException;

/**
 * Channel allows tasks to communicate between each other and share data Broker
 * creates channel
 * 
 * @author cl__nguyen
 * 
 * 
 */
public class Channel {

	Channel channelConnectedTo;
	boolean isDisconnected;
	CircularBuffer c_buffer;

	public Channel() {
		this.isDisconnected = false;
		this.c_buffer = new CircularBuffer(5); // 5 bytes
	}

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
	public int read(byte[] bytes, int offset, int length) throws IOException { // ProdCons from last year
		if (this.channelConnectedTo.disconnected() || this.isDisconnected) {
			this.channelConnectedTo.disconnect();
			this.disconnect();
			throw new IOException("Channels have been disconnected");
		}

		int counter = 0;

		while (counter < bytes.length - 1 && counter < length - 1) {
			try {
				while (this.c_buffer.empty()) {
					synchronized (this) {
						this.wait();
					}
				}
				byte b = c_buffer.get();
				bytes[offset + counter] = b;
				counter++;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return 0;
			}
			synchronized (this.channelConnectedTo) {
				this.channelConnectedTo.notifyAll();
			}
		}

		return counter;
	}

	/**
	 * This method allows a task to write a content in a buffer.
	 * 
	 * @param bytes  : buffer of byte which will be write
	 * @param offset : beginning indexs
	 * @param length : length of the buffer
	 * @return int indicating how many bytes have been writing sucessfully
	 * @throws IOException if channel is disconnected during the method
	 */
	public int write(byte[] bytes, int offset, int length) throws IOException { // ProdCons from last year
		if (this.channelConnectedTo.disconnected() || this.isDisconnected) {
			this.channelConnectedTo.disconnect();
			this.disconnect();
			throw new IOException("Channels disconnected");
		}

		int counter = 0;

		while (counter < length && counter < bytes.length) {
			try {
				while (this.channelConnectedTo.c_buffer.full()) {

					synchronized (this) {
						this.wait();
					}
				}
				if (this.channelConnectedTo.disconnected() || this.isDisconnected) {
					this.channelConnectedTo.disconnect();
					this.disconnect();
					throw new IOException("Channels disconnected");
				}
				this.channelConnectedTo.c_buffer.put(bytes[offset + counter]);
				counter++;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return 0;
			}
			synchronized (this.channelConnectedTo) {
				this.channelConnectedTo.notifyAll();
			}
		}

		return counter;
	}

	/**
	 * This method allows a channel to disconnect
	 */

	public void disconnect() {
		this.isDisconnected = true;
	}

	/**
	 * This method indicates if the channel is disconnected
	 * 
	 * @return
	 */
	public boolean disconnected() {
		return isDisconnected;
	}

	public void channelConnectedTo(Channel external) {
		this.channelConnectedTo = external;
	}
}
