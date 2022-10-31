package exam.e4;

public class Employee {
	private int empno;
	private String name;
	private long salary;

	public Employee(int empno, String name, long salary) {
		this.empno = empno;
		this.name = name;
		this.salary = salary;
	}

//	public Employee() {
//		
//	}
//	描述
	public String dsecEmp() {
		return String.format("員工編號:%-10d姓名:%-10s薪水:%-10d", this.empno, this.name, this.salary);
	}

	public String saveData() {
		return String.format("%d,%s,%d,0", this.empno, this.name, this.salary);
	}
	
	public int getEmpno() {
		return empno;
	}

	public String getName() {
		return name;
	}

	public long getSalary() {
		return salary;
	}

}
