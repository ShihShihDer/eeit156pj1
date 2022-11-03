package pg1.ioCSV;

import mysql.com.pj1.model.Candidate;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class WriteCSV {

    public static void writeCSV(Connection conn) {
        String filePar = "D:\\javaSSD\\project1\\electionDataBase";
        File myPath = new File(filePar);
        if (!myPath.exists()) {
            boolean m = myPath.mkdir();
            if (!m) {
                System.out.println("Path error");
            }
        }
        File file = new File("D:\\javaSSD\\project1\\electionDataBase\\election_title.csv");
        try (
                FileOutputStream fos = new FileOutputStream(file);
                OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
                FileWriter fw = new FileWriter(file);
        ) {
            fw.write("");
            osw.write("選舉區,登記日期,姓名,推薦之政黨,備註\n");
            String sql = "select * from `election`.`elections`";
            PreparedStatement preState = conn.prepareStatement(sql);
            ResultSet rs = preState.executeQuery();
            while (rs.next()) {
                Candidate c = new Candidate();
                c.setEd(rs.getString("elePlace"));
                c.setsData(rs.getDate("sDate").toLocalDate());
                c.setName(rs.getString("eleName"));
                c.setParty(rs.getString("party"));
                c.setRemark(rs.getString("remark"));
                osw.write(c.toCSV());
                osw.write("\n");
            }
            rs.close();
            preState.close();
            System.out.println("已更新本地資料");


        } catch (IOException | SQLException e) {
            System.out.println("本地資料更新失敗");
            e.printStackTrace();
        }

    }


}
