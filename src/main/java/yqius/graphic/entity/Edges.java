package yqius.graphic.entity;

public class Edges<T> {
    private String name;// 项目名称
    private Vertex startVex;
    private Vertex endVex;
    private T weight;// 项目花费时间

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Vertex getStartVex() {
        return startVex;
    }

    public void setStartVex(Vertex startVex) {
        this.startVex = startVex;
    }

    public Vertex getEndVex() {
        return endVex;
    }

    public void setEndVex(Vertex endVex) {
        this.endVex = endVex;
    }

    public T getWeight() {
        return weight;
    }

    public void setWeight(T weight) {
        this.weight = weight;
    }

    public Edges(){
        this.startVex = startVex;
        this.endVex = endVex;
        this.name = name;
        this.weight = weight;
    }

    public Edges(String name, Vertex startVex, Vertex endVex, T weight) {
        this.name = name;
        this.startVex = startVex;
        this.endVex = endVex;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Edges{" +
                "name='" + name + '\'' +
                ", startVex=" + startVex.getId() +
                ", endVex=" + endVex.getId() +
                ", weight=" + weight +
                '}';
    }
}