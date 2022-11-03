package pg1.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;


import mysql.com.pj1.model.Candidate;
import mysql.com.pj1.model.CandidateDao;
import mysql.com.pj1.util.ConnectionFactory;
import pg1.func.Del;
import pg1.ioCSV.ReadCSV;
import pg1.scinput.InputSelect;
import pg1.scinput.InputSystemSelect;
import pg1.scinput.Inputno;

import static pg1.func.Del.del;
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
                case 2 -> del(cDao);
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
//    public static int inputKey() {
//        return Inputno.inputno();
//    }

    public static int selectKey() {
        return InputSelect.inputSelect();
    }





}