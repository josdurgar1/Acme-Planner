package acme.spam;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class SpamRead {
//	  public static void main (final String[] args) {
//		  final String com="hoy viagra y sex estaba el día feo, pero al final mejoró";
//		  final boolean res= SpamRead.isSpam(10.0,com);
//		  System.out.println(res);
//	  }
	
	private SpamRead() {
		
	}
	  
	protected static final File FILE = new File("spam/spam.txt");
	protected static final List<String> SPAMLIST = new ArrayList<String>();
	
	public static List<String> spamList(final String url) throws IOException{
		//final File file = new File("D:\\Desktop\\DP 2020\\DP2\\Nueva carpeta\\Workspace-21.1\\Projects\\Acme-Planner\\src\\main\\webapp\\META-INF\\resources\\spam/spam.txt");
		//final File file=new File(url);
		//final URL ur2 = new URL("spam/spam.txt");
		final URL url1 = new URL(url);
		final List<String> result=new ArrayList<String>();
		final List<String> spamlist=new ArrayList<String>();
		
		BufferedReader file2 = null;
		file2 =  new BufferedReader( new InputStreamReader(url1.openStream()));
		
		
			String lines;
			while((lines=file2.readLine())!=null){
		           result.add(lines);
		        }
			file2.close();

		final Set<String>listaLimpia=new HashSet<String> ();
		listaLimpia.clear();
		listaLimpia.addAll(result);
		spamlist.addAll(listaLimpia);
		return spamlist;
		
	}
	
	public static boolean isSpam(final double umbral,final String comentario, final String url) throws IOException {
		
		
		final String comentario2=comentario.toLowerCase();
		boolean result=false;
		int detectadas = 0;
		final StringTokenizer st = new StringTokenizer(comentario);
		final int numeroDePalabras=st.countTokens();
		final List<String> spamlist=SpamRead.spamList(url);
		System.out.println(spamlist);
		
		for(int i=0;i<spamlist.size();i++) {
			if(comentario2.contains(spamlist.get(i))) {
				//comentario2.replace(spamlist.get(i),"");
				detectadas++;
				System.out.println("detectada: "+spamlist.get(i));
			}
			 
			
		}
		System.out.println("detectadas: "+detectadas);
		if(((detectadas*1.0)/numeroDePalabras>(umbral/100))) {
			System.out.println("result: "+((detectadas*1.0)/numeroDePalabras)+":"+(umbral/100));
			result=true;
		}
		
		return result;
	}
	


}
