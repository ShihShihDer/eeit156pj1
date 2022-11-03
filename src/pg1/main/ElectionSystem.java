package pg1.main;

import mysql.com.pj1.model.CandidateDao;
import mysql.com.pj1.util.ConnectionFactory;
import pg1.ioCSV.WriteCSV;
import pg1.scinput.InputSelect;

import java.sql.Connection;
import java.sql.SQLException;

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
//          5.上傳備份檔
                case 5 -> {
                    cDao.addAllCandidates();
                    System.out.println("批次作業完成");
                }
//			6.退出訊息管理系統
                case 6 -> {
                    System.out.println("退出管理系統");
                    try {
                        conn.close();
                        a = false;
//                        System.exit(0);
                    }
                    catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                }
//                下載資料庫
                case 7 -> WriteCSV.writeCSV(conn);
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