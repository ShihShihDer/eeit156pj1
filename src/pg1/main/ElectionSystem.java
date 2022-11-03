package pg1.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;


import mysql.com.pj1.model.Candidate;
import mysql.com.pj1.model.CandidateDao;
import mysql.com.pj1.util.ConnectionFactory;
import pg1.func.Del;
import pg1.ioCSV.ReadCSV;
import pg1.ioCSV.WriteCSV;
import pg1.scinput.InputSelect;
import pg1.scinput.InputSystemSelect;
import pg1.scinput.Inputno;

import static pg1.func.Add.add;
import static pg1.func.Del.del;
import static pg1.func.Show.show;
import static pg1.func.Upd.upd;
import static pg1.scinput.InputSystemSelect.inputSys;

public class ElectionSystem {


    public static void main(String[] args) {
        Connection conn = ConnectionFactory.createMYSQLConnection();
        CandidateDao cDao = new CandidateDao(conn);
boolean a = true;
        while (a) {
            switch (inputSys()) {
//			1.檢視訊息
                case 1 -> show(cDao);
//			2.新增訊息
                case 2 -> add(cDao);
//			3.修改訊息
                case 3 -> upd(cDao);
//			4.刪除訊息
                case 4 -> del(cDao);
//			5.退出訊息管理系統
//                case 5 -> {}
                case 6 -> {
                    System.out.println("退出管理系統");
                    try {
                        conn.close();
                        a = false;
//                        System.exit(0);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

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