package ioEmp;

import com.drew.metadata.Metadata;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.tika.exception.TikaException;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.BodyContentHandler;
import org.apache.tika.*;
import org.xml.sax.SAXException;

import java.io.*;
import java.lang.management.MemoryType;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Objects;

public class TestIO {
    /*public static void findAllFilesInFolder(File folder){
        for (File file:folder.listFiles()) {
            if (!file.isDirectory()){
                System.out.println(file.getName());
                AutoDetectParser parser = new AutoDetectParser();
                parser.setParsers(new HashMap<MemoryType, Parser>());

                Metadata metadata = new Metadata();
                metadata.add(TikaMetadataKeys);
            }else {
                findAllFilesInFolder(file);
            }
        }
    };*/
//	D:\javaSSD\project1\P1Data\111年9月2日候選人登記概況_csv
/*public String TestTika() throws IOException {
	File file = new File("..project1\\P1Data\\111年9月2日候選人登記概況_csv");
	Tika tika = new Tika();
	return tika.detect(file);
}*/
    public static void main(String[] args) {
        File folder = new File("..\\project1\\P1Data\\111年9月2日候選人登記概況_csv");
        Tika tika = new Tika();
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
                                System.out.println(record.get("\"選舉區\"").replace(",","")+","+
                                        record.get("登記日期").replace(",","")+","+
                                        record.get("姓名").replace(",","")+","+
                                        record.get("推薦之政黨").replace(",","")+","+
                                        record.get("備註").replace(",",""));
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    }
                    System.out.println(file.getName());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }


}
