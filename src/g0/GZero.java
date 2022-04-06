package g0;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class GZero {
	
	private ArrayList<Node> A ;
	
	private Queue<Node> pile ;
	
	static ArrayList<String> symbols = new ArrayList<>();
	
	static ArrayList<String> nonTerminal = new ArrayList<>();
	
	static ArrayList<String> terminal = new ArrayList<>();
	
	
	public GZero(ArrayList<Node> A,ArrayList<String> nT, ArrayList<String> t) {
		this.A = A;
		this.pile= new ArrayDeque<>();
		
		symbols.add("->");
		symbols.add(".");
		symbols.add("+");
		symbols.add("[");
		symbols.add("]");
		symbols.add("(|");
		symbols.add("|)");
		symbols.add(",");
		symbols.add(";");

		nonTerminal.addAll(nT);
		
		terminal.addAll(t);
	}
	
	/**
	public boolean analyse(Node n) {
		switch(n.getClasse()) {
		case CONC:
			if(analyse(n.getLeftNode())) {
				return analyse(n.getRightNode());
			}else {
				return false;
			}
		case UNION:
			if(analyse(n.getLeftNode())){
				return true;
			}else {
				return analyse(n.getRightNode());
			}
		case STAR:
			while(analyse(n.getStar())) {
				
			}
			return true;
		case UN:
			if(analyse(n.getUn())) {
				
			}
			return true;
		case ATOM:
			switch(n.getAtomType()) {
			case TERMINAL:
				if(n.getCod()==code) {
					if(n.getAct()!=0) {
						action(n.getAct());
					}
					scan();
					return true;
				}else {
					return false;
				}
				break;
			case NONTERMINAL:
				if(analyse(A.get(n.getCod()))) {
					if(n.getAct()!=0) {
						action(n.getAct());
					}
				}else {
					return false;
				}
			}
		}
	}
	
	public void action(int code) {
		Node t1,t2 ;
		switch(code) {
		case 1 :
			t1=pile.remove();
			t2=pile.remove();
			A.set(t2.getCod()+5,t1);
			break;
		case 2 :
			pile.add(Foret.genAtom(rechercheDico(), action, type));
			break;
		case 3 :
			t1=pile.remove();
			t2=pile.remove();
			pile.add(Foret.genUnion(t2,t1));
			break;
		case 4 :
			t1=pile.remove();
			t2=pile.remove();
			pile.add(Foret.genConc(t2,t1));
			break;
		case 5 :
			
			break;
		case 6 :
			break;
		case 7 :
			break;
		}
		
	}*/
	
	public boolean rechercheDico(ArrayList<String> dico,String val) {
		for(String s : dico) {
			if(val.equals(s)) {
				return true;
			}
		}
		return false;
	}
	
	
	
	public Node scan(String path,int cpt) throws IOException {
		List<String> content = Files.readAllLines(Paths.get(path));
		String val = "";
		int cptUnit = 0 ;
		boolean startTerminal = false;
		
		for(String s : content) {
			for(int i = 0 ; i<s.length();i++) {
				
				Node n = null;
				
				val+=s.charAt(i);
				
				System.out.println("VALL : "+val);
				
				if(s.charAt(i)=='\'') {
					startTerminal = !startTerminal;
					val="";
				}else if(symbols.contains(val) && !startTerminal) {
					System.out.println("SYMBOLE TROUVEE");
					val="";
				}else if(startTerminal && rechercheDico(terminal,val)) {
					cptUnit++;
					System.out.println("UNITE LEXICALE TERMINALE TROUVEE : "+val+" | SCAN : "+cptUnit);
					
					n = new Node(Operation.ATOM);
					n.setCod(val);
					n.setAtomType(AtomType.TERMINAL);
					if(i+3<=s.length()-1) {
						if(s.charAt(i+2) =='#') {
							try {
								n.setAct(Integer.parseInt(""+s.charAt(i+3)));
							}catch(IndexOutOfBoundsException e) {
								
							}
						}
					}
					
					val="";
	
					
					
				}else if(rechercheDico(nonTerminal,val)) {
					cptUnit++;
					System.out.println("UNITE LEXICALE NON TERMINALE TROUVEE : "+val+" | SCAN : "+cptUnit);
					
					
					n = new Node(Operation.ATOM);
					n.setCod(val);
					n.setAtomType(AtomType.NONTERMINAL);
					if(i+1<s.length()) {
						if(s.charAt(i+1) =='#') {
							try {
								n.setAct(Integer.parseInt(""+s.charAt(i+2)));
							}catch(IndexOutOfBoundsException e) {
								
							}
						}
					}
					
					val="";
				}
				
				if(cptUnit==cpt) {
					return n;
				}
				
					
			}
		  
		}
		
		System.out.println("Pas trouvé");
		return null;
		
	}
	
	public static ArrayList<String> fileToArray(String path) throws IOException{
		ArrayList<String> value = new ArrayList<>();
		List<String> content = Files.readAllLines(Paths.get(path));
		for(String s : content) {
			value.add(s);
		}
		return value;
	}
	
	public static void main(String[] args) throws IOException {
		
		GZero g = new GZero(new ArrayList<Node>(),fileToArray("src/gpl/dicoNT.txt"),fileToArray("src/gpl/dicoT.txt"));
		System.out.println(g.scan("src/gpl/g0.txt", 8));
		
	}

}
