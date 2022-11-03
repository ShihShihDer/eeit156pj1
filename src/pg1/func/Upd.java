package pg1.func;

import mysql.com.pj1.model.CandidateDao;
import pg1.ioCSV.ReadCSV;
import pg1.ioCSV.WriteCSV;

import java.sql.SQLException;

public class Upd {
    public static void upd(CandidateDao cDao) {

        try {
            int id = cDao.selectCandidatesToUpd();
            if (id!=0){
                cDao.updCandidates(id);
                WriteCSV.writeCSV(cDao.getAllCandidates());
            }

        } catch (SQLException e) {
            System.out.println("搜尋錯誤");
            throw new RuntimeException(e);
        }

    }
}
