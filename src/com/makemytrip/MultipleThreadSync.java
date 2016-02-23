package com.makemytrip;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class MultipleThreadSync {
	private final int numParties;

	private final Counter counter;
	private final Set<String> runners = new HashSet<String>();
	private final CyclicBarrier barrier;
	private final Thread[] parties;

	public MultipleThreadSync(int numParties, int maxCounterValue) {
		this.numParties = numParties;
		counter = new Counter(numParties, maxCounterValue);
		barrier = new CyclicBarrier(numParties + 1);
		parties = new Thread[numParties];

		for (int i = 0; i < numParties; i++) {
			parties[i] = new Thread(new OddEvenRunnable(i, counter, barrier),
					getThreadName(i));
		}
	}

	private void runInSingleJvm() throws BrokenBarrierException,
			InterruptedException {
		// start all the counting parties
		for (int i = 0; i < numParties; i++) {
			parties[i].start();
		}
		startCounting();
	}

	private void startCounting() throws InterruptedException,
			BrokenBarrierException {
		System.out.println(Thread.currentThread().getName()
				+ ": Sleeping for 1 secs....");
		Thread.sleep(1000);
		System.out.println(Thread.currentThread().getName()
				+ ": ... And letting all the counting threads go!!");
		// let thy parties proceed
		barrier.await();
	}

	private String getThreadName(int partyNum) {
		String prefix = "";
		for (int i = 0; i < partyNum; i++) {
			prefix += "  ";
		}
		return prefix + "Party-" + partyNum;
	}

	public static void main(String[] args) throws Exception {
		MultipleThreadSync main = new MultipleThreadSync(4, 30);
		if (args.length == 0) {
			// run in a single node/jvm
			main.runInSingleJvm();
		} else {
			if (args.length != 1) {
				printUsageAndExit();
			}
			if ("odd".equals(args[0])) {
				main.startFirstThread();
			} else if ("even".equals(args[0])) {
				main.startSecondThread();
			} else if ("main".equals(args[0])) {
				main.startMainThread();
			} else if ("reset".equals(args[0])) {
				main.reset();
			} else
				printUsageAndExit();
		}

	}

	private void reset() {
		for (int i = 0; i < numParties; i++) {
			parties[i] = new Thread(new OddEvenRunnable(i, counter, barrier),
					getThreadName(i));
		}
		synchronized (runners) {
			this.runners.clear();
			counter.setValue(0);
		}
		System.out.println("Reset Done.");
	}

	private void startMainThread() throws Exception {
		if (runners.size() != 2) {
			System.out
					.println("Make sure that you have started both the odd and even threads.");
			printUsageAndExit();
		}
		synchronized (runners) {
			runners.add("main");
		}
		startCounting();
	}

	private void startSecondThread() {
		if (runners.contains("even")) {
			System.out
					.println("You have already started the even-printing thread.");
			printUsageAndExit();
		}
		synchronized (runners) {
			runners.add("even");
		}
		parties[1].start();
		System.out.println("Started even thread");
	}

	private void startFirstThread() {
		if (runners.contains("odd")) {
			System.out
					.println("You have already started the odd-printing thread.");
			printUsageAndExit();
		}
		synchronized (runners) {
			runners.add("odd");
		}
		parties[0].start();
		System.out.println("Started odd thread");
	}

	private static void printUsageAndExit() {
		System.out.println("USAGE: java Main [odd | even | main | reset]");
		System.out
				.println("   No-arguments - Starts 2 threads printing odd and even values in single jvm.");
		System.out
				.println("   odd - starts the odd-number printing thread in this node.");
		System.out
				.println("   even - starts the even-number printing thread in this node.");
		System.out
				.println("   main - starts a thread which lets the odd and even threads go ahead.");
		System.out
				.println("   reset - Resets all states so you can start all over again.");
		System.exit(1);
	}

}

class OddEvenRunnable implements Runnable {

	private final Counter counter;
	private final int partyId;
	private final CyclicBarrier barrier;

	public OddEvenRunnable(int partyId, Counter counter, CyclicBarrier barrier) {
		this.partyId = partyId;
		this.counter = counter;
		this.barrier = barrier;
	}

	public void run() {
		try {
			System.out.println(Thread.currentThread().getName()
					+ ": Waiting for GREEN signal from main guy...");
			barrier.await();
		} catch (Exception e) {
			e.printStackTrace();
		}
		while (true) {
			synchronized (counter) {
				while (!(counter.isMyTurn(partyId) || counter
						.isMaxValueReached())) {
					try {
						counter.wait();
					} catch (InterruptedException e) {
						System.out
								.println(partyId
										+ ": Got Interrupted. Continuing for my turn...");
					}
				}
				if (counter.isMaxValueReached()) {
					// make sure other-threads don't keep waiting for my signal
					// when I'm
					// leaving
					counter.notifyAll();
					break;
				}
				int value = counter.increment();
				System.out.println(Thread.currentThread().getName()
						+ ": Counter Value=" + value);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// ignored
				}
				counter.notifyAll();
			}
		}

		System.out.println(Thread.currentThread().getName() + ": DONE!!");
	}

}

class Counter {
	private final int numParties;
	private int value = 0;
	private final int maxValue;

	public Counter(int numParties, int maxValue) {
		this.numParties = numParties;
		this.maxValue = maxValue;
	}

	public synchronized boolean isMyTurn(int partyNum) {
		return value % numParties == partyNum;
	}

	public synchronized void setValue(int val) {
		this.value = val;
	}

	public int getMaxValue() {
		return maxValue;
	}

	public synchronized boolean isMaxValueReached() {
		return value >= maxValue;
	}

	public synchronized int increment() {
		this.value++;
		return value;
	}

	public synchronized int getValue() {
		return value;
	}
}