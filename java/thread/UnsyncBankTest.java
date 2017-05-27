package thread;

/**
 * Created by YuanXiang on 2017/5/25.
 */
public class UnsyncBankTest {
    public static final int NACCOUNTS = 10;
    public static final double INITAL_BALANCE =1000;

    public static void main(String[] args) {
        Bank b = new Bank(NACCOUNTS, INITAL_BALANCE);
        for(int i=0;i<NACCOUNTS;i++){
            TransferRunnable r = new TransferRunnable(b,i,INITAL_BALANCE);
            Thread t = new Thread(r);
            t.start();
        }

    }
}
