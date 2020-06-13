package telran.employees.items;

import java.time.LocalDate;
import java.time.Period;
import java.util.Random;
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
		int[] ids;
		if(nEmployees > idDiff) {
			return;
		}
		Employee testEmployee;
		for(int i = 0; i < nEmployees; i++) {
			
		}
		
		
		int minSalary = io.inputInteger("Input min salary");
		int maxSalary = io.inputInteger("Enter max salary", minSalary+1, Integer.MAX_VALUE);
		
		LocalDate minDate = io.inputDate("Enter min Birth date");
		
		LocalDate maxDate;
		while(Period.between(minDate, maxDate).getDays()<=0) {
			maxDate = io.inputDate("Enter max Birth date");
		}
		
		Employee[] randomEmployees = new Employee[nEmployees];
		Employee empl =  new Employee(id, salary, company, birthDate, name);
		Random random = new Random();
		for(int i = 0; i < nEmployees; i++) {
			
			
			randomEmployees[i]  = new Employee(
					randId(minId, maxId), 
					randSalary(minSalary, maxSalary),
					randComp(nCompanies),
					randBirthDate(),
					"name " + random.nextInt(20)
					);
		}
		
	}
	
	private long randId(int minId, int maxId) {
		
		
		return 0;
	}

	public int getRandInt(int min, int max) {
		return (int) (Math.random() * (max - min + 1) + min);
	}
	

}
