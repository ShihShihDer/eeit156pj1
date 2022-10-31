package ioEmp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import exam.e4.*;

public class WriteCSV {
	public static void writeCSV(List<Employee> emplist) {
		String filePar = "C:\\EmpDataBase";
		File myPath =new File(filePar);
		if (!myPath.exists()) {
			boolean m = myPath.mkdir();
			if(!m){
				System.out.println("Path error");
			}
		}
		try (
				FileOutputStream fos = new FileOutputStream("C:\\EmpDataBase\\Employee.csv");
				OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
				){	
			osw.write("員工編號,姓名,薪水,加給\n");
			for (Employee item : emplist) {
				
				osw.write(item.saveData());
				
				osw.write("\n");
			}
			
			

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	

}
