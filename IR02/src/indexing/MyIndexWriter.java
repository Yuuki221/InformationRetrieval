package indexing;

import java.io.IOException;
import java.util.*;

import indexing.TermList.Node;

/**
 * Please construct your code efficiently, otherwise, your memory cannot hold the whole corpus...
 *
 * 
 */
public class MyIndexWriter {
	public static TermList indexTable;
	public static ArrayList<Integer> docidIndex;
	public static ArrayList<String> docnoIndex;


    /**
     This constructor should initiate the FileWriter to output your index files
     remember to close files if you finish writing the index
    */
	public MyIndexWriter(String type) throws IOException {
		   indexTable = new TermList();//initialize the structure for indexing table
			 docidIndex = new ArrayList<Integer>();
			 docnoIndex = new ArrayList<String> ();
	}

    /**
     you are strongly suggested to build the index by installments
     in your implementation of the index, you should transform your
		 string docnos into non-negative integer docids !!!
     In MyIndexReader, you will need to request the integer docid for docnos.
    */
	public void index (String docno, String content) throws IOException {
		StringTokenizer st = new StringTokenizer(content);
		int docid = docNoTodocId(docno);
		//transform the String docNo to docId
		while(st.hasMoreTokens()){
			String newWord = st.nextToken();
			TermList.Node element = indexTable.first();
		for(int i=0; i<indexTable.size; ++i){
			if(newWord.equals(element.getTerm())){
				element.getPosting().add(docid);
				//int index = element.getPosting().size();
				element.getPosting().add(1);
				element.changeDocFreq (1);
				element.changeCollectionFreq(1);
				break;
			}//if the word has already exist in the table then skip it
			element = element.getNext();
			// if not keep iterate until the end of the table
		}
		Node newOne = new Node(newWord, 0, 0, null, null, null);
		newOne.setPrev(element);
		element.setNext(newOne);
		element.getNext().getPosting().add(docid);
		element.getNext().getPosting().add(1);
		element.changeDocFreq (1);
		element.changeCollectionFreq(1);
		//update the linked list if there are new words appear
		//update their frequency also
	 }
	}

	/**
	 *   close the index writer, and you should output all the buffered content (if any).
     *   and if you write your index into several files, you need to fuse them here.
	 */
	public void close() throws IOException {
	}

	public static int docNoTodocId (String docno){
		int l=docno.length();
		StringBuilder id = new StringBuilder();
		for(int i=0; i<l; ++i){
			if(Character.isDigit(docno.charAt(i))){
				id.append(docno.charAt(i));
			}else if(((Character)docno.charAt(i)).equals(".")){
				continue;
			}else{
				int numTrans = docno.toUpperCase().charAt(i)-'A';
				if(numTrans/10==0){
					id.append('0');
					id.append(numTrans);//if the number returned is a single digit number,
				}else{								// then 0 will be append before it
					id.append(numTrans);//otherwise append the number directly
				}
			}
		}
		return Integer.parseInt(id.toString());
	}
}
