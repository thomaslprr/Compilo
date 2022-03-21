package g0;

import java.util.ArrayList;

public class Foret {
	
	public static Node genConc(Node n1, Node n2){
		Node n = new Node(Operation.CONC);
		n.setLeftNode(n1);
		n.setRightNode(n2);
		return n;
	}
	
	public static Node genUnion(Node n1, Node n2) {
		Node n = new Node(Operation.UNION);
		n.setLeftNode(n1);
		n.setRightNode(n2);
		return n;
	}
	
	public static Node genStar(Node n1){
		Node n = new Node(Operation.STAR);
		n.setStar(n1);
		return n;
	}
	
	public static Node genUn(Node n1) {
		Node n = new Node(Operation.UN);
		n.setUn(n1);
		return n;
	}
	
	public static Node genAtom(String cod,int act,AtomType atomType) {
		Node n = new Node(Operation.ATOM);
		n.setCod(cod);
		n.setAct(act);
		n.setAtomType(atomType);
		return n;
	}
	
	private ArrayList<Node>  genForet() {
		ArrayList<Node> A = new ArrayList<>();
		//revoir les symboles
		
		Node s = genConc(genStar(genConc(genConc(genAtom("N",0,AtomType.NONTERMINAL),genAtom("->",0,AtomType.NONTERMINAL)),genAtom("E",0,AtomType.NONTERMINAL)),genAtom(",",1,AtomType.TERMINAL)),genAtom(";",0,AtomType.TERMINAL));
		A.add(s);
		Node n = genAtom("IDNTER",2,AtomType.TERMINAL);
		A.add(n);
		Node e = genConc(genAtom("T",0,AtomType.NONTERMINAL),genStar(genConc(genAtom("+",0,AtomType.TERMINAL),genAtom("T",3,AtomType.NONTERMINAL))));
		A.add(e);
		Node t = genConc(genAtom("F",0,AtomType.NONTERMINAL),genStar(genConc(genAtom(".",0,AtomType.TERMINAL),genAtom("F",4,AtomType.NONTERMINAL))));		
		A.add(t);
		Node f = genUnion(genUnion(genUnion(genUnion(genAtom("IDNTER",0,AtomType.TERMINAL),
				genAtom("ELTER",0,AtomType.NONTERMINAL)),genConc(genConc(genAtom("(",0,AtomType.TERMINAL),
						genAtom("E",0,AtomType.NONTERMINAL)),genAtom(")",0,AtomType.TERMINAL))),
				genConc(genConc(genAtom("[",0,AtomType.TERMINAL),genAtom("E",0,AtomType.NONTERMINAL)),
						genAtom("]",0,AtomType.TERMINAL))),genConc(genConc(genAtom("(/",0,AtomType.TERMINAL),
								genAtom("E",0,AtomType.NONTERMINAL)),genAtom("/)",0,AtomType.TERMINAL)));
		A.add(f);
		
		
		return A;
		
	}
	
	

}





