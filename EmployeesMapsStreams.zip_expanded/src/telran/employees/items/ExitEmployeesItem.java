package telran.employees.items;

import java.io.Closeable;

import org.hamcrest.core.IsInstanceOf;

import telran.employees.api.EmployeesService;
import telran.menu.InputOutput;

public class ExitEmployeesItem extends EmployeesItem {

	public ExitEmployeesItem(EmployeesService employees, InputOutput io) {
		super(employees, io);
	}

	@Override
	public String displayName() {
		
		return "Exit";
	}

	@Override
	public void perform() {
		if(employees instanceof Closeable) {
			try {
				((Closeable)employees).close();
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}
	
	@Override
	public boolean isFinished(){
		return true;
	}

}
