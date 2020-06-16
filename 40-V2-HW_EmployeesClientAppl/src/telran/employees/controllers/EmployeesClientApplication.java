package telran.employees.controllers;

import telran.employees.api.EmployeesService;
import telran.employees.items.*;
import telran.employees.service.EmployeesServiceMapsImpl;
import telran.employees.service.EmployeesTcpProxy;
import telran.menu.ConsoleInputOutput;
import telran.menu.InputOutput;
import telran.menu.Item;
import telran.menu.Menu;

public class EmployeesClientApplication {
private static final String HOST = "localhost";
private static final int PORT = 4040;
static InputOutput io = new ConsoleInputOutput();
//static EmployeesService employees = new EmployeesServiceMapsImpl();
static EmployeesService employees = new EmployeesTcpProxy(HOST, PORT);
public static void main(String[] args) {
			Item[] items = {
					new AddEmployee(employees, io),
					new DisplayEmployeesSalary(employees, io),
					new GetEmployee(employees, io),
					new RemoveEmployee(employees, io),
					new GetCompaniesAvgSalary(employees, io),
					new GetCompaniesGreaterAvgSalaryItem(employees, io),
					new GetEmployeesAgesItem(employees, io),
					new GetEmployeesGroupedBySalaryItem(employees, io),
					new UpdateCompanyItem(employees, io),
					new UpdateEmployeeSalary(employees, io),
					new RandomGeneration(employees, io),
					new ExitEmployeesItem(employees, io),
					
			};
			
			Menu menu = new Menu(items, io);
			menu.menuRun();
}
}


				 
				 
				 
				 
				 
				 
				 
				 
				 

