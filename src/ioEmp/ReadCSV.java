package ioEmp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import exam.e4.Employee;
import exam.e4.Manger;

public class ReadCSV {
	public static List<Employee> readCSV() {
		List<Employee> dataList = new ArrayList<>();
		try (FileInputStream fs = new FileInputStream("C:/EmpDataBase/Employee.csv");
			 InputStreamReader ir = new InputStreamReader(fs, StandardCharsets.UTF_8);
			 BufferedReader br = new BufferedReader(ir);) {
			br.readLine();
			String line = null;
			while ((line = br.readLine()) != null) {
				String[] item = line.split(",");
				int id = Integer.parseInt(item[0]);
				String name = item[1];
				int salary = Integer.parseInt(item[2]);
				long bonus = Long.parseLong(item[3]);
				if (bonus > 0) {
					dataList.add(new Manger(id, name, salary, bonus));
				} else {
					dataList.add(new Employee(id, name, salary));
				}
			}

		} catch (Exception ignored) {

		}
		dataList.sort(Comparator.comparing(Employee::getEmpno));
		return dataList;
	}

}
