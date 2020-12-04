package com.Xiaoyi.depositandtake;

/**
 * @author ：Xiaoyi
 * @Date ：Created in 17:00 2020/12/5
 * @Description：
 * @Modified By：
 * @Version: 1.0
 */
public class TakeThread implements Runnable {
    @Override
    public void run() {
        DepositAndTake.getInstance().take();
    }
}
