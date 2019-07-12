package mediator.chap1_self;

public class Purchase {

    public int buyIBMComputer(int number, int status) {
        int buyNumber = number;

        if (status > 80) {
            System.out.println("采购IBM电脑：" + number + "台");
        } else {
            buyNumber = number / 2;
            System.out.println("采购IBM电脑：" + buyNumber + "台");
        }
        return buyNumber;
    }

    public void refuseBuyIBM() {
        System.out.println("不再采购IBM电脑");
    }

}
