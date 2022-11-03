package pg1.func;

import mysql.com.pj1.model.CandidateDao;

import java.sql.SQLException;

import static pg1.main.ElectionSystem.*;

public class Show {
    public static void show(CandidateDao cDao) {

        System.out.println("1.分類查詢");
        System.out.println("2.全部顯示");
        int s = selectKey();
        if (s == 1) {
            try {
                cDao.selectCandidates();
            } catch (SQLException e) {
                System.out.println("搜尋錯誤");
                e.printStackTrace();
            }
        } else if (s == 2) {
            try {
                cDao.getAllCandidates();
            } catch (SQLException e) {
                System.out.println("資料擷取失敗");
                e.printStackTrace();
            }
            System.out.println("===========================");
            System.out.println("展示完畢");
        } else {
            System.out.println("輸入錯誤回到選單");
        }
    }
}
