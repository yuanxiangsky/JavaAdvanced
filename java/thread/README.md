#### 1、线程的状态转换
[线程转换](https://cloud.githubusercontent.com/assets/14313582/26517424/c077b30a-42ca-11e7-8e09-898d6a68be4d.jpg)                                                             
#### 2、线程同步
Question :java中原子性操作(非原子性操作在多线程中都可能被其他线程打断)

1、可重入锁ReentrantLock<br/>
        
    class Bank{
        private Lock bankLock = new ReentrantLock();  //在对象里面定义锁，每一个对象都有一把锁
         bankLock.lock()    //获得锁
         try{
            需要同步的代码块
         } finally{
            bankLock.unlock();  // 释放锁
         }
         
    }
    
该锁是可重入的是指，当前如果已经获得了该锁的线程可以再次获取该锁，锁的持有计数++

2、条件对象（condition variable）
设想如下场景，当一个线程需要从银行账户转出钱，此时账户钱不够，那么该线程需要等待其他线程转入才能继续执行
下去，但是由于转出的线程已经获得了银行的锁，其他线程不能进入。此时单靠锁机制解决不了这个问题。
一个锁对象可以关联多个条件，条件满足即可执行
    
    public class Bank {
        private Condition sufficientFunds;
        private Lock bankLock = new ReentrantLock();
        public Bank(){
            sufficientFunds = bankLock.newCondition(); //锁上添加条件
        }
        public void transfer(){
            while(...)  //条件不满足
                sufficientFunds.await(); //当前线程释放锁，线程进入该条件等待集，线程阻塞
        }
    
    }
    当另一个线程往账户转入钱之后
    sufficientFunds.singalAll();//通知因为该条件等待的所有线程,这些线程将再次竞争锁，进入该对象
    

Lock 和 Condition 可以提供高度的锁定控制，一般情况下使用synchronized就能保持同步。
从java1.0 开始每个对象都有一个内部锁，如果一个方法使用了synchronized声明，那么要想使用该方法就得先获取对象的
内部锁。内部锁之后一个相关条件。

内部锁缺陷：
1、不能中断正在获取锁的线程
2、试图获取锁不能设定超时
3、锁只有单一的条件，可能不够

4、ReentrantReadWriteLock 读写锁