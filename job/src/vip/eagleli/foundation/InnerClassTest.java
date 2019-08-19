package vip.eagleli.foundation;

import java.lang.reflect.InvocationTargetException;

public class InnerClassTest {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Class<InnerClass> class1 = InnerClass.class;
		InnerClass innerClass = class1.getConstructor(InnerClassTest.class, String.class)
				.newInstance(new InnerClassTest(), "hello");
		System.out.println(innerClass);
	}

	class InnerClass {
		private String name;

		public InnerClass(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return "InnerClass[" + this.name + "]";
		}
	}
}
