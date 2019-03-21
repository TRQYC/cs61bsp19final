import edu.princeton.cs.algs4.BST;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K , V> {
    private Node root;
    private class Node{
        private K key;
        private V val;
        private Node left, right;
        private int N;

        public Node(K key, V val, int n) {
            this.key = key;
            this.val = val;
            N = n;
        }
    }

    public BSTMap(){} ;



    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public V get(K key) {
        return  get(root, key);
    }

    public V get(Node x, K key){
        if(x == null) return  null;
        if(x.key.compareTo(key) < 0)
            return get(x.right, key);
        else if (x.key.compareTo(key) > 0)
            return get(x.left, key);
        return  x.val;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node x){
        if(x == null) return 0;
        return x.N;
    }

    @Override
    public void put(K key, V value) {
        root = put(root, key, value);
    }

    public Node put(Node x, K key, V val){
        if(x == null) x = new Node(key, val, 1);
        if(x.key.compareTo(key) < 0)
            x.right = put(x.right, key, val);
        else if(x.key.compareTo(key) > 0)
            x.left = put(x.left, key, val);
        else x.val = val;
        x.N = size(x.left) + size(x.right) +1;
        return x;
    }

    public void printInOrder(){
        printInOrder(root);
    }
    private void printInOrder(Node x){
        if(x == null) return;
        if(x.left != null) printInOrder(x.left);
        System.out.println("key = " + x.key + " val = " + x.val);
        if(x. right != null) printInOrder(x.right);
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        keySet(root, set);
        return  set;
    }

    private void keySet(Node x, Set<K> set){
        if(x == null) return;
        keySet(x.left, set);
        set.add(x.key);
        keySet(x.right, set);
    }

    @Override
    public V remove(K key) {
        V val = get(key);
        if(val != null) root = remove(root, key);
        return val;
    }

    private Node remove(Node x, K key){
        if(x == null){
            return null;
        }
        int cmp = key.compareTo(x.key);
        if(cmp < 0) x.left = remove(x.left, key);
        else if(cmp > 0) x.right = remove(x.right, key);
        else{
            if(x.right == null) return x.left;
            if(x.left == null) return x.right;
            Node t = x;
            x = min(x.right);
            x.right = removeMin(t.right);
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
    public K min(){
        return min(root).key;
    }

    public Node min(Node x){
        if(x.left == null) return x;
        return min(x.left);
    }
    @Override
    public V remove(K key, V value) {
        if(value != get(key)) return null;
        return remove(key);
    }

    public void removeMin(){
        root = removeMin(root);
    }
    private Node removeMin(Node x){
        if (x.left == null) return x.right;
        x.left = removeMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return  x;
    }
    public static void main(String[] args){
        BSTMap<String, Integer> a  = new BSTMap<>();
        a.put("a",1);
        a.put("b",2);
        a.put("d",3);
        a.printInOrder();
    }
}
