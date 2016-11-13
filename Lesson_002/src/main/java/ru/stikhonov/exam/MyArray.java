package ru.stikhonov.exam;

import java.util.Arrays;

/**
 * @author Sergey Tikhonov
 */
class MyArray {
    private Object[] objects;

    MyArray(int elementCount) {
        this.objects = new Object[elementCount];
    }

    boolean add(Object object, int index) {
        boolean result = false;
        try {
            if (this.objects[index] == null) {
                this.objects[index] = object;
                result = true;
            }
        } catch (Exception e) {
            return result;
        }
        return result;
    }

    Object get(int index) {
        Object object;
        try {
            object = this.objects[index];
        } catch (Exception e) {
            return null;
        }
        return object;
    }
}
