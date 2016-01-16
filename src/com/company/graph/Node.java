package com.company.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Chaque noeud va contenir une classe qui lui sera propre.
 * Nous aurons donc grace au graphe la relation parents enfants
 * entre les noeuds ce qui nous permettra d'avoir la relation
 * d'héritage/implémentation entres les différentes classes.
 */
public class Node<T> {

    /**
     * la classe associé au noeud
     */
    private T value;

    /**
     * la liste de ses arêtes vers ses noeuds parents
     */
    private List<Edge<T>> parents = new ArrayList<Edge<T>>();

    /**
     * la liste de ses arêtes vers ses noeuds enfants
     */
    private List<Edge<T>> children = new ArrayList<Edge<T>>();

    /**
     *
     * @param value : la classe que l'on veut stocker dans un noeud
     */
    public Node(T value) {
        this.value = value;
    }

    /**
     * @return Class : la classe contenut dans le noeud
     */
    public T getValue() {
        return value;
    }

    /**
     * Permet d'ajouter une arête entre deux noeud
     * @param n Node : l'autre noeud
     * @param et EdgeType : le type d'arête (IN/OUT)
     */
    public void addEdge(Node<T> n, EdgeType et) {
        if(!isSameNode(n)) {
            if(et.equals(EdgeType.IN)) { // si on veut une relation parent
                if(!haveThisParent(n)) {
                    Edge<T> e = new Edge<T>(n);
                    parents.add(e);
                }
            }
            else if (et.equals(EdgeType.OUT)) { // si on veut une relation enfant
                if(!haveThisChild(n)) {
                    Edge<T> e = new Edge<T>(n);
                    children.add(e);
                }
            }
        }
    }

    /**
     * Deux noeud sont en effet identique si leur classe
     * correspondente sont identique
     * @param n : l'autre noeud à comparer à notre noeud courant
     * @return boolean : true si deux noeuds sont identique faut sinon
     */
    public boolean isSameNode(Node<T> n) {
        return value.equals(n.getValue());
    }

    /**
     * Permet de savoir si le noeud n est un enfants de notre noeud
     * @param n Node : le noeud à tester
     * @return boolean : true si n est enfant de this sinon false
     */
    public boolean haveThisChild(Node<T> n) {
        for(Edge<T> edge : children)
            if(edge.getNode().isSameNode(n))
                return true;
        return false;
    }

    /**
     * Permet de savoir si le noeud n est un parent de notre noeud
     * @param n Node : le noeud à tester
     * @return boolean : true si n est parent de this sinon false
     */
    public boolean haveThisParent(Node<T> n) {
        for(Edge<T> edge : parents)
            if(edge.getNode().isSameNode(n))
                return true;
        return false;
    }

    /**
     * le toString d'un noeud
     * @return String
     */
    @Override
    public String toString() {
        String result =  "Node{" +
                "value = " + value.toString();


        result += "\nparents : " + " \n\t";

        for (Edge parent : parents) {
            result += parent.getNode().getValue().toString() + " - ";
        }

        result += "\nchild : " + (children.size()) + " \n\t";

        for (Edge child : children) {
            result += child.getNode().getValue().toString() + " - ";
        }
        return result;
    }

    /**
     * @return List<Edge> : la liste des arêtes vers les noeuds parents de notre noeud
     */
    public List<Edge<T>> getParents() {
        return parents;
    }

    /**
     * @return List<Edge> : la liste des arêtes vers les noeuds enfants de notre noeud
     */
    public List<Edge<T>> getChildren() {
        return children;
    }
}

