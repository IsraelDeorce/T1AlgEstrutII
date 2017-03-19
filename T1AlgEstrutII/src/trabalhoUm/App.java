package trabalhoUm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Scanner;

public class App {
	
	private static GeneralTreeOfStrings arvore;
	private static String codigo ="";
	private static int tamanho;
	private static String arquivo;
	
	public static void main(String[] args) throws IOException{
		
		Scanner in = new Scanner(System.in);
		
		arvore = new GeneralTreeOfStrings();		
		
		String c = "abcdefg";
		//System.out.println("Informe o nome do Arquivo sem a extenção: ");
		//String arquivo = in.nextLine();
		arquivo ="s";
		
		System.out.println("teste loco:");
		for(int i=0; i<c.length();i++){
			System.out.println(c.substring(i));
		}
				
		/*================================================================================*/
		System.out.print("Carregando arquivo " + arquivo + "... ");
		if(lerArquivo(arquivo+".txt"))
			System.out.print("ok\n");
		else{
			System.out.println("Erro ao carregar arquivo livro.txt");
			return;
		}
		
		
		
		/*================================================================================*/
		//transforma em número	
		
		tamanho();
		
		arvore.montaArvore(codigo, tamanho);
		System.out.println(tamanhoArvore());
		
		fazDocumento();
			
		
		
		
		//gerarDocumento();
		//printacodigo();				
		//8 cb c cbpbp cpbpb cbpbpb c cbpbpb cbpbp cpbpb cbbbp
		
		
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
		String aux = "";
		int j=1;
		for(int i=0; !(codigo.substring(i,j).equals(" ")); i++, j++) {							
			aux += codigo.substring(i, j);			
		}		
		codigo = codigo.substring(j);
		aux.replaceAll("\\s+","");
		tamanho = Integer.parseInt(aux);
		
	}
	
	public static void printaArvore(){		
		LinkedListOfStrings a = arvore.positionsPre();
		for(int i=0; i<a.size(); i++){
			System.out.println(a.get(i));
		}		
	}
	
	public static int tamanhoArvore(){
		LinkedListOfStrings a = arvore.positionsPre();
		return a.size();
	}
	
	
	
	
	
	
	
	
	
	public static void fazDocumento() throws UnsupportedEncodingException, FileNotFoundException, IOException{
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(				
			       new FileOutputStream(arquivo+".svg"), "utf-8"))) {
						writer.write("<?xml version='1.0' standalone='no'?>\n\n"
								+ "<svg xmlns='http://www.w3.org/2000/svg' width='20cm' height='20cm' viewBox=0 0 "
								+tamanho+" "+tamanho+"> \n"
								+ "<g style='stroke-width:.05; stroke:black'>");
													
						
							writer.write(recursao());						
						
		}
	}
	
	
	public static String recursao(){
		int nivel = 0;
		int n_filhos = 0;
		//if(arvore.getRoot())
		while(n_filhos<4){
			
		}
		return "";
	}	
	
		

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	/*
	public static void gerarDocumento(){
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(				
			       new FileOutputStream(arquivo+".svg"), "utf-8"))) {
						writer.write("<?xml version="1.0" standalone="no"?>");
						for(int i=1; i<=15; i++){
							writer.write("\n"+i);
							if(i==7)
								writer.write("               "+livro.get(0)+"               ");
							if(i==15)
								writer.write("\n-------------------------------------- Capa");
						}
	}
	*/
	
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
