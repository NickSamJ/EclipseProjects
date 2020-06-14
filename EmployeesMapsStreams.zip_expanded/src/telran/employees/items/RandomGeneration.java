package telran.employees.items;

import java.time.LocalDate;
import java.time.Period;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import telran.employees.api.EmployeesService;
import telran.employees.dto.Employee;
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
		int nEmployees = io.inputInteger("Enter number of employees");
		int nCompanies = io.inputInteger("Enter number of companies");
		
		int minId = io.inputInteger("Enter minimum id value");
		int maxId = io.inputInteger("Enter maximum id value", minId+1, 999999 );
		
		int idDiff = maxId - minId;
		Integer[] ids = new Integer[nEmployees];
		if(nEmployees > idDiff) {
			return;
		}
		Employee testEmployee = null;
		int counter = 0;
		while(counter<nEmployees) {
			testEmployee = employees.getEmployee(minId);
			if (minId == maxId) {
				return;
			}
			if(testEmployee == null) {
				ids[counter] = minId++;
				counter++;
				continue;
			}
		}
		
		
		int minSalary = io.inputInteger("Input min salary");
		int maxSalary = io.inputInteger("Enter max salary", minSalary+1, Integer.MAX_VALUE);
		
		LocalDate minDate = io.inputDate("Enter min Birth date");
		
		LocalDate maxDate = LocalDate.MIN;
		while(maxDate.toEpochDay() - minDate.toEpochDay()<=0) {
			System.out.println(Period.between(minDate, maxDate).getDays());
			maxDate = io.inputDate("Enter max Birth date");
		}
		
		Employee[] randomEmployees = new Employee[nEmployees];
//		Employee empl =  new Employee(id, salary, company, birthDate, name);
		Random random = new Random();
		for(int i = 0; i < nEmployees; i++) {
			
			
			randomEmployees[i]  = new Employee(
//					randId(minId, maxId),
					ids[i],
					randSalary(minSalary, maxSalary),
					randComp(nCompanies),
					randBirthDate(minDate, maxDate),
					"Name" + random.nextInt(19)+1
					);
		}
		
		for(Employee e : randomEmployees) {
			io.displayLine(e);
		}
	}
	private LocalDate randBirthDate(LocalDate minDate, LocalDate maxDate) {
		long minDay = minDate.toEpochDay();
	    long maxDay = maxDate.toEpochDay();
	    long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
	    LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
		return randomDate;
	}
	
	
	private String randComp(int nCompanies) {
		return "Test comp";
	}

	private int randSalary(int minSalary, int maxSalary) {
		return getRandInt(minSalary, maxSalary);
	}

	public int getRandInt(int min, int max) {
		return (int) (Math.random() * (max - min + 1) + min);
	}
	

}
