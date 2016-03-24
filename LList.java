//Team iter8 -- Ricky Wu and Joel Ye
//APCS1 pd10
//HW18
//2016 - 03 - 24

import java.util.Iterator;
public class LList<T> implements List<T>{

    private DLLNode<T> head;
    private DLLNode<T> tail;
    private int size;
    
    public LList(){
	head = null;
	size = 0;
    }
        
    public int size(){
	return size;
    } //size
	

    public Iterator<T> iterator(){
	return new MyIterator();
    }

    private class MyIterator implements Iterator<T>{
	
	private DLLNode<T> _curr;
	private boolean nexted;

	public MyIterator(){
	    _curr = head;
	}

	public boolean hasNext(){
	    return _curr.getNext() != null;
	}
	
	public T next(){
	    _curr = _curr.getNext();
	    nexted = true;
	    return _curr.getCargo();
	}
	
	public void remove(){
	    if (nexted){
	    size--; 
	    if (_curr == head) { 
		head = head.getNext();
		head.setPrev(null); 
		_curr = head;
		return;} 
	    if (_curr == tail) {
		tail = tail.getPrev();
		tail.setNext(null); 
		_curr = tail;
		return;
	    }
	    _curr.getPrev().setNext(_curr.getNext());
	    _curr.getNext().setPrev(_curr.getPrev()); 
	    _curr = _curr.getNext();
	}
	}
	
    }
    public boolean add(T y){ //add
	try{
	    if (size == 0) {
		tail = new DLLNode(y);
		head = tail;
	    }
	    else {
		DLLNode<T> newTail = new DLLNode<>(y); //create alias for new node
		newTail.setPrev(tail); //set prev of new tail as former tail
		tail.setNext(newTail); //set next of former tail as new tail
		tail = tail.getNext(); //set new tail as tail
	    }
	    size ++; //increment size
	    return true; //it worked!
	}
	catch(Exception e){return false;} //it didn't work...
    }

    public void add( int index, T newVal ) {

	if ( index < 0 || index > size() )
	    throw new IndexOutOfBoundsException();

	else if ( index == size() ) 
	    addLast( newVal );

	DLLNode newNode = new DLLNode( newVal, null, null );

	//if index==0, insert node before head node
	if ( index == 0 ) 
	    addFirst( newVal );
	else {
	    DLLNode tmp1 = head; //create alias to head

	    //walk tmp1 to node before desired node
	    for( int i=0; i < index-1; i++ )
		tmp1 = tmp1.getNext();

	    //init a pointer to node at insertion index
	    DLLNode tmp2 = tmp1.getNext(); 

	    //insert new node
	    newNode.setNext( tmp2 );
	    newNode.setPrev( tmp1 );
	    tmp1.setNext( newNode );
	    tmp2.setPrev( newNode );

	    //increment size attribute
	    size++;

	}
    }

    public void addFirst( T newFirstVal ) { 
	//insert new node before first node (prev=null, next=_head)
	head = new DLLNode<T>( newFirstVal, null, head );

	if ( size == 0 ) 
	    tail = head;
	else 
	    head.getNext().setPrev( head );
	size++;
    }

    public void addLast( T newLastVal ) { 
	//insert new node before first node (prev=_last, next=null)
	tail = new DLLNode<T>( newLastVal, tail, null );

	if ( size == 0 ) 
	    head = tail;
	else 
	    tail.getPrev().setNext( tail );
	size++;
    }

    public T remove(int i){ 
	if ( i < 0 || i >= size )
	    throw new IndexOutOfBoundsException();
	size--;
	DLLNode<T> temp = head;
	if (size() == 1){
	    head = tail = null;
	    size--;
	    return null;
	}
	if (i == 0) { 
	    head = head.getNext();
	    head.setPrev(null); 
	    return temp.getCargo();} 
	if (i == size-1) {
	    DLLNode<T> temp2 = tail;
	    tail = tail.getPrev();
	    tail.setNext(null); 
	    return temp2.getCargo();
	}
	for (; i > 0; i--){ 
	    temp = temp.getNext(); 
	}
	T ret = temp.getCargo(); 
	temp.getPrev().setNext(temp.getNext()); 
	if (temp.getNext() != null)
	    temp.getNext().setPrev(temp.getPrev()); 
	return ret;
    }

    public T get(int i){
	DLLNode<T> temp = head;
	while (i > 0) {temp = temp.getNext(); i--;}
	return temp.getCargo();
    };
	
    public T set(int i, T x){
	DLLNode<T> temp = head;
	while (i > 0) {temp = temp.getNext(); i--;}
	T ret = temp.getCargo();
	temp.setCargo(x);
	return ret;
    };

    public String toString() { 
	String retStr = "HEAD->";
	DLLNode<T> tmp = head; 
	while( tmp != null ) {
	    retStr += tmp.getCargo() + "->";
	    tmp = tmp.getNext();
	}
	retStr += "NULL";
	return retStr;
    }

    public static void main( String[] args ) {
	List<String> James = new LList<>();
	James.add("Food");
	James.add("Coelum");
	James.add("Poop");
	System.out.println(James.size());
	James.add(0, "Pokemon");
	James.add(3, "Lala");
	System.out.println(James);
	James.remove(4);
	System.out.println(James);
    }
}
