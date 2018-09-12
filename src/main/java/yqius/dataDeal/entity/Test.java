package yqius.dataDeal.entity;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        ArrayList<Vertex> nodeList = new ArrayList<Vertex>();
        Vertex vertex1= new Vertex();
        Vertex vertex2= new Vertex();
        Vertex vertex3= new Vertex();
        Vertex vertex4= new Vertex();
        vertex1.setId(1);
        vertex2.setId(2);
        vertex3.setId(3);
        vertex4.setId(4);
        nodeList.add(vertex1);
        nodeList.add(vertex2);
        nodeList.add(vertex3);
        nodeList.add(vertex4);
        GraphImpl gi = new GraphImpl();
        gi.createGraph(nodeList);

        Edges e1 = new Edges("第一线",vertex1,vertex2,2);
        Edges e2 = new Edges("第二线",vertex2,vertex3,3);
        Edges e3 = new Edges("第三线",vertex3,vertex4,5);
        gi.addEdge(e1);
        gi.addEdge(e2);
        gi.addEdge(e3);
        gi.printEdge();
        System.out.println(gi.edgesNumber());

    }

}