package dev.mkon.tree.model;

public class Node {
    private final  int id;
    private final int parentId;
    private final String name;

    Node(int id, int parentId, String name) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
    }

    public static Node fromCsvLine(String csvLine) {
        // the input should be validated
        String[] rowValues = csvLine.split(",");

        int parentId = rowValues[1].isBlank() ? -1 : Integer.parseInt(rowValues[1]);

        return new Node(Integer.parseInt(rowValues[0]), parentId, rowValues[2]);
    }

    @Override
    public String toString() {
        return "Node{" +
            "id=" + id +
            ", parentId=" + parentId +
            ", name='" + name + '\'' +
            '}';
    }
}
