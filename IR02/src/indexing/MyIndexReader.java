package indexing;

import java.io.IOException;
import java.util.ArrayList;

import indexing.TermList.Node;

/**
 *  
 */
public class MyIndexReader {
	//you are suggested to write efficient code here, otherwise, your memory cannot hold the whole corpus...
	TermList indexList;
	ArrayList<Integer> idIndex;
	ArrayList<String> noIndex;


	public MyIndexReader ( String type ) throws IOException {
		//read the index files you generated in task 1
		//remember to close reader when you finish using them
		//use appropriate structure to maintain the index
			indexList = MyIndexWriter.indexTable;
			idIndex = MyIndexWriter.docidIndex;
			noIndex = MyIndexWriter.docnoIndex;

	}

	//get the non-negative integer dociId for the requested docNo
	//If the requested docno does not exist in the index, return -1
	public int getDocid( String docno ) {
		int l = noIndex.size();
		for(int i=0; i<l; ++i){
			if(noIndex.get(i).equals(docno)){
				return MyIndexWriter.docNoTodocId(docno);
			}
		}
		return -1;
	}

	// Retrieve the docno given the integer docid
	public String getDocno( int docid ) {
      int m = idIndex.size();
			for(int k=0; k<m; ++k){
				if(idIndex.get(k).equals(docid)){
					return noIndex.get(k);
				}
			}
			return null;
	}

	/**
	 * Get the posting list for the requested token.
	 *
	 * The posting list records 1.the documents' docids which contain given token and 2.corresponding frequencies of the term, such as:
	 *
	 *  [docid]		[freq]
	 *  1			3
	 *  5			7
	 *  9			11
	 *  13			19
	 *
	 * ...
	 *
	 * In the returned 2-dimension array, the first dimension refers to each document, and the second dimension records the docid and frequency.
	 *
	 * For example:
	 * array[0][0] records the docid of the first document the token appears.
	 * array[0][1] records the frequency of the token in the documents with docid = array[0][0]
	 * ...
	 *
	 * NOTE that the returned posting list should be ranked by docid from the smallest to the largest.
	 *
	 * @param token
	 */
	public int[][] getPostingList( String token ) throws IOException {
		long size = indexList.size;
		Node element = indexList.first();
		int[][] postingList = null;
		for(int i=0; i<size; ++i){
			if(element.getTerm().equals(token)){
				ArrayList<Integer> posting = element.getPosting();
				int innerSize = posting.size();
				postingList = new int[innerSize/2][2];
				for (int k=0; k<innerSize; k+=2){
					postingList[k][0]=posting.get(k);
					postingList[k][1]=posting.get(k+1);
				}
			}
			element = element.getNext();
		}
		return postingList;
	}

	// Return the number of documents that contain the given token.
	public int DocFreq( String token ) throws IOException {
    Node element = indexList.first();
		long size = indexList.size;
		for(int j=0; j<size; ++j){
			if(element.getTerm().equals(token)){
				return (int) element.getDocFreq();
			}
		}//iterate throught the list to find fi there are match of the token
		return 0;
	}

	// Return the total number of times the token appears in the collection.
	public long CollectionFreq( String token ) throws IOException {
		Node element = indexList.first();
		long size = indexList.size;
		for(int j=0; j<size; ++j){
			if(element.getTerm().equals(token)){
				return element.getCollectionFreq();
			}
		}
		return 0;
	}

	public void close() throws IOException {
	}

}
