package com.Xiaoyi.depositandtake;

/**
 * @author ：Xiaoyi
 * @Date ：Created in 16:38 2020/12/5
 * @Description：
 * @Modified By：
 * @Version: 1.0
 */
public class Test {
    public static void main(String[] args) {
        for (int i = 0; i< 10; i++){
            new Thread(new DepositThread()).start();
        }
        for (int i = 0; i< 10; i++){
            new Thread(new TakeThread()).start();
        }
    }
}
