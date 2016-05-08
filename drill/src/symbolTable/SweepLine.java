package symbolTable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeMap;

public class SweepLine {

	private class Line {
		public final int x1;
		public final int y1;
		public final int x2;
		public final int y2;
		public boolean rightEnd;

		public Line(int x1, int y1, int x2, int y2) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
			rightEnd = false;
		}

		// Copy constructor with rightEnd = true
		public Line(Line l) {
			this.x1 = l.x1;
			this.y1 = l.y1;
			this.x2 = l.x2;
			this.y2 = l.y2;
			rightEnd = true;
		}

		public boolean isHorizontal() {
			if (y1 == y2)
				return true;
			else
				return false;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("Line(").append(x1).append(',').append(y1).append('-').append(x2).append(',').append(y2);
			if (rightEnd)
				sb.append(" R");
			sb.append(')');
			return sb.toString();
		}
	}

	public SweepLine() throws FileNotFoundException {

		PriorityQueue<Line> q = new PriorityQueue<Line>(new Comparator<Line>() {
			@Override
			public int compare(Line o1, Line o2) {
				int cmp1 = o1.x1;
				if (o1.rightEnd)
					cmp1 = o1.x2;

				int cmp2 = o2.x1;
				if (o2.rightEnd)
					cmp2 = o2.x2;

				if (cmp1 != cmp2)
					return cmp1 - cmp2;
				else {
					if (o1.isHorizontal() && !o2.isHorizontal()) {
						if (o1.rightEnd)
							return 1;
						else
							return -1;
					} else if (!o1.isHorizontal() && o2.isHorizontal()) {
						if (o2.rightEnd)
							return -1;
						else
							return 1;
					}
					return cmp1 - cmp2;
				}
			}
		});

		Scanner in = new Scanner(new File("input/symTab/sweepLine"));
		int N = in.nextInt();
		for (int i = 0; i < N; i++) {
			Line l = new Line(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt());
			q.add(l);
			if (l.isHorizontal())
				q.add(new Line(l));
		}
		in.close();

		System.out.println(q);

		TreeMap<Integer, Line> map = new TreeMap<Integer, Line>();

		while (!q.isEmpty()) {
			Line l = q.remove();

			if (l.isHorizontal()) {
				if (l.rightEnd) {
					map.remove(l.y1);
					System.out.println("Delete: " + l);
				} else {
					map.put(l.y1, l);
					System.out.println("Insert: " + l);
				}
			} else {
				for (Line hl : map.subMap(l.y1, l.y2).values()) {
					System.out.println("Intersection of " + l + ", " + hl + ": " + l.x1 + "," + hl.y1);
				}
			}
		}
	}

	public static void main(String[] args) {
		try {
			SweepLine sl = new SweepLine();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
