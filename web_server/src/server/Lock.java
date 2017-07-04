package server;

import java.util.concurrent.locks.ReentrantLock;

public class Lock {
	public static ReentrantLock users_lock = new ReentrantLock();
	public static ReentrantLock airplanes_lock = new ReentrantLock();
	public static ReentrantLock flights_lock = new ReentrantLock();
	public static ReentrantLock history_lock = new ReentrantLock();
}
