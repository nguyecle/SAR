
public class Test {

	public static void main(String[] args) {

		Broker broker = new Broker("Test 1");
		TaskA taskA = new TaskA(broker);
		TaskB taskB = new TaskB(broker);

		taskA.start();
		taskB.start();

		try {
			taskA.join();
			taskB.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
