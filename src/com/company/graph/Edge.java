package com.company.graph;

/**
 * Permet de représenter la liaison entre deux noeud
 * Mais une arête ne stocke que le noeud cible
 * Car on représente ici un graphe orinté
 */
public class Edge<T> {

    /**
     * le noeud cible de l'arête
     */
    private Node<T> node;

    /**
     * le poid de l'arrête
     */
    private int weight;

    public Edge(Node<T> node) {
        this.node = node;
        this.weight = 0;
    }

    /**
     * @return Node : on recupère le noeud cible de notre arète
     */
    public Node<T> getNode() {
        return node;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void addWeight() {
        this.weight++;
    }

    /**
     * toString d'une arête
     * @return String
     */
    @Override
    public String toString() {
        return "Edge {N = " + node.getValue().toString() + " - weight = " + weight +  "}";
    }
}