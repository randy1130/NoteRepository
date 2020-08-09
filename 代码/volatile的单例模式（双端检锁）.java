class Plant {
    //volatile采用了禁止指令重排
    private static volatile Plant plant = null;

    private Plant() {
        System.out.println("Plant的构造方法执行了");
    }
    public static Plant getInstance() {
        //双端检锁
        if (plant == null) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (Plant.class) {
                //双端检锁
                if (plant == null) {
                    plant = new Plant();
                }
            }
        }
        return plant;
    }
}
public class Demo {
    public static void main(String[] args) {
        for (int j = 0; j < 20; j++) {
            new Thread(() -> {
                Plant plant = Plant.getInstance();
            }).start();
        }
    }
}