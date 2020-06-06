package telran.employee;

import java.time.LocalDate;

public class Employee {
String email;
String phone;
LocalDate birthDate;
int salary;
String title;
public Employee(String email, String phone, LocalDate birthDate, int salary, String title) {
	super();
	this.email = email;
	this.phone = phone;
	this.birthDate = birthDate;
	this.salary = salary;
	this.title = title;
}
@Override
public String toString() {
	return "Employee [email=" + email + ", phone=" + phone + ", birthDate=" + birthDate + ", salary=" + salary
			+ ", title=" + title + "]";
}
public String getEmail() {
	return email;
}

public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public LocalDate getBirthDate() {
	return birthDate;
}

public int getSalary() {
	return salary;
}
public void setSalary(int salary) {
	this.salary = salary;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
}
