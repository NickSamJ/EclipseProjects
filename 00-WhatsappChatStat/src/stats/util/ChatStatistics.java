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
	private HashMap<String, HashMap<String, Integer>> statObj2 = new HashMap<>();
	
	private int totalMessages;
	private int totalWords = 0;
	
	public ChatStatistics(String chatFileAdress) {
		this.chatFile = chatFileAdress;
		generateStatObj();
	}

	private String currentNumber;
	private void generateStatObj() {
		HashMap<String, Integer> contacts = new HashMap<>();
		
		FileSystem fs = FileSystems.getDefault();
		Path filePath = fs.getPath(chatFile).toAbsolutePath();
		
		try (Stream<String> lines = Files.lines( filePath )){
			String exp = "\\+[\\d- ]+:|- [A-Za-z0-9 ]{10,}:";
			Pattern pattern = Pattern.compile(exp, Pattern.CASE_INSENSITIVE); 
			
		    lines.forEach(e -> {
		    	Matcher matcher = pattern.matcher(e);
		    	if (matcher.find()) {
			        String number = e.substring(matcher.start(), matcher.end()-1);
			        currentNumber = number;
			        String message = e.replaceAll("\\d+/[A-Za-z0-9 ,\\+-:]+: ", "");			        
			        countMessagesWords(number, message);
			       
			    }
		    	else {
		    		if(currentNumber!=null) {
		    			countMessagesWords(currentNumber, e);
		    		}
		    	}
		    	
		    });
		} 
		catch (IOException e) 
		{
		    e.printStackTrace();
		}
	}
	
	private void countMessagesWords(String number, String message) {
		 String[] words = message.split(" "); 
	        
	        
	        HashMap<String, Integer> currStat = statObj2.getOrDefault(number, new HashMap<>());

	        int nMessages = currStat.getOrDefault("messages", 0);
	        currStat.put("messages", nMessages + 1);

	        int nWords = currStat.getOrDefault("words", 0);
	        currStat.put("words", nWords + words.length);
	        statObj2.put(number, currStat);
	        
	        totalMessages++;
	        totalWords += words.length;
		
	}
	
	public void printTotalUsers() {
		System.out.println("Total users: " + statObj2.size());
	}
	
	public void printTotalMessages() {
		System.out.println("TotalMessages: " + totalMessages);
	}
	
	public void printTotalWords() {
		System.out.println(totalWords);
	}
	
	public void printWordsPerUser() {
		String words = getWordsPerUser();
		System.out.println(words );		
	}
	private String getWordsPerUser() {
		StringBuilder res = new StringBuilder();
		statObj2.entrySet()
			.stream()
			.sorted((e1, e2) -> e2.getValue().get("words").compareTo(e1.getValue().get("words")))
			.forEach(e -> res.append(e.getKey() + ": " + e.getValue().get("words") + " words\n"));
		return res.toString();
	}

	public void printMessagesPerUser() {
		String messages = getMessagesPerUser();
		System.out.println(messages );		
	}
	
	private String getMessagesPerUser() {
		StringBuilder res = new StringBuilder();
		statObj2.entrySet()
			.stream()
			.sorted((e1, e2) -> e2.getValue().get("messages").compareTo(e1.getValue().get("messages")))
			.forEach(e -> res.append(e.getKey() + ": " + e.getValue().get("messages") + " messages\n"));
		return res.toString();
	}
	
	public void printPercentStatictics() {
		String statistics = getPercentStatictics(); 
		System.out.println(statistics);
	}
	
	private String getPercentStatictics() {
		StringBuilder res = new StringBuilder(); 
		statObj2.entrySet()
			.stream()
			.sorted((e1, e2) -> e2.getValue().get("messages").compareTo(e1.getValue().get("messages")))
			.forEach(e -> {
				int nMessages = e.getValue().get("messages");
				int nWords = e.getValue().get("words");
				double percentMessages = ((double)nMessages /  (double)totalMessages) * 100;
				double percentWords = ((double)nWords /  (double)totalWords) * 100;
		res.append(String.format("%s - Messages: %.2f%%; Words: %.2f%%\n", e.getKey(), percentMessages, percentWords));
		});
		return res.toString();
	}
	
	public void printAveragePercent() {
		String[] averagePercent =  getAveragePercent();
		String avgM = averagePercent[0];
		String avgW = averagePercent[1];
		System.out.printf("Average percent Messages: %s%%\n", avgM );
		System.out.printf("Average percent Words %s%%\n", avgW);
	}
	
	private String[] getAveragePercent() {
		int avgMessagesPerPerson = totalMessages/statObj2.size();
		int avgWordsPerPerson = totalWords/statObj2.size();
		
//		String 
		double percentMessages = ((double)avgMessagesPerPerson / (double)totalMessages)*100;
		double percentWords = ((double)avgWordsPerPerson / (double)totalWords)*100;
		String[] res =  {
				String.format("%.2f", percentMessages),
				String.format("%.2f", percentWords),
		};
		return  res;
	}
	
	public void saveAllStatistics() {
		try(FileWriter writer = new FileWriter("src/statistics.txt", false))
        {
			writer.write("Total users: " + statObj2.size() + "\n");
			
			writer.write("\nTotal messages: " + totalMessages + "\n");
			writer.write("Total words: " + totalWords + "\n");

			String[] averagePercent =  getAveragePercent();
			String avgM = averagePercent[0];
			String avgW = averagePercent[1];
			writer.write(String.format("Average percent Messages: %s%%\n", avgM ));
			writer.write(String.format("Average percent Words %s%%\n", avgW));
            
            String messageAmounts = getMessagesPerUser();
            writer.write("\nAmount of messages for all users: \n");
            writer.write(messageAmounts);

            String wordsAmounts = getWordsPerUser();
            writer.write("\nAmount of words for all users: \n");
            writer.write(wordsAmounts);

            String messagepPercentage = getPercentStatictics();
            writer.write("\nAmount of messages and words statistics in percents: \n");
            writer.write(messagepPercentage);
            
             
            writer.flush();
            
            System.out.println("Statictics has been saved");
        }
        catch(IOException ex){
             
            System.out.println(ex.getMessage());
        } 
	}

}
