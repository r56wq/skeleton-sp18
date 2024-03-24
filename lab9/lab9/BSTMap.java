package lab9;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private Node root;
    private class Node {
        private K key; //sorted by key
        private V value; //associated data
        private Node left, right; //left and right subtree
        private int size; //number of nodes in subtrees

        private Node(K k, V v, int s) {
            key = k;
            value = v;
            size = s;
        }
    }

    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("cannot put null key");
        } else if (value == null) {
            throw new IllegalArgumentException("value cannot be null");
        }

        root = put(root, key, value);
    }

    /*
    helper method to put a pair
     */
    private Node put(Node node, K key, V value) {
        if (node == null) {
            return new Node(key, value, 1);
        }
        int cmp = key.compareTo(node.key);

        //insert on the left side
        if (cmp < 0) {
            node.left = put(node.left, key, value);
        }

        //insert on the right side
        if (cmp > 0) {
            node.right = put(node.right, key, value);
        }

        if (cmp == 0) {
            node.value = value;
        }

        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }


    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new RuntimeException("cannot search a null key");
        }
        return containsKey(root, key);
    }

    /*
    helper method that check if a key is contained
     */
    private boolean containsKey(Node node, K key) {
        if (node == null) {
            return false;
        }
        int cmp = (node.key).compareTo(key);
        if (cmp == 0) {
            return true;
        } else if (cmp > 0) {
            //search on the left
            return containsKey(node.left, key);
        } else {
            //search on the right
            return containsKey(node.right, key);
        }
    }

    @Override
    public int size() {
        if (root == null) {
            return 0;
        }
        return root.size;
    }

    /*
    private method to calculate the size of given node
     */
    private int size(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + size(node.left) + size(node.right);
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new RuntimeException("key cannot be null");
        }
        return get(root, key);
    }

    /*
    helper method to find a value of corresponding key
     */
    private V get(Node node, K key) {
        if (node == null) {
            return null;
        }
        int cmp = (node.key).compareTo(key);
        if (cmp == 0) {
            return node.value;
        } else if (cmp > 0) {
            //search on the left
            return get(node.left, key);
        } else {
            //search on the right
            return get(node.right, key);
        }
    }

    @Override
    public Set<K> keySet() {
        Set<K> keyset = new HashSet<>();
        addKeySet(root, keyset);
        return keyset;
    }

    /*
    helper method that add keys to a set
     */

    private void addKeySet(Node node, Set<K> keyset) {
        if (node == null) {
            return;
        }
        addKeySet(node.left, keyset);
        keyset.add(node.key);
        addKeySet(node.right, keyset);
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException("no this method");
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException("no this method");
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException("no this method");
    }

}
