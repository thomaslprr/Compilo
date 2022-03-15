package g0;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class GZero {
	
	private ArrayList<Node> A ;
	
	private Queue<Node> pile ;
	
	
	public GZero(ArrayList<Node> A) {
		this.A = A;
		this.pile= new ArrayDeque<>();
	}
	
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
		
	}
	
	public void scan() {
		
	}

}
