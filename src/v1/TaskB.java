package v1;

public class TaskB extends Task{
	
	Broker broker;
	String m_name;

	public TaskB(String name, String name2) {
		broker = new Broker(name);
		m_name = name2;
	}

	public void run() {
		Channel channel;
		try {
			channel = broker.connect(m_name, 5555);

			byte[] tab = new byte[1024];

			int length = channel.read(tab, 0, 6);

			for (int i = 0; i < length; i++) {
				System.out.print((char) tab[i]);
			}
			System.out.println();
			channel.disconnect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
