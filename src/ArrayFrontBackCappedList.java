import java.sql.Array;
import java.util.Arrays;
import java.util.List;

public class ArrayFrontBackCappedList <T> implements FrontBackCappedListInterface{

    private T[] list;
    private int numberOfElements;

    public ArrayFrontBackCappedList(int maxSize) {
        list = (T[]) new Object[maxSize];
        numberOfElements = 0;
    }

    @Override
    public String toString() {
        Object[] arrayToPrint = new Object[numberOfElements];
        for(int i = 0; i < numberOfElements; i++) {
            arrayToPrint[i] = list[i];
        }
        return "size=" + numberOfElements + "; " + "capacity=" + list.length + "; " + "\t"
                + Arrays.toString(arrayToPrint);
    }

    @Override
    public boolean addFront(Object newEntry) {
        if(!isFull()) {
            for(int i = numberOfElements - 1; i >= 0; i--) {
                list[i + 1] = list[i];
            }
            list[0] = (T) newEntry;
            numberOfElements++;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean addBack(Object newEntry) {
        if(!isFull()) {
            list[numberOfElements] = (T) newEntry;
            numberOfElements++;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Object removeFront() {
        if(!isEmpty()) {
            Object elementRemoved = list[0];
            for(int i = 1; i < numberOfElements; i++) {
                list[i - 1] = list[i];
            }
            numberOfElements--;
            return elementRemoved;
        }
        return null;
    }

    @Override
    public Object removeBack() {
        if(!isEmpty()) {
            Object removedElement = list[numberOfElements - 1];
            numberOfElements--;
            return removedElement;
        }else {
            return null;
        }
    }

    @Override
    public void clear() {
        for (int i = 0; i < numberOfElements; i++) {
            list[i] = null;
        }
        numberOfElements = 0;
    }

    @Override
    public Object getEntry(int givenPosition) {
        T entry;
        if(givenPosition >= numberOfElements || givenPosition < 0) {
            entry = null;
        } else {
           entry = list[givenPosition];
            }
        return entry;
    }

    @Override
    public int indexOf(Object anEntry) {
        for(int i = 0; i < numberOfElements; i++) {
            if(list[i].equals(anEntry)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object anEntry) {
        for(int i = numberOfElements - 1; i >= 0; i--) {
            if(list[i].equals(anEntry)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean contains(Object anEntry) {
        if(isEmpty()) {
            return true;
        } else if(!isEmpty()) {
            for(int i = 0; i < numberOfElements; i++) {
               if (list[i].equals(anEntry)) {
                   return true;
               }
            }
        }
        return false;
    }

    @Override
    public int size() {
        return numberOfElements;
    }

    @Override
    public boolean isEmpty() {
        return numberOfElements == 0;
    }

    @Override
    public boolean isFull() {
        return numberOfElements == list.length;
    }
}
