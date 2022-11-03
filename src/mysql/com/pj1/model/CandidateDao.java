package mysql.com.pj1.model;


import com.healthmarketscience.jackcess.Cursor;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import static pg1.ioCSV.WriteCSV.writeCSV;
import static pg1.scinput.InputSelect.inputSelect;
import static pg1.scinput.Inputno.inputCno;
import static pg1.scinput.Inputno.inputid;

public class CandidateDao {
    private final Connection conn;
    static Scanner sc = new Scanner(System.in);
    List<Candidate> list = new ArrayList<>();

    public CandidateDao(Connection conn) {
        this.conn = conn;
    }


//    public void addAllCandidates(List<Candidate> list)  {
//
////        INSERT INTO `election`.`elections` (`cdID`,`elePlace`,`sDate`,`eleName`,`party`,`remark`,`eleNO`)
////        VALUES (<{cdID: }>,<{elePlace: }>,<{registerData: }>,<{eleName: }>,<{party: }>,<{remark: }>,<{eleNO: }>);
//        String sql = "insert into `election`.`elections` (`cdID`,`elePlace`,`sDate`,`eleName`,`party`,`remark`,`eleNO`)  values (?,?,?,?,?,?,?)";
//
//        PreparedStatement preState = null;
//        try {
//            preState = conn.prepareStatement(sql);
//        int i = 1;
//        for (Candidate c : list
//        ) {
//            preState.setInt(1, i);
//            preState.setString(2, c.getEd());
//            preState.setDate(3, Date.valueOf(c.getsData()));
//            preState.setString(4, c.getName());
//            preState.setString(5, c.getParty());
//            preState.setString(6, c.getRemark());
//            preState.setString(7, null);
//            preState.addBatch();
//            i++;
//        }
//        preState.executeBatch();
//        System.out.println("資料上傳完成");
//        preState.close();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public List<Candidate> getAllCandidates() throws SQLException {
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
            switch (inputid()) {
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
                    System.out.println("===========================");
                    System.out.println("展示完畢");
                    rs.close();
                    preState.close();
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
                    } else if (m.equals("01") | m.equals("03") | m.equals("05") | m.equals("07") | m.equals("08") | m.equals("1") | m.equals("3") | m.equals("5") | m.equals("7") | m.equals("8") | m.equals("10") | m.equals("12")) {
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

                    preState.setDate(1, Date.valueOf(sDate));
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
                    System.out.println("查無資料");
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
                    System.out.println("退出查詢");
                    e = false;
                }
                default -> System.out.println("功能錯誤");
            }
        }
    }

    public void delCandidates() {
        try {
            conn.setAutoCommit(false);
            boolean e = true;
            while (e) {
                System.out.println("1.選區");
                System.out.println("2.登記日期");
                System.out.println("3.姓名");
                System.out.println("4.政黨");
                System.out.println("5.退出");
                switch (inputid()) {
                    case 1 -> {
                        String sql = "SELECT * FROM `election`.`elections` where `elePlace` like ?";
                        PreparedStatement preState = conn.prepareStatement(sql);
                        System.out.print("請輸入選區:");
                        String where = sc.nextLine();
                        preState.setString(1, "%" + where + "%");
                        ResultSet rs = preState.executeQuery();
                        while (rs.next()) {
                            int cdID = rs.getInt("cdID");
                            String elePlace = rs.getString("elePlace");
                            LocalDate sDate = rs.getDate("sDate").toLocalDate();
                            String eleName = rs.getString("eleName");
                            String party = rs.getString("party");
                            System.out.format("編號: %d 選區: %s 登記日期 : %s 姓名 : %s 政黨 : %s\n", cdID, elePlace, sDate, eleName, party);
                        }
                        System.out.println("===========================");
                        System.out.println("展示完畢");
                        System.out.println("1.以編號刪除");
                        System.out.println("2.全部刪除");
                        System.out.println("3.取消刪除");
                        rs.close();
                        preState.close();
                        switch (inputid()) {
                            case 1 -> {
                                System.out.print("輸入編號:");
                                int id = inputid();
                                String sql3 = "SELECT * FROM election.elections where cdID = ?";
                                PreparedStatement preState3 = conn.prepareStatement(sql3);
                                preState3.setInt(1, id);
                                ResultSet rs3 = preState3.executeQuery();
                                while (rs3.next()) {
                                    int cdID = rs3.getInt("cdID");
                                    String elePlace = rs3.getString("elePlace");
                                    LocalDate sDate = rs3.getDate("sDate").toLocalDate();
                                    String eleName = rs3.getString("eleName");
                                    String party = rs3.getString("party");
                                    System.out.format("編號: %d 選區: %s 登記日期 : %s 姓名 : %s 政黨 : %s\n", cdID, elePlace, sDate, eleName, party);

                                }
                                rs3.close();
                                preState3.close();
                                System.out.println("1.確認刪除");
                                System.out.println("2.取消");
                                switch (inputid()) {
                                    case 1 -> {
                                        String sqlDel = "DELETE FROM election.elections WHERE cdID = ?";
                                        PreparedStatement preState2 = conn.prepareStatement(sqlDel);
                                        preState2.setInt(1, id);
                                        System.out.println("已刪除" + preState2.executeUpdate() + "筆資料");
                                        preState2.close();

                                    }
                                    case 2 -> {
                                        System.out.println("退出");

                                    }
                                    default -> {
                                        System.out.println("輸入錯誤 退出功能！");

                                    }
                                }

                            }
                            case 2 -> {
                                String sqlDel = "DELETE FROM election.elections WHERE `elePlace` like ?";
                                PreparedStatement preState2 = conn.prepareStatement(sqlDel);
                                preState2.setString(1, where);
                                System.out.println("已刪除" + preState2.executeUpdate() + "筆資料");
                                preState2.close();

                            }
                            case 3 -> {
                                System.out.println("退出");

                            }
                            default -> {
                                System.out.println("輸入錯誤 退出功能！");

                            }
                        }

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
                        } else if (m.equals("01") | m.equals("03") | m.equals("05") | m.equals("07") | m.equals("08") | m.equals("1") | m.equals("3") | m.equals("5") | m.equals("7") | m.equals("8") | m.equals("10") | m.equals("12")) {
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

                        String scdata = "2022-" + m + "-" + d;

                        preState.setDate(1, Date.valueOf(scdata));
                        ResultSet rs = preState.executeQuery();
                        while (rs.next()) {
                            int cdID = rs.getInt("cdID");
                            String elePlace = rs.getString("elePlace");
                            LocalDate sDate = rs.getDate("sDate").toLocalDate();
                            String eleName = rs.getString("eleName");
                            String party = rs.getString("party");
                            System.out.format("編號: %d 選區: %s 登記日期 : %s 姓名 : %s 政黨 : %s\n", cdID, elePlace, sDate, eleName, party);
                        }
                        System.out.println("===========================");
                        System.out.println("展示完畢");
                        System.out.println("1.以編號刪除");
                        System.out.println("2.全部刪除");
                        System.out.println("3.取消刪除");
                        rs.close();
                        preState.close();
                        switch (inputid()) {
                            case 1 -> {
                                System.out.print("輸入編號:");
                                int id = inputid();
                                String sql3 = "SELECT * FROM election.elections where cdID = ?";
                                PreparedStatement preState3 = conn.prepareStatement(sql3);
                                preState3.setInt(1, id);
                                ResultSet rs3 = preState3.executeQuery();
                                while (rs3.next()) {
                                    int cdID = rs3.getInt("cdID");
                                    String elePlace = rs3.getString("elePlace");
                                    LocalDate sDate = rs3.getDate("sDate").toLocalDate();
                                    String eleName = rs3.getString("eleName");
                                    String party = rs3.getString("party");
                                    System.out.format("編號: %d 選區: %s 登記日期 : %s 姓名 : %s 政黨 : %s\n", cdID, elePlace, sDate, eleName, party);
                                }
                                rs3.close();
                                preState3.close();
                                System.out.println("1.確認刪除");
                                System.out.println("2.取消");
                                switch (inputid()) {
                                    case 1 -> {
                                        String sqlDel = "DELETE FROM election.elections WHERE cdID = ?";
                                        PreparedStatement preState2 = conn.prepareStatement(sqlDel);
                                        preState2.setInt(1, id);
                                        System.out.println("已刪除" + preState2.executeUpdate() + "筆資料");
                                        preState2.close();

                                    }
                                    case 2 -> {
                                        System.out.println("退出");

                                    }
                                    default -> {
                                        System.out.println("輸入錯誤 退出功能！");

                                    }
                                }
                            }

                            case 2 -> {
                                String sqlDel = "DELETE FROM election.elections WHERE `sDate` = ?";
                                PreparedStatement preState2 = conn.prepareStatement(sqlDel);
                                preState2.setString(1, scdata);
                                System.out.println("已刪除" + preState2.executeUpdate() + "筆資料");
                                preState2.close();

                            }
                            case 3 -> {
                                System.out.println("退出");

                            }
                            default -> {
                                System.out.println("輸入錯誤 退出功能！");

                            }
                        }
                    }
                    case 3 -> {
                        String sql = "SELECT * FROM `election`.`elections` where `eleName` like ?";
                        PreparedStatement preState = conn.prepareStatement(sql);
                        System.out.print("請輸入姓名:");
                        String where = sc.nextLine();
                        preState.setString(1, "%" + where + "%");
                        ResultSet rs = preState.executeQuery();
                        while (rs.next()) {
                            int cdID = rs.getInt("cdID");
                            String elePlace = rs.getString("elePlace");
                            LocalDate sDate = rs.getDate("sDate").toLocalDate();
                            String eleName = rs.getString("eleName");
                            String party = rs.getString("party");
                            System.out.format("編號: %d 選區: %s 登記日期 : %s 姓名 : %s 政黨 : %s\n", cdID, elePlace, sDate, eleName, party);

                        }
                        System.out.println("===========================");
                        System.out.println("展示完畢");
                        System.out.println("1.以編號刪除");
                        System.out.println("2.全部刪除");
                        System.out.println("3.取消刪除");
                        rs.close();
                        preState.close();
                        switch (inputid()) {
                            case 1 -> {
                                System.out.print("輸入編號:");
                                int id = inputid();
                                String sql3 = "SELECT * FROM election.elections where cdID = ?";
                                PreparedStatement preState3 = conn.prepareStatement(sql3);
                                preState3.setInt(1, id);
                                ResultSet rs3 = preState3.executeQuery();
                                while (rs3.next()) {
                                    int cdID = rs3.getInt("cdID");
                                    String elePlace = rs3.getString("elePlace");
                                    LocalDate sDate = rs3.getDate("sDate").toLocalDate();
                                    String eleName = rs3.getString("eleName");
                                    String party = rs3.getString("party");
                                    System.out.format("編號: %d 選區: %s 登記日期 : %s 姓名 : %s 政黨 : %s\n", cdID, elePlace, sDate, eleName, party);
                                }
                                rs3.close();
                                preState3.close();
                                System.out.println("1.確認刪除");
                                System.out.println("2.取消");
                                switch (inputid()) {
                                    case 1 -> {
                                        String sqlDel = "DELETE FROM election.elections WHERE cdID = ?";
                                        PreparedStatement preState2 = conn.prepareStatement(sqlDel);
                                        preState2.setInt(1, id);
                                        System.out.println("已刪除" + preState2.executeUpdate() + "筆資料");
                                        preState2.close();

                                    }
                                    case 2 -> {
                                        System.out.println("退出");

                                    }
                                    default -> {
                                        System.out.println("輸入錯誤 退出功能！");

                                    }

                                }
                            }
                            case 2 -> {

                                String sqlDel = "DELETE FROM election.elections WHERE `eleName` like ?";
                                PreparedStatement preState2 = conn.prepareStatement(sqlDel);
                                preState2.setString(1, where);
                                System.out.println("已刪除" + preState2.executeUpdate() + "筆資料");
                                preState2.close();

                            }
                            case 3 -> {
                                System.out.println("退出");

                            }
                            default -> {
                                System.out.println("輸入錯誤 退出功能！");

                            }
                        }
                    }
                    case 4 -> {
                        String sql = "SELECT * FROM `election`.`elections` where `party` like ?";
                        PreparedStatement preState = conn.prepareStatement(sql);
                        System.out.print("請輸入政黨:");
                        String where = sc.nextLine();
                        preState.setString(1, "%" + where + "%");
                        ResultSet rs = preState.executeQuery();
                        while (rs.next()) {
                            int cdID = rs.getInt("cdID");
                            String elePlace = rs.getString("elePlace");
                            LocalDate sDate = rs.getDate("sDate").toLocalDate();
                            String eleName = rs.getString("eleName");
                            String party = rs.getString("party");
                            System.out.format("編號: %d 選區: %s 登記日期 : %s 姓名 : %s 政黨 : %s\n", cdID, elePlace, sDate, eleName, party);

                        }
                        System.out.println("===========================");
                        System.out.println("展示完畢");
                        System.out.println("1.以編號刪除");
                        System.out.println("2.全部刪除");
                        System.out.println("3.取消刪除");
                        rs.close();
                        preState.close();
                        switch (inputid()) {
                            case 1 -> {
                                System.out.print("輸入編號:");
                                int id = inputid();
                                String sql3 = "SELECT * FROM election.elections where cdID = ?";
                                PreparedStatement preState3 = conn.prepareStatement(sql3);
                                preState3.setInt(1, id);
                                ResultSet rs3 = preState3.executeQuery();
                                while (rs3.next()) {
                                    int cdID = rs3.getInt("cdID");
                                    String elePlace = rs3.getString("elePlace");
                                    LocalDate sDate = rs3.getDate("sDate").toLocalDate();
                                    String eleName = rs3.getString("eleName");
                                    String party = rs3.getString("party");
                                    System.out.format("編號: %d 選區: %s 登記日期 : %s 姓名 : %s 政黨 : %s\n", cdID, elePlace, sDate, eleName, party);
                                }
                                rs3.close();
                                preState3.close();
                                System.out.println("1.確認刪除");
                                System.out.println("2.取消");
                                switch (inputid()) {
                                    case 1 -> {
                                        String sqlDel = "DELETE FROM election.elections WHERE cdID = ?";
                                        PreparedStatement preState2 = conn.prepareStatement(sqlDel);
                                        preState2.setInt(1, id);
                                        System.out.println("已刪除" + preState2.executeUpdate() + "筆資料");
                                        preState2.close();

                                    }
                                    case 2 -> {
                                        System.out.println("退出");

                                    }
                                    default -> {
                                        System.out.println("輸入錯誤 退出功能！");

                                    }
                                }
                            }
                            case 2 -> {
                                String sqlDel = "DELETE FROM election.elections WHERE ``party` like ?";
                                PreparedStatement preState2 = conn.prepareStatement(sqlDel);
                                preState2.setString(1, where);
                                System.out.println("已刪除" + preState2.executeUpdate() + "筆資料");
                                preState2.close();

                            }
                            case 3 -> {
                                System.out.println("退出");

                            }
                            default -> {
                                System.out.println("輸入錯誤 退出功能！");

                            }
                        }
                    }
                    case 5 -> {
                        System.out.println("退出刪除");
                        e = false;
                    }
                    default -> System.out.println("功能錯誤");
                }
            }
            conn.commit();
            System.out.println("資料更新成功");
            conn.setAutoCommit(true);
            writeCSV(getAllCandidates());
            ;
        } catch (SQLException e) {
            System.out.println("資料更新失敗");
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void addCandidates() {
        try {
            conn.setAutoCommit(false);
            System.out.print("請輸入選區:");
            String place = sc.nextLine();
            System.out.print("請輸入姓名:");
            String name = sc.nextLine();
            System.out.println("請輸入登記日期:");
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
            } else if (m.equals("01") | m.equals("03") | m.equals("05") | m.equals("07") | m.equals("08") | m.equals("1") | m.equals("3") | m.equals("5") | m.equals("7") | m.equals("8") | m.equals("10") | m.equals("12")) {
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

            String date = "2022-" + String.format("%02d", Integer.parseInt(m)) + "-" + String.format("%02d", Integer.parseInt(d));

            Date sDate = Date.valueOf(date);
            System.out.print("請輸入政黨:");
            String party = sc.nextLine();
            System.out.print("請輸入備註:");
            String remark = sc.nextLine();

            String sqlmn = "SELECT * FROM `election`.`elections` where `eleName` = ?";
            PreparedStatement preState1 = conn.prepareStatement(sqlmn);
            preState1.setString(1, name);
            ResultSet rs1 = preState1.executeQuery();
            boolean mName;
            mName = rs1.next();
            rs1.close();
            preState1.close();
            String sqlmpc = "SELECT * FROM `election`.`elections` where `elePlace` = ?";
            PreparedStatement preState2 = conn.prepareStatement(sqlmpc);
            preState2.setString(1, place);
            ResultSet rs2 = preState2.executeQuery();
            boolean mPlace;
            mPlace = rs2.next();
            rs2.close();
            preState2.close();
            String sqlmpt = "SELECT * FROM `election`.`elections` where `party` = ?";
            PreparedStatement preState3 = conn.prepareStatement(sqlmpt);
            preState3.setString(1, party);
            ResultSet rs3 = preState3.executeQuery();
            boolean mParty;
            mParty = rs3.next();
            rs3.close();
            preState3.close();
            Candidate addc = new Candidate();
            addc.setEd(place);
            addc.setsData(LocalDate.parse(date));
            addc.setName(name);
            addc.setParty(party);
            addc.setRemark(remark);
            System.out.println("輸入的資料為");
            System.out.println(addc.Cdlist());
            int yn = 0;
            if (mName && mPlace && mParty) {
                System.out.println("選區:" + place + "政黨:" + party + "的候選人可能重複！");
                String s = "SELECT * FROM `election`.`elections` where `elePlace` = ? and `party` = ? and `eleName` = ?";
                PreparedStatement preState = conn.prepareStatement(s);
                preState.setString(1, place);
                preState.setString(2, party);
                preState.setString(3, name);
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
                System.out.println("1.繼續新增");
                System.out.println("2.取消新增");
            } else if (mName && mParty) {
                System.out.println("政黨:" + party + "的候選人可能重複！");
                String s = "SELECT * FROM `election`.`elections` where `party` = ? and `eleName` = ?";
                PreparedStatement preState = conn.prepareStatement(s);
                preState.setString(1, party);
                preState.setString(2, name);
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
                System.out.println("1.繼續新增");
                System.out.println("2.取消新增");
            } else if (mName && mPlace) {
                System.out.println("選區:" + place + "的候選人可能重複！");
                String s = "SELECT * FROM `election`.`elections` where `elePlace` = ? and `eleName` = ?";
                PreparedStatement preState = conn.prepareStatement(s);
                preState.setString(1, place);
                preState.setString(2, name);
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
                System.out.println("1.繼續新增");
                System.out.println("2.取消新增");

            } else {
                System.out.println("資料無重複");
                System.out.println("1.繼續新增");
                System.out.println("2.取消新增");
            }
            boolean bl = true;
            yn = inputSelect();
            while (bl) {
                if (yn == 1) {
                    String addsql = "INSERT INTO `election`.`elections`(`elePlace`,`sDate`,`eleName`,`party`,`remark`)VALUES(?,?,?,?,?)";
                    PreparedStatement preState = conn.prepareStatement(addsql);
                    preState.setString(1, place);
                    preState.setDate(2, sDate);
                    preState.setString(3, name);
                    preState.setString(4, party);
                    preState.setString(5, remark);
                    int i = preState.executeUpdate();
                    System.out.println("新增" + i + "筆資料");
                    preState.close();
                    conn.commit();
                    bl = false;

                } else if (yn == 2) {
                    System.out.println("退出");
                    bl = false;
                } else {
                    System.out.println("輸入錯誤");
                    System.out.println(addc.Cdlist());
                    System.out.println("是否繼續新增");

                }
            }
            conn.setAutoCommit(true);
            System.out.println("資料更新成功");
            writeCSV(getAllCandidates());
        } catch (SQLException e) {
            System.out.println("資料新增錯誤");
            throw new RuntimeException(e);
        }
    }

    public int selectCandidatesToUpd() throws SQLException {
        int id = 0;
        boolean e = true;
        while (e) {
            System.out.println("1.選區");
            System.out.println("2.登記日期");
            System.out.println("3.姓名");
            System.out.println("4.政黨");
            System.out.println("0.退出查詢");
            switch (inputid()) {
                case 1 -> {
                    String sql = "SELECT * FROM `election`.`elections` where `elePlace` like ?";
                    PreparedStatement preState = conn.prepareStatement(sql);
                    System.out.print("請輸入選區:");
                    preState.setString(1, "%" + sc.nextLine() + "%");
                    ResultSet rs = preState.executeQuery();
                    while (rs.next()) {
                        int cdID = rs.getInt("cdID");
                        String elePlace = rs.getString("elePlace");
                        LocalDate sDate = rs.getDate("sDate").toLocalDate();
                        String eleName = rs.getString("eleName");
                        String party = rs.getString("party");
                        System.out.format("編號: %d 選區: %s 登記日期 : %s 姓名 : %s 政黨 : %s\n", cdID, elePlace, sDate, eleName, party);
                    }
                    System.out.println("===========================");
                    rs.close();
                    preState.close();
                    System.out.print("請輸入欲修改之編號:");
                    id = inputid();
                    String sql3 = "SELECT * FROM election.elections where cdID = ?";
                    PreparedStatement preState3 = conn.prepareStatement(sql3);
                    preState3.setInt(1, id);
                    ResultSet rs3 = preState3.executeQuery();
                    while (rs3.next()) {
                        int cdID = rs3.getInt("cdID");
                        String elePlace = rs3.getString("elePlace");
                        LocalDate sDate = rs3.getDate("sDate").toLocalDate();
                        String eleName = rs3.getString("eleName");
                        String party = rs3.getString("party");
                        System.out.format("編號: %d 選區: %s 登記日期 : %s 姓名 : %s 政黨 : %s\n", cdID, elePlace, sDate, eleName, party);

                    }
                    System.out.println("===========================");
                    rs3.close();
                    preState3.close();
                    System.out.println("1.確認修改");
                    System.out.println("2.取消");
                    switch (inputid()) {
                        case 1 -> {
                            return id;

                        }
                        case 2 -> {
                            System.out.println("退出");
                            return 0;
                        }
                        default -> {
                            System.out.println("輸入錯誤 退出功能！");
                            return 0;
                        }
                    }
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
                    } else if (m.equals("01") | m.equals("03") | m.equals("05") | m.equals("07") | m.equals("08") | m.equals("1") | m.equals("3") | m.equals("5") | m.equals("7") | m.equals("8") | m.equals("10") | m.equals("12")) {
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
                    String date = "2022-" + String.format("%02d", Integer.parseInt(m)) + "-" + String.format("%02d", Integer.parseInt(d));

                    preState.setDate(1, Date.valueOf(date));
                    ResultSet rs = preState.executeQuery();
                    while (rs.next()) {
                        int cdID = rs.getInt("cdID");
                        String elePlace = rs.getString("elePlace");
                        LocalDate sDate = rs.getDate("sDate").toLocalDate();
                        String eleName = rs.getString("eleName");
                        String party = rs.getString("party");
                        System.out.format("編號: %d 選區: %s 登記日期 : %s 姓名 : %s 政黨 : %s\n", cdID, elePlace, sDate, eleName, party);
                    }
                    System.out.println("===========================");
                    rs.close();
                    preState.close();
                    System.out.print("請輸入欲修改之編號:");
                    id = inputid();
                    String sql3 = "SELECT * FROM election.elections where cdID = ?";
                    PreparedStatement preState3 = conn.prepareStatement(sql3);
                    preState3.setInt(1, id);
                    ResultSet rs3 = preState3.executeQuery();
                    while (rs3.next()) {
                        int cdID = rs3.getInt("cdID");
                        String elePlace = rs3.getString("elePlace");
                        LocalDate sDate = rs3.getDate("sDate").toLocalDate();
                        String eleName = rs3.getString("eleName");
                        String party = rs3.getString("party");
                        System.out.format("編號: %d 選區: %s 登記日期 : %s 姓名 : %s 政黨 : %s\n", cdID, elePlace, sDate, eleName, party);

                    }
                    System.out.println("===========================");
                    rs3.close();
                    preState3.close();
                    System.out.println("1.確認修改");
                    System.out.println("2.取消");
                    switch (inputid()) {
                        case 1 -> {
                            return id;

                        }
                        case 2 -> {
                            System.out.println("退出");
                            return 0;
                        }
                        default -> {
                            System.out.println("輸入錯誤 退出功能！");
                            return 0;
                        }
                    }
                }
                case 3 -> {
                    String sql = "SELECT * FROM `election`.`elections` where `eleName` like ?";
                    PreparedStatement preState = conn.prepareStatement(sql);
                    System.out.print("請輸入姓名:");
                    preState.setString(1, "%" + sc.nextLine() + "%");
                    ResultSet rs = preState.executeQuery();
                    while (rs.next()) {
                        int cdID = rs.getInt("cdID");
                        String elePlace = rs.getString("elePlace");
                        LocalDate sDate = rs.getDate("sDate").toLocalDate();
                        String eleName = rs.getString("eleName");
                        String party = rs.getString("party");
                        System.out.format("編號: %d 選區: %s 登記日期 : %s 姓名 : %s 政黨 : %s\n", cdID, elePlace, sDate, eleName, party);
                    }
                    System.out.println("===========================");
                    rs.close();
                    preState.close();
                    System.out.print("請輸入欲修改之編號:");
                    id = inputid();
                    String sql3 = "SELECT * FROM election.elections where cdID = ?";
                    PreparedStatement preState3 = conn.prepareStatement(sql3);
                    preState3.setInt(1, id);
                    ResultSet rs3 = preState3.executeQuery();
                    while (rs3.next()) {
                        int cdID = rs3.getInt("cdID");
                        String elePlace = rs3.getString("elePlace");
                        LocalDate sDate = rs3.getDate("sDate").toLocalDate();
                        String eleName = rs3.getString("eleName");
                        String party = rs3.getString("party");
                        System.out.format("編號: %d 選區: %s 登記日期 : %s 姓名 : %s 政黨 : %s\n", cdID, elePlace, sDate, eleName, party);

                    }
                    System.out.println("===========================");
                    rs3.close();
                    preState3.close();
                    System.out.println("1.確認修改");
                    System.out.println("2.取消");
                    switch (inputid()) {
                        case 1 -> {
                            return id;

                        }
                        case 2 -> {
                            System.out.println("退出");
                            return 0;
                        }
                        default -> {
                            System.out.println("輸入錯誤 退出功能！");
                            return 0;
                        }
                    }
                }
                case 4 -> {
                    String sql = "SELECT * FROM `election`.`elections` where `party` like ?";
                    PreparedStatement preState = conn.prepareStatement(sql);
                    System.out.print("請輸入政黨:");
                    preState.setString(1, "%" + sc.nextLine() + "%");
                    ResultSet rs = preState.executeQuery();
                    while (rs.next()) {
                        int cdID = rs.getInt("cdID");
                        String elePlace = rs.getString("elePlace");
                        LocalDate sDate = rs.getDate("sDate").toLocalDate();
                        String eleName = rs.getString("eleName");
                        String party = rs.getString("party");
                        System.out.format("編號: %d 選區: %s 登記日期 : %s 姓名 : %s 政黨 : %s\n", cdID, elePlace, sDate, eleName, party);
                    }
                    System.out.println("===========================");
                    rs.close();
                    preState.close();
                    System.out.print("請輸入欲修改之編號:");
                    id = inputid();
                    String sql3 = "SELECT * FROM election.elections where cdID = ?";
                    PreparedStatement preState3 = conn.prepareStatement(sql3);
                    preState3.setInt(1, id);
                    ResultSet rs3 = preState3.executeQuery();
                    while (rs3.next()) {
                        int cdID = rs3.getInt("cdID");
                        String elePlace = rs3.getString("elePlace");
                        LocalDate sDate = rs3.getDate("sDate").toLocalDate();
                        String eleName = rs3.getString("eleName");
                        String party = rs3.getString("party");
                        System.out.format("編號: %d 選區: %s 登記日期 : %s 姓名 : %s 政黨 : %s\n", cdID, elePlace, sDate, eleName, party);

                    }
                    System.out.println("===========================");
                    rs3.close();
                    preState3.close();
                    System.out.println("1.確認修改");
                    System.out.println("2.取消");
                    switch (inputid()) {
                        case 1 -> {
                            return id;

                        }
                        case 2 -> {
                            System.out.println("退出");
                            return 0;
                        }
                        default -> {
                            System.out.println("輸入錯誤 退出功能！");
                            return 0;
                        }

                    }
                }
                case 0 -> {
                    System.out.println("退出查詢");
                    e = false;
                }
                default -> System.out.println("功能錯誤");
            }
        }
        return id;
    }


    public void updCandidates(int id) {
        try {
            conn.setAutoCommit(false);
            System.out.print("請輸入選區:");
            String place = sc.nextLine();
            System.out.print("請輸入姓名:");
            String name = sc.nextLine();
            System.out.println("請輸入登記日期:");
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
            } else if (m.equals("01") | m.equals("03") | m.equals("05") | m.equals("07") | m.equals("08") | m.equals("1") | m.equals("3") | m.equals("5") | m.equals("7") | m.equals("8") | m.equals("10") | m.equals("12")) {
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

            String date = "2022-" + String.format("%02d", Integer.parseInt(m)) + "-" + String.format("%02d", Integer.parseInt(d));

            Date sDate = Date.valueOf(date);
            System.out.print("請輸入政黨:");
            String party = sc.nextLine();
            System.out.print("請輸入備註:");
            String remark = sc.nextLine();
            System.out.println("輸入選號");
            int no = inputCno();
            String sql ="UPDATE `election`.`elections` SET `elePlace` = ? , `sDate` = ? , `eleName` = ? , `party` = ? , `remark` = ? , `eleNO` = ? WHERE (`cdID` = ? )";
            PreparedStatement preState = conn.prepareStatement(sql);
            preState.setString(1,place);
            preState.setDate(2,sDate);
            preState.setString(3,name);
            preState.setString(4,party);
            preState.setString(5,remark);
            preState.setInt(6,no);
            preState.setInt(7,id);
            preState.executeUpdate();
            conn.commit();
            System.out.println("修改成功");
            conn.setAutoCommit(true);
            String sql2 = "SELECT * FROM `election`.`elections` where `cdID` = ? ";
            PreparedStatement preState2 = conn.prepareStatement(sql2);
            preState2.setInt(1, id);
            ResultSet resultSet = preState2.executeQuery();
            while (resultSet.next()) {
                Candidate c = new Candidate();
                c.setEd(resultSet.getString("elePlace"));
                c.setsData(resultSet.getDate("sDate").toLocalDate());
                c.setName(resultSet.getString("eleName"));
                c.setParty(resultSet.getString("party"));
                c.setRemark(resultSet.getString("remark"));
                System.out.println(c.Cdlist()+"選號: "+resultSet.getInt("eleNO"));
            }
            resultSet.close();
            preState2.close();
            System.out.println("===========================");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

