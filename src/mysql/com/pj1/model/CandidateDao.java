package mysql.com.pj1.model;


import java.sql.*;
import java.time.LocalDate;
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

    public void delCandidates()  {
        try {
            conn.setAutoCommit(false);
        boolean e = true;
        while (e) {
            System.out.println("1.選區");
            System.out.println("2.登記日期");
            System.out.println("3.姓名");
            System.out.println("4.政黨");
            System.out.println("5.退出");
            switch (selectKey()) {
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
                        System.out.format("編號: %d 選區: %s 登記日期 : %s 姓名 : %s 政黨 : %s", cdID, elePlace, sDate, eleName, party);
                    }
                    System.out.println("===========================");
                    System.out.println("展示完畢");
                    System.out.print("1.以編號刪除");
                    System.out.println("2.全部刪除");
                    System.out.println("3.取消刪除");
                    rs.close();
                    preState.close();
                    switch (sc.nextInt()) {
                        case 1 -> {
                            System.out.print("輸入編號:");
                            int id = sc.nextInt();
                            String sql3 = "SELECT * FROM election.elections where cdID = ?";
                            PreparedStatement preState3 = conn.prepareStatement(sql);
                            preState.setInt(1, id);
                            ResultSet rs3 = preState.executeQuery();
                            while (rs.next()) {
                                int cdID = rs.getInt("cdID");
                                String elePlace = rs.getString("elePlace");
                                LocalDate sDate = rs.getDate("sDate").toLocalDate();
                                String eleName = rs.getString("eleName");
                                String party = rs.getString("party");
                                System.out.format("編號: %d 選區: %s 登記日期 : %s 姓名 : %s 政黨 : %s", cdID, elePlace, sDate, eleName, party);
                            }
                            rs3.close();
                            preState3.close();
                            System.out.println("1.確認刪除");
                            System.out.println("2.取消");
                            switch (sc.nextInt()) {
                                case 1 -> {
                                    String sqlDel = "DELETE FROM election.elections WHERE `elePlace` like ?";
                                    PreparedStatement preState2 = conn.prepareStatement(sqlDel);
                                    preState2.setInt(1, id);
                                    System.out.println("已刪除" + preState2.executeUpdate() + "筆資料");
                                    preState2.close();
                                    e = false;
                                }
                                case 2 -> {
                                    System.out.println("退出");
                                    e = false;
                                }
                                default -> {
                                    System.out.println("輸入錯誤 退出功能！");
                                    e = false;
                                }
                            }

                        }
                        case 2 -> {
                            String sqlDel = "DELETE FROM election.elections WHERE `elePlace` like ?";
                            PreparedStatement preState2 = conn.prepareStatement(sqlDel);
                            preState2.setString(1, where);
                            System.out.println("已刪除" + preState2.executeUpdate() + "筆資料");
                            preState2.close();
                            e = false;
                        }
                        case 3 -> {
                            System.out.println("退出");
                            e = false;
                        }
                        default -> {
                            System.out.println("輸入錯誤 退出功能！");
                            e = false;
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
                        System.out.format("編號: %d 選區: %s 登記日期 : %s 姓名 : %s 政黨 : %s", cdID, elePlace, sDate, eleName, party);
                    }
                    System.out.println("===========================");
                    System.out.println("展示完畢");
                    System.out.print("1.以編號刪除");
                    System.out.println("2.全部刪除");
                    System.out.println("3.取消刪除");
                    rs.close();
                    preState.close();
                    switch (sc.nextInt()) {
                        case 1 -> {
                            System.out.print("輸入編號:");
                            int id = sc.nextInt();
                            String sql3 = "SELECT * FROM election.elections where cdID = ?";
                            PreparedStatement preState3 = conn.prepareStatement(sql);
                            preState.setInt(1, sc.nextInt());
                            ResultSet rs3 = preState.executeQuery();
                            while (rs.next()) {
                                int cdID = rs.getInt("cdID");
                                String elePlace = rs.getString("elePlace");
                                LocalDate sDate = rs.getDate("sDate").toLocalDate();
                                String eleName = rs.getString("eleName");
                                String party = rs.getString("party");
                                System.out.format("編號: %d 選區: %s 登記日期 : %s 姓名 : %s 政黨 : %s", cdID, elePlace, sDate, eleName, party);
                            }
                            rs3.close();
                            preState3.close();
                            System.out.println("1.確認刪除");
                            System.out.println("2.取消");
                            switch (sc.nextInt()) {
                                case 1 -> {
                                    String sqlDel = "DELETE FROM election.elections WHERE cdID = ?";
                                    PreparedStatement preState2 = conn.prepareStatement(sqlDel);
                                    System.out.println("已刪除" + preState2.executeUpdate() + "筆資料");
                                    preState2.close();
                                    e = false;
                                }
                                case 2 -> {
                                    System.out.println("退出");
                                    e = false;
                                }
                                default -> {
                                    System.out.println("輸入錯誤 退出功能！");
                                    e = false;
                                }
                            }
                        }
                        case 2 -> {
                            String sqlDel = "DELETE FROM election.elections WHERE `sDate` = ?";
                            PreparedStatement preState2 = conn.prepareStatement(sqlDel);
                            preState2.setString(1, scdata);
                            System.out.println("已刪除" + preState2.executeUpdate() + "筆資料");
                            preState2.close();
                            e = false;
                        }
                        case 3 -> {
                            System.out.println("退出");
                            e = false;
                        }
                        default -> {
                            System.out.println("輸入錯誤 退出功能！");
                            e = false;
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
                        System.out.format("編號: %d 選區: %s 登記日期 : %s 姓名 : %s 政黨 : %s", cdID, elePlace, sDate, eleName, party);

                    }
                    System.out.println("===========================");
                    System.out.println("展示完畢");
                    System.out.print("1.以編號刪除");
                    System.out.println("2.全部刪除");
                    System.out.println("3.取消刪除");
                    rs.close();
                    preState.close();
                    switch (sc.nextInt()) {
                        case 1 -> {
                            System.out.print("輸入編號:");
                            int id = sc.nextInt();
                            String sql3 = "SELECT * FROM election.elections where cdID = ?";
                            PreparedStatement preState3 = conn.prepareStatement(sql);
                            preState.setInt(1, sc.nextInt());
                            ResultSet rs3 = preState.executeQuery();
                            while (rs.next()) {
                                int cdID = rs.getInt("cdID");
                                String elePlace = rs.getString("elePlace");
                                LocalDate sDate = rs.getDate("sDate").toLocalDate();
                                String eleName = rs.getString("eleName");
                                String party = rs.getString("party");
                                System.out.format("編號: %d 選區: %s 登記日期 : %s 姓名 : %s 政黨 : %s", cdID, elePlace, sDate, eleName, party);
                            }
                            rs3.close();
                            preState3.close();
                            System.out.println("1.確認刪除");
                            System.out.println("2.取消");
                            switch (sc.nextInt()) {
                                case 1 -> {
                                    String sqlDel = "DELETE FROM election.elections WHERE cdID = ?";
                                    PreparedStatement preState2 = conn.prepareStatement(sqlDel);
                                    System.out.println("已刪除" + preState2.executeUpdate() + "筆資料");
                                    preState2.close();
                                    e = false;
                                }
                                case 2 -> {
                                    System.out.println("退出");
                                    e = false;
                                }
                                default -> {
                                    System.out.println("輸入錯誤 退出功能！");
                                    e = false;
                                }

                            }
                        }
                        case 2 -> {

                            String sqlDel = "DELETE FROM election.elections WHERE `eleName` like ?";
                            PreparedStatement preState2 = conn.prepareStatement(sqlDel);
                            preState2.setString(1, where);
                            System.out.println("已刪除" + preState2.executeUpdate() + "筆資料");
                            preState2.close();
                            e = false;
                        }
                        case 3 -> {
                            System.out.println("退出");
                            e = false;
                        }
                        default -> {
                            System.out.println("輸入錯誤 退出功能！");
                            e = false;
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
                        System.out.format("編號: %d 選區: %s 登記日期 : %s 姓名 : %s 政黨 : %s", cdID, elePlace, sDate, eleName, party);

                    }
                    System.out.println("===========================");
                    System.out.println("展示完畢");
                    System.out.print("1.以編號刪除");
                    System.out.println("2.全部刪除");
                    System.out.println("3.取消刪除");
                    rs.close();
                    preState.close();
                    switch (sc.nextInt()) {
                        case 1 -> {
                            System.out.print("輸入編號:");
                            int id = sc.nextInt();
                            String sql3 = "SELECT * FROM election.elections where cdID = ?";
                            PreparedStatement preState3 = conn.prepareStatement(sql);
                            preState.setInt(1, sc.nextInt());
                            ResultSet rs3 = preState.executeQuery();
                            while (rs.next()) {
                                int cdID = rs.getInt("cdID");
                                String elePlace = rs.getString("elePlace");
                                LocalDate sDate = rs.getDate("sDate").toLocalDate();
                                String eleName = rs.getString("eleName");
                                String party = rs.getString("party");
                                System.out.format("編號: %d 選區: %s 登記日期 : %s 姓名 : %s 政黨 : %s", cdID, elePlace, sDate, eleName, party);
                            }
                            rs3.close();
                            preState3.close();
                            System.out.println("1.確認刪除");
                            System.out.println("2.取消");
                            switch (sc.nextInt()) {
                                case 1 -> {
                                    String sqlDel = "DELETE FROM election.elections WHERE cdID = ?";
                                    PreparedStatement preState2 = conn.prepareStatement(sqlDel);
                                    System.out.println("已刪除" + preState2.executeUpdate() + "筆資料");
                                    preState2.close();
                                    e = false;
                                }
                                case 2 -> {
                                    System.out.println("退出");
                                    e = false;
                                }
                                default -> {
                                    System.out.println("輸入錯誤 退出功能！");
                                    e = false;
                                }
                            }
                        }
                        case 2 -> {
                            String sqlDel = "DELETE FROM election.elections WHERE ``party` like ?";
                            PreparedStatement preState2 = conn.prepareStatement(sqlDel);
                            preState2.setString(1, where);
                            System.out.println("已刪除" + preState2.executeUpdate() + "筆資料");
                            preState2.close();
                            e = false;
                        }
                        case 3 -> {
                            System.out.println("退出");
                            e = false;
                        }
                        default -> {
                            System.out.println("輸入錯誤 退出功能！");
                            e = false;
                        }
                    }
                }
                case 5 -> {
                    System.out.println("退出");
                    e = false;
                }
                default -> System.out.println("功能錯誤");
            }
        }
        conn.commit();
            System.out.println("資料更新成功");
        conn.setAutoCommit(true);
        } catch (SQLException e) {
            System.out.println("資料更新失敗");
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
