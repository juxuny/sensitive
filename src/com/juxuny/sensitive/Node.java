package com.juxuny.sensitive;

import java.util.ArrayList;
import java.util.HashMap;

public class Node extends HashMap<Character, Node> {
    public boolean isEnd;

    public Node() {
        this.isEnd = false;
    }
}
