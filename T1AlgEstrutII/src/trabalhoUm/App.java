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
			
		arvore = new GeneralTreeOfStrings();	
		
		arquivo ="taaa";	
				
		/*================================================================================*/
		System.out.print("Carregando arquivo " + arquivo + "... ");
		if(lerArquivo(arquivo+".txt"))
			System.out.print("ok\n");
		else{
			System.out.println("Erro ao carregar arquivo livro.txt");
			return;
		}		
		/*================================================================================*/
		processaDados();		
		arvore.montaArvore(codigo);
		fazDocumento();	
		System.out.println("POSSUI:"+arvore.getCountPretas()+" pretas");
	}	
	
	public static boolean lerArquivo(String arquivo) throws FileNotFoundException, IOException{		
		try(BufferedReader in = new BufferedReader(new FileReader("Files/t1arqs/"+arquivo))){ 				
			while(in.ready()) { 			
				codigo+=(in.readLine());				
			}				
		}catch(FileNotFoundException a){
				return false;
		}
			return true;			
	}	
	
	public static void processaDados(){
		String aux = "";
		int j=1;
		for(int i=0; !(codigo.substring(i,j).equals(" ")); i++, j++) {							
			aux += codigo.substring(i, j);			
		}		
		codigo = codigo.substring(j);
		aux.replaceAll("\\s+","");
		tamanho = Integer.parseInt(aux);		
	}
	
//	public static void printaArvore(){		
//		LinkedListOfStrings a = arvore.positionsPre();
//		for(int i=0; i<a.size(); i++){
//			System.out.println(a.get(i));
//		}		
//	}
	
	public static void fazDocumento() throws UnsupportedEncodingException, FileNotFoundException, IOException{
		StringBuilder str = new StringBuilder();
		arvore.writing(tamanho, str);
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(				
			       new FileOutputStream(arquivo+".svg"), "utf-8"))) {
						writer.write("<?xml version='1.0' standalone='no'?>\n\n"
								+ "<svg xmlns='http://www.w3.org/2000/svg' width='20cm' height='20cm' viewBox='0 0 "
								+tamanho+" "+tamanho+"'> \n"
								+ "<g style='stroke-width:.05; stroke:black'>\n");											
						
							writer.write(str.toString());			
							
							writer.write("</g>\n</svg>");					
		}
	}
	
//	public static int tamanhoArvore(){
//		LinkedListOfStrings a = arvore.positionsPre();
//		return a.size();
//	}
}
