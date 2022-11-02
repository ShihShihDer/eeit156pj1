package mysql.com.pj1.model;


import pg1.scinput.InputSelect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import static pg1.main.ElectionSystem.selectKey;

public class CandidateDao {
    private Connection conn;
    static Scanner sc = new Scanner(System.in);
    List<Candidate> list = new ArrayList<>();

    public CandidateDao(Connection conn) {
        this.conn = conn;
    }


    public void addAllCandidates(List<Candidate> list) throws SQLException {

//        INSERT INTO `election`.`elections` (`cdID`,`elePlace`,`sDate`,`eleName`,`party`,`remark`,`eleNO`)
//        VALUES (<{cdID: }>,<{elePlace: }>,<{registerData: }>,<{eleName: }>,<{party: }>,<{remark: }>,<{eleNO: }>);
        String sql = "insert into `election`.`elections` (`cdID`,`elePlace`,`sDate`,`eleName`,`party`,`remark`,`eleNO`)  values (?,?,?,?,?,?,?)";

        PreparedStatement preState = conn.prepareStatement(sql);
        int i = 1;
        for (Candidate c : list
        ) {
            preState.setInt(1, i);
            preState.setString(2, c.getEd());
            preState.setDate(3, Date.valueOf(c.getsData()));
            preState.setString(4, c.getName());
            preState.setString(5, c.getParty());
            preState.setString(6, c.getRemark());
            preState.setString(7, null);
            preState.addBatch();
            i++;
        }
        preState.executeBatch();
        System.out.println("資料上傳完成");
        preState.close();
    }

    public List<Candidate> getAllConnection() throws SQLException {
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
            list.add(c);

        }
        rs.close();
        preState.close();
        return list;
    }

    public void selectCandidates() throws SQLException {
        boolean e = true;
        while (e) {
            System.out.println("1.選區");
            System.out.println("2.登記日期");
            System.out.println("3.姓名");
            System.out.println("4.政黨");
            System.out.println("5.選號(未抽籤)");
            System.out.println("6.退出查詢");
            switch (selectKey()) {
                case 1 -> {
                    String sql = "SELECT * FROM `election`.`elections` where `elePlace` like ?";
                    PreparedStatement preState = conn.prepareStatement(sql);
                    System.out.print("請輸入選區:");
                    preState.setString(1, "%" + sc.nextLine() + "%");
                    ResultSet rs = preState.executeQuery();
                    while (rs.next()) {
                        Candidate c = new Candidate();
                        c.setEd(rs.getString("elePlace"));
                        c.setsData(rs.getDate("sDate").toLocalDate());
                        c.setName(rs.getString("eleName"));
                        c.setParty(rs.getString("party"));
                        c.setRemark(rs.getString("remark"));
                        System.out.println(c.Cdlist());
                    }
                    rs.close();
                    preState.close();
                    System.out.println("===========================");
                    System.out.println("展示完畢");
                }
                case 2 -> {
                    String sql = "SELECT * FROM `election`.`elections` where `sDate` = ?";
                    PreparedStatement preState = conn.prepareStatement(sql);
                    boolean b = true;
                    String m = null;
                    String d = null;
                    while (b) {
                        System.out.print("月份:");
                        m = sc.nextLine();
                        boolean s = Pattern.matches("^(0?[1-9]|1[0-2])$", m);
                        b = !s;
                    }
                    if (m.equals("2")) {
                        boolean c = true;
                        while (c) {
                            System.out.print("日期:");
                            d = sc.nextLine();
                            boolean s = Pattern.matches("^((0?[1-9])|((1)[0-9])|((2)[0-8]))$", d);
                            c = !s;
                        }
                    } else if (m.equals("01") |m.equals("03") |m.equals("05") |m.equals("07") |m.equals("08") |m.equals("1") | m.equals("3") | m.equals("5") | m.equals("7") | m.equals("8") | m.equals("10") | m.equals("12")) {
                        boolean c = true;
                        while (c) {
                            System.out.print("日期:");
                            d = sc.nextLine();
                            boolean s = Pattern.matches("^((0?[1-9])|((1)[0-9])|((2)[0-9])|30|31)$", d);
                            c = !s;
                        }
                    } else {
                        boolean c = true;
                        while (c) {
                            System.out.print("日期:");
                            d = sc.nextLine();
                            boolean s = Pattern.matches("^((0?[1-9])|((1)[0-9])|((2)[0-9])|30)$", d);
                            c = !s;
                        }


                    }

                    String sDate = "2022-" + m + "-" + d;

                    preState.setDate(1,Date.valueOf(sDate));
                    ResultSet rs = preState.executeQuery();
                    while (rs.next()) {
                        Candidate c = new Candidate();
                        c.setEd(rs.getString("elePlace"));
                        c.setsData(rs.getDate("sDate").toLocalDate());
                        c.setName(rs.getString("eleName"));
                        c.setParty(rs.getString("party"));
                        c.setRemark(rs.getString("remark"));
                        System.out.println(c.Cdlist());
                    }
                    System.out.println("===========================");
                    System.out.println("展示完畢");
                    rs.close();
                    preState.close();
                }
                case 3 -> {
                    String sql = "SELECT * FROM `election`.`elections` where `eleName` like ?";
                    PreparedStatement preState = conn.prepareStatement(sql);
                    System.out.print("請輸入姓名:");
                    preState.setString(1, "%" + sc.nextLine() + "%");
                    ResultSet rs = preState.executeQuery();
                    while (rs.next()) {
                        Candidate c = new Candidate();
                        c.setEd(rs.getString("elePlace"));
                        c.setsData(rs.getDate("sDate").toLocalDate());
                        c.setName(rs.getString("eleName"));
                        c.setParty(rs.getString("party"));
                        c.setRemark(rs.getString("remark"));
                        System.out.println(c.Cdlist());
                    }
                    System.out.println("===========================");
                    System.out.println("展示完畢");
                    rs.close();
                    preState.close();
                }
                case 4 -> {
                    String sql = "SELECT * FROM `election`.`elections` where `party` like ?";
                    PreparedStatement preState = conn.prepareStatement(sql);
                    System.out.print("請輸入政黨:");
                    preState.setString(1, "%" + sc.nextLine() + "%");
                    ResultSet rs = preState.executeQuery();
                    while (rs.next()) {
                        Candidate c = new Candidate();
                        c.setEd(rs.getString("elePlace"));
                        c.setsData(rs.getDate("sDate").toLocalDate());
                        c.setName(rs.getString("eleName"));
                        c.setParty(rs.getString("party"));
                        c.setRemark(rs.getString("remark"));
                        System.out.println(c.Cdlist());
                    }
                    System.out.println("===========================");
                    System.out.println("展示完畢");
                    rs.close();
                    preState.close();
                }
                case 5 -> System.out.println("未開放");
                case 6 -> {
                    System.out.println("退出管理系統");
                    e = false;
                }
                default -> System.out.println("功能錯誤");
            }
        }
    }
}
