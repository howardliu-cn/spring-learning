package cn.howardliu.spring.learning.ms.zuul;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <br>created at 18-3-4
 *
 * @author liuxh
 * @since 0.1.0
 */
public class PatternRouteTester {
    private static final Logger logger = LoggerFactory.getLogger(PatternRouteTester.class);

    @Test
    public void test() {
        Pattern servicePattern = Pattern.compile("(?<name>^.+)-(?<version>v.+$)");
        Matcher matcher = servicePattern.matcher("simple-provider-user-v1");
        String route = matcher.replaceFirst("${version}/${name}");
        Assert.assertNotNull(route);
        Assert.assertEquals("v1/simple-provider-user", route);
    }
}
