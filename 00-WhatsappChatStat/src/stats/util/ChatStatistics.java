package stats.util;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class ChatStatistics {
	private String chatFile;
	private HashMap<String, Integer> statObj = new HashMap<String, Integer>();
	private HashMap<String, HashMap> statOb2 = new HashMap<>();
	
	private int totalMessages;
	private int totalWords;
	
	public ChatStatistics(String chatFileAdress) {
		this.chatFile = chatFileAdress;
		generateStatObj();
	}

	private void generateStatObj() {
		HashMap<String, Integer> contacts = new HashMap<>();
		
		FileSystem fs = FileSystems.getDefault();
		Path filePath = fs.getPath(chatFile).toAbsolutePath();
		
		try (Stream<String> lines = Files.lines( filePath )){
			String exp = "\\+[\\d- ]+:|- [A-Za-z0-9 ]{10,}:";
			Pattern pattern = Pattern.compile(exp, Pattern.CASE_INSENSITIVE); 
			
		    lines.forEach(e -> {
				Matcher matcher = pattern.matcher(e);
			    while (matcher.find()) {
			        String number = e.substring(matcher.start(), matcher.end()-1);
			        
			        int newValue = statObj.getOrDefault(number, 0) + 1; 
			        totalMessages++;
			        statObj.put(number, newValue);
			    }
		    	
		    });
		} 
		catch (IOException e) 
		{
		    e.printStackTrace();
		}
	}
	
	public void printTotalMessages() {
		System.out.println("TotalMessages:" + totalMessages);
	}
	
	public void printMessagesPerUser() {
		String messages = getMessagesPerUser();
		System.out.println(messages );		
	}
	
	private String getMessagesPerUser() {
		StringBuilder res = new StringBuilder();
		statObj.entrySet()
		.stream()
		.sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
		.forEach(e -> res.append(e.getKey() + ": " + e.getValue() + " messages\n"));
		return res.toString();
	}
	
	public void printPercentStatictics() {
		String statistics = getPercentStatictics(); 
		System.out.println(statistics);
	}
	
	private String getPercentStatictics() {
		StringBuilder res = new StringBuilder(); 
		statObj.entrySet()
		.stream()
		.sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
		.forEach(e -> {
			double percent = ((double)e.getValue() /  (double)totalMessages) * 100;
			res.append(e.getKey() + ": " + Math.round(percent * 100.0) / 100.0 + "%\n");
		});
		return res.toString();
	}
	
	public void printAveragePercent() {
		String avg = getAveragePercent();
		System.out.println("Average percent: " + avg);
	}
	
	private String getAveragePercent() {
		int avgPerPerson = totalMessages/statObj.size();
		double percent = ((double)avgPerPerson / (double)totalMessages)*100;
		return Math.round(percent * 100.0) / 100.0 + "%";
	}
	
	public void saveAllStatistics() {
		try(FileWriter writer = new FileWriter("src/statistics.txt", false))
        {
			writer.write("Total messages: " + totalMessages + "\n");

			String averagePercent =  getAveragePercent();
            writer.write("\nMessages in average per person");
            writer.write(averagePercent + "\n");
            
            String messageAmounts = getMessagesPerUser();
            writer.write("\nAmount of messages for all users: \n");
            writer.write(messageAmounts);

            String messagepPercentage = getPercentStatictics();
            writer.write("\nAmount of messages statistics in percents: \n");
            writer.write(messagepPercentage);
            
             
            writer.flush();
            
            System.out.println("Statictics has been saved");
        }
        catch(IOException ex){
             
            System.out.println(ex.getMessage());
        } 
	}

}
