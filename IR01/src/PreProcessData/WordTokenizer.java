package PreProcessData;
import java.io.IOException;
import java.util.*;
/**
 * 
 *
 * TextTokenizer can split a sequence of text into individual word tokens, the delimiters can be any common punctuation marks(space, period etc.).
 */
public class WordTokenizer {
	//you can add any essential private method or variable
	DocSinglyLinkedList<String> words;
	// YOU MUST IMPLEMENT THIS METHOD
	public WordTokenizer( char[] texts ) {
		// this constructor will tokenize the input texts (usually it is the char array for a whole document)
		 words = getToken(texts);
	}

	private DocSinglyLinkedList<String> getToken ( char[] text){
		DocSinglyLinkedList<String> wordsList = new DocSinglyLinkedList<String>();
		StringBuffer newWord;
		for (int i=0; i<=text.length-1;i++){
			newWord = new StringBuffer(); 
			if((text[i]>=97&&text[i]<=122)||(text[i]>=65&&text[i]<=90)){
			for (;i<=text.length-1 &&(text[i]>=97&&text[i]<=122)||(text[i]>=65&&text[i]<=90);i++)
				{
				       newWord.append(text[i]);
				}
			wordsList.addLast(newWord.toString());	
				}
			}
			//wordsList.add(newWord.toString());
		
		
		
		return wordsList;
	}

	// YOU MUST IMPLEMENT THIS METHOD
	public char[] nextToken() {
		// read and return the next word of the document
		// or return null if it reaches the end of the document
		
	    if(words.size==0){
	    	return null;
	    }else{
	    	String removedID = words.removeFirst();
	    	return removedID.toCharArray();
	    }
		
	}
/*	The following code is used to test the class 
	public static void main(String[] args) throws IOException{
		Map<String, Object> doc = null;
		DocumentCollection corpus = new TrectextCollection();
		doc = corpus.nextDocument();
		String docno = doc.keySet().iterator().next();
		StringBuffer wr = new StringBuffer();
		char[] content = (char[]) doc.get(docno);
		WordNormalizer normalizer = new WordNormalizer();
		StopWordRemover stopwordRemover = new StopWordRemover();
		System.out.println(content);
		
		//System.out.println(getToken(content).size);
		//System.out.println(getToken(content).removeFirst());
		
		
		WordTokenizer tokenizer = new WordTokenizer(content);
		
		char[] word = null;
		while((word = tokenizer.nextToken())!=null){
		    
			System.out.println(word);	
		}
		
		System.out.println(wr);

}
*/
}
