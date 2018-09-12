package yqius.dataDeal.entity.inter;


import yqius.dataDeal.entity.Edges;
import yqius.dataDeal.entity.Vertex;

import java.util.ArrayList;
import java.util.List;

public interface Graph {
    void createGraph(ArrayList<Vertex> vertices);
    int vertexNumber();
    int edgesNumber();
    void addEdge(Edges edge);//添加边 实现了
    void printEdge();//实现了
}