package trabalhoUm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.plaf.synth.SynthSeparatorUI;


public class GeneralTreeOfStrings {	
	
	private Node root;
	private int count;	
	
	private int i;
	private int j;
	
	protected static final class Node{
		
		public Node father;
		public String element;
		public LinkedListOfNodes subtrees;
		
		public Node(String element){
			
			this.father = null;
			this.element = element;
			subtrees = new LinkedListOfNodes();
		}
		
		public void addSubtree(Node n){
			n.father = this;
			subtrees.add(n);					
		}		
		
		public boolean removeSubtree(Node n){
			n.father = null;
			return subtrees.remove(n);
		}
		
		public Node getSubtree(int i) {
			if(i<0 || i>=subtrees.size())				
				throw new RuntimeException("Index inválido");
			return subtrees.get(i);				
		}
		
		public int getSubtreeSize(){
			return subtrees.size();
		}
		@Override
		public String toString() {
			return "Node [father=" + father + ", element=" + element + ", subtrees=" + subtrees + "]";
		}	
	}
	
	public GeneralTreeOfStrings(){
		this.root = null;
		this.count = 0;
	}
	public GeneralTreeOfStrings(String element){
		this.root = new Node(element);
		this.count = 1;
	}
	
	//Retorna referencia de um nodo que possua determinado elemento a partir de uma arvore/subarvore
	private Node searchNodeRef(String element, Node target){
		Node n = null;
		if(target != null){
			if(element.equals(target.element))
				n = target;
			else{
				int i=0;
				Node aux = null;
				while(aux==null && i<target.getSubtreeSize()){
					aux = searchNodeRef(element, target.getSubtree(i));
					i++;
				}
				n=aux;
			}
		}
		return n;	
	}
	
	// insere o elemento e como	filho de father
	public boolean add(String element, String father){
		Node n = new Node(element);
		if(father==null){//se for nulo, é para adicionar na raiz
			if(root!=null){	
				root.father = n;
				n.addSubtree(root);
			}
			root = n;			
		}
		//se father não é nulo, é para adicionar como subtree dele
		else{
			Node aux = searchNodeRef(father, root);
			if(aux==null)
				return false;
			aux.addSubtree(n);
			n.father = aux;		
		}
		count++;
		return true;		
	}
	
	// retorna o elemento armazenado na raiz
	public String getRoot(){
		if(root==null)
			throw new RuntimeException("Raíz nula");			
		return root.element;
	}
	
	//altera o elemento armazenado na raiz
	public void setRoot(String e){
		if(root==null)
			add(e, null);
		root.element = e;						
	}
	
	//retorna o pai do elemento e
	public String getParent(String e){
		Node n = searchNodeRef(e, root);
		if(n==null)
			throw new RuntimeException("Elemento não encontrado!");
		return n.father.element;
	}
	
	// remove o elemento e e seus filhos
	public boolean removeBranch(String e){
		Node n = searchNodeRef(e, root);	
		if(n==null)
			 return false;
		count = count - n.getSubtreeSize();
		Node father = n.father;
		father.removeSubtree(n);
		return true;
	}
	
	//retorna true se a árvore contém o	elemento
	public boolean contains(String e){
		if(searchNodeRef(e,root)!=null)
			return true;
		return false;
	}
	
	// retorna true se o elemento está armazenado em um nodo interno
	public boolean isInternal(String e){
		Node n = searchNodeRef(e, root);
		if(n!=null){
			return n.getSubtreeSize()>0;
		}
		return false;
	}
	
	// retorna true se o elemento está armazenado em um nodo externo
	public boolean isExternal(String e){
		Node n = searchNodeRef(e, root);
		if(n!=null){
			return n.getSubtreeSize()==0;
		}
		return false;
	}
	
	//retorna true se o elemento e está	armazenado na raiz
	public boolean isRoot(String e){
		if(e!=null){
			return root.element == e;
		}
		return false;
	}
	
	//retorna true se a árvore está vazia
	public boolean isEmpty(){
		return count==0;
	}
	
	//retorna o número de elementos armazenados na árvore
	public int size(){
		return count;
	}
	
	//remove todos os elementos da árvore
	public void clear(){
		root = null;
		count = 0;
	}
	
	//retorna uma lista com todos os elementos da árvore na ordem pré-fixada
	public LinkedListOfStrings positionsPre(){
		LinkedListOfStrings lista = new LinkedListOfStrings();
		positionsPreAux(root, lista);
		return lista;		
	}
	
	public void positionsPreAux(Node n, LinkedListOfStrings lista){
		if(n!=null){
			lista.add(n.element);			
			for(int i=0; i<n.getSubtreeSize();i++){				
				positionsPreAux(n.getSubtree(i),lista);
			}			
		}
	}
	
	//retorna uma lista com todos os elementos da árvore na ordem pos-fixada
	public LinkedListOfStrings positionsPos(){
		LinkedListOfStrings lista = new LinkedListOfStrings();
		positionsPosAux(root, lista);
		return lista;		
	}
	
	public void positionsPosAux(Node n, LinkedListOfStrings lista){
		if(n!=null){			
			for(int i=0; i<n.getSubtreeSize();i++){
				positionsPosAux(n.getSubtree(i),lista);
			}
			lista.add(n.element);
		}
	}
	
	// retorna uma lista com todos os elementos da árvore com um caminhamento em largura
	public LinkedListOfStrings positionsWidth(){
		LinkedListOfStrings lista = new LinkedListOfStrings();
		if(root!=null){
			LinkedQueueOfNodes fila = new LinkedQueueOfNodes();
			Node aux = root;
			fila.enqueue(aux);
			while(!fila.isEmpty()){
				aux=fila.dequeue();
				lista.add(aux.element);
				for(int i=0; i<aux.getSubtreeSize(); i++)
					fila.enqueue(aux.getSubtree(i));
			}
		}
		return lista;
	}
	
	public boolean montaArvore(String codigo, int tamanho){		
		if(codigo.isEmpty()) return false;			
		int total = tamanho*tamanho;			
		Node pai = new Node(codigo.substring(0,1));	
		Node filho = new Node("");
		//codigo.substring(1,2)
		this.root = pai;		
		count++;		
		i = 1;
		j = 2;
		//System.out.println(root.element);
		//System.out.println(codigo);		
		montaArvoreAux(codigo,pai, filho);			
		return true;
	}
	
	public boolean montaArvoreAux(String codigo, Node pai, Node filho){		
		while(j <= codigo.length()){		
			if(codigo.substring(i,j).equals("c")&&pai.getSubtreeSize()<4){				
				filho.element = codigo.substring(i,j);				
				pai.addSubtree(filho);		
				i++;
				j++;				
				montaArvoreAux(codigo, filho,new Node(""));							
			} 
			
			else if(codigo.substring(i,j).equals("b")&&pai.getSubtreeSize()<4){					
				filho.element = codigo.substring(i,j);	
				pai.addSubtree(filho);					
				i++;
				j++;			
				montaArvoreAux(codigo, filho.father,new Node(""));
			} 
			
			else if(codigo.substring(i,j).equals("p")&&pai.getSubtreeSize()<4){					
				filho.element = codigo.substring(i,j);	
				pai.addSubtree(filho);	
				i++;
				j++;
				montaArvoreAux(codigo, filho.father,new Node(""));
			}
			else {
				montaArvoreAux(codigo, pai.father,filho);
			}
		}
			return true;		
		
		
	}		
	
/*	 
	public boolean montaArvore(LinkedListOfStrings livro){
		if(livro.isEmpty()) return false;
		if(livro.size()==1){
			this.root = new Node(livro.get(0));
			return true;
		}
		else{		
			Node pai = new Node(livro.get(0));
			Node filho = new Node(livro.get(1));
			this.root = pai;	
			if(pai.element.substring(0, 2).equals("L ")){			
				montaArvoreAux(pai,filho,2,livro);				
			}
			return true;
		}		
	}
	
	private void montaArvoreAux(Node pai, Node filho, int pos, LinkedListOfStrings livro){		
		
		
		if(filho.element.substring(0, 2).equals("C ")){			
			this.root.addSubtree(filho);
			montaArvoreAux(filho,new Node(livro.get(pos)),pos+1, livro);
		}		
		if(filho.element.substring(0, 2).equals("S ")){
			while(!(pai.element.substring(0, 2).equals("C ")))				
				pai  = pai.father;
			pai.addSubtree(filho);			
			montaArvoreAux(filho,new Node(livro.get(pos)),pos+1, livro);
		}		

		if(filho.element.substring(0, 2).equals("SS")){			
			while(!(pai.element.substring(0, 2).equals("S ")))				
				pai  = pai.father;
			pai.addSubtree(filho);
			montaArvoreAux(filho,new Node(livro.get(pos)),pos+1, livro);
			
		}		
		if(filho.element.substring(0, 2).equals("P ")){
			while(pai.element.substring(0, 2).equals("P "))					
				pai  = pai.father;
			pai.addSubtree(filho);		
			if(pos>=livro.size()) return;
			montaArvoreAux(filho.father,new Node(livro.get(pos)),pos+1, livro);
		}					
	}	
*/
	
	public LinkedListOfStrings positions(){
		LinkedListOfStrings lista = new LinkedListOfStrings();
		if(root != null){
			LinkedQueueOfNodes fila = new LinkedQueueOfNodes();
			Node aux = root;
			fila.enqueue(aux);
			while(!fila.isEmpty()) {
				aux = fila.dequeue();
				lista.add(aux.element);
				for(int i=0; i<aux.getSubtreeSize(); i++){
					fila.enqueue(aux.getSubtree(i));
				}
			}
		}
		return lista;
	}
	
	

	@Override
	public String toString() {
		return "GeneralTreeOfInteger [root=" + root + ", count=" + count + "]";
	}
		
}
