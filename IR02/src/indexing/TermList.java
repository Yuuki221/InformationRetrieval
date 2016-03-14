package indexing;

import java.util.ArrayList;

/**
 * store each term in a double linked list with Node of 5 different field
 * position list, document frequency, collection frequency
 * document frequency and collection frequency could be computed using posting list.
 * 
 *
 */
public class TermList {

	//--------Inner class of linked list------------------
	public static class Node{
		public long documentFreq=0;
		public long collectionFreq=0;
		public ArrayList<Integer> posting;
		public Node next;
		public Node prev;
		public String word;
		public Node(String term, long dFreq, long cFreq, Node n, Node p, ArrayList<Integer> post){
			documentFreq = dFreq;
			collectionFreq = cFreq;
			posting = post;
			next = n;
			prev = p;
			word = term;
		}
		//accessor
		public long getDocFreq(){return documentFreq;}
		public long getCollectionFreq(){return collectionFreq;}
		public ArrayList<Integer> getPosting(){return posting;}
		public Node getNext(){return next;}
		public Node getPrev(){return prev;}
		public String getTerm(){return word;}

		//setter
		public void setNext(Node n){next = n;}
		public void setPrev(Node n){prev = n;}
		public void setDocFreq(long f){ documentFreq = f;}
		public void setCollectionFreq(long f){collectionFreq = f;}
		public void changeDocFreq (int change){documentFreq+=change;}
		public void changeCollectionFreq(int change){collectionFreq+=change;}
	}
	//----------Node nested class ended here ----------
	//methods for TermList class
	public int size = 0;
	public Node header;
	public Node trailer;
	public TermList(){
		header = new Node (null, 0, 0, null, null, null);
		trailer = new Node (null, 0, 0, null, header, null);
		header.setNext(trailer);
	}//initialize

	public int size(){return size;}
	public boolean isEmpty(){
		return size==0;
	}
	//accessor
	public void addFirst(String s){
		Node temp = trailer.getPrev();
		Node newest = new Node(s,0, 0, null, null, null);
		trailer.setPrev(newest);
		newest.setPrev(temp);
		temp.setNext(newest);
		newest.setNext(trailer);
		size++;
	}

	public void addLast(String s){
		Node temp = trailer.getPrev();
		Node newest = new Node(s, 0, 0, null, null, null);
		trailer.setPrev(newest);
		newest.setPrev(temp);
		temp.setNext(newest);
		newest.setNext(trailer);
		size++;
	}
	//get access to the node in table 
	public Node first(){
		return header;
	}
	//update method for document frequency 
	

}
