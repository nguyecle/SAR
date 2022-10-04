package v2;

import java.util.HashMap;

public class Test {

	public static HashMap<String, Broker> BrokerList = new HashMap<String, Broker>();

	public static void main(String[] args) {

		TaskA tA = new TaskA("TaskA");
		TaskB tB = new TaskB("TaskB", "TaskA");

		tA.start();
		tB.start();

		try {
			tB.join();
			tA.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
