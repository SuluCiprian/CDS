package headphoneHunter;

import java.util.concurrent.atomic.AtomicIntegerArray;

class Dekker {
	
	private volatile int turn;
	private AtomicIntegerArray flag = new AtomicIntegerArray(24);

	public Dekker() {
		for (int i = 0; i < 25; i++) {
			flag.set(i, 0);
}
		turn = 0;
	}

	public void Pmutex(int t) {
		int other;

		other = 24 - t;
		flag.set(t, 1);
		while (flag.get(other) == 1) {
			if (turn == other) {
				flag.set(t, 0);
				while (turn == other)
					;
				flag.set(t, 1);
			}
		}
	}

	public void Vmutex(int t) {
		turn = 24 - t;
		flag.set(t, 0);
	}

}