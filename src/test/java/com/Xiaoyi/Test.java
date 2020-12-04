package com.Xiaoyi;

import com.Xiaoyi.test.DynamicProxyTestAutoBoxing;
import com.Xiaoyi.test.ITestAutoBoxing;
import com.Xiaoyi.test.TestAutoBoxing;

/**
 * @author ：Xiaoyi
 * @Date ：Created in 16:08 2020/12/1
 * @Description：
 * @Modified By：
 * @Version: 1.0
 */
public class Test{
    public static void main(String[] args) {
        ITestAutoBoxing testAutoBoxing = (ITestAutoBoxing)new DynamicProxyTestAutoBoxing().bind(new TestAutoBoxing());
        testAutoBoxing.LongSum();
        testAutoBoxing.longSum();
    }

}
