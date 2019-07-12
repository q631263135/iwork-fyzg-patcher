package mediator.chap1_self;

public class Client {
    public static void main(String[] args) {
        Stock stock = new Stock();
        Purchase purchase = new Purchase();
        Sale sale = new Sale();

        System.out.println("------采购人员采购电脑------");
        int number = purchase.buyIBMComputer(100, sale.getSaleStatus());
        stock.increase(number);

        System.out.println("------销售人员销售电脑电脑------");
        number = sale.sellIBMComputer(1);
        stock.decrease(number);
    }
}
