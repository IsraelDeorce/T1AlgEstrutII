package trabalhoUm;

import trabalhoUm.GeneralTreeOfStrings.Node;

public class LinkedQueueOfNodes {
	private Nodo head;
	private Nodo tail;
	private int count;
	
	private class Nodo {
		public Node element;
		public Nodo next;
		
		public Nodo(Node e) {
			element = e;
			next = null;
		}
	}
	
	public LinkedQueueOfNodes() {
		head = null;
		tail = null;
		count = 0;
	}
	
	public void enqueue(Node e){
		Nodo n = new Nodo(e);
		if(count == 0) {
			head = n;
		} else {
			tail.next = n;
		}
		tail = n;
		count++;
	}
	
	public Node dequeue() {
		if(count == 0) 
			throw new IndexOutOfBoundsException("Cannot dequeue, queue is empty.");
		Node aux = head.element;
		head = head.next;
		count--;
		return aux;
	}
	
	public Node head() {
		if(count == 0) 
			throw new IndexOutOfBoundsException("Cannot show head, queue is empty.");
		return head.element;
	}
	
	public int size() {
		return count;
	}
	
	public boolean isEmpty() {
		return count==0;		
	}
	
	public void clear() {
		count = 0;
	}
	
	//toString
	@Override
	public String toString() {
		String aux = "";
		Nodo n = head;
		for(int i=0; i<count; i++) {
			aux += (n.element + "\n");
			n = n.next;
		}
		return aux;
	}
}