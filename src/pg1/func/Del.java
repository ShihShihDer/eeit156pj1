package pg1.func;


import mysql.com.pj1.model.CandidateDao;

import java.sql.SQLException;

import static pg1.main.ElectionSystem.*;

public class Del {
    public static void del(CandidateDao cDao){
        System.out.println("1.分類刪除");
        System.out.println("2.退出");
        int s = selectKey();
        if (s==1){
            cDao.delCandidates();
        }else if (s==2){
            System.out.println("退出");
        }else {
            System.out.println("輸入錯誤回到選單");
        }

    }


}