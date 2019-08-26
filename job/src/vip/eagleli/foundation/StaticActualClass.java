package vip.eagleli.foundation;

public class StaticActualClass {
	static class Per {
		int a = 1;

		void fun() {
			System.out.println("per");
		}
	}

	static class Stu extends Per {
		int a = 2;

		@Override
		void fun() {
			System.out.println("stu");
		}
	}

	static void fun(Per per) {
		System.out.println("per");
	}

	static void fun(Stu stu) {
		System.out.println("stu");
	}

	public static void main(String[] args) {
		Per per = new Per();
		Stu stu = new Stu();
		per = stu;

		System.out.println(per.a);
		System.out.println(stu.a);

		per.fun();
		stu.fun();

		fun(per);
		fun(stu);
	}
}
