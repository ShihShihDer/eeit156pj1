package exam.e4;


public class Manger extends Employee {

	private long bonus;

	public Manger(int empno, String name, long salary, long bonus) {
		super(empno, name,salary);
		
		this.bonus = bonus;
	}
//	  annotation 註釋
//	spring,hibernate  增加額外的功能
	
	@Override
	public long getSalary() {
		return super.getSalary()+bonus;
	}
	
	
	@Override
	public String dsecEmp() {
		return String.format("員工編號:%-10d姓名:%-10s薪水:%-10d", super.getEmpno(), super.getName(), getSalary());
		
	}
	
	@Override
	public String saveData() {
		return String.format("%d,%s,%d,%d",super.getEmpno(),super.getName(),super.getSalary() , this.bonus);
	}
	
	
//	@Override
//	public String dsecEmp() {
////		String infoFromSuper = super.dsecEmp();
//		String info = String.format("員工編號:%-10d姓名:%-10s薪水:%-10d", getEmpno(), getName(), getSalary() + this.bonus);
//		return info;
//	}

	public long getBonus() {
		return bonus;
	}
}
