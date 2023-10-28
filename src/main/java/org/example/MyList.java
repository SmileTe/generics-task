package org.example;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Function;
import java.util.Arrays;
public class MyList<T extends Number> implements Iterable {
    private T[] array;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public MyList() {
        array =(T[]) Array.newInstance(Number.class, DEFAULT_CAPACITY);;
        size = 0;
    }

    public MyList(T[] array) {
        this.array = array;
        size = array.length;
    }

    @Override
    public Iterator iterator() {
        return new MyListIterator();
    }

    public void add(T item) {
        if (size == array.length) {
            resize();
        }
        array[size++] = item;
    }

    public T get(int index) {
        return array[index];
    }

    private void resize() {
        int newCapacity = array.length * 2;
        array = Arrays.copyOf(array, newCapacity);
    }

    public T remove(int index) {
        try {


            T oldValue = array[index];
            final int newSize;
            fastRemove(array, index);

            return oldValue;
        }
        catch (ArrayIndexOutOfBoundsException e) {
           throw  new ArrayIndexOutOfBoundsException("Некорректный индекс");

        }


    }

    private void fastRemove(Object[] es, int i) {
        int newSize;
        if ((newSize = size - 1) > i)
            System.arraycopy(es, i + 1, es, i, newSize - i);
        es[size = newSize] = null;
    }

    public <R extends Number> MyList<R> map(Function<T, R> mapper) {
        MyList<R> list = new MyList<>();
        for (int i = 0; i < size; i++) {
            list.add(mapper.apply(this.get(i)));
        }
        return list;
    }

    public int size() {
        return size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyList<?> myList = (MyList<?>) o;
        return size == myList.size && Arrays.equals(array, myList.array);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(array);
        return result;
    }

    @Override
    public String toString() {
        return "MyList{" +
                "array=" + Arrays.toString(array) +
                ", size=" + size +
                '}';
    }

    /*
    статический класс может быть создан без экземпляра объекта
    и может использоваться один объект статического класса для всех объектов класса MyList.
    Но в данном случае идет обращение к данным объекта, поэтому не указан static
     */

    class MyListIterator implements Iterator<Integer>{
       int nextIndex;
        public MyListIterator() {
            nextIndex = 0;
        }

        @Override
        public boolean hasNext() {
            return nextIndex<size;
        }

        @Override
        public Integer next() {
            Integer result =  (Integer) array[nextIndex];
            nextIndex++;
            return result;
        }
    }

}
