/* *****************************************************************************
 *  Name:
 *  Date: 25/02/2020
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

// construct an empty randomized queue

public class RandomizedQueue<Item> implements Iterable<Item> {

    private static final int MIN_ARRAY_SIZE = 2;
    private Item[] items;
    private int count = 0;


    // @SuppressWarnings("unchecked")
    public RandomizedQueue() {
        items = (Item[]) new Object[MIN_ARRAY_SIZE];
    }


    // @SuppressWarnings("unchecked")
    private RandomizedQueue(Item[] array, int count) {
        int length = array.length;
        items = (Item[]) new Object[length];
        System.arraycopy(array, 0, items, 0, length);
        this.count = count;
    }

    // is the randomized queue empty?

    public boolean isEmpty() {
        return count == 0;
    }          // is the queue empty?

    // return the number of items on the randomized queue

    public int size() {
        return count;
    }                     // return the number of items on the queue

    // add the item

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("You can not add Null to randomized queue!");
        }
        if (count == items.length) {
            resize(count << 1);
        }
        items[count++] = item;
    }

    // remove and return a random item

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Randomized queue is already empty!");
        }
        int index = StdRandom.uniform(0, count);
        Item item = items[index];
        items[index] = items[--count];
        items[count] = null;
        if (count > 0 && count <= items.length >> 2) {
            resize(items.length >> 1);
        }
        return item;
    }


    // return a random item (but do not remove it)

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("Randomized queue is already empty!");
        }
        return items[StdRandom.uniform(0, count)];
    }

    // return an independent iterator over items in random orde

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator<>();
    }


    // @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        if (capacity < count) {
            return;
        }
        Item[] tmpArr = (Item[]) new Object[capacity];
        System.arraycopy(items, 0, tmpArr, 0, count);
        items = tmpArr;
    }


    private class RandomizedQueueIterator<T> implements Iterator<T> {

        // @SuppressWarnings("unchecked")
        private RandomizedQueue<T> randomizedQueue = new RandomizedQueue<>((T[]) items, count);


        @Override
        public boolean hasNext() {
            return randomizedQueue.size() > 0;
        }


        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException(
                        "You've already reached the end of randomized queue!");
            }
            return randomizedQueue.dequeue();
        }


        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove action is not supported!");
        }

    }

    public static void main(String[] args) {
        // RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();
        // StdOut.println("Queue is empty: " + randomizedQueue.isEmpty());
        // for (int i = 0; i < 30; i++) {
        //     randomizedQueue.enqueue(i);
        //     if (i % 3 == 0) {
        //         randomizedQueue.dequeue();
        //     }
        // }
        // StdOut.println("Size: " + randomizedQueue.size());
        // StreamSupport.stream(randomizedQueue.spliterator(), false)
        //         .forEach(a -> StdOut.print(a + " "));
        // StdOut.println();
        // StdOut.print("Random pick ups: ");
        // for (int j = 0; j < 5; j++) {
        //     StdOut.print(randomizedQueue.sample() + " ");

        int n = 9;
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        for (int i = 0; i < n; i++)
            queue.enqueue(i);
        for (int a : queue) {
            for (int b : queue)
                StdOut.print(a + "-" + b + " ");
            StdOut.println();

        }
    }
}
