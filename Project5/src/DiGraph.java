/* Kyler Ramsey karamsey@calpoly.edu Liam Lefferts ljcates@calpoly.edu 3/12/2018 Project5 Graph Algorithms */

import java.util.Iterator;
import java.util.LinkedList;

public class DiGraph {

	private LinkedList<Integer>[] AdjacencyList;

	public DiGraph(int N) {
		AdjacencyList = new LinkedList[N];
		for (int i = 0; i < AdjacencyList.length; i++) {
			AdjacencyList[i] = new LinkedList<Integer>();
		}
	}

	public boolean addEdge(int from, int to) {
		if (AdjacencyList[from - 1].contains(to)) {
			System.err.println("INPUT ERROR!");
			System.out.println("Invalid input: (" + from + ", " + to + ")");
			return false;
		}
		if (from > 0 && from <= AdjacencyList.length) {
			AdjacencyList[from - 1].add(new Integer(to));
			System.out.println("(" + from + "," + to + ")" + " edge is now added to the graph");
			return true;
		} else {
			System.err.println("INPUT ERROR!");
			System.out.println("Invalid input: (" + from + ", " + to + ")");
			return false;
		}
	}

	public boolean deleteEdge(int from, int to) {
		if (from > 0 && from <= AdjacencyList.length) {
			AdjacencyList[from - 1].removeFirstOccurrence(to);
			System.out.println("(" + from + "," + to + ")" + " edge is now removed from the graph");
			return true;
		} else {
			System.err.println("INPUT ERROR!");
			System.out.println("Invalid input: (" + from + ", " + to + ")");
			return false;
		}
	}

	public int edgeCount() {
		int count = 0;
		for (LinkedList<Integer> l : AdjacencyList) {
			count += l.size();
		}
		return count;
	}

	public int vertexCount() {
		return AdjacencyList.length;
	}

	public void print() {
		for (int i = 0; i < AdjacencyList.length; i++) {
			System.out.print((i + 1) + " is connected to: ");
			Iterator<Integer> it = AdjacencyList[i].iterator();
			while (it.hasNext()) {
				System.out.print(it.next());
				if (it.hasNext())
					System.out.print(", ");
			}
			System.out.println();
		}
	}

	private int[] indegrees(LinkedList<Integer>[] G) {
		int N = G.length;
		int[] IN = new int[N];
		for (int u = 0; u < N; u++) {
			for (Integer v : G[u]) {
				IN[v - 1] += 1;
			}
		}

		return IN;
	}

	public int[] topSort() throws IllegalArgumentException {
		int N = AdjacencyList.length;
		int IN[] = indegrees(AdjacencyList);
		int A[] = new int[N];
		LinkedList<Integer> Q = new LinkedList<Integer>();
		for (int u = 0; u < N; u++) {
			if (IN[u] == 0) {
				Q.addFirst(u);
			}
		}
		int i = 0;
		while (Q.peek() != null) {
			int u = Q.pollLast();
			A[i] = u;
			i++;
			for (Integer v : AdjacencyList[u]) {
				IN[v - 1] -= 1;
				if (IN[v - 1] == 0) {
					Q.addFirst(v - 1);

				}
			}
		}
		if (!(i == N)) {
			System.out.println("Cyclic Graph");
			return null;
		}
		for (int a = 0; a < A.length; a++) {
			System.out.print((A[a] + 1));
			if (a + 1 < A.length)
				System.out.print((", "));
		}
		System.out.println();

		return A;
	}

	private class VertexInfo {

		int distance;
		int parent;

		VertexInfo(int distance, int parent) {
			this.distance = distance;
			this.parent = parent;
		}

	}

	private VertexInfo[] BFS(int s) {
		int N = AdjacencyList.length;
		VertexInfo[] VA = new VertexInfo[N];
		for (int i = 0; i < N; i++) {
			VA[i] = new VertexInfo(-1, -1);

		}
		VA[s - 1].distance = 0;
		LinkedList<Integer> Q = new LinkedList<Integer>();
		Q.addFirst(s);

		while (Q.size() != 0) {
			int u = Q.pollLast();

			for (int v : AdjacencyList[u - 1]) {
				if (VA[v - 1].distance == -1) {
					VA[v - 1].distance = VA[u - 1].distance + 1;
					VA[v - 1].parent = u;
					Q.addFirst(v);
				}
			}

		}
		return VA;
	}

	public boolean isTherePath(int from, int to) {
		VertexInfo[] A = BFS(from);
		if (A[to].distance != -1) {
			return true;
		}
		return false;
	}

	public int lengthOfPath(int from, int to) {
		VertexInfo[] A = BFS(from);
		return A[to - 1].distance;
	}

	public void printPath(int from, int to) {
		VertexInfo[] VA = BFS(from);
		if (VA[to].distance == -1)
			System.out.println("No Path");

		else {
			String output = "";
			int count = 0;
			while (from != to) {
				output = " -> " + (to) + output;
				to = VA[to - 1].parent;
				if (count++ > AdjacencyList.length) {
					System.exit(0);
				}
			}
			output = from + output;
			System.out.println(output);
		}

	}

	public void printTree(int s) {

		TreeNode head = new TreeNode(s).buildTree(s);
		head.printTreeAux(head, 0);

	}

	private class TreeNode {
		int vertexNumber;
		LinkedList<TreeNode> children = new LinkedList<TreeNode>();

		public TreeNode(int vertexNumber) {
			this.vertexNumber = vertexNumber;
		}

		public TreeNode buildTree(int s) {
			VertexInfo[] VA = BFS(s);

			LinkedList<TreeNode> Q = new LinkedList();
			TreeNode head = new TreeNode(s);
			Q.push(head);

			while (Q.size() != 0) {
				TreeNode current = Q.pop();

				for (int index = 0; index < VA.length; index++) {

					if (VA[index].parent == current.vertexNumber) {
						TreeNode temp = new TreeNode(index + 1);
						Q.push(temp);
						current.children.add(temp);

					}
				}

			}
			return head;
		}

		private void printTreeAux(TreeNode head, int indent) {
			int tabs = indent;
			while (tabs-- > 0)
				System.out.print("    ");
			System.out.println(head.vertexNumber);

			if (head.children.peekFirst() == null)
				return;

			for (TreeNode child : head.children) {
				printTreeAux(child, indent + 1);
			}
		}
	}
}
