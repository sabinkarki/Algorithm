package string.examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

//Sort List of String by frequency of string
public class SortListOfStringByFrequencyOfString {

	public static void main(String[] args) {

		List<String> input = Arrays.asList("   oo0o0 o0o00  ", "ba nana","sabin", "karki", "78$%$sak");
		System.out.println();
		System.out.println("****************************WithoutUsingHashMap*************************");
		System.out.println(sortStringOnBasisOfFrequency(input).toString());
		System.out.println();
//		System.out.println("****************************UsingHashMapAndComparator*************************");
//		System.out.println(finalListOfSortdString(input).toString());
		System.out.println();
		System.out.println("****************************UsingHashMapButNoComparator********************************");
		System.out.println(finalListOfSortdString(input).toString());

	}

	// Without using HashMap
	public static List<String> sortStringOnBasisOfFrequency(List<String> inputString) {
		List<String> lstFinal = new ArrayList<String>();

		inputString.stream()
				   .forEach(str -> {
							char[] arr = str.toCharArray();
							List<Character>[] lstOfChar = characterAndTheirRespectiveCount(arr);
							StringBuilder sb = new StringBuilder();
							for (int i = lstOfChar.length - 1; i >= 0; i--) {
								if (lstOfChar[i] != null) {
									for (char c : lstOfChar[i]) {
										String individualCharacters = getStringOfIndividualCharacter(i, c);
										sb.append(individualCharacters);
									}
								}
							}
							lstFinal.add(sb.toString());
						});
		return lstFinal;
	}

	public static List<Character>[] characterAndTheirRespectiveCount(char[] arr) {
		int[] asciiValues = new int[128];
		for (char c : arr) {
			asciiValues[c]++;
		}
		@SuppressWarnings("unchecked")
		List<Character>[] arrayOfList = new List[arr.length+1];
		for (int i = 1; i < asciiValues.length; i++) {
			if (i + 1 < asciiValues.length) {
				if (asciiValues[i] > 0) {
					List<Character> lst;
					int indexOfArrayOfList = asciiValues[i];
					if (arrayOfList[indexOfArrayOfList] == null) {
						lst = new ArrayList<Character>();
						lst.add((char) i);
						arrayOfList[indexOfArrayOfList] = lst;

					} else {
						lst = arrayOfList[indexOfArrayOfList];
						lst.add((char) i);
					}

				}

			}
		}
		return arrayOfList;
	}

	public static String getStringOfIndividualCharacter(int times, char c) {

		if (times > 0) {
			return c + getStringOfIndividualCharacter(--times, c);
		} else {
			return "";
		}
	}

	// With using HashMap

	public static List<String> finalListOfSortdString(List<String> input) {
		List<String> finalList = new ArrayList<String>();
		for (String str : input) {
			List<String> tempLstOfFilteredCharAndString = charOfString(str.toCharArray());
			mergeSort(tempLstOfFilteredCharAndString);
			finalList.add(lstToString(tempLstOfFilteredCharAndString));

			
//			 String finalSortedStringUsingComparator = sortStringBasedOnLengthUsingComparator(tempLstOfFilteredCharAndString);
//			 finalList.add(finalSortedStringUsingComparator);
			
		}

		return finalList;
	}

	public static List<String> charOfString(char[] charArr) {
		HashMap<Character, String> mapOfCharAndString = new HashMap<Character, String>();
		for (char c : charArr) {
			if (mapOfCharAndString.containsKey(c)) {
				StringBuilder sb = new StringBuilder();
				String value = mapOfCharAndString.get(c);
				sb.append(value);
				sb.append(c);
				mapOfCharAndString.put(c, sb.toString());

			} else {
				mapOfCharAndString.put(c, String.valueOf(c));
			}
		}

		List<String> tempLst = new ArrayList<>();

		for (String str : mapOfCharAndString.values()) {
			tempLst.add(str);
		}
		return tempLst;
	}

	// sort on the basis of String length using comparator and return single
	// String
	private static String sortStringBasedOnLengthUsingComparator(List<String> lstOfString) {
		Collections.sort(lstOfString,(str1, str2) -> Integer.compare(str2.length(), str1.length()));
		return lstToString(lstOfString);
	}

	private static String lstToString(List<String> lstOfString) {
		StringBuilder sb = new StringBuilder();
		lstOfString.stream().forEach(str -> {
			sb.append(str);
		});
		return sb.toString();
	}

	// sort on the basis of String length without using MergeSort
	public static void mergeSort(List<String> tempLstOfFilteredCharAndString) {
		ArrayList<String> left = new ArrayList<String>();
		ArrayList<String> right = new ArrayList<String>();
		
		int center;

		if (tempLstOfFilteredCharAndString.size() <2) {
			return ;
		} else {
			center = tempLstOfFilteredCharAndString.size() / 2;

			for (int i = 0; i < center; i++) {
				left.add(i, tempLstOfFilteredCharAndString.get(i));
			}

			for (int i = center; i < tempLstOfFilteredCharAndString.size(); i++) {
				right.add(i-center, tempLstOfFilteredCharAndString.get(i));
			}

			mergeSort(left);
		    mergeSort(right);
			merge(left, right, tempLstOfFilteredCharAndString);
		}
	}

	private static void merge(ArrayList<String> left, ArrayList<String> right,List<String> wholeList) {

		int leftSize = left.size();
		int rightSize = right.size();
		int i = 0, j = 0, k = 0;

		while (i < leftSize && j < rightSize) {

			if (left.get(i).length() > right.get(j).length()) {
				wholeList.set(k, left.get(i));
				i++;
				k++;

			}else{	
				wholeList.set(k, right.get(j));
				j++;
				k++;
			}

		}
		while (i < leftSize) {
			wholeList.set(k, left.get(i));
			i++;
			k++;
		}
		while (j < rightSize) {
			wholeList.set(k, right.get(j));
			j++;
			k++;
		}

	}
}
