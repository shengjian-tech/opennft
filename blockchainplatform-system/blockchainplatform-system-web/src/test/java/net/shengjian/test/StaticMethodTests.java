package net.shengjian.test;

import net.shengjian.frame.util.SecUtils;
import org.junit.jupiter.api.Test;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

public class StaticMethodTests {

    @Test
    public void randomIntegrTest() {
        System.out.println(SecUtils.randomInteger(1000));
    }

    @Test
    public void getTimeNOTest() {
        System.out.println(SecUtils.getTimeNO());
    }

    @Test
    public void testUrlPath() {
        PathMatcher matcher = new AntPathMatcher();

        String patternPath = "/a/*/c";
        String uri = "/a/b/c";

        System.out.println(matcher.match(patternPath, uri));

    }


}
