package vip.eagleli.foundation;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Generic {
	Map<String, Integer> map = new HashMap<String, Integer>();

	public static void main(String[] args) throws Exception {
		Field field = Generic.class.getDeclaredField("map");
		field.setAccessible(true);
		Type genericType = field.getGenericType();
		if (genericType instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) genericType;
			System.out.println(Arrays.toString(parameterizedType.getActualTypeArguments()));
		}
	}
}
