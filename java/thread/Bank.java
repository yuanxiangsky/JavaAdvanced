package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by YuanXiang on 2017/5/25.
 */
public class Bank {
    private final double [] accounts;
    private Lock bankLock = new ReentrantLock();  //对bank创建一把显示锁
    private Condition sufficientFunds;
    /**
     * 初始化银行账户
     * @param n 账户数目
     * @param initalBanace 初始账户余额
     */
    public Bank(int n, double initalBanace) {
        accounts = new double[n];
        for(int i=0;i<accounts.length;i++)
            accounts[i] = initalBanace;
        sufficientFunds = bankLock.newCondition();
    }

    /**
     * 账户交易
     * @param from 转出账户
     * @param to 转入账户
     * @param amount 金额
     */
    public   void  transfer(int from,int to,double amount) throws InterruptedException {
        bankLock.lock();
        try {
            if (accounts[from] < amount)
                sufficientFunds.await();
            System.out.print(Thread.currentThread());
            accounts[from] -= amount;
            System.out.printf("%10.2f from %d to %d", amount, from, to);
            accounts[to] += amount;
            System.out.printf("The Balance: %10.2fn", getTotalBalance());
            /*System.out.println("锁的计数为"+bankLock.);*/
            System.out.println();
            sufficientFunds.signal();

        }finally {
             bankLock.unlock();
        }
    }

    public double getTotalBalance(){
        bankLock.lock();
        try {
            double sum = 0;
            for (double a : accounts)
                sum += a;
            return sum;
        }finally {
            bankLock.unlock();
        }
    }

    public int size(){
        return accounts.length;
    }
}
