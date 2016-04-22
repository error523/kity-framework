package pu.study.test;

import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

import static org.junit.Assert.assertEquals;

/**
 * Created by project on 2016/4/12.
 */
public class ClassTest {
    @Test
    public void testResource(){
        URL url = Thread.currentThread().getContextClassLoader().getResource("pu/study/test");
        System.out.println(url);
        String protocol =  url.getProtocol();
        System.out.print(protocol);
        assert "get:testAction".matches("\\w+:\\w*");
//        try {
//            Enumeration<URL> ems = Thread.currentThread().getContextClassLoader().getResources("pu/study/test");
////            assertEquals("file:/D:/MyWorkSpace/IDEA/kity-framework/target/test-classes/pu/study/test/innerTest",
////                    ems.nextElement());
//            while(ems.hasMoreElements()){
//                //System.out.println(ems.nextElement());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }


}
