package pg1.func;

import mysql.com.pj1.model.CandidateDao;
import mysql.com.pj1.util.ConnectionFactory;
import pg1.ioCSV.WriteCSV;

import java.sql.Connection;
import java.sql.SQLException;

public class Upd {



    public static void upd(CandidateDao cDao) {
        Connection conn = ConnectionFactory.createMYSQLConnection();
        try {
            int id = cDao.selectCandidatesToUpd();
            if (id != 0) {
                cDao.updCandidates(id);

                WriteCSV.writeCSV(conn);
                conn.close();
            }

        } catch (SQLException e) {
            System.out.println("搜尋錯誤");
            throw new RuntimeException(e);
        }

    }
}
