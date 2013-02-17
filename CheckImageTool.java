import java.io.*;
import java.util.*;

public class CheckImageTool{

	private static String PROPERTY_FILE_NAME = "config.properties";
	private static String SOURCE_SUFFIX = "source_suffix";
	private static String TARGET_SUFFIX = "target_suffix";
	private static String SOURCE_FILE_PATH = "source_file_path";
	private static String TARGET_FILE_PATH = "target_file_path";

	public static void main(String []args) throws Exception{

		Properties properties = ReadProperties.getPropertiesObj(PROPERTY_FILE_NAME);
		String sourceSuffix = properties.getProperty(SOURCE_SUFFIX);
		String []sourceSuffixArray = sourceSuffix.split(",");
		List<String> fileNameArray = new ArrayList<String>();
		for(String suffix : sourceSuffixArray){
			fileNameArray.addAll(FileViewer.getListFiles(properties.getProperty(SOURCE_FILE_PATH), suffix, true));
		}
		//System.out.println(fileNameArray);
	
		String targetSuffix = properties.getProperty(TARGET_SUFFIX);
		//System.out.println("targetSuffix" + targetSuffix);
		String []targetSuffixArray = targetSuffix.split(",");
		List<String> targetFilePathArray = new ArrayList<String>();
		for(String suffix : targetSuffixArray){
			targetFilePathArray.addAll(FileViewer.getListFilePaths(properties.getProperty(TARGET_FILE_PATH), suffix, true));
		}
		//System.out.println(targetFilePathArray);
		for(String imageName : fileNameArray) {
			//System.out.println(imageName);
			boolean used = false;
			for(String filePath : targetFilePathArray){
				File file = new File(filePath);
				if(file.exists()){
					BufferedReader br = new BufferedReader(new FileReader(filePath));  
					String data = br.readLine(); 				
					while(data!=null){  
      					if(data.indexOf("\"" + imageName) >= 0) {
     		 				used = true;
     		 				break;
     		 			} else {
     		 				data = br.readLine(); 
     		 			}
					}
					br.close();
					if(used){
						break;				
					}
				}
			}
			if(!used){
				System.out.println("The image " + imageName + " is not used in the App.");
			}
		}

	}
}