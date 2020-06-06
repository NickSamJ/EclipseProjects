package telran.employee;

import java.util.HashMap;

import telran.menu.InputOutput;
import telran.menu.Item;

public abstract class AbstractEmployeesItem implements Item {
protected HashMap<String, Employee> employees;

public AbstractEmployeesItem(HashMap<String, Employee> employees, InputOutput io) {
	this.employees = employees;
	
}

}
