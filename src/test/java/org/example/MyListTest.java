package org.example;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Iterator;

public class MyListTest {
    @Test
    public void addAndOrderElementsInListTest() {
        MyList test = new MyList<>();
        test.add(1);
        test.add(2);
        test.add(3);
        Assertions.assertAll(
                "3 сценария сравнения",
                () -> Assertions.assertEquals(1, test.get(0)),
                () -> Assertions.assertEquals(2, test.get(1)),
                () -> Assertions.assertEquals(3, test.get(2))
        );
    }

    @Test
    public void addAndOrderElementsWithDoubleInListTest() {
        MyList test = new MyList<>();
        test.add(1);
        test.add(2);
        test.add(2);
        Assertions.assertAll(
                "4 сценария сравнения",
                () -> Assertions.assertEquals(1, test.get(0)),
                () -> Assertions.assertEquals(2, test.get(1)),
                () -> Assertions.assertEquals(2, test.get(2)),
                () -> Assertions.assertEquals(3, test.size())
        );
    }

    @Test
    public void checkRemoveFromListTest() {
        MyList test = new MyList<>();
        test.add(1);
        test.add(2);
        test.add(3);
        test.remove(1);
        Assertions.assertAll(
                "1 сценарий сравнения",
                () -> Assertions.assertEquals(2, test.size()),
                () -> Assertions.assertEquals(1, test.get(0)),
                () -> Assertions.assertEquals(3, test.get(1))
        );
    }

    @Test
    public void removeIncorrectIndexFromListIsArrayIndexOutOfBoundsException() {
        MyList test = new MyList<>();
        test.add(1);
        test.add(2);
        test.add(3);
        Throwable exception = Assertions.assertThrows(ArrayIndexOutOfBoundsException.class,
                ()->{test.remove(50); }
                    );
        Assertions.assertEquals("Некорректный индекс", exception.getMessage());
    }

    @Test
    public void addElementsInlistUseIteratorTest() {
        MyList test = new MyList<>();
        test.add(1);
        test.add(2);
        test.add(3);

        MyList test2 = new MyList<>();
        Iterator<Integer> iterator = test.iterator();
        while (iterator.hasNext()) {
            test2.add(iterator.next());
        }
        Assertions.assertTrue(test.equals(test2));
    }

    @Test
    public void checkEqualsUseTwoIdenticalArraysTest() {
        Integer[] array = new Integer[]{1, 2, 3};

        MyList test = new MyList<>(array);
        MyList myList2 = new MyList<>(array);

        Assertions.assertTrue(test.equals(myList2));
    }

    @Test
    public void checkHashCodeUseTwoIdenticalArraysTest() {

        Integer[] array = new Integer[]{1,2,3};
        MyList test =  new MyList<>(array);
        MyList test2 =  new MyList<>(array);

        Assertions.assertEquals(test.hashCode(), test2.hashCode());
    }

    }


