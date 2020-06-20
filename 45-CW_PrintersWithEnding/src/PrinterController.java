import java.util.Scanner;

public class PrinterController {
public static void main(String[] args) {
	Printer printer1 =  new Printer("Promise");
	Printer printer2 =  new Printer("Promise");
	Printer printer3 =  new Printer("Promise");

	Printer[] printers = {printer1, printer2, printer3};
	
	for(Printer p: printers) p.start();
	
	@SuppressWarnings("resource")
	Scanner scanner = new Scanner(System.in);
	
	String line = null;
	int ind;
	
	while(true) {
		line = scanner.nextLine();
		
		if(line.equals("q")) {
			break;
		}else {
			ind = Integer.parseInt(line); 
			if(ind >= 0 && ind < printers.length) {
				printers[ind].interrupt();
			}
			
		}
	}
	for(Printer p: printers) p.setRunning(false);
	
}
}
