package pg1.func;

public class Upd {
    public static void upd() {
//		System.out.println("功能未開放");
//		 1.提示輸入編號
        int id = inputKey();
//		 2.搜尋標記
        int index = -1;
        int sel = -1;
//		 遍歷並比較
        for (int i = 0; i < empList.size(); i++) {
            Candidate delEmp = (Candidate) empList.get(i);
//            if (delEmp.getEmpno() == id) {
//                System.out.println(delEmp.dsecEmp());
//                // 找到了 // 改變標記
//                index = i;
//            }
        }
        if (index != -1) {
            System.out.println("1.修改");
            System.out.println("2.取消");
            sel = selectKey();
        } else {
//			 沒找到
            System.out.println("查無此員工編號");
        }
        if (sel == 1) {
//			 3.收集其他訊息
            try {
                System.out.println("更改姓名為");
                String name = sc.nextLine();
                System.out.println("更改薪水為");
                int salary = sc.nextInt();
                sc.nextLine();
                System.out.println("更改獎金為");
                long bonus = sc.nextLong();
                sc.nextLine();
                if (bonus != 0) {
//                    empList.set(index, new Manger(id, name, salary, bonus));
                } else {
//                    empList.set(index, new Candidate(id, name, salary));
                }
                System.out.println("修改完畢");
            } catch (Exception e) {
                System.out.println("輸入錯誤");
            }

        } else {
            System.out.println("返回主畫面");
        }

    }
}
