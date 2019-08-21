package vip.eagleli.foundation;

class PrivateClass {
	private PrivateClass() {

	}
}

public class ExendTest {

}

class A {

	void fun() {

	}
}

class B extends A {

}

class C extends B {
	@Override
	void fun() {
		super.fun();
	}
}