package sort;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MergeSort {

	public static void sort(Comparable[] a) {
	}

	public static void sort(Comparable[] a, Comparable[] buf, int lo, int hi) {
	}

	private static void merge(Comparable[] from, Comparable[] to, int lo, int mid, int hi) {
	}

	@SuppressWarnings("rawtypes")
	public static void print(Comparable[] a) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < a.length; i++) {
			sb.append(a[i]).append(' ');
		}
		System.out.println(sb.toString());
	}

	public static void main(String[] args) {
	}

}
