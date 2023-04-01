package aufgabe1;

public class LinkedListFrequencyTable extends AbstractFrequencyTable  {
    private Node begin;
    private Node end;
    private int size;

    public LinkedListFrequencyTable () {clear();}

    @Override
    public int size() {
        return this.size;
    }

    public final void clear() {
        begin = new Node(new Word("", 0), null, null);
        end = new Node(new Word("", 0), null, null);
        begin.next = end;
        end.prev = begin;
        size = 0;
    }

    @Override
    public void add(String w, int f) {
        Node p = begin;
        while (p.next != end) {
            if (p.next.data.getWord().equals(w)) {
                p.next.data.addFrequency(f);
                moveLeft(p.next);
                return;
            }
            p = p.next;
        }
        Node r = new Node(new Word(w, f), p.next, p);
            r.next.prev = r;
            p.next = r;
            size++;
            moveLeft(r);
    }

    @Override
    public void add(String w) {
        add(w, 1);
    }

    @Override
    public Word get(int pos) {
        Node p = begin.next;
        int i = 0;
        while (i < pos) {
            p = p.next;
            i++;
        }
        return p.data;
    }

    @Override
    public int get(String w) {
        Node p = begin;
        while (!p.data.getWord().equals(w)) {
            if (p.next == null) {
                return 0;
            }
            p = p.next;
        }
        return p.data.getFrequency();
    }

    public void moveLeft(Node x) {
        Node y = x.prev;
        while(y.data != begin.data) { // solange begin nicht erreicht
            if (x.data.getFrequency() > y.data.getFrequency()) {
                x.next.prev = y;
                y.next = x.next;
                y.prev.next = x;
                x.prev = y.prev;
                x.next = y;
                y.prev = x;
                y = x.prev;
            } else {
                break;
            }
        }
    }
    private static class Node {
        private Node next;
        private Node prev;
        private Word data;

        Node (Word x, Node n, Node p) {
            data = x;
            next = n;
            prev = p;
        }
    }
}
