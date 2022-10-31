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
import java.util.HashMap;

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
public String TestTika() throws IOException {
	File file = new File("D:\\javaSSD\\project1\\P1Data\\111年9月2日候選人登記概況_csv");
	Tika tika = new Tika();
	return tika.detect(file);

}
	public static void main(String[] args) {
		File file = new File("D:\\javaSSD\\project1\\P1Data\\111年9月2日候選人登記概況_csv\\1_2-111年直轄市長選舉候選人登記彙總表.csv");
		Tika tika = new Tika();
		try {
			String a = tika.detect(file);
			System.out.println(a);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}


//		try (FileInputStream fis = new FileInputStream("/Users/vincent/Downloads/Employees.csv");
//			 InputStreamReader isr = new InputStreamReader(fis, "ms950");
//			 BufferedReader br = new BufferedReader(isr)
//		) {
//			CSVFormat.Builder builder = CSVFormat.Builder.create();
//			builder.setHeader("員工編號","姓名","薪水","年齡");
//			builder.setSkipHeaderRecord(true);
//			Iterable<CSVRecord> records = builder.build().parse(br);
//			int sum = 0;
//			for (CSVRecord record : records) {
//				String salary = record.get("薪水").replace(",","");
//				int sal = Integer.parseInt(salary);
//				sum += sal;
//			}
//			System.out.println("Sum = "+sum);
//
//		} catch (IOException e) {
//			throw new RuntimeException(e);
//		}

	}
}
