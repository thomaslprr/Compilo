package g0;

public class Node {
	
	private Node leftNode;
	private Node rightNode;
	private Node star;
	private Node un;
	private int act;
	private int cod;
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

	public void setAct(int act) {
		this.act = act;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public AtomType getAtomType() {
		return atomType;
	}

	public void setAtomType(AtomType atomType) {
		this.atomType = atomType;
	}
	
	
	
	

}
