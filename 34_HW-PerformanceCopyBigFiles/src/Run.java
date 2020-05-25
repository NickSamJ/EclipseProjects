import java.nio.file.*;

import telran.tests.performance.CopyFilePerformance;
import telran.util.CopyFileStreamsImpl;
import telran.util.CopyFileFilesImpl;

public class Run {
	public static void main(String[] args) {
		FileSystem fs =  FileSystems.getDefault();
		
		String basename = "video";
		String fileExtentsion = "avi";
		int currId = 0;
		
		int[] streamsBufferSizes = {100, 102400, (int)Runtime.getRuntime().freeMemory()};
 		
		Path from = fs.getPath("src/test.avi");
		
		CopyFilePerformance CopyUsingFiles = new CopyFilePerformance(
				"Copying using class \"Files\"",
				from, 
				fs.getPath(basename + currId++ + "." + fileExtentsion), 
				new CopyFileFilesImpl()
			); 
		
		CopyFilePerformance CopyUsingStreams100b = new CopyFilePerformance(
				"Copying using Streams. Buffer size = 100 bytes",
				from, 
				fs.getPath(basename + currId++ + "." + fileExtentsion), 
				new CopyFileStreamsImpl(streamsBufferSizes[0])
			); 
		
		CopyFilePerformance CopyUsingStreams100M = new CopyFilePerformance(
				"Copying using Streams. Buffer size = 100 Mb",
				from, 
				fs.getPath(basename + currId++ + "." + fileExtentsion), 
				new CopyFileStreamsImpl(streamsBufferSizes[1])
			); 
		
		CopyFilePerformance CopyUsingStreamsMax = new CopyFilePerformance(
				"Copying using Streams. Buffer size = Runtime.getRuntime().freeMemory()",
				from, 
				fs.getPath(basename + currId++ + "." + fileExtentsion), 
				new CopyFileStreamsImpl(streamsBufferSizes[2])
			); 
		
		CopyUsingFiles.run();
		CopyUsingStreams100b.run();
		CopyUsingStreams100M.run();
		CopyUsingStreamsMax.run();
	}
	

}
