import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
public class FileManager {

	//Attributes
	
	private String SCORE_TXT_DIR;
	private FileManager fileManager;
	private FileWriter writer;
	private FileReader reader;
	
	//Constructor
	
	private FileManager() {
            try {
				writer = new FileWriter("Scores.txt", true);
			} catch (IOException e) {
				e.printStackTrace();
			}
            try {
				reader = new FileReader("Scores.txt");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
            createFileManager();
	}
	
	
	//Methods
	
	public void writeToFile (String highscoreList) {
		try {
			writer.write(highscoreList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String readFromFile() {
		int score;
		String returnSt ="";
		 
        try {
			while ((score = reader.read()) != -1) {
			    returnSt += (char)score;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
        try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return returnSt;
        
	}
	
	public void createFileManager() {
		fileManager = new FileManager();
	}
	
	public FileManager getFileManager() {
		return fileManager;
	}
	
}
