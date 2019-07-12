package mediator.chap2;

// 采购管理
public class Purchase {
    private AbstractMediator mediator;

    public Purchase(AbstractMediator mediator) {
        this.mediator = mediator;
    }

    public void buyIBMComputer(int number) {

        int saleStatus = this.mediator.sale.getSaleStatus();
        if (saleStatus > 80) {
            System.out.println("采购IBM电脑：" + number + "台");
            this.mediator.stock.increase(number);
        } else {
            int buyNumber = number / 2;
            System.out.println("采购IBM电脑：" + buyNumber + "台");
            this.mediator.stock.increase(buyNumber);
        }
    }

    public void refuseBuyIBM() {
        System.out.println("不再采购IBM电脑");
    }
}
