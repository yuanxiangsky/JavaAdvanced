package thread;

/**
 * Created by YuanXiang on 2017/5/25.
 */
public class TransferRunnable implements Runnable {

    private Bank bank;
    private int fromAccount;
    private double maxAmount;
    private final int DELAY=10;

    /**
     * 构造方法
     * @param bank 银行
     * @param fromAccount 转出账户
     * @param maxAmount 最大转出额
     */
    public TransferRunnable(Bank bank, int fromAccount, double maxAmount) {
        this.bank = bank;
        this.fromAccount = fromAccount;
        this.maxAmount = maxAmount;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int toAccount = (int) (bank.size() * Math.random());
                double amount = maxAmount * Math.random();
                bank.transfer(fromAccount, toAccount, amount);
                Thread.sleep((int) (DELAY * Math.random()));
            }
        }catch (Exception e){

        }

    }
}
