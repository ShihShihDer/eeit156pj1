package ioEmp;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.tika.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class TestIO {
    public static void main(String[] args) {
        File folder = new File("C:\\electionDataBase");
        Tika tika = new Tika();
        List<String> electionlist = new ArrayList<>();
        for (File file : Objects.requireNonNull(folder.listFiles())) {
            if (!file.isDirectory()) {
                try {
                    String fileType = tika.detect(file);
                    if (fileType.equals("text/csv")) {
                        try (FileInputStream fis = new FileInputStream(file);
                             InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
                             BufferedReader br = new BufferedReader(isr)
                        ) {
                            CSVFormat.Builder builder = CSVFormat.Builder.create();
                            builder.setHeader("\"選舉區\"", "登記日期", "姓名", "推薦之政黨", "備註");
                            builder.setSkipHeaderRecord(true);
                            Iterable<CSVRecord> records = builder.build().parse(br);
                            for (CSVRecord record : records) {
                                String item = record.get("\"選舉區\"").replaceAll("\\r", "") + "," +
                                        record.get("登記日期").replaceAll("\\r", "") + "," +
                                        record.get("姓名").replaceAll("\\r", "") + "," +
                                        record.get("推薦之政黨").replaceAll("\\r", "") + "," +
                                        record.get("備註").replaceAll("\\r", "");
                                if (!item.equals("選舉區,登記日期,姓名,推薦之政黨,備註")) {
                                    electionlist.add(item);
                                    System.out.println(item);
                                }
                            }

                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    }
//                    System.out.println(file.getName());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        }
        String filePar = "C:\\electionDataBase";
        File myPath = new File(filePar);
        if (!myPath.exists()) {
            boolean m = myPath.mkdir();
            if (!m) {
                System.out.println("Path error");
            }
        }
        try (
                FileOutputStream fos = new FileOutputStream("C:\\electionDataBase\\election_title.csv");
                OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
        ) {
            osw.write("選舉區,登記日期,姓名,推薦之政黨,備註\n");
            for (String item : electionlist ) {

                osw.write(item);

                osw.write("\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
