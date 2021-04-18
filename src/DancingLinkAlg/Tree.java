package DancingLinkAlg;

import java.util.*;


public class Tree<T> {
    T val;
    Tree<T> parent;
    LinkedList<Tree<T>> childList;
    public Tree(T val) {
        this.val = val;
        childList = new LinkedList<>();
    }
    public void addChild(Tree<T> child) {
        childList.add(child);
    }
    public void removeLastChild() {
        childList.removeLast();
    }
    public int amountChildren() {
        return childList.size();
    }
    public boolean isLeaf() {
        return childList.isEmpty();
    }
    public String toString() {
        StringBuilder result= new StringBuilder();
        TreeIterator<T> it = this.iterator();
        while(it.hasNext()) {
            LinkedList<Tree<T>> nextPath = it.next();
            while(!nextPath.isEmpty()) {
                Tree<T> nextVal = nextPath.removeLast();
                if(nextVal.val != null) result.append(nextVal.val.toString());
                if(!nextPath.isEmpty()) result.append(" | ");
            }

            result.append("\n");
        }
        return result.toString();
    }
    public TreeIterator<T> iterator() {
        return new TreeIterator<>(this);
    }
}

class TreeIterator<T> {
    Stack<Iterator<Tree<T>>> itStack;
    LinkedList<Tree<T>> stack;
    boolean returned;

    TreeIterator(Tree<T> arg) {
        itStack = new Stack<>();
        stack = new LinkedList<>();
        List<Tree<T>> start = new LinkedList<>();
        start.add(arg);
        itStack.add(start.iterator());
        returned = true;
    }

    boolean hasNext() {
        try {
            mutate();
            returned = false;
            return true;
        } catch (NoSuchElementException e) {return false;}
    }

    LinkedList<Tree<T>> next() {
        if(returned) mutate();
        else returned = true;
        return new LinkedList<>(stack);
    }

    void mutate() throws NoSuchElementException {
        if(!stack.isEmpty()) stack.pop();
        while (!itStack.isEmpty()) {

            Iterator<Tree<T>> it = itStack.peek();

            if (it.hasNext()) {
                stack.push(it.next());

                if (stack.peek().isLeaf()) return;
                else itStack.push(stack.peek().childList.iterator());
            } else {
                stack.pop();
                itStack.pop();
            }
        }
    }


}


