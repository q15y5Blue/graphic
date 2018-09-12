package yqius.graphic.entity.inter;


import yqius.graphic.entity.Edges;
import yqius.graphic.entity.Vertex;

import java.util.ArrayList;

public interface Graph {
    void createGraph(ArrayList<Vertex> vertices);
    int vertexNumber();
    int edgesNumber();
    void addEdge(Edges edge);//添加边 实现了
    void printEdge();//实现了
}