import java.util.concurrent.atomic.AtomicInteger;

class Person {
    int number = 0;
    AtomicInteger atomicInteger = new AtomicInteger();

    public void add() {
        number++;
    }

    public void atomicAdd() {
        atomicInteger.incrementAndGet();
    }
}

public class Demo {
    public static void main(String[] args) {
        Person person = new Person();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    person.add();
                    person.atomicAdd();
                }
            }).start();
        }
        //Thread.activeCount()判断有几个线程在跑，一般是jvm和gc
        while (Thread.activeCount() > 2) {
            //大于2个线程就礼让线程，不让主方法跑下去
            Thread.yield();
        }
        /**
         * 1.第一个没有保证原子性导致出来的结果小于20000
         * 2.第二个采用的原子操作包类AtomicInteger解决了这一问题的结果为20000
         */
        System.out.println("number=" + person.number);//number=19989
        System.out.println("atomicInteger=" + person.atomicInteger);//atomicInteger=20000
    }
}