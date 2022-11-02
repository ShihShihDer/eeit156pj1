package pg1.ioCSV;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class SSSS {
    public static void main(String[] args) {

//        List<String> s = new ArrayList<>();
//        try (FileInputStream fs = new FileInputStream("C:\\electionDataBase\\election_title.csv");
//             InputStreamReader ir = new InputStreamReader(fs, StandardCharsets.UTF_8);
//             BufferedReader br = new BufferedReader(ir);) {
//            CSVFormat.Builder builder = CSVFormat.Builder.create();
//            builder.setHeader("選舉區", "登記日期", "姓名", "推薦之政黨", "備註");
//            Iterable<CSVRecord> records = builder.build().parse(br);
//
//            for (CSVRecord record : records
//            ) {
//                String item = record.get("選舉區") + "," +
//                        record.get("登記日期") + "," +
//                        record.get("姓名") + "," +
//                        record.get("推薦之政黨") + "," +
//                        record.get("備註") + ",";
//                s.add(item);
//            }
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        String filePar = "C:\\electionDataBase";
//        File myPath = new File(filePar);
//        if (!myPath.exists()) {
//            boolean m = myPath.mkdir();
//            if (!m) {
//                System.out.println("Path error");
//            }
//        }
//        try (
//                FileOutputStream fos = new FileOutputStream("C:\\electionDataBase\\election_title2.csv");
//                OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
//        ) {
//            osw.write("選舉區,登記日期,姓名,推薦之政黨,備註,選舉號碼\n");
//            for (String item : s) {
//
//                osw.write(item);
//
//                osw.write("\n");
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}

