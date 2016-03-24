//Team Double Trouble - Joel Ye and Jennifer Yu
//APCS1 pd10
//HW15 -- Generically Speaking
//2016 - 03 - 17
public class LList<T> implements List<T>{
    private DLLNode<T> head;
    private DLLNode<T> tail;
    private int size; //tracks size to avoid excessive calculation each time.
    
    public LList(){ //default constructor
	head = null;
	size = 0;
    }
        
    public int size(){
	return size;
    } //size
	
    public boolean add(T y){ //add
        DLLNode<T> x = new DLLNode<>(y);
	try{
	    if (size == 0) {
		tail = new DLLNode<T>(y);
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

    public void add(int i, T x){ //add-at-index
	if ( i < 0 || i > size ) //index is in range
	    throw new IndexOutOfBoundsException();
	if (i == 0) {
	    DLLNode<T> tmp = head; //set tmp to head
	    DLLNode<T> tmp2 = new DLLNode<T>(x); //set tmp2 as new node
	    tmp.setPrev(tmp2); //set prev of head to new node
	    tmp2.setNext(tmp); //set next of new node to head
	    tmp2.setPrev(null); //set prev of new node to null
	    head = tmp2; //set new node as new head
	    size++;
	    return;} //if adding to back, use add
	if (i == size-1) { //if last element use add
		add(x);
		size++;
		return;
	}
	DLLNode<T> tmp = head; //set alias to the head
	for (; i > 0; i--) {tmp = tmp.getNext();} //for tmp node at index
	tmp.getPrev().setNext(new DLLNode<T>(x)); //for tmp's previous' next, set next as new node
	tmp.getPrev().getNext().setNext(tmp); //for new node, set next as tmp
	tmp.setPrev(tmp.getPrev().getNext());
	size++; //increment size
    }	

    public T remove(int i){ //remove
	if ( i < 0 || i >= size ) //if not in range
	    throw new IndexOutOfBoundsException();
	size--;
	DLLNode<T> temp = head; //set alias to the head
	if (i == 0) { //if removing first node
	    head = head.getNext(); //set next node as new head
	    head.setPrev(null); //cut it off from removed node
	    return temp.getVal();} //return alias of old head
	if (i == size-1) {
		DLLNode<T> temp2 = tail; //set alias to the tail
		tail = tail.getPrev(); //set new tail
		tail.setNext(null); //set new tail's next to null
		return temp2.getVal(); //return alias
	}
	for (; i > 0; i--){ //if removing another node
	    temp = temp.getNext(); //set alias to node at index
	}
	T ret = temp.getVal(); //store first value
	temp.getPrev().setNext(temp.getNext()); //set prev node's next to next
	if (temp.getNext() != null)
	    temp.getNext().setPrev(temp.getPrev()); //set next node's prev to prev
	return ret; //return stored value
    }

    public T get(int i){
	DLLNode<T> temp = head;
	while (i > 0) {temp = temp.getNext(); i--;}
	return temp.getVal();
    };
	
    public T set(int i, T x){
	DLLNode<T> temp = head;
	while (i > 0) {temp = temp.getNext(); i--;}
	T ret = temp.getVal();
	temp.setVal(x);
	return ret;
    };

    // override inherited toString
    public String toString() { 
	String retStr = "HEAD->";
	DLLNode<T> tmp = head; //init tr
	while( tmp != null ) {
	    retStr += tmp.getVal() + "->";
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
