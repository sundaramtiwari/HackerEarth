package com.makemytrip;

public class OddEvenTwoThreads {

	public static void main(String[] args) {
		String lock = "lock";
		Thread evenThread = new Thread(new MyThread(lock, 0), "EvenThread");
		Thread oddThread = new Thread(new MyThread(lock, 1), "OddThread");
		evenThread.start();
		oddThread.start();
	}

}

class MyThread implements Runnable {

	private Object object;
	private int i;

	public MyThread(Object object, int i) {
		this.object = object;
		this.i = i;
	}
	
	@Override
	public void run() {
		synchronized (object) {
			for (; i<=10; i+= 2) {
				System.out.println(Thread.currentThread().getName() + " " + i);
				object.notify();
				try {
					object.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
}