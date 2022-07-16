package simplilearn.phase.one.project;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LockerOperationsImpl implements LockerOperations {

	@Override
	public void listFiles(String path, String filename, String opTyp) {
		
		if (path == null || path.isEmpty() || path.isBlank())
			throw new NullPointerException("Path cannot be Empty or null");
		
		File dir = new File(path);
		
		if(!dir.exists())
			throw new IllegalArgumentException("Path does not exist");
		
		if(dir.isFile())
			throw new IllegalArgumentException("The given path is a file. Please provide a Directory path.");
		
		String [] files = dir.list();
		
		// To list all the files
		if ((filename == null || filename.isEmpty() || filename.isBlank()) && opTyp.equalsIgnoreCase("list"))
		{			
			System.out.println("\n***********************************");
			if(files != null && files.length > 0) {
				
				Set<String>filesList = new TreeSet<String>(Arrays.asList(files)); 
				System.out.println("The Files in "+ dir.getAbsolutePath() + " are: \n");
				for(String file:filesList) {
					
					System.out.println(file);
					
				}
				
				System.out.println("\nTotal Number of files: "+ filesList.size());	
			}else {
				System.out.println("Directory is Empty");
			}
		}
		else if ((filename != null || !filename.isEmpty() || !filename.isBlank()) && opTyp.equalsIgnoreCase("search")) // search files based on filename input
		{	
			boolean flag = false;
			
			Pattern p = Pattern.compile(filename);
			if(files != null && files.length > 0) {
				for(String file:files) {
					Matcher m = p.matcher(file);
					if(m.matches()) {
						System.out.println("File Found at location: " + dir.getAbsolutePath());
						flag = true;
						break;
					}
				}
			}
			
			if(flag == false)
				System.out.println("File Not Found.. Please try again.");		
		}
		
	}

	@Override
	public void addFiles(String path, List<String> fileList) throws IOException {
		
		boolean isValidPath = isPathValid(path);
		
		if(!isValidPath)
			throw new IllegalArgumentException("Please provide a valid path.\n--Path cannot be null or empty.\n--Path mentioned cannot be a File.");
		
		if (fileList == null || fileList.isEmpty())
			throw new NullPointerException("File Name cannot be Empty or null");
		
		for(String file : fileList)
		{
			if(file == null || file.isEmpty() || file.isBlank())
			{
				throw new NullPointerException("File Name cannot be Empty or null");			
			}
			File finalFile = new File(path + File.separator + file);
			addFiles(path, finalFile);
		}
	}

	@Override
	public void deleteFiles(String path, List<String> fileList) {
		
		boolean isValidPath = isPathValid(path);
		
		if(!isValidPath)
			throw new IllegalArgumentException("Please provide a valid path.\n--Path cannot be null or empty.\n--Path mentioned cannot be a File.");
		
		if (fileList == null || fileList.isEmpty())
			throw new NullPointerException("File Name cannot be Empty or null");
		
		for(String file : fileList)
		{
			if(file == null || file.isEmpty() || file.isBlank())
			{
				throw new NullPointerException("File Name cannot be Empty or null");			
			}
			File finalFile = new File(path + File.separator + file);
			deleteFiles(path, finalFile);
		}
	}

	@Override
	public void addFiles(String path, File filename) throws IOException {
		
		boolean isFileCreated = filename.createNewFile();
		
		if (isFileCreated) {
			System.out.println("\nFile Successfully Created: " + filename.getAbsolutePath());
		}else if(!isFileCreated) {
			System.out.println("\nFile Already Exist.. Please try again." );
		}
	}

	@Override
	public void deleteFiles(String path, File filename) {

		boolean isFileDeleted = filename.delete();
		
		if (isFileDeleted) {
			System.out.println("\nFile deleted Successfully");
		}else {
			System.out.println("\nFile Not Found.. Please try again." );
		}
	}
	
	public boolean isPathValid(String path)
	{
		boolean isValid = true;
		if (path == null || path.isEmpty() || path.isBlank())
		{
			isValid = false;
		}
		
		File dir = new File(path);
		
		if(dir.isFile())
		{
			isValid = false;
		}

		return isValid;
	}

}
