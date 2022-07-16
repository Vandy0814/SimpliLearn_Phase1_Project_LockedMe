package simplilearn.phase.one.project;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface LockerOperations {
	
	//To list files based on the parameter passed
	public abstract void listFiles(String path, String filename, String opTyp);
	
	//To add a new file to an existing directory
	//public abstract void addFiles(String path, String filename) throws IOException;
	
	//To add multiple new files to an existing directory
	//public abstract void addFiles(String path, List<String> filename) throws IOException;
	
	//To delete an existing file
	//public abstract void deleteFiles(String path, String filename);
	
	//To delete multiple existing files
	//public abstract void deleteFiles(String path, List<String> filename);

	//To add a new file(s) to an existing directory
	public void addFiles(String path, File filename) throws IOException;

	//To add a new file(s) to an existing directory
	public void addFiles(String path, List<String> fileList) throws IOException;

	//To delete file(s) to an existing directory
	public void deleteFiles(String path, File filename);
	
	//To delete file(s) to an existing directory
	public void deleteFiles(String path, List<String> fileList);

}
