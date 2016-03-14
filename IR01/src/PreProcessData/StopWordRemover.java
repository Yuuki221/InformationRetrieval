package PreProcessData;
import Classes.Path;
//above code cannot be changed
import java.util.*;
import java.io.*;

/**
 * 
 *
 * StopWordRemover is a class takes charge of judging whether a given word
 * is a stopword by calling the method <i>isStopword(word)</i>.
 */
public class StopWordRemover {
	//you can add essential private methods or variables
	final int alphebatArrayLength = 26;

	File stopWordFile;
	FileInputStream stopInput;
	BufferedReader stopBr;
	DocSinglyLinkedList<String> stopWordList;
	Path stopWordPath;


	public StopWordRemover() {
		// load and store the stop words from the fileinputstream with appropriate data structure
		// that you believe is suitable for matching stop words.
		// address of stopword.txt should be Path.StopwordDir
	 try{
		stopWordFile = new File(Path.StopwordDir);
		stopInput = new FileInputStream(stopWordFile);
		stopBr = new BufferedReader(new InputStreamReader(stopInput));
	 }catch(FileNotFoundException e){
		 System.out.println("Can not access to the file.");
	 }
	 try{
		stopWordList = getStopWordList();
	 }catch (IOException e){
		 System.out.println("Something wrong with I/O.");
	 }
	}

	private DocSinglyLinkedList<String> getStopWordList() throws IOException {

		String line = stopBr.readLine();
		DocSinglyLinkedList<String> temp = new DocSinglyLinkedList<String>();
		while(line!=null){
			
			temp.addLast(line);
			line = stopBr.readLine();

		}
			try{
				stopInput.close();
			}catch(IOException e){
				System.out.println("File is not closed successfully.");
			}//close the file

			return temp;
		}//end of getStopWordTable method


	// YOU MUST IMPLEMENT THIS METHOD
	public boolean isStopword( char[] word ) {
		// return true if the input word is a stopword, or false if not
		String testWord = String.valueOf(word).trim();
		
		DocSinglyLinkedList<String> compareTemp = stopWordList;
		/*
		if(testWord.equals(stopWordList.first())){
			return true;
		}
		*/
		while(!((compareTemp.removeFirst().trim()).equals(testWord))&&compareTemp.size!=0){}
		
		if(compareTemp.size==0){
			return false;
		}else{
			return true;
		}
	}
/* this part is used to test	
	public static void main(String[] args) throws IOException{
		char[] testData = "Horu".toCharArray();
		StopWordRemover s = new StopWordRemover();
		DocSinglyLinkedList<String> n = s.stopWordList;
		System.out.println(s.isStopword(testData));
		
		//System.out.println(n.removeFirst());
		//n.removeFirst();
		//System.out.println(n.removeFirst());
		//n.removeFirst();
		//System.out.println(n.removeFirst());
		//System.out.println(n.first());
		
	}
*/
}
