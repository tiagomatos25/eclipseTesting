/*
public class Agap {
	static int value =0;
	static int depth =0;
	
	public static int calculate(INode node) {
		int value = 0;	
		if(node instanceof ICautuion) {
			if(node.isDangerous()) {
				try {
					value+=node.getValue();
				} catch{
					value-= depth;
				}
			} else {
				value+=node.getValue();
			}
		
		} else {
			value+=node.getValue();
		}
		
		if(node.getChildren()!= null)
			depth++;
			for(int i =0;i<node.getChildren().size(); i++) {
				
				value+=calculate(node.getChildren().index(i));
				
			}
		
		return value;
	}
	
	
	public static void main(String[] args) {
		
	}
}*/
