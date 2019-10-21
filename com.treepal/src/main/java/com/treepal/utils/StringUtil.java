package com.treepal.utils;


import java.util.regex.Pattern;

/**
*  Check similarity degree for two string
*
* @author  Maida Ge(Mark)
* @version 3.0
* @date   2019-10-07
*/
public class StringUtil {
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }
    
    public static double similarity(String s1,String s2) {
		//System.out.println(s1 + "AAAA" + s2);
 		int t1 = s1.hashCode() - s2.hashCode();
 		
 		int max = Integer.MAX_VALUE;
 		
 		return (1 - (double)Math.abs(t1) / max)*100;
 	}
    
	public static double SimilarDegree(String strA, String strB) {

		String newStrA = removeSign(strA.trim());
		// System.out.println(newStrA);
		String newStrB = removeSign(strB.trim());
		// System.out.println(newStrB);

		int temp = Math.max(newStrA.length(), newStrB.length());
		int lenA = newStrA.length();
		int lenB = newStrB.length();
		//System.out.println(temp);
		int temp2 = 0;
		if (lenA >= lenB ? true : false) {
			temp2 = longestCommonSubstring(newStrA, newStrB).length();
		} else {
			temp2 = longestCommonSubstring(newStrB, newStrA).length();
		}

		//System.out.println(temp2);
		return temp2 * 1.0 / temp;

	}

	private static String removeSign(String str) {

		StringBuffer sb = new StringBuffer();

		for (char item : str.toCharArray())

			if (charReg(item)) {

				//System.out.println("--" + item);

				sb.append(item);

			}
		//System.out.println(sb.toString());

		return sb.toString();

	}

	private static boolean charReg(char charValue) {

		return (charValue >= 0x4E00 && charValue <= 0X9FA5)

				|| (charValue >= 'a' && charValue <= 'z')

				|| (charValue >= 'A' && charValue <= 'Z')

				|| (charValue >= '0' && charValue <= '9');

	}

	private static String longestCommonSubstring(String strA, String strB) {

		char[] chars_strA = strA.toCharArray();

		char[] chars_strB = strB.toCharArray();

		int m = chars_strA.length;

		int n = chars_strB.length;

		int[][] matrix = new int[m + 1][n + 1];

		for (int i = 1; i <= m; i++) {

			for (int j = 1; j <= n; j++) {

				if (chars_strA[i - 1] == chars_strB[j - 1])

					matrix[i][j] = matrix[i - 1][j - 1] + 1;

				else

					matrix[i][j] = Math.max(matrix[i][j - 1], matrix[i - 1][j]);

			}

		}

		char[] result = new char[matrix[m][n]];

		int currentIndex = result.length - 1;

		while (matrix[m][n] != 0) {

			if (matrix[n] == matrix[n - 1])

				n--;

			else if (matrix[m][n] == matrix[m - 1][n])

				m--;

			else {

				result[currentIndex] = chars_strA[m - 1];

				currentIndex--;

				n--;

				m--;

			}
		}

		return new String(result);

	}
}
