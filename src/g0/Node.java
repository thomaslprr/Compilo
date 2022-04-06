package g0;

public class Node {
	
	private Node leftNode;
	private Node rightNode;
	private Node star;
	private Node un;
	private int act;
	private String cod;
	private String baseName;
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
	
	public String toString() {
        StringBuilder buffer = new StringBuilder(50);
        String start = "";
        if(this.getBaseName()!=null) {
        	start=this.getBaseName()+"\n"+"| \n";
        }
        buffer.append(start);
        print(buffer, "", "");
        return buffer.toString();
    }

    private void print(StringBuilder buffer, String prefix, String childrenPrefix) {
        buffer.append(prefix);
        buffer.append(cod);
        buffer.append('\n');
           
            if (this.getLeftNode()!=null) {
                this.getLeftNode().print(buffer, childrenPrefix + "├── ", childrenPrefix + "│   ");
            } 
            
            if(this.getRightNode()!=null) {
                this.getRightNode().print(buffer, childrenPrefix + "└── ", childrenPrefix + "    ");
            }
            
            if(this.getStar()!=null) {
                this.getStar().print(buffer, childrenPrefix + "└── ", childrenPrefix + "    ");
            }
            
            if(this.getUn()!=null) {
                this.getUn().print(buffer, childrenPrefix + "└── ", childrenPrefix + "    ");
            }
        
    }
    
    
    
    
	public String getBaseName() {
		return baseName;
	}

	public void setBaseName(String baseName) {
		this.baseName = baseName;
	}

	public String infos() {
		return "Node [leftNode=" + leftNode + ", rightNode=" + rightNode + ", star=" + star + ", un=" + un + ", act="
				+ act + ", cod=" + cod + ", atomType=" + atomType + ", classe=" + classe + "]";
	}
	
	

}
