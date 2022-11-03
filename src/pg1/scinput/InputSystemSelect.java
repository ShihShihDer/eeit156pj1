package pg1.scinput;

import java.util.Scanner;
import java.util.regex.Pattern;

public class InputSystemSelect {
	public static int inputSys() {

		Scanner inputSys = new Scanner(System.in);

		final int maxLength = 1;
		int num = 0;
		String str;
		System.out.println("==========候選人資料庫==========");
		sysMassage();
		inputno: while (inputSys.hasNextLine()) {
			if (inputSys.hasNextLine()) {
				str = inputSys.nextLine();
				if (!str.isEmpty() && !str.contains(" ") && !str.contains("\n")
						&& !str.contains("\r") && !str.contains("\t")) {
					Pattern pattern = Pattern.compile("[0-9]*");
					if (pattern.matcher(str).matches()) {
						num = Integer.parseInt(str);
						if (String.valueOf(num).length() <= maxLength && num > 0 && num <= 7) {
							break inputno;
						} else {
							System.out.println("指令錯誤");
							sysMassage();
						}
					} else {
						System.out.println("指令錯誤");
						sysMassage();
					}
				} else {
					System.out.println("指令錯誤");
					sysMassage();
				}
			} else {
				System.out.println("指令錯誤");
				sysMassage();
				inputSys.nextLine();
			}
		}
		return num;
	}

	public static void sysMassage() {

		System.out.println("===========================");
		System.out.println("1.查詢候選人訊息");
		System.out.println("2.新增候選人訊息");
		System.out.println("3.修改候選人訊息");
		System.out.println("4.刪除候選人訊息");
		System.out.println("6.退出系統");
		System.out.println("===========================");
		System.out.print("請輸入對應功能的序號:");
	}
}
