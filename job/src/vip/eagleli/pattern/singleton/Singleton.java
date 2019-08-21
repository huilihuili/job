package vip.eagleli.pattern.singleton;

public class Singleton {

	private Singleton() {

	}

	private static class SingletonHolder {
		private static Singleton uniqueInstance = new Singleton();
	}

	public static Singleton getUniqueInstance() {
		return SingletonHolder.uniqueInstance;
	}
}
