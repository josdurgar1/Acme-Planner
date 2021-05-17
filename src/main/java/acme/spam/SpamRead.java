package acme.spam;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class SpamRead {
	
	private SpamRead() {}
	
	public static List<String> spamList(final List<String> list){

		List<String> result;
		final List<String> spamlist=new ArrayList<String>();
		final Set<String>listaLimpia=new HashSet<String> ();
		
		result=list;
		
		listaLimpia.clear();
		listaLimpia.addAll(result);
		spamlist.addAll(listaLimpia);
		return spamlist;
		
	}
	
	public static boolean isSpam(final double umbral,final String comentario, final List<String> list){
		final String comentario2=comentario.toLowerCase();
		boolean result=false;
		int detectadas = 0;
		final StringTokenizer st = new StringTokenizer(comentario);
		final int numeroDePalabras=st.countTokens();
		final List<String> spamlist=SpamRead.spamList(list);
		
		for(int i=0;i<spamlist.size();i++) {
			if(comentario2.contains(spamlist.get(i))) {
				detectadas++;
			}
		}
		if(((detectadas*1.0)/numeroDePalabras>=(umbral/100))) {
			result=true;
		}
		return result;
	}

}
