package PreProcessData;

import Classes.Path;
//the above code cannot be changed
import java.util.*;
import java.io.*;
import PreProcessData.DocSinglyLinkedList;
/**
 *
 *
 * Implementation of DocumentCollection
 *
 */
public class TrectextCollection implements DocumentCollection{
	//you can add essential private methods or variables
  static File file;
  static FileInputStream textFile;
  static BufferedReader br;
  
  int docNumber=0;
  
  Path textFilePath;
  DocSinglyLinkedList<String> docChain;
  Map<String, char[]> textTable;

	// YOU SHOULD IMPLEMENT THIS METHOD
	public TrectextCollection() throws IOException {
		// This constructor should open the file in Path.DataTextDir
		// and also should make preparation for function nextDocument()
    // Do not load the whole corpus into memory!!!
  
	file = new File(Path.DataTextDir);
	textFile = new FileInputStream(file);
	br = new BufferedReader(new InputStreamReader(textFile));

    docChain = (DocSinglyLinkedList<String>)getDocChain()[0];
    textTable = (Hashtable<String,char[]>)getDocChain()[1];
	}// end of constructor

  //this method is uesd to get the document chain
  private Object[] getDocChain() throws IOException{
      //int textIndex = 0;
	  
	  file = new File("/Users/pengxuechan/Desktop/src/testSample.txt");
	  textFile = new FileInputStream(file);
	  br = new BufferedReader(new InputStreamReader(textFile));
	  String line = br.readLine(), title;

    DocSinglyLinkedList<String> tempList = new DocSinglyLinkedList<String>();
	Hashtable<String, char[]>	tempTable = new Hashtable<String, char[]>();		//StringBuffer sbContent = new StringBuffer();
    //for each document content shoudl have a new StringBuffer;
	tempTable.put("This is an place holder","This is an place holder".toString().toCharArray());
	tempList.addLast("This is an place holder");
	line = br.readLine();
	while(br.read()!=-1){
      StringBuffer sbContent = new StringBuffer();
      while(!line.trim().startsWith("<DOCNO>")){
        line = br.readLine();
      }//read lines until reach id
      //store the one document in a string buffer line by line

			int i = line.indexOf(">");
			int k = line.lastIndexOf("<");
			title = line.substring(i+1,k);
			//get the document title using substring

      while(!line.trim().equals("<TEXT>")){
          line = br.readLine();
        }
       //keep reading until get to start of text content
          line = br.readLine();
       //keep reading to skip the </TEXT> tag
       while(!line.trim().equals("</TEXT>")){
         sbContent.append(line);
         line = br.readLine();
       }
       //get the content of the TEXT file

    tempTable.put(new String(title), new StringBuffer(sbContent).toString().toCharArray());
    tempList.addLast(new String(title));
    line = br.readLine();
      //put title to the key and put stringbuffer sbContent as document content
		} //keep reading the file until read the end
	docNumber++;
  
    try{
      textFile.close();
    }catch(IOException e) {
      System.out.println("There is something wrong with Closing File.");
    }//close the file after reading it


  return  new Object[]{tempList,tempTable};
}// this is the end of getDocChain method;

	// YOU SHOULD IMPLEMENT THIS METHOD
	public Map<String, Object> nextDocument() throws IOException {
		// this method should load one document from the corpus, and return this document's number and content.
		// the returned document should never be returned again.
		// when no document left, return null
		// NTT: remember to close the file that you opened, when you do not use it any more
		
    //if all the document has been loaded
   // Map tempReturn = (Map)textTable;
    String removedID = docChain.removeFirst();
    textTable.remove(removedID);
    if(docChain.size==0){
    	return null;
    }else{
    	return (Map)textTable;
    }
    
    }//if the hashtable is not empty remove the read document and return the new table
	//the below code is used for testing the class
	public static void main (String[] args) throws Exception{
		TrectextCollection test = new TrectextCollection();
		System.out.println(test.textTable.size());
		System.out.println(test.docChain.size());
		
		Map<String, Object> doc ;
		DocumentCollection collection = new TrectextCollection();
		
		while ((doc = collection.nextDocument()) != null){
		String docno = doc.keySet().iterator().next();
		
		char[] content = (char[])doc.get(docno);
		
		System.out.println(content);
		}
		
	}

}
