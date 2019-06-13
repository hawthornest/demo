package test.com.example.demo.common; 

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import com.example.demo.common.DailyMethod;

import static org.junit.Assert.assertEquals;

public class DailyMethodTest {
    private DailyMethod dailyMethod = new DailyMethod();
    static int a;

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
}

@BeforeClass
public static void beforeClass() throws Exception {
    a = 9;
}

    /**
* 
* Method: add(int a, int b) 
* 
*/ 
@Test
public void testAdd() throws Exception {
    assertEquals(1,dailyMethod.add(a,1));
//    assertEquals(3,dailyMethod.add(0,1));
//    assertEqualsrtEquals("equals","1","1");
} 
}
