package DataBinding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;

public class IO {

	private static BufferedReader keyb = new BufferedReader( new InputStreamReader(System.in) );

	public static float round(float x, int digits) {
		int help = (int) Math.pow(10, digits);
		return ((float) Math.round(x * help)) / help;
	}

	public static double round(double x, int digits) {
		long help = (long) Math.pow(10, digits);
		return ((double) Math.round(x * help)) / help;
	}

	public static void advance(int n) {
		for (int i = 0; i < n; i++)
			writeln();
	}

	public static void write(String s) {
		System.out.print(s);
		System.out.flush();
	}

	public static void write(Object o) {
		String str = (o == null) ? "null" : o.toString();
		write(str);
	}

	public static void write(int i) {
		write("" + i);
	}

	public static void write(long lg) {
		write("" + lg);
	}

	public static void write(float f) {
		write("" + f);
	}

	public static void write(double d) {
		write("" + d);
	}

	public static void write(boolean b) {
		write("" + b);
	}

	public static void write(char c) {
		write("" + c);
	}

	public static void writeln(String s) {
		write(s + '\n');
	}

	public static void writeln(Object o) {
		write(o);
		writeln();
	}

	public static void writeln(int i) {
		write(i);
		writeln();
	}

	public static void writeln(long lg) {
		write(lg);
		writeln();
	}

	public static void writeln(float f) {
		write(f);
		writeln();
	}

	public static void writeln(double d) {
		write(d);
		writeln();
	}

	public static void writeln(boolean b) {
		write(b);
		writeln();
	}

	public static void writeln(char c) {
		write(c);
		writeln();
	}

	public static void writeln() {
		writeln("");
	}

	public static String promptAndReadString(String s) {
		write(s);
		try {
			return keyb.readLine();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static byte promptAndReadByte(String s) {
		return Byte.valueOf(promptAndReadString(s)).byteValue();
	}

	public static short promptAndReadShort(String s) {
		return Short.valueOf(promptAndReadString(s)).shortValue();
	}

	public static int promptAndReadInt(String s) {
		return Integer.valueOf(promptAndReadString(s)).intValue();
	}

	public static long promptAndReadLong(String s) {
		return Long.valueOf(promptAndReadString(s)).longValue();
	}

	public static char promptAndReadChar(String s) {
		return promptAndReadString(s).charAt(0);
	}

	public static double promptAndReadDouble(String s) {
		return Double.valueOf(promptAndReadString(s)).doubleValue();
	}

	public static float promptAndReadFloat(String s) {
		return Float.valueOf(promptAndReadString(s)).floatValue();
	}

	public static boolean promptAndReadBoolean(String s) {
		return Boolean.valueOf(promptAndReadString(s)).booleanValue();
	}
	
	public static void printObject(Object obj) {
        try {
            Class<?> objClass = obj.getClass();
            Field[] fields = objClass.getDeclaredFields();
            System.out.println(objClass.getSimpleName() + " {");
            for (Field field : fields) {
                field.setAccessible(true); // Zugriff auf private Felder
                System.out.println("  " + field.getName() + ": " + field.get(obj));
            }
            System.out.println("}");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}