package trabalhoUm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class App {
	
	private static GeneralTreeOfStrings arvore;
	private static int tamanhoImagem;
	private static String arquivo;
	private static LinkedListOfStrings lista;
	
	public static void main(String[] args) throws IOException{
		
		Scanner in = new Scanner(System.in);
		
		arvore = new GeneralTreeOfStrings();
		lista = new LinkedListOfStrings();
		
		System.out.println("Informe o nome do Arquivo: ");
		arquivo = in.nextLine();
				
		/*================================================================================*/
		System.out.print("Carregando arquivo " + arquivo + "... ");
		if(lerArquivo(arquivo))
			System.out.print("ok\n");
		else{
			System.out.println("Erro ao carregar arquivo livro.txt");
			return;
		}
		
		printaLista();
		
	}
	
	public static boolean lerArquivo(String inscricao) throws FileNotFoundException, IOException{
		//String filePath = new File(inscricao).getAbsolutePath();
		//System.out.println(filePath);
		try(BufferedReader in = new BufferedReader(new FileReader("Files/t1arqs/"+inscricao))){ 				
			while(in.ready()) { 			
				lista.add(in.readLine());				
			}
			return true;
		}
		
	}
	
	public static void printaLista(){
		if(lista.isEmpty())
			return;		
		for(int i = 0, j = 4; i<lista.get(0).length(); i+=4, j+=4){	
			while(j>lista.get(0).length()){
				j--;
			}
			System.out.println(lista.get(0).substring(i, j));			
		}
	}	

}
