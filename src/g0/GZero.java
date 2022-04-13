package g0;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class GZero {
	
	private LinkedHashMap<String,Node> A ;
	
	private Queue<Node> pile ;

	private Node scanned;

	private ArrayList<Integer> pilex = new ArrayList<>();
	private ArrayList<Integer> P_code = new ArrayList<>();
	private int spx;
	private int co;

	
	static ArrayList<String> symbols = new ArrayList<>();
	
	static ArrayList<String> nonTerminal = new ArrayList<>();
	
	static ArrayList<String> terminal = new ArrayList<>();

	static ArrayList<String> dicot = new ArrayList<>();
	
	static ArrayList<String> dicont = new ArrayList<>();
	
	
	public GZero(LinkedHashMap<String,Node> A,ArrayList<String> nT, ArrayList<String> t) {
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

		spx = 0;
		co = 0;
	}
	

	public boolean analyse(Node n, int cpt, String path) throws IOException {
		switch(n.getClasse()) {
		case CONC:
			if(analyse(n.getLeftNode(), cpt, path)) {
				return analyse(n.getRightNode(), cpt, path);
			}else {
				return false;
			}
		case UNION:
			if(analyse(n.getLeftNode(), cpt, path)){
				return true;
			}else {
				return analyse(n.getRightNode(), cpt, path);
			}
		case STAR:
			while(analyse(n.getStar(), cpt, path)) {
				
			}
			return true;
		case UN:
			if(analyse(n.getUn(), cpt, path)) {
				
			}
			return true;
		case ATOM:
			switch(n.getAtomType()) {
			case TERMINAL:
				if(n.getCod()==scanned.getCod()) {
					if(n.getAct()!=0) {
						action(n.getAct());
					}
					scan(path, cpt);
					return true;
				}else {
					return false;
				}
			case NONTERMINAL:
				if(analyse(A.get(n.getCod()), cpt, path)) {
					if(n.getAct()!=0) {
						action(n.getAct());
					}
				}else {
					return false;
				}
			}
		}
		return false;
	}
	
	public void action(int code) {
		Node t1,t2 ;
		switch(code) {
		case 1 :
			t1=pile.remove();
			t2=pile.remove();
			A.put(t2.getCod(),t1);
			break;
		case 2 :
			pile.add(Foret.genAtom(scanned.getBaseName(), scanned.getAct(), scanned.getAtomType()));
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
			if(scanned.getAtomType() == AtomType.NONTERMINAL) {
				pile.add(Foret.genAtom(scanned.getBaseName(), scanned.getAct(), scanned.getAtomType()));
			}else {
				pile.add(Foret.genAtom(scanned.getBaseName(), scanned.getAct(), scanned.getAtomType()));
			}
			break;
		case 6 :
			t1=pile.remove();
			pile.add(Foret.genStar(t1));
			break;
		case 7 :
			t1=pile.remove();
			pile.add(Foret.genUn(t1));
			break;
		}
	}
	
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
					n.setAct(i,s);
					val="";	
				}else if(rechercheDico(nonTerminal,val)) {
					cptUnit++;
					System.out.println("UNITE LEXICALE NON TERMINALE TROUVEE : "+val+" | SCAN : "+cptUnit);
					n = new Node(Operation.ATOM);
					n.setCod(val);
					n.setAtomType(AtomType.NONTERMINAL);
					n.setAct(i,s);
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


	public boolean analyseP(Node n, int cpt, String path) throws IOException {
		switch(n.getClasse()) {
		case CONC:
			if(analyseP(n.getLeftNode(), cpt, path)) {
				return analyseP(n.getRightNode(), cpt, path);
			}else {
				return false;
			}
		case UNION:
			if(analyseP(n.getLeftNode(), cpt, path)){
				return true;
			}else {
				return analyseP(n.getRightNode(), cpt, path);
			}
		case STAR:
			while(analyseP(n.getStar(), cpt, path)) {
				
			}
			return true;
		case UN:
			if(analyseP(n.getUn(), cpt, path)) {
				
			}
			return true;
		case ATOM:
			switch(n.getAtomType()) {
			case TERMINAL:
				if(n.getCod()==scanned.getCod()) {
					if(n.getAct()!=0) {
						actionP(n.getAct());
					}
					scanP(path, cpt);
					return true;
				}else {
					return false;
				}
			case NONTERMINAL:
				if(analyseP(A.get(n.getCod()), cpt, path)) {
					if(n.getAct()!=0) {
						actionP(n.getAct());
					}
				}else {
					return false;
				}
			}
		}
		return false;
	}

	public Node scanP(String path,int cpt) throws IOException {
		List<String> content = Files.readAllLines(Paths.get(path));
		String val = "";
		int cptUnit = 0 ;
		boolean startTerminal = false;
		
		for(String s : content) {
			for(int i = 0 ; i<s.length();i++) {
				
				Node n = null;
				
				val+=s.charAt(i);
								
				if(s.charAt(i)=='\'') {
					startTerminal = !startTerminal;
					val="";
				}else if(symbols.contains(val) && !startTerminal) {
					System.out.println("SYMBOLE TROUVEE");
					val="";
				}else if(startTerminal && rechercheDico(dicot,val)) {
					cptUnit++;
					System.out.println("UNITE LEXICALE TERMINALE TROUVEE : "+val+" | SCAN : "+cptUnit);
					n = new Node(Operation.ATOM);
					n.setCod(val);
					n.setAtomType(AtomType.TERMINAL);
					n.setAct(i,s);
					val="";	
				}else if(rechercheDico(dicont,val)) {
					cptUnit++;
					System.out.println("UNITE LEXICALE NON TERMINALE TROUVEE : "+val+" | SCAN : "+cptUnit);
					n = new Node(Operation.ATOM);
					n.setCod(val);
					n.setAtomType(AtomType.NONTERMINAL);
					n.setAct(i,s);
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

	public void interpret(String x) {
		switch (x) {
		case "LDA":
			spx++;
			pilex.set(spx, P_code.get(co + 1));
			co += 2;
		break;
		case "LDV":
			spx++;
			pilex.set(spx, pilex.get(P_code.get(co + 1)));
			co += 2;
		break;
		case "RD":
			spx++;
			Scanner scan = new Scanner(System.in);
			pilex.set(spx, scan.nextInt());
			break;
		case "NOT":
			pilex.set(spx, !pilex.get(spx));
			co++;
		break;
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		GZero g = new GZero(new LinkedHashMap<String,Node>(),fileToArray("src/gpl/dicoNT.txt"),fileToArray("src/gpl/dicoT.txt"));
		System.out.println(g.scan("src/gpl/g0.txt", 1).infos());
		
	}

}
