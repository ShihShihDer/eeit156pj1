package pg1.ioCSV;


import mysql.com.pj1.model.Candidate;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.MinguoChronology;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DecimalStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ReadCSV {
	public static List<Candidate> readCSV() throws IOException {
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
//				SimpleDateFormat sdf = new SimpleDateFormat("yyy/MM/dd");
//				Date date = sdf.parse(record.get("登記日期"));
				MinguoChronology chrono = MinguoChronology.INSTANCE;
				DateTimeFormatter df = new DateTimeFormatterBuilder().parseLenient().appendPattern("yyy/MM/dd").toFormatter().withChronology(chrono).withDecimalStyle(DecimalStyle.of(Locale.getDefault()));
				ChronoLocalDate rd =chrono.date(df.parse(record.get("登記日期")));
				LocalDate date =LocalDate.from(rd);
				Candidate candidate = new Candidate(record.get("選舉區"),date,record.get("姓名"),record.get("推薦之政黨"),record.get("備註"));
				list.add(candidate);
			}

		} 
		return list;
	}

}
