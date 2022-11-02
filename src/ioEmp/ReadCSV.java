package ioEmp;

import exam.e4.Candidate;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReadCSV {
	public static List<Candidate> readCSV() {
		List<Candidate> list = new ArrayList<>();
		try (FileInputStream fs = new FileInputStream("..\\project1\\electionDataBase\\election_title.csv");
			 InputStreamReader ir = new InputStreamReader(fs, StandardCharsets.UTF_8);
			 BufferedReader br = new BufferedReader(ir);) {
			CSVFormat.Builder builder = CSVFormat.Builder.create();
			builder.setHeader("選舉區", "登記日期", "姓名", "推薦之政黨", "備註","選舉號碼");
			builder.setSkipHeaderRecord(true);
			Iterable<CSVRecord> records = builder.build().parse(br);
			for (CSVRecord record :records
				 ) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyy/MM/dd");
				Date date = sdf.parse(record.get("登記日期"));
				Candidate candidate = new Candidate(record.get("選舉區"),date,record.get("姓名"),record.get("備註"),record.get("選舉號碼"));
				list.add(candidate);
			}

		} catch (Exception ignored) {
			ignored.printStackTrace();
		}
		return list;
	}

}
