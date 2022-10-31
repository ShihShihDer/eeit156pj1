package input;

import java.util.Scanner;
import java.util.regex.Pattern;

public class InputSelect {
	public static int inputSelect() {
		Scanner selectKey = new Scanner(System.in);
		final int maxLength = 1;
		int select = 0;
		String str;
		selectable: while (selectKey.hasNextLine()) {
			if (selectKey.hasNextLine()) {
				str = selectKey.nextLine();
				if (!str.isEmpty() && !str.contains(" ") && !str.contains("\n")
						&& !str.contains("\r") && !str.contains("\t")) {
					Pattern pattern = Pattern.compile("[0-9]*");
					if (pattern.matcher(str).matches()) {
						select = Integer.parseInt(str);
						if (String.valueOf(select).length() <= maxLength) {
							select = Integer.parseInt(str);
							break selectable;
						} else {
							System.out.println("指令錯誤");
						}
					} else {
						System.out.println("指令錯誤");
					}
				} else {
					System.out.println("指令錯誤");
				}
			} else {
				System.out.println("指令錯誤");
				selectKey.nextLine();
			}
		}
		return select;
	}
}
