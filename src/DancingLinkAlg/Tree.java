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
        StringBuilder result = new StringBuilder();
        TreeIterator<T> it = this.iterator();
        while (it.hasNext()) {
            Iterator<Tree<T>> nextPath = it.nextIt();
            while (nextPath.hasNext()) {
                Tree<T> nextVal = nextPath.next();
                if (nextVal.val != null) result.append(nextVal.val.toString());
                if (nextPath.hasNext()) result.append(" | ");
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
            if(returned) {
                mutate();
                returned = false;
            }
            return true;
        } catch (NoSuchElementException e) {return false;}
    }
    Iterator<Tree<T>> nextIt() {
        if(returned) mutate();
        else returned = true;
        return stack.iterator();
    }
    LinkedList<Tree<T>> next() {
        if(returned) mutate();
        else returned = true;
        return new LinkedList<>(stack);
    }

    void mutate() throws NoSuchElementException {

        if(!stack.isEmpty()) stack.removeLast();
        while (!itStack.isEmpty()) {

            Iterator<Tree<T>> it = itStack.peek();

            if (it.hasNext()) {
                stack.add(it.next());

                if (stack.getLast().isLeaf()) return;
                else itStack.push(stack.getLast().childList.iterator());
            } else {
                stack.removeLast();
                itStack.pop();
            }
        }
    }


}


