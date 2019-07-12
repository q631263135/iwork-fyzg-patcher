package mediator.chap1_self;



import java.util.Random;

public class Sale {

    public int sellIBMComputer(int number) {
        System.out.println("销售IBM电脑" + number + "台");
        return number;
    }

    public int getSaleStatus() {
        Random rand = new Random(System.currentTimeMillis());
        int saleStatus = rand.nextInt(100);
        System.out.println("IBM电脑的销售情况为：" + saleStatus);
        return saleStatus;
    }

}
