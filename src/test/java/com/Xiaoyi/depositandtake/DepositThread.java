package com.Xiaoyi.depositandtake;

/**
 * @author ：Xiaoyi
 * @Date ：Created in 16:34 2020/12/5
 * @Description：
 * @Modified By：
 * @Version: 1.0
 */
public class DepositThread implements Runnable {
    @Override
    public void run() {
        DepositAndTake.getInstance().desposit();
    }
}
