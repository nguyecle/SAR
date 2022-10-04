package v2;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.locks.ReentrantLock;

public class MessageQueueImpl extends MessageQueue {

	private Channel channel;
	private ReentrantLock sendLock;
	private ReentrantLock receiveLock;
	private boolean isClosed;

	MessageQueueImpl(Channel channel) {
		super(channel);
		this.channel = channel;
		this.sendLock = new ReentrantLock();
		this.receiveLock = new ReentrantLock();
		this.isClosed = false;
	}

	@Override
	void send(byte[] bytes, int offset, int length) {
		this.sendLock.lock();
		byte[] len = ByteBuffer.allocate(Integer.BYTES).putInt(length).array();
		int cpt = 0;
		try {
			cpt = this.channel.write(len, 0, Integer.BYTES);
			cpt += this.channel.write(bytes, 0, length);
			if (cpt != length + Integer.BYTES) {
				throw new IOException("Error");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.sendLock.unlock();

	}

	@Override
	byte[] receive() {
		this.receiveLock.lock();
		byte[] tab = new byte[64];
		try {
			int res = this.channel.read(tab, 0, Integer.BYTES);
			if (res != Integer.BYTES) {
				throw new IOException("Error");
			}
			int len = (tab[0] << 24) & 0xff000000 | (tab[1] << 24) & 0x00ff0000 | (tab[2] << 24) & 0x0000ff00
					| (tab[3] << 24) & 0x000000ff;
			res = this.channel.read(tab, 4, len);
			this.receiveLock.unlock();
			return tab;
		} catch (IOException e) {
			e.printStackTrace();
			this.receiveLock.unlock();
			return null;
		}
	}

	@Override
	void close() {
		this.isClosed = true;

	}

	@Override
	boolean closed() {
		return this.isClosed;
	}

}
