package pg1.scinput;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Inputno {
	public static int inputno() {

		Scanner inputno = new Scanner(System.in);
		final int maxLength = 5;
		int num = 0;
		String str;
		System.out.print("請輸入員工編號:");

		inputno: while (inputno.hasNextLine()) {
			if (inputno.hasNextLine()) {
				str = inputno.nextLine();
				if (!str.isEmpty() && !str.contains(" ") && !str.contains("\n")
						&& !str.contains("\r") && !str.contains("\t")) {
					Pattern pattern = Pattern.compile("[0-9]*");
					if (pattern.matcher(str).matches()) {
						num = Integer.parseInt(str);
						if (String.valueOf(num).length() <= maxLength && num != 0) {
							break inputno;
						} else {
							System.out.print("輸入錯誤，請重新輸入:");
						}
					} else {
						System.out.print("輸入錯誤，請重新輸入:");
					}
				} else {
					System.out.print("輸入錯誤，請重新輸入:");
				}
			} else {
				System.out.print("輸入錯誤，請重新輸入:");
				inputno.nextLine();
			}
		}
		System.out.printf("輸入的員工編號為:%d%n", num);
		return num;
	}
	public static int inputid() {

		Scanner inputno = new Scanner(System.in);
		final int maxLength = 20;
		int num = 0;
		String str;
		inputno: while (inputno.hasNextLine()) {
			if (inputno.hasNextLine()) {
				str = inputno.nextLine();
				if (!str.isEmpty() && !str.contains(" ") && !str.contains("\n")
						&& !str.contains("\r") && !str.contains("\t")) {
					Pattern pattern = Pattern.compile("[0-9]*");
					if (pattern.matcher(str).matches()) {
						num = Integer.parseInt(str);
						if (String.valueOf(num).length() <= maxLength && num != 0) {
							break inputno;
						} else {
							System.out.print("輸入錯誤，請重新輸入:");
						}
					} else {
						System.out.print("輸入錯誤，請重新輸入:");
					}
				} else {
					System.out.print("輸入錯誤，請重新輸入:");
				}
			} else {
				System.out.print("輸入錯誤，請重新輸入:");
				inputno.nextLine();
			}
		}
		return num;
	}
	public static int inputCno() {

		Scanner inputno = new Scanner(System.in);
		final int maxLength = 2;
		int num = 0;
		String str;
		inputno: while (inputno.hasNextLine()) {
			if (inputno.hasNextLine()) {
				str = inputno.nextLine();
				if (!str.isEmpty() && !str.contains(" ") && !str.contains("\n")
						&& !str.contains("\r") && !str.contains("\t")) {
					Pattern pattern = Pattern.compile("[0-9]*");
					if (pattern.matcher(str).matches()) {
						num = Integer.parseInt(str);
						if (String.valueOf(num).length() <= maxLength && num != 0) {
							break inputno;
						} else {
							System.out.print("輸入錯誤，請重新輸入:");
						}
					} else {
						System.out.print("輸入錯誤，請重新輸入:");
					}
				} else {
					System.out.print("輸入錯誤，請重新輸入:");
				}
			} else {
				System.out.print("輸入錯誤，請重新輸入:");
				inputno.nextLine();
			}
		}
		return num;
	}
}
