package telran.employees.controllers;

import telran.employees.api.EmployeesService;
import telran.employees.items.*;
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
					new GetEmployee(employees, io),
					new RemoveEmployee(employees, io),
					new RandomGeneration(employees, io),
					new ExitEmployeesItem(employees, io),
					
			};
			
			Menu menu = new Menu(items, io);
			menu.menuRun();
}
}
