package PreProcessData;

import Classes.Path;
//above code cannot be changed
import java.util.*;
import java.io.*;
import PreProcessData.DocSinglyLinkedList;

/**
 * 
 *
 * Implementation of DocumentCollection
 */
public class TrecwebCollection implements DocumentCollection {
	//you can add essential private methods or variables
	static File fileWeb;
	static FileInputStream webFile;
	static BufferedReader br;
	//Path webFilePath;
	
	DocSinglyLinkedList<String> webChain;
	Map<String, char[]> webTable;

	int webNumber;
	// YOU SHOULD IMPLEMENT THIS METHOD
	public TrecwebCollection() throws IOException {
		// This constructor should open the file in Path.DataWebDir
		// and also should make preparation for function nextDocument()
		// Do not load the whole corpus into memory!!!

		 fileWeb = new File(Path.DataWebDir);
		 webFile = new FileInputStream(fileWeb);
			  // webFile = new FileInputStream(fileWeb);
		 br = new BufferedReader(new InputStreamReader(webFile));

		 webChain = (DocSinglyLinkedList<String>)getWebChain()[0];
		 webTable = (Hashtable<String, char[]>)getWebChain()[1];
	}//end of constructor

	private Object[] getWebChain() throws IOException{
		//int webIndex = 0;
		fileWeb = new File("/Users/pengxuechan/Desktop/src/webSample.txt");
		webFile = new FileInputStream(fileWeb);			  // webFile = new FileInputStream(fileWeb);
		br = new BufferedReader(new InputStreamReader(webFile));
		
		String line=br.readLine(), title;

		DocSinglyLinkedList<String> tempLinkedList = new DocSinglyLinkedList<String>();
		Hashtable<String, char[]> tempHash = new Hashtable<String, char[]>();

		tempHash.put("This is a place holder", "This is a place holder".toString().toCharArray());
		//tempLinkedList.addFirst("This is a place holder");
		line = br.readLine();
		
		while (br.read()!=-1){
			StringBuffer sbContent = new StringBuffer();
			while(!line.trim().startsWith("<DOCNO>")){
				line = br.readLine();
			}
			//store the one web id in a string buffer line by line

			int i = line.indexOf(">");
			int k = line.lastIndexOf("<");
			title = line.substring(i+1,k);
			//get the document title

			while(!line.trim().equals("</DOCHDR>")){
					line = br.readLine();

				}
			 //keep reading to get the TEXT Content
			 //line = br.readLine();
			 //skip the </DOCHDR> tag
			 while(!line.trim().equals("</DOC>")){
				 while(line.indexOf("<")!=-1 && line.indexOf(">")!=-1){
					 int start = line.indexOf("<");
					 int end = line.indexOf(">");
					 String tag = line.substring(start,end+1);
					 line = line.replace(tag,"");
					 //sbContent.append(line);
				 }//remove all html tags from this line
				 sbContent.append(line);
				 line = br.readLine();
			 }
			 //get the content of the web file
		tempLinkedList.addFirst(new String(title));
		tempHash.put(new String(title), new StringBuffer(sbContent).toString().toCharArray());
		//tempLinkedList.addLast(new String(title));
			//put title to the key and put stringbuffer sbContent as document content
		}//keep reading the file until read the end
		tempLinkedList.addFirst("This is a place holder");
		try{
			webFile.close();
		}catch(IOException e) {
			System.out.println("There is something wrong with Closing File.");
		}//close the file after reading it


	return new Object[]{tempLinkedList,tempHash};

	}


	// YOU SHOULD IMPLEMENT THIS METHOD
	public Map<String, Object> nextDocument() throws IOException {
		// this method should load one document from the corpus, and return this document's number and content.
		// the returned document should never be returned again.
		// when no document left, return null
		// NT: the returned content of the document should be cleaned, all html tags should be removed.
		// NTT: remember to close the file that you opened, when you do not use it any more

		//if the hashtable is not empty remove the read document and return the new table
		String removedId = webChain.removeFirst();
		webTable.remove(removedId);
		if(webChain.size==0){
			return null;
		}else{
			return (Map)webTable;
		}
	}
/*	This part is use to test 
	public static void main (String[] args) throws Exception{
		TrecwebCollection test = new TrecwebCollection();
		System.out.println(test.webTable.size());
		System.out.println(test.webChain.size());
		
		Map<String, Object> doc ;
		DocumentCollection collection = new TrecwebCollection();
		
		//doc = collection.nextDocument();
		String docno = test.webTable.keySet().iterator().next();
		System.out.println(docno);
		char[] content = (char[])test.webTable.get(docno);
		System.out.println(content);
		test.webTable.remove(docno);
		
		
		//doc = collection.nextDocument();
		
		 docno = test.webTable.keySet().iterator().next();
		System.out.println(docno);
		content = (char[])test.webTable.get(docno);
		System.out.println(content);
		test.webTable.remove(docno);
		
		docno = test.webTable.keySet().iterator().next();
		System.out.println(docno);
		content = (char[])test.webTable.get(docno);
		System.out.println(content);
		test.webTable.remove(docno);
		
		System.out.println(test.webChain.removeFirst());
		System.out.println(test.webChain.removeFirst());
		System.out.println(test.webChain.removeFirst());
		
		
		//
	}

	
	public static void main (String[] args) throws Exception{
		TrecwebCollection test = new TrecwebCollection();
		System.out.println(test.webTable.size());
		System.out.println(test.webChain.size());
		
		Map<String, Object> doc ;
		DocumentCollection collection = new TrecwebCollection();
		
		while ((doc = collection.nextDocument()) != null){
		String docno = doc.keySet().iterator().next();
		
		char[] content = (char[])doc.get(docno);
		
		System.out.println(content);
		}
		
	}
*/
	
}

	

