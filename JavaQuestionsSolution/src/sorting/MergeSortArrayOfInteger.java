package sorting;

public class MergeSortArrayOfInteger {

	public static void main(String[] args) {
		int[] input = { 5, 6, 2, 1, 8 };
		mergeSort(input);
		for (int i = 0; i < input.length; i++) {
			System.out.println(input[i]);
		}
	}

	public static void mergeSort(int[] arrInput) {
		int length = arrInput.length;

		if (length < 2) {
			return;
		}
		int mid = arrInput.length / 2;
		int[] left = new int[mid];
		int[] right = new int[arrInput.length - mid];

		for (int i = 0; i < mid; i++) {
			left[i] = arrInput[i];
		}
		for (int i = mid; i < length; i++) {
			right[i - mid] = arrInput[i];
		}
		mergeSort(left);
		mergeSort(right);
		merge(left, right, arrInput);
	}

	public static void merge(int left[], int right[], int arrInput[]) {
		int leftLength = left.length;
		int rightLength = right.length;
		int i = 0, j = 0, k = 0;

		while (i < leftLength && j < rightLength) {
			if (left[i] < right[j]) {
				arrInput[k] = left[i];
				i++;
				k++;
			} else {
				arrInput[k] = right[j];
				j++;
				k++;
			}

		}
		while (i < leftLength) {
			arrInput[k] = left[i];
			k++;
			i++;
		}
		while (j < rightLength) {
			arrInput[k] = right[j];
			k++;
			j++;
		}

	}
}
