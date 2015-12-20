import java.util.NavigableMap;
import java.util.Scanner;
import java.util.TreeMap;

public class SherlockAndValidString {

	private static final int R = 26; // radix

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String s = in.next();
		in.close();

		int len = s.length();

		int[] count = new int[R];

		for (int i = 0; i < len; i++)
			count[s.charAt(i) - 'a']++;

		NavigableMap<Integer, Integer> map = new TreeMap<Integer, Integer>();

		for (int i = 0; i < R; i++) {
			int key = count[i];
			if (key != 0) {
				if (map.containsKey(key))
					map.put(key, map.get(key) + 1);
				else
					map.put(key, 1);
			}
		}

		boolean perfect = false;

		if (map.size() == 1)
			perfect = true;
		else if (map.size() == 2) {
			int key1 = map.firstKey();
			int key2 = map.lastKey();
			if ((key1 == 1 && map.get(key1) == 1) || (key1 == 1 && map.get(key1) == 1)
					|| (key1 + 1 == key2 && map.get(key2) == 1))
				perfect = true;
		}

		if (perfect)
			System.out.println("YES");
		else
			System.out.println("NO");

	}

}
