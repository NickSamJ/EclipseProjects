package telran.employees.items;

import java.time.LocalDate;
import java.time.Period;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import telran.employees.api.EmployeesService;
import telran.employees.dto.Employee;
import telran.employees.dto.EmployeesReturnCodes;
import telran.menu.InputOutput;

public class RandomGeneration extends EmployeesItem {

	public RandomGeneration(EmployeesService employees, InputOutput io) {
		super(employees, io);
	}

	@Override
	public String displayName() {
		return "Generate random employees";
	}

	@Override
	public void perform() {
		int nEmployees = io.inputInteger("Enter number of employees", 1, Integer.MAX_VALUE);
		int nCompanies = io.inputInteger("Enter number of companies");
		
		int minId = io.inputInteger("Enter minimum id value");
		int maxId = minId;
		while(maxId-minId < nEmployees) {			
			maxId = io.inputInteger("Enter maximum id value", minId+1, 999999 );
		}
		
		int minSalary = io.inputInteger("Input min salary");
		int maxSalary = io.inputInteger("Enter max salary", minSalary+1, Integer.MAX_VALUE);
		
		LocalDate minDate = io.inputDate("Enter min Birth date");
		
		LocalDate maxDate = LocalDate.MIN;
		while(maxDate.toEpochDay() - minDate.toEpochDay()<=0) {
			maxDate = io.inputDate("Enter max Birth date");
		}
		
		
		EmployeesReturnCodes code;
		int employeesAdded=0;
		
		for(int i = 0; i < nEmployees; i++) {			
			
			int userId = minId + i;
			
			code = employees.addEmployee(new Employee(
					minId++,
					randSalary(minSalary, maxSalary),
					randCompany(nCompanies),
					randBirthDate(minDate, maxDate),
					randName()
				));
			if(code == EmployeesReturnCodes.OK) {
				employeesAdded++;
			}
		}
		io.displayLine(employeesAdded + " Employees was sucessfully added");
	}
	
	private long randId(int minId, int maxId) {
		
		return minId++;
	}

	private String randName() {
		return "Name" + getRandInt(1, 100);
	}

	private LocalDate randBirthDate(LocalDate minDate, LocalDate maxDate) {
		long minDay = minDate.toEpochDay();
	    long maxDay = maxDate.toEpochDay();
	    long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
	    LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
		return randomDate;
	}
	
	
	private String randCompany(int nCompanies) {
		return "CompanyName" + getRandInt(1, nCompanies);
	}

	private int randSalary(int minSalary, int maxSalary) {
		return getRandInt(minSalary, maxSalary);
	}

	public int getRandInt(int min, int max) {
		return (int) (Math.random() * (max - min + 1) + min);
	}
	

}
