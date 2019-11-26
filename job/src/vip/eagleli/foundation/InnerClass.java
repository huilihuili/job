package vip.eagleli.foundation;

public class InnerClass {
	public static void main(String[] args) {
		System.out.println("hello, world");
	}

	private String a;

	class Test1 {
		public Test1() {
			System.out.println(a);
		}
	}

	Object fun() {
		return new Object() {
			@Override
			public String toString() {
				return a;
			}
		};
	}
}
