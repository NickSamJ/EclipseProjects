package stats.runner;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import stats.util.ChatStatistics;

public class Runner {
	public static void main(String[] args) {
		String fileSource = "src/chat.txt";
		
		ChatStatistics stat = new ChatStatistics(fileSource);
//		stat.printMessagesPerUser();
//		stat.printTotalMessages();
		stat.printPercentStatictics();
		stat.printAveragePercent();
//		stat.saveAllStatistics();
	}

}
