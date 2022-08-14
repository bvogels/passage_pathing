package PassagePathing;

import java.util.ArrayList;
import java.util.List;

public class Node {
	
	String name;
	List<Node> connections;
	Boolean visited;
	Boolean isBigCave;
	Node nextInPath;
	
	public Node() {
		connections = new ArrayList<>();
	}
	
	public Node(Boolean visited, String name, Boolean isBigCave) {
		this.name = name;
		this.visited = visited;
		connections = new ArrayList<>();
		nextInPath = null;
		this.isBigCave = isBigCave;
	}
	
	

	
}
