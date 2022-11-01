package v3;

import java.io.IOException;

public class ChannelImpl extends Channel {

	private boolean disconnected;
	private ChannelImpl channelConnectedTo;
	private CircularBuffer c_buffer;

	ChannelImpl() {
		super();
		this.disconnected = false;
		this.c_buffer = new CircularBuffer(5);
	}

	public int read(byte[] bytes, int offset, int length) throws IOException {
		if (this.channelConnectedTo.disconnected() || this.disconnected) {
			this.channelConnectedTo.disconnect();
			this.disconnect();
			throw new IOException("Channels have been disconnected");
		}

		int counter = 0;

		while (counter < bytes.length && counter < length) {
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
				e.printStackTrace();
				return 0;
			}
			synchronized (this.channelConnectedTo) {
				this.channelConnectedTo.notifyAll();
			}
		}

		return counter;
	}

	public int write(byte[] bytes, int offset, int length) throws IOException {
		if (this.channelConnectedTo.disconnected() || this.disconnected) {
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
				if (this.channelConnectedTo.disconnected() || this.disconnected) {
					this.channelConnectedTo.disconnect();
					this.disconnect();
					throw new IOException("Channels disconnected");
				}
				this.channelConnectedTo.c_buffer.put(bytes[offset + counter]);
				counter++;
			} catch (InterruptedException e) {

				e.printStackTrace();
				return 0;
			}
			synchronized (this.channelConnectedTo) {
				this.channelConnectedTo.notifyAll();
			}
		}

		return counter;
	}

	public void disconnect() {
		this.disconnected = true;
		channelConnectedTo.disconnected = true;
	}

	public boolean disconnected() {
		return this.disconnected;
	}

	public void connectTo(ChannelImpl external) {
		this.channelConnectedTo = external;
	}
}
