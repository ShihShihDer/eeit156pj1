package pg1.ioCSV;

import mysql.com.pj1.model.Candidate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;


public class WriteCSV {
	public static void writeCSV(List<Candidate> emplist) {
		String filePar = "..\\project1\\electionDataBase";
		File myPath =new File(filePar);
		if (!myPath.exists()) {
			boolean m = myPath.mkdir();
			if(!m){
				System.out.println("Path error");
			}
		}
		try (
				FileOutputStream fos = new FileOutputStream("..\\project1\\electionBase\\election_title.csv");
				OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
				){
			osw.write("選舉區,登記日期,姓名,推薦之政黨,備註\n");
			for (Candidate item : emplist) {

				osw.write(item.toCSV());

				osw.write("\n");
			}



		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	

}
