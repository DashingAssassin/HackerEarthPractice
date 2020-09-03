package com.he.graphs.shortestpath.practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class DijkstraNode {
	private int node;

	public DijkstraNode(int node) {
		this.node = node;
	}

	public int getNode() {
		return node;
	}

	public void setNode(int node) {
		this.node = node;
	}

	@Override
	public int hashCode() {
		return Objects.hash(node);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DijkstraNode other = (DijkstraNode) obj;
		return node == other.node;
	}

	@Override
	public String toString() {
		return "DijkstraNode [node=" + node + "]";
	}

}

class DijkstraEdge implements Comparable<DijkstraEdge> {

	private DijkstraNode startNode;
	private DijkstraNode endNode;
	private int weight;

	public DijkstraEdge(DijkstraNode startNode, DijkstraNode endNode, int weight) {
		super();
		this.startNode = startNode;
		this.endNode = endNode;
		this.weight = weight;
	}

	public DijkstraNode getStartNode() {
		return startNode;
	}

	public void setStartNode(DijkstraNode startNode) {
		this.startNode = startNode;
	}

	public DijkstraNode getEndNode() {
		return endNode;
	}

	public void setEndNode(DijkstraNode endNode) {
		this.endNode = endNode;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public int hashCode() {
		return Objects.hash(endNode, startNode, weight);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DijkstraEdge other = (DijkstraEdge) obj;
		return Objects.equals(endNode, other.endNode) && Objects.equals(startNode, other.startNode)
				&& weight == other.weight;
	}

	@Override
	public String toString() {
		return "DijkstraEdge [startNode=" + startNode + ", endNode=" + endNode + ", weight=" + weight + "]";
	}

	@Override
	public int compareTo(DijkstraEdge o) {
		return this.weight - o.weight;
	}

}

public class DjkstraAlgorithm {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pw = new PrintWriter(System.out, false);

	public static void main(String[] args) throws Exception {
		long time1 = System.currentTimeMillis();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[] distance = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			distance[i] = Integer.MAX_VALUE;
		}
		distance[1] = 0;
		PriorityQueue<DijkstraEdge> pq = new PriorityQueue<>();
		List<DijkstraEdge>[] edges = new LinkedList[m + 1];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			if (edges[a] == null) {
				edges[a] = new LinkedList<DijkstraEdge>();
			}

			DijkstraEdge edge = new DijkstraEdge(new DijkstraNode(a), new DijkstraNode(b), weight);
			edges[a].add(edge);
			System.out.println("total available=" + (i + 1));
		}

		boolean[] visited = new boolean[n + 1];
		pq.add(new DijkstraEdge(new DijkstraNode(1), new DijkstraNode(1), 0));
		while (!pq.isEmpty()) {
			DijkstraEdge edge = pq.poll();
			if (!visited[edge.getEndNode().getNode()]) {
				visited[edge.getEndNode().getNode()] = true;
				List<DijkstraEdge> edgeList = edges[edge.getEndNode().getNode()];
				if (null == edgeList)
					continue;
				for (DijkstraEdge dijkstraEdge : edgeList) {
					if (distance[dijkstraEdge.getEndNode().getNode()] > distance[dijkstraEdge.getStartNode().getNode()]
							+ dijkstraEdge.getWeight()) {
						distance[dijkstraEdge.getEndNode().getNode()] = distance[dijkstraEdge.getStartNode().getNode()]
								+ dijkstraEdge.getWeight();
						//dijkstraEdge.setWeight(distance[dijkstraEdge.getEndNode().getNode()]);
						pq.add(dijkstraEdge);
					}

				}

			}
		}
		for (int i = 1; i <= n; i++) {
			pw.print(distance[i] + " ");
		}
		pw.println();
		pw.flush();

	}

}
