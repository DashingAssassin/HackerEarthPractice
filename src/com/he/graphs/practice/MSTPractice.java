package com.he.graphs.practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 * This class is practice of MST algorithm for Graphs
 * 
 * @author nikhil
 *
 */
class Node<T> {
	private T x;

	public Node(T x) {
		this.x = x;
	}

	public T getNode() {
		return this.x;
	}

	public void setNode(T x) {
		this.x = x;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (x != other.x)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Node [x=" + x + "]";
	}

}

class Edge<T extends Number> implements Comparable<Edge<T>> {
	private Node<T> start;
	private Node<T> end;
	private T weight;

	public Edge(Node<T> start, Node<T> end, T weight) {
		this.start = start;
		this.end = end;
		this.weight = weight;
	}

	public Node<T> getStart() {
		return start;
	}

	public void setStart(Node<T> start) {
		this.start = start;
	}

	public Node<T> getEnd() {
		return end;
	}

	public void setWeight(T weight) {
		this.weight = weight;
	}

	public T getWeight() {
		return this.weight;
	}

	public void setEnd(Node<T> end) {
		this.end = end;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge other = (Edge) obj;
		if (end == null) {
			if (other.end != null)
				return false;
		} else if (!end.equals(other.end))
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Edge [start=" + start + ", end=" + end + "]";
	}

	public static <T extends Number> Node<T> root(Map<Node<T>, Node<T>> id, Node<T> value) {

		if (id.containsKey(value)) {
			while (!id.get(value).equals(value)) {
				id.put(id.get(value), id.get(id.get(value)));
				// id.put(value, id.get(value));
				value = id.get(value);
			}
		}

		return value;
	}

	public static <T extends Number> void union(Map<Node<T>, Node<T>> id, Node<T> x, Node<T> y) {
		Node<T> p = root(id, x);
		Node<T> q = root(id, y);
		id.put(p, q);
	}

	public static <T extends Number> long kruskals(Edge<T>[] id, Map<Node<T>, Node<T>> idMap) {
		long minCost = 0;
		for (Edge<T> edge : id) {
			Node<T> first = edge.getStart();
			Node<T> end = edge.getEnd();
			if (root(idMap, first) != root(idMap, end)) {
				minCost += ((Number) edge.getWeight()).longValue();
				union(idMap, first, end);

			}

		}

		return minCost;
	}

	@Override
	public int compareTo(Edge<T> o) {
		return ((Long) this.weight).compareTo((Long) (o.getWeight()));
	}
}

public class MSTPractice {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pw = new PrintWriter(System.out, false);

	public static void main(String[] args) throws Exception {
		// For Disjoint you need an array of the nodes and initialize method , root and
		// union
		long time1 = System.currentTimeMillis();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int nodes = Integer.parseInt(st.nextToken());
		int edges = Integer.parseInt(st.nextToken());
		TreeMap<Edge<Integer>, Boolean> treeMap = new TreeMap<>();
		Node<Integer>[] nodesArray = new Node[nodes + 1];
		Edge<Integer>[] edgesArray = new Edge[edges];
		Map<Node<Integer>, Node<Integer>> idMap = new HashMap<>();
		for (int i = 1; i < nodesArray.length; i++) {
			nodesArray[i] = new Node(i);
			idMap.put(nodesArray[i], nodesArray[i]);
		}
		for (int i = 0; i < edges; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			long weight = Integer.parseInt(st.nextToken());
			edgesArray[i] = new Edge(nodesArray[x], nodesArray[y], weight);
		}
		Arrays.sort(edgesArray);
		pw.println(Edge.kruskals(edgesArray, idMap));
		long time2 = System.currentTimeMillis();
		System.out.println("Total Time taken= " + (time1 - time2) / 1000 + " secs");

	}

	private static void printTreeMap(TreeMap<Edge<Integer>, Boolean> treeMap) {

//		for (Entry<Edge<Integer>, Boolean> idEntry : treeMap.entrySet()) {
//			Edge<Integer> edge = idEntry.getKey();
//			Node<Integer> first = edge.getStart();
//			Node<Integer> end = edge.getEnd();
//			//String str = "treeMap for edge x=" + first + "   y=" + end + "  weight = "
//					//+ ((Number) edge.getWeight()).longValue();
//			//System.out.println(str);
//
//		}

	}

}
