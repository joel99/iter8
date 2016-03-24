//Team Double Trouble - Joel Ye and Jennifer Yu
//APCS1 pd10
//HW15 -- Generically Speaking
//2016 - 03 - 17

public class DLLNode<T>{
    private T val;
    private DLLNode next;
    private DLLNode prev;

    public DLLNode (T val){
	this.val = val;
    }
    public DLLNode (T val, DLLNode<T> prev, DLLNode<T> next){
	this.val = val;
	this.next = next;
    }
    public T getVal(){
	return val;
    }
    public T setVal(T newVal){
	T temp = val;
	val = newVal;
	return temp; 
    }
    public DLLNode<T> getNext(){
	return next;
    }
    public DLLNode<T> setNext(DLLNode<T> newNext){
	DLLNode<T> temp = next;
	next = newNext;
	return temp;
    }

    public DLLNode<T> getPrev(){
	return prev;
    }

    public DLLNode<T> setPrev(DLLNode<T> newPrev){
	DLLNode<T> temp = prev;
	prev = newPrev;
	return temp;
    }

    public String toString(){
	return val.toString();
    }
}
