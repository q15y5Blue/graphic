package yqius.dataDeal.entity;


import yqius.dataDeal.entity.inter.Graph;

import java.util.ArrayList;

public class GraphImpl<T> implements Graph {
    private int vertexNumber;
    private int edgeNumber;
    public ArrayList<Edges> []nodeList;//每一个点对应一个EdgeList

    @Override
    public void createGraph(ArrayList<Vertex> vertices) {
        this.vertexNumber = vertices.size();
        nodeList = new ArrayList[vertexNumber];
        for(int i=0;i<vertexNumber;i++){
            nodeList[i] = new ArrayList<Edges>();
        }
    }

    @Override
    public int vertexNumber() {
        return this.vertexNumber;
    }

    @Override
    public int edgesNumber() {
        return this.edgeNumber;
    }

    @Override
    public void addEdge(Edges edge) {
        nodeList[edge.getStartVex().getId()].add(edge);
        this.edgeNumber++;
    }

    @Override
    public void printEdge() {
        for(int i=0;i<vertexNumber;i++){
            for(Edges e:nodeList[i]){
                System.out.println(e+" ");
            }
        }
    }
}