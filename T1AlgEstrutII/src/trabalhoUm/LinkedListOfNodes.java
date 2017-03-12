package trabalhoUm;

import trabalhoUm.GeneralTreeOfStrings.Node;

public class LinkedListOfNodes {
	
	private Nodo head;
	private Nodo tail;
	private int count;
	
	
	//==============================================Node
	private class Nodo {
		public Node element;
		public Nodo next;				
		
		public Nodo(Node element){
			this.element = element;
			next = null;
		}
	}			
	//===============================================End Node	
	
	
	
	public LinkedListOfNodes() {
		count = 0;
	}
	
	public LinkedListOfNodes(Node element){
		this.head = new Nodo(element);
		this.tail = this.head;
		count++;
	}
	
	//Adiciona elemento no final
	public void add(Node element){
		Nodo n = new Nodo(element);
		if(count==0)head = n;
		else tail.next = n;
		tail = n;
		count++;
	}
	
	//Remove um elemento do vetor e reposiciona os demais retornando true se o valor foi encontrado
	public boolean remove(Node element){
		if(count==0) return false;
		if(head.element==element){
			head = head.next;	
			if(count==1){
				tail=null;
			}
			count--;
			return true;			
		}
		Nodo ant = head;
		Nodo n = head.next;
		for(int i=1; i<count; i++){
			if(n.element==element){
				ant.next=n.next;
				count--;
				if(n==tail)
					tail=ant;
					return true;				}
			else{
				ant = ant.next;
				n = n.next;
			}
		}
		return false;
	}
	
		//Retorna o valor na posição do index informado
	public Node get(int index){
		if(index<0 || index >=count)
			throw new IndexOutOfBoundsException("Invalid Index");			
		if(index==count-1)
			return tail.element;
		Nodo aux = head;
		for(int i=0; i<index; i++){		
			aux = aux.next;
		}
		return aux.element;
	}			
	
	public int size(){
		return count;
	}

	@Override
	public String toString() {
		return "LinkedListOfNodes [head=" + head + ", tail=" + tail + ", count=" + count + "]";
	}	

}
