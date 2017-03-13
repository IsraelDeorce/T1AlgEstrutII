package trabalhoUm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class App {
	
	private static GeneralTreeOfStrings arvore;
	private static String codigo ="";
	private static int tamanho;
	
	public static void main(String[] args) throws IOException{
		
		Scanner in = new Scanner(System.in);
		
		arvore = new GeneralTreeOfStrings();		
		
		//System.out.println("Informe o nome do Arquivo: ");
		//String arquivo = in.nextLine();
		String arquivo ="s.txt";
				
		/*================================================================================*/
		System.out.print("Carregando arquivo " + arquivo + "... ");
		if(lerArquivo(arquivo))
			System.out.print("ok\n");
		else{
			System.out.println("Erro ao carregar arquivo livro.txt");
			return;
		}
		
		
		
		/*================================================================================*/
		//transforma em número	
		
		tamanho();
		
		arvore.montaArvore(codigo, tamanho);
		
		//System.out.println(codigo.length());
		
		//printacodigo();
		
		LinkedListOfStrings a = arvore.positionsPre();
		for(int i=0; i<a.size(); i++){
			System.out.println(a.get(i));
		}
		
		
		
		
	}
	
	
	public static boolean lerArquivo(String arquivo) throws FileNotFoundException, IOException{		
		try(BufferedReader in = new BufferedReader(new FileReader("Files/t1arqs/"+arquivo))){ 				
			while(in.ready()) { 			
				codigo+=(in.readLine());				
			}
			return true;
		}
		
	}
	
	public static void tamanho(){
		String tamanho = "";
		int j=1;
		for(int i=0; !(codigo.substring(i,j).equals(" ")); i++, j++) {							
			tamanho += codigo.substring(i, j);			
		}		
		codigo = codigo.substring(j);
	}
	
	
/*
	public static void printacodigo(){
		if(codigo.isEmpty())
			return;		
		for(int i = 0, j = 4; i<codigo.length(); i+=4, j+=4){	
			while(j>codigo.length()){
				j--;
			}
			System.out.println(codigo.substring(i, j));			
		}
	}
*/
	
}
