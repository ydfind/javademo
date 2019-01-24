package com.ydfind.dubbo.provider;
import com.ydfind.dubbo.DemoService;
/**
 * @author Yudi
 */
public class DemoServiceImpl implements DemoService {
    public String sayHello(String name) {
        return "Hello " + name;
    }
}