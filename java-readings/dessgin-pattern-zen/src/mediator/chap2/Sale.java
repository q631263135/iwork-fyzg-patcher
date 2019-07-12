package mediator.chap2;

import java.util.Random;

public class Sale {
    private AbstractMediator mediator;

    public Sale(AbstractMediator mediator) {
        this.mediator = mediator;
    }

    public void sellIBMComputer(int number) {
        if (this.mediator.stock.getStockNumber() < number) {
            this.mediator.purchase.buyIBMComputer(number);
        }

        System.out.println("销售IBM电脑" + number + "台");
        this.mediator.stock.decrease(number);
    }

    public int getSaleStatus() {
        Random rand = new Random(System.currentTimeMillis());
        int saleStatus = rand.nextInt(100);
        System.out.println("IBM电脑的销售情况为：" + saleStatus);
        return saleStatus;
    }

    public void offSale() {
        System.out.println("折价销售IBM电脑" + this.mediator.stock.getStockNumber());
    }
}
