package telran.employees.controllers;

import telran.employees.api.EmployeesService;
import telran.employees.items.AddEmployee;
import telran.employees.items.DisplayEmployeesSalary;
import telran.employees.items.ExitEmployeesItem;
import telran.employees.service.EmployeesServiceMapsImpl;
import telran.menu.ConsoleInputOutput;
import telran.menu.InputOutput;
import telran.menu.Item;
import telran.menu.Menu;

public class EmployeesClientApplication {
static InputOutput io = new ConsoleInputOutput();
static EmployeesService employees = new EmployeesServiceMapsImpl();
public static void main(String[] args) {
			Item[] items = {
					new AddEmployee(employees, io),
					new DisplayEmployeesSalary(employees, io),
					new ExitEmployeesItem(employees, io),
					
			};
			
			Menu menu = new Menu(items, io);
			menu.menuRun();
}
}
