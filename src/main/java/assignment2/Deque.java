import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Generic data type which represents double-ended queue or deque (pronounced "deck"). It is a
 * generalization of a stack and a queue that supports adding and removing items from either the
 * front or the back of the data structure. For more complexity current implementation is based on
 */

public class Deque<Item> implements Iterable<Item> {

    private static final int MIN_ARRAY_SIZE = 4;

    private Item[] items;

    private int itemCount, firstPosition, lastPosition, lenght;


    // @SuppressWarnings("unchecked")

    public Deque() {
        itemCount = 0;
        items = (Item[]) new Object[MIN_ARRAY_SIZE];
        firstPosition = 0;
        lastPosition = 1;
        lenght = MIN_ARRAY_SIZE;
    }

    // is the deque empty?

    public boolean isEmpty() {
        return size() == 0;
    }

    // return the number of items on the deque

    public int size() {
        return itemCount;
    }

    // add the item to the front

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("You can not add Null to deque!");
        }
        // если массив заполнен, то надо увеличить
        if (itemCount == lenght) {
            resize(lenght << 1);
        }
        // проверить позицию, если она вышла за границы, надо "зациклить"
        // firstPosition становиться в конец массива
        if (firstPosition < 0) {
            // resize(lenght + itemCount, Side.FRONT);
            firstPosition = lenght - 1;
            // resize(lenght << 1, Side.FRONT);
        }
        items[firstPosition--] = item;
        itemCount++;
    }

    // add the item to the back

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("You can not add Null to deque!");
        }

        if (itemCount == lenght) {
            resize(lenght << 1);
        }
        // проверить позицию, если она вышла за границы, надо "зациклить"
        // lastPosition становиться в начало массива

        if (lastPosition == lenght) {
            lastPosition = 0;
            // resize(lenght + itemCount, Side.END);
            // resize(lenght << 1, Side.END);
        }
        items[lastPosition++] = item;
        itemCount++;
    }

    // remove and return the item from the front

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is already empty!");
        }
        Item item = items[++firstPosition];


        items[firstPosition] = null;
        itemCount--;

        /*
        StdOut.println("Remove first");
        StdOut.print("i:  ");
        StdOut.println(item);
        StdOut.print("count:  ");
        StdOut.println(itemCount);
        StdOut.print("last:  ");
        StdOut.println(lastPosition);
        StdOut.print("first:  ");
        StdOut.println(firstPosition);
        StdOut.print("diff:  ");
        StdOut.println(lastPosition - firstPosition - 1);
        */

        if (itemCount > 0 && itemCount == lenght >> 2) {
            resize(lenght >> 1);
        }

        if (firstPosition == (lenght - 1)) {

            firstPosition = -1;
            // resize(itemCount << 1, Side.BOTH);

        }
        return item;
    }

    // remove and return the item from the back

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is already empty!");
        }
        Item item = items[--lastPosition];


        items[lastPosition] = null;
        itemCount--;
        /*
        StdOut.println("Remove last");
        StdOut.print("i:  ");
        StdOut.println(item);
        StdOut.print("count:  ");
        StdOut.println(itemCount);
        StdOut.print("last:  ");
        StdOut.println(lastPosition);
        StdOut.print("first:  ");
        StdOut.println(firstPosition);
        StdOut.print("diff:  ");
        StdOut.println(lastPosition - firstPosition - 1);
        */

        if (itemCount > 0 && itemCount == lenght >> 2) resize(lenght >> 1);

        if (lastPosition == 0) {

            lastPosition = lenght;
            // resize(itemCount << 1, Side.BOTH);

        }
        return item;
    }

    // return an iterator over items in order from front to back

    public Iterator<Item> iterator() {
        return new Iterator<Item>() {

            private int cursor = firstPosition;


            @Override
            public boolean hasNext() {
                return cursor != lastPosition - 1;
            }


            @Override
            public Item next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("You've already reached the end of deque!");
                }
                ++cursor;
                cursor = cursor % lenght;

                return items[cursor];
            }


            @Override
            public void remove() {
                throw new UnsupportedOperationException("Remove action is not supported!");
            }
        };
    }


    // @SuppressWarnings("unchecked")

    private void resize(int capacity) {


        if (capacity < lenght) {

            if (capacity < 4) return;

        }


        // проверить начало, конец и длину, если хватает и это увеличение, то менять массив не надо
        // иначе надо изменить индексы

        // если начало больше чем хвост, то копировать массив надо с 0 до "хвоста" и с "начала"
        // до конца массива
        // соотвественно индекс "начала" надо пересчитать под новый размер


        Item[] tmpArr = (Item[]) new Object[capacity];

        if (firstPosition + 1 >= lastPosition) {

            // for (int i = 0; i < lastPosition; i++)
            //     tmpArr[i] = items[i];
            //
            // if (downgrade)
            //     // space = firstPosition - lastPosition - capacity;
            //     space = -capacity;
            // else
            //     // space = firstPosition - lastPosition + lenght;
            //     space = lenght;
            //
            // for (int i = firstPosition; i < lenght; i++)
            //     tmpArr[i + space] = items[i];
            //
            // firstPosition = firstPosition + space;
            int j = 0;
            for (int i = firstPosition + 1; i < lenght; i++) {
                tmpArr[j] = items[i];
                j++;
            }
            for (int i = 0; i < lastPosition; i++) {
                tmpArr[j] = items[i];
                j++;
            }
            firstPosition = -1;
            lastPosition = j;
        }
        else {
            int j = 0;
            for (int i = firstPosition + 1; i < lastPosition; i++) {

                tmpArr[j] = items[i];
                j++;
            }
            firstPosition = -1;
            lastPosition = j;
        }

        lenght = capacity;


        items = tmpArr;
    }


    private enum Side {

        FRONT,
        END,
        BOTH
    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();

        // for (int i = 0; i < 256; i++) {
        //
        //     deque.addFirst(i);
        //
        // }
        //
        // StdOut.println(deque.size());
        //
        // for (int i = 0; i < 256; i++) {
        //     deque.removeFirst();
        // }
        //
        // for (int i = 0; i < 256; i++) {
        //
        //     deque.addLast(i);
        //
        // }
        //
        // StdOut.println(deque.size());
        // for (int i = 0; i < 256; i++) {
        //     deque.removeLast();
        // }
        // StdOut.println(deque.size());

        //******

        for (int i = 0; i < 566; i++) {
            if (i % 2 == 0) {
                deque.addFirst(i);
            }
            else {
                deque.addLast(i);
            }
        }
        StdOut.println(deque.size());


        for (int i = 0; i < 566; i++) {
            if (i % 3 == 0) {
                deque.removeLast();
            }
            else {
                deque.removeFirst();
            }
        }
        StdOut.println(deque.size());


        for (int i = 0; i < 128; i++) {
            if (i % 2 == 0) {
                deque.addFirst(i);
            }
            else {
                deque.addLast(i);
            }
        }
        StdOut.println(deque.size());

        // Deque<String> deque2 = new Deque<>();
        // deque2.addLast("CAJYPHSLBX");
        // deque2.addLast("TQCTLIGAHG");
        // deque2.removeFirst();
        // deque2.addLast("EUXQVSMUWF");
        // deque2.removeFirst();
        // deque2.removeFirst();
        // deque2.addLast("XLGQQIHPXE");


        Deque<String> deque3 = new Deque<>();
        deque3.addFirst("CAJYPHSLBX");
        deque3.removeLast();
        deque3.addFirst("TQCTLIGAHG");
        deque3.addFirst("EUXQVSMUWF");
        deque3.removeLast();
        deque3.removeLast();
        deque3.addFirst("XLGQQIHPXE");
    }

}
