package indexing;

import java.util.HashMap;
import java.util.Map;

import java.io.*;
/**
 * This is a simple corpus reader
 * 
 */
public class PreProcessedCorpusReader {
	static File file;
	static FileInputStream fileInput;
	static BufferedReader br;

    /*
     This constructor should open the pre-processed corpus file, Path.ResultHM1 + type
     which was generated in assignment 1
     remember to close the file that you opened, when you do not use it any more
     */
	public PreProcessedCorpusReader(String type) throws IOException {
		if(type.equals("trectext")){
			file = new File(Path.DataTextDir);
		} else {
			file = new File(Path.DataWebDir);
		}
			fileInput = new FileInputStream(file);
			br= new BufferedReader(new InputStreamReader(fileInput));
			//prepare for the reading of text;
	}


	/*
     read a line for docNo, then read another line for the word list
     put them in a map, and return it
    */
	public Map<String, String> nextDocument() throws IOException {
		Map<String, String> doc = new HashMap<String, String>();
		String line;
		while((line = br.readLine())!=null){
			StringBuffer wordList = new StringBuffer();
			String docNo = new String();
			docNo = line; //put the title into the docNo
			while(Character.isLowerCase(line.charAt(0))){
				wordList.append(line);
				line = br.readLine();
			}//read the content until the start of the title of next document
			 //finishing processing one document
			 doc.put(docNo, wordList.toString());
		}
		return doc;
	}

}
