package com.company.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Permet de représenter un graphe orinenté composé de noeud
 */
public abstract class DiGraph<T> {

    /**
     * La liste des noeuds du graphe
     */
    private List<Node<T>> nodes = new ArrayList<Node<T>>();

    public DiGraph() {
    }

    /**
     * Permet d'ajouter un nouveau noeud à notre graphe
     * à partir d'une classe
     * @param c Class : la classe que l'on veut rajouter au graphe
     */
    public abstract void addNode(T c);

    public List<Node<T>> getNodes() {
        return nodes;
    }

    /**
     * Permet de savoir si un noeud n est dans notre graphe
     * On ne veut pas de doublon dans notre graphe
     * @param n Node : le noeud à tester
     * @return boolean : true si n est dans notre graphe false sinon
     */
    public boolean haveThisNode(Node<T> n) {
        for(Node<T> node : nodes)
            if(node.isSameNode(n))
                return true;
        return false;
    }

    /**
     * Permet de trouver un noeud de notre graphe à partir
     * de sa classe associé
     * @param c Class
     * @return Node
     */
    public Node<T> getNode(T c) {
        for(Node<T> node : nodes)
            if(node.getValue().equals(c))
                return node;
        return null; // TODO exception
    }

    /**
     * toString d'un DiGraph
     * @return String
     */
    @Override
    public String toString() {
        String result =  "DiGraph{";

        for(Node<T> node : nodes)
            result += "\n\n" + node.toString();

        return result + '}';
    }
}
