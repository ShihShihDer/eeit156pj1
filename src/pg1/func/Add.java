package pg1.func;

import mysql.com.pj1.model.CandidateDao;
import pg1.scinput.InputSelect;
import pg1.scinput.Inputno;

import static pg1.scinput.InputSelect.inputSelect;

public class Add {
    public static void add(CandidateDao cDao) {
        System.out.println("1.新增候選人");
        System.out.println("2.取消新增");
        int i = inputSelect();
        if (i == 1) {
            cDao.addCandidates();
        } else if (i == 2) {
            System.out.println("退出");
        }else {
            System.out.println("輸入錯誤回到選單");
        }


    }
}

