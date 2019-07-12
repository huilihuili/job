package vip.eagleli.pattern.singleton;

public class Singleton {

	private Singleton() {

	}

	private static class SingletonHolder {
		private static Singleton uniqueInstance;
	}

	public static Singleton getUniqueInstance() {
		return SingletonHolder.uniqueInstance;
	}
}
