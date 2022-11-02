package pg1.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;


import mysql.com.pj1.model.Candidate;
import mysql.com.pj1.model.CandidateDao;
import mysql.com.pj1.util.ConnectionFactory;
import pg1.ioCSV.ReadCSV;
import pg1.scinput.InputSelect;
import pg1.scinput.InputSystemSelect;
import pg1.scinput.Inputno;

import static pg1.func.Show.show;

public class ElectionSystem {


    public static void main(String[] args) {


        Connection conn = ConnectionFactory.createMYSQLConnection();
        CandidateDao cDao = new CandidateDao(conn);

        while (true) {
            switch (InputSystemSelect.inputSys()) {
//			1.檢視訊息
                case 1 -> show(cDao);
//			2.新增訊息
                case 2 -> {
                    try {
                        List<Candidate> list = ReadCSV.readCSV();
                        cDao.addAllCandidates(list);
                    } catch (IOException e) {
                        System.out.println("err");
                        e.printStackTrace();
                    } catch (SQLException e) {
                        System.out.println("add err");
                        e.printStackTrace();
                    }
                }
//			3.修改訊息
                case 3 -> {
                    try {
                        for (Candidate c:ReadCSV.readCSV()
                             ) {
                            System.out.println(c.toCSV());

                        }
                    } catch (IOException e) {
                        System.out.println("re err");
                        e.printStackTrace();
                    }
                }

//			4.刪除訊息
                case 4 -> System.out.println(4);
//			5.退出訊息管理系統
                case 5 -> {
//                    empList.sort(Comparator.comparing(Candidate::getEmpno));
//                    WriteCSV.writeCSV(empList);
                    System.out.println("儲存檔案");
                }
                case 6 -> {
                    System.out.println("退出管理系統");
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
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

//    public static void del() {
//        // 1.提示輸入empNo
//        int select;
//        int id = inputKey();
//        // 2.搜尋 定義標記
//        int index = 0;
//        // 遍歷比較 並修改
//        for (int i = 0; i < empList.size(); i++) {
//            Candidate delEmp = (Candidate) empList.get(i);
////            if (delEmp.getEmpno() == id) {
////                System.out.println(delEmp.dsecEmp());
////                // 找到了 // 改變標記
////                index = i;
////                break;
////            } else {
////                index = -1;
////            }
//        }
//        // 3.判斷結果 // 判斷標記
//        if (index == -1) {
//            // 沒有找到
//            System.out.println("查無此員工 回到選單");
//        } else {
//            // 找到了是否執行刪除
//            System.out.println("是否刪除資料");
//            System.out.println("1.刪除");
//            System.out.println("2.跳出");
//            select = selectKey();
//            if (select == 1) {
//                empList.remove(index);
//                System.out.println("刪除完畢");
//            } else if (select == 2) {
//                System.out.println("回到選單");
//            } else {
//                System.out.println("輸入錯誤,回到選單");
//            }
//        }
//    }
//
//    public static void upd() {
////		System.out.println("功能未開放");
////		 1.提示輸入編號
//        int id = inputKey();
////		 2.搜尋標記
//        int index = -1;
//        int sel = -1;
////		 遍歷並比較
//        for (int i = 0; i < empList.size(); i++) {
//            Candidate delEmp = (Candidate) empList.get(i);
////            if (delEmp.getEmpno() == id) {
////                System.out.println(delEmp.dsecEmp());
////                // 找到了 // 改變標記
////                index = i;
////            }
//        }
//        if (index != -1) {
//            System.out.println("1.修改");
//            System.out.println("2.取消");
//            sel = selectKey();
//        } else {
////			 沒找到
//            System.out.println("查無此員工編號");
//        }
//        if (sel == 1) {
////			 3.收集其他訊息
//            try {
//                System.out.println("更改姓名為");
//                String name = sc.nextLine();
//                System.out.println("更改薪水為");
//                int salary = sc.nextInt();
//                sc.nextLine();
//                System.out.println("更改獎金為");
//                long bonus = sc.nextLong();
//                sc.nextLine();
//                if (bonus != 0) {
////                    empList.set(index, new Manger(id, name, salary, bonus));
//                } else {
////                    empList.set(index, new Candidate(id, name, salary));
//                }
//                System.out.println("修改完畢");
//            } catch (Exception e) {
//                System.out.println("輸入錯誤");
//            }
//
//        } else {
//            System.out.println("返回主畫面");
//        }
//
//    }
//
//    public static void add() {
//        int id = inputKey();
//        int index = -1;
//        for (int i = 0; i < empList.size(); i++) {
//            Candidate delEmp = (Candidate) empList.get(i);
////            if (delEmp.getEmpno() == id) {
////                index = i;
////                System.out.println(delEmp.dsecEmp());
////                System.out.println("員工編號已使用");
////            }
//        }
//        if (index == -1) {
//            try {
//                System.out.println("請輸入姓名");
//                String name = sc.nextLine();
//                System.out.println("請輸入薪水");
//                int salary = sc.nextInt();
//                sc.nextLine();
//                System.out.println("請輸入獎金");
//                long bonus = sc.nextLong();
//                sc.nextLine();
//                if (bonus > 0) {
////                    empList.add(new Manger(id, name, salary, bonus));
//                    System.out.println("新增完畢");
//                } else {
////                    empList.add(new Candidate(id, name, salary));
//                    System.out.println("新增完畢");
//                }
//            } catch (Exception e) {
//                System.out.println("輸入錯誤");
//            }
//        }
//    }


    static Scanner sc = new Scanner(System.in);
}