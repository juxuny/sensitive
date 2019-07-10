package com.juxuny.sensitive;

import java.util.HashMap;
import java.util.Map;

public class Sensitive {

    private Node tree;

    public Sensitive() {
        tree = new Node();
    }

    public String toString() {
        return "";
    }


    /**
     *
     * @param chars
     * @param index
     * @param node
     * @return
     */
    private int insertToTree(char[] chars, int index, Node node) {
        if (index >= chars.length) return 0;
        if (node.containsKey(chars[index])) {
            return insertToTree(chars, index + 1, node.get(chars[index]));
        } else {
            Node n = new Node();
            node.put(chars[index], n);
            int r = insertToTree(chars, index + 1, n);
            n.isEnd = r == 0;
        }
        return 1;
    }


    private void pr(Node node, String current, Listener l) {
        HashMap<Character, Node> list = node == null ? tree : node;
        for (Map.Entry<Character, Node> entry: list.entrySet()) {
            Node n = entry.getValue();
            if (n.isEnd) l.onEndPoint(current + entry.getKey());
            pr(n, current + entry.getKey(), l);
        }
    }

    public void walkThrough(Listener l) {
        pr(null, "", l);
    }

    private void addOne(String word) throws EmptyException {
        char[] chars = new char[word.length()];
        if (word.isEmpty()) throw new EmptyException("word is empty");
        word.getChars(0, word.length(), chars, 0);
        insertToTree(chars, 0, tree);
    }

    public void addWord(String ...words) throws EmptyException {
        for (int i = 0; i < words.length; i++) addOne(words[i]);
    }

    private int find(char[] chars, int index, int len, Node node) {
        if (index >= chars.length) return node.isEnd ? len : 0;
        int r = 0;
        if (node.containsKey(chars[index])) {
            r = find(chars, index + 1, len + 1, node.get(chars[index]));
        } else {
            return node.isEnd ? len : 0;
        }
        return r;
    }

    public String replace(String input, Character value) {
        int[] h = new int[input.length()];
        char[] chars = new char[input.length()];
        input.getChars(0, chars.length, chars, 0);
        for (int i = 0; i < chars.length; i += Math.max(h[i], 1)) h[i] = find(chars, i, 0, tree);
        int i = 0;
        while (i < chars.length) {
            if (h[i] > 0) {
                for (int j = 0; j < h[i]; j++) chars[i + j] = value;
                i += h[i];
                continue;
            }
            i += 1;
        }
        return new String(chars);
    }


    public boolean contain(String input) {
        char[] chars = new char[input.length()];
        input.getChars(0, chars.length, chars, 0);
        int h = 0;
        for (int i = 0; i < chars.length; i += Math.max(h, 1)) {
            h = find(chars, i, 0, tree);
            if (h > 0) return true;
        }
        return false;
    }

    /**
     *
     * @param input
     * @return -1 means not found
     */
    public int first (String input) {
        char[] chars = new char[input.length()];
        input.getChars(0, chars.length, chars, 0);
        int h = 0;
        for (int i = 0; i < chars.length; i += Math.max(h, 1)) {
            h = find(chars, i, 0, tree);
            if (h > 0) return i;
        }
        return -1;
    }

    public String filter(String input) {
        int[] h = new int[input.length()];
        char[] chars = new char[input.length()];
        input.getChars(0, chars.length, chars, 0);
        for (int i = 0; i < chars.length; i += Math.max(h[i], 1)) h[i] = find(chars, i, 0, tree);
        int sum = 0;
        for (int i = 0; i < chars.length; i += 1) sum += h[i];
        int i = 0;
        int j = 0;
        char[] ret = new char[chars.length - sum];
        while (i < ret.length) {
            if (h[j] > 0) {
                j += h[j];
            }
            ret[i] = chars[j];
            i++;
            j++;
        }
        return new String(ret);
    }
}
