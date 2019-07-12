package mediator.chap2;

public abstract class AbstractMediator {
    protected Purchase purchase;
    protected Sale sale;
    protected Stock stock;

    public AbstractMediator(Purchase purchase, Sale sale, Stock stock) {
        this.purchase = new Purchase(this);
        this.sale = new Sale(this);
        this.stock = new Stock(this);
        // 为什么这里把this传递给构造，我隐约觉得，这其中有可扩展性方面的优势
        // 把this传过去：
        // 1. 通过this的stock、sale、purchase，替换原来关系中的，这样做由原来的多个关系，降低为1:1关系
        // 2. 可以覆盖stock、sale、purchase的业务逻辑，如果不满意this的stock、sale、purchase，可以扩展
    }

    public abstract void execute(String str, Object... objects);
}
