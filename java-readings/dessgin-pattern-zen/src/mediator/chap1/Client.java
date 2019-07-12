package mediator.chap1;

public class Client {
    public static void main(String[] args) {
        System.out.println("------采购人员采购电脑------");
        Purchase purchase = new Purchase();
        purchase.buyIBMComputer(100);

        System.out.println("------销售人员销售电脑电脑------");
        Sale sale = new Sale();
        sale.sellIBMComputer(1);

        System.out.println("------库房管理人清库处理------");
        Stock stock = new Stock();
        stock.clearStock();
    }
}
