package mysql.com.pj1.model;

import java.lang.reflect.Member;
import java.sql.Connection;

public class CandidateDao {
    private Connection conn;
    public CandidateDao(Connection conn){
        this.conn = conn;
    }

    public void  addMember(Member m){

    }
}
