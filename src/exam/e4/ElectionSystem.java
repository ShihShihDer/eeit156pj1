package exam.e4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import input.*;
import ioEmp.ReadCSV;
import ioEmp.WriteCSV;

public class ElectionSystem {


    public static void main(String[] args) {

        List<Candidate> eleList = new ArrayList<>();
        eleList = ReadCSV.readCSV();
        for (Candidate c:eleList
             ) {
            System.out.println(c.dsecCd());
        }
        System.out.println("以下");
        while (true) {
            switch (InputSystemSelect.inputSys()) {
//			1.檢視訊息
                case 1 -> show(eleList);
//			2.新增訊息
                case 2 -> add(eleList);
//			3.修改訊息
                case 3 -> upd(eleList);
//			4.刪除訊息
                case 4 -> del(eleList);
//			5.退出訊息管理系統
                case 5 -> {
//                    empList.sort(Comparator.comparing(Candidate::getEmpno));
//                    WriteCSV.writeCSV(empList);
                    System.out.println("儲存檔案");
                }
                case 6 -> {
                    System.out.println("退出管理系統");
                    System.exit(0);
                }
                default -> System.out.println("功能錯誤");
            }
        }
    }

    // 功能方法
        public static int inputKey() {
        return Inputno.inputno();
    }

    public static int selectKey() {
        return InputSelect.inputSelect();
    }

    public static void del(List<Candidate> empList) {
        // 1.提示輸入empNo
        int select;
        int id = inputKey();
        // 2.搜尋 定義標記
        int index = 0;
        // 遍歷比較 並修改
        for (int i = 0; i < empList.size(); i++) {
            Candidate delEmp = (Candidate) empList.get(i);
//            if (delEmp.getEmpno() == id) {
//                System.out.println(delEmp.dsecEmp());
//                // 找到了 // 改變標記
//                index = i;
//                break;
//            } else {
//                index = -1;
//            }
        }
        // 3.判斷結果 // 判斷標記
        if (index == -1) {
            // 沒有找到
            System.out.println("查無此員工 回到選單");
        } else {
            // 找到了是否執行刪除
            System.out.println("是否刪除資料");
            System.out.println("1.刪除");
            System.out.println("2.跳出");
            select = selectKey();
            if (select == 1) {
                empList.remove(index);
                System.out.println("刪除完畢");
            } else if (select == 2) {
                System.out.println("回到選單");
            } else {
                System.out.println("輸入錯誤,回到選單");
            }
        }
    }

    public static void upd(List<Candidate> empList) {
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

    public static void add(List<Candidate> empList) {
        int id = inputKey();
        int index = -1;
        for (int i = 0; i < empList.size(); i++) {
            Candidate delEmp = (Candidate) empList.get(i);
//            if (delEmp.getEmpno() == id) {
//                index = i;
//                System.out.println(delEmp.dsecEmp());
//                System.out.println("員工編號已使用");
//            }
        }
        if (index == -1) {
            try {
                System.out.println("請輸入姓名");
                String name = sc.nextLine();
                System.out.println("請輸入薪水");
                int salary = sc.nextInt();
                sc.nextLine();
                System.out.println("請輸入獎金");
                long bonus = sc.nextLong();
                sc.nextLine();
                if (bonus > 0) {
//                    empList.add(new Manger(id, name, salary, bonus));
                    System.out.println("新增完畢");
                } else {
//                    empList.add(new Candidate(id, name, salary));
                    System.out.println("新增完畢");
                }
            } catch (Exception e) {
                System.out.println("輸入錯誤");
            }
        }
    }

    public static void show(List<Candidate> empList) {
        System.out.println("1.以編號查詢");
        System.out.println("2.全部顯示");
        int select = selectKey();
        if (select == 1) {
            int id = inputKey();
            int index = -1;
            for (int i = 0; i < empList.size(); i++) {
                Candidate addTmp = (Candidate) empList.get(i);
//                if (addTmp.getEmpno() == id) {
//                    System.out.println(addTmp.dsecEmp());
////				 找到了 修改標記
//                    index = i;
//                }
            }
//		// 1.判斷集合是否有元素
            if (index == -1) {
//			 如果沒有給出特定的提示
                System.out.println("查無此人");
                System.out.println("1.重新查詢");
                System.out.println("2.回到目錄");
//				Scanner sc = new Scanner(System.in);
                select = selectKey();
                if (select == 1) {
                    show(empList);
                } else {
                    System.out.println("輸入錯誤回到選單");
                }
            }
        } else if (select == 2) {
//            empList.sort(Comparator.comparing(Candidate::getEmpno));
//            empList.forEach(item -> System.out.println(item.dsecEmp()));
            System.out.println("===========================");
            System.out.println("展示完畢");
        } else {
            System.out.println("輸入錯誤回到選單");
        }
    }

    static Scanner sc = new Scanner(System.in);
}