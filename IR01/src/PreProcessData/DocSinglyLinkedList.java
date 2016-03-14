package PreProcessData;
/*this class create a singly linked list to navigate through document
*/
public class DocSinglyLinkedList <E>{

  //-------this is the start for nested Node class--------//
  private static class Node<E>{
      private E element;
      private Node<E> next;

      public Node(E e, Node<E> n){
        element = e;
        next = n;
      }

      public E getElement(){return element;}
      public Node<E> getNext(){return next;}
      public void setNext(Node<E> n){next = n;}

  }
  //-------this is the end of nested Node class-------//

  //instance variabel for DocSinglyLinkedList class
  private Node<E> head = null;
  private Node<E> tail = null;
  protected int size = 0;
  public DocSinglyLinkedList(){}//constructor

  //access methods
  public int size(){return size;}
  public boolean isEmpty(){return size==0;}
  public E first(){
     return head.getElement();
  }
  public E last(){
    return tail.getElement();
  }
  //update methods
  public void addFirst(E e){
    head = new Node<E>(e, head);
    if(size == 0)
    tail = head;
    size++;
  }

  public void addLast(E e){
    Node<E> newest = new Node<E>(e, null);
    if(isEmpty()){
      head = newest;
    }else{
      tail.setNext(newest);
    }

    tail = newest;
    size++;
  }

  public E removeFirst(){
    if(isEmpty()) return null;
    E answer = head.getElement();
    head = head.getNext();
    size--;
    if(size == 0)
    tail = null;

    return answer;
  }
  

}
