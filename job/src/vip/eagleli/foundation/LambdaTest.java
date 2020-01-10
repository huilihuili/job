package vip.eagleli.foundation;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LambdaTest {

	static class Dish {
		String name;

		String getName() {
			return name;
		}
	}

	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
		List<Integer> squaresList = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
		System.out.println(squaresList);

		numbers.forEach(i -> System.out.println(i));
		numbers.forEach(System.out::println);
	}
}
