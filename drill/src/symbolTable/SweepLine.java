package symbolTable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class SweepLine {

	private class Line {
		public final int x1;
		public final int x2;
		public final int y1;
		public final int y2;

		public final boolean vertical;
		public boolean swept;

		public Line(int a, int b, int c, int d) {
			if (a < c) {
				x1 = a;
				x2 = c;
			} else {
				x1 = c;
				x2 = a;
			}
			if (b < d) {
				y1 = b;
				y2 = d;
			} else {
				y1 = d;
				y2 = b;
			}
			swept = false;

			if (x1 == x2)
				vertical = true;
			else
				vertical = false;
		}

		public String toString() {
			return "Line(" + x1 + "," + y1 + "," + x2 + "," + y2 + ")";
		}
	}

	public SweepLine() throws FileNotFoundException {

		Line[] hs; // horizontal lines
		Line[] vs; // vertical lines

		Scanner in = new Scanner(new File("input/symTab/sweepLine"));
		hs = new Line[in.nextInt()];
		vs = new Line[in.nextInt()];
		for (int i = 0; i < hs.length; i++)
			hs[i] = new Line(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt());
		for (int i = 0; i < vs.length; i++)
			vs[i] = new Line(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt());
		in.close();

		// priority queue that sorts lines with its x values
		PriorityQueue<Line> q = new PriorityQueue<Line>(new Comparator<Line>() {
			public int compare(Line o1, Line o2) {
				int o1x, o2x;
				if (!o1.swept)
					o1x = o1.x1;
				else
					o1x = o1.x2;
				if (!o2.swept)
					o2x = o2.x1;
				else
					o2x = o2.x2;
				return o1x - o2x;
			}
		});

		for (int i = 0; i < hs.length; i++)
			q.add(hs[i]);

		for (int i = 0; i < vs.length; i++)
			q.add(vs[i]);

		// bst for storing y coordinate of lines currently being swept
		BST<Integer, Line> ys = new BST<Integer, Line>();

		while (!q.isEmpty()) {
			Line l = q.remove();
			if (l.vertical) {
				Iterable<Line> intersects = ys.harvestValues(l.y1, l.y2);
				System.out.println(l + " intersects with: " + intersects);
			} else if (!l.swept) {
				l.swept = true;
				q.add(l);

				ys.put(l.y1, l);
			} else {
				ys.delete(l.y1);
			}
		}
	}

	public static void main(String[] args) throws FileNotFoundException {

		new SweepLine();

	}

}
