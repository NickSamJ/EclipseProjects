package telran.employee;

import java.time.LocalDate;
import java.util.HashMap;

import telran.menu.*;

public class EmployeesMenuAppl {
	static InputOutput io = new ConsoleInputOutput();
	
	
	
	public static void main(String[] args) {
		Employee emp1 = new Employee("lala@la.ua", "0559012999", LocalDate.parse("2000-11-22"), 3000, "Developer");
		Employee emp2 = new Employee("bront@kas.fr", "0559062999", LocalDate.parse("2000-11-22"), 3000, "Developer");
		
		HashMap<String, Employee> employees = new HashMap<>();
		employees.put(emp1.getEmail(), emp1);
		employees.put(emp2.getEmail(), emp2);
		Item[] items = {
				new DisplayAllEmployeesItem(employees, io),
				new AddEmployeeItem(employees, io),
				new DisplayEmployeeItem(employees, io),
				new RemoveEmployeeItem(employees, io),
				new ExitItem()
		};
		
		Menu menu = new Menu(items, io);
		menu.menuRun();
	}

}
