package g0;

public class Node {
	
	private Node leftNode;
	private Node rightNode;
	private Node star;
	private Node un;
	private int act;
	private String cod;
	private AtomType atomType;
	private Operation classe;
	
	public Node(Operation classe) {
		this.classe = classe;
	}

	public Node getLeftNode() {
		return leftNode;
	}

	public void setLeftNode(Node leftNode) {
		this.leftNode = leftNode;
	}

	public Node getRightNode() {
		return rightNode;
	}

	public void setRightNode(Node rightNode) {
		this.rightNode = rightNode;
	}

	public Operation getClasse() {
		return classe;
	}

	public void setClasse(Operation classe) {
		this.classe = classe;
	}

	public Node getStar() {
		return star;
	}

	public void setStar(Node star) {
		this.star = star;
	}

	public Node getUn() {
		return un;
	}

	public void setUn(Node un) {
		this.un = un;
	}

	public int getAct() {
		return act;
	}

	public void setAct(int i,String s) {
		if(atomType==AtomType.TERMINAL) {
			if(i+3<=s.length()-1) {
				if(s.charAt(i+2) =='#') {
					try {
						this.act = Integer.parseInt(""+s.charAt(i+3));
					}catch(IndexOutOfBoundsException e) {
						System.out.println("ERREUR : le caractère succédant le # n'est pas un chiffre");
					}
				}
			}
		}else {			
			if(i+1<s.length()) {
				if(s.charAt(i+1) =='#') {
					try {
						this.act = Integer.parseInt(""+s.charAt(i+2));
					}catch(IndexOutOfBoundsException e) {
						System.out.println("ERREUR : le caractère succédant le # n'est pas un chiffre");
					}
				}
			}
		}
		
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public AtomType getAtomType() {
		return atomType;
	}

	public void setAtomType(AtomType atomType) {
		this.atomType = atomType;
	}

	@Override
	public String toString() {
		return "Node [leftNode=" + leftNode + ", rightNode=" + rightNode + ", star=" + star + ", un=" + un + ", act="
				+ act + ", cod=" + cod + ", atomType=" + atomType + ", classe=" + classe + "]";
	}
	
	

}
