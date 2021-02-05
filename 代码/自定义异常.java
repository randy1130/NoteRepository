package com.sxt;

/**
 * 自定义异常
 */
public class Main {
    public static void main(String[] args) {
        try {
            doSome(10, 0);
        } catch (MyException e) {
            String message = e.getMessage();
            System.out.println(message);
        }
    }

    public static int doSome(int i, int j) throws MyException {
        if (j != 0) {
            return i / j;
        } else {
            throw new MyException("除数不能为0");
        }
    }
}

class MyException extends Exception {
    public MyException(String message) {
        super(message);
    }
}
