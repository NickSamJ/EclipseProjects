package telran.employees.dto;

import java.time.LocalDate;

public class Employee {
private long id;
private int salary;
private String company;
private LocalDate birthYear;
private String name;
public Employee(long id, int salary, String company, LocalDate birthYear, String name) {
	super();
	this.id = id;
	this.salary = salary;
	this.company = company;
	this.birthYear = birthYear;
	this.name = name;
}
@Override
public String toString() {
	return "Employee [id=" + id + ", salary=" + salary + ", company=" + company + ", birthYear=" + birthYear + ", name="
			+ name + "]";
}

public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public int getSalary() {
	return salary;
}
public void setSalary(int salary) {
	this.salary = salary;
}
public String getCompany() {
	return company;
}

}
