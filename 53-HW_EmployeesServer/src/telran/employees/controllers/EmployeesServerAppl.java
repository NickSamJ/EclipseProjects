package telran.employees.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

import telran.employees.dto.Employee;
import telran.employees.net.EmployeesOperationsProtocol;
import telran.employees.service.EmployeesServiceMapsImpl;
import telran.net.server.ProtocolJava;
import telran.net.server.ServerJava;

public class EmployeesServerAppl {
private static final int PORT = 4040;

	
	public static void main(String[] args) {
     	EmployeesServiceMapsImpl employeesService = EmployeesServiceMapsImpl.getEmployeesService(); 
		
		ProtocolJava protocol = new EmployeesOperationsProtocol(employeesService);
		ServerJava server = new ServerJava(protocol , PORT);
		Thread serverThread = new Thread(server);

		serverThread.start();
		Scanner scanner = new Scanner(System.in);
		while(true) {
			System.out.println("For stopping server enter exit");
			String line = scanner.nextLine();
			if(line.equalsIgnoreCase("exit")) {
				break;
			}
		}
		
		server.stop();
		employeesService.save();
	
	}
}
