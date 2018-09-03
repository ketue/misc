package com.monical;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

/**
 * @author zijie.cao
 * @date 2018-09-03 17:21:11
 */
public class JunitMatchTest {

    @Test
    public void t0() {

        // 元素相等
        Object o = new String("1");
        assertThat(o, is(new String("1")));

        // 元素包含
        String s = "string###";
        assertThat(s, containsString("#"));

        // compare
        Date date = new Date();
        assertThat(date, equalTo(new Date()));

        // span
        assertThat(10.01, closeTo(11.00, 1.00));

        // allof
        long testedNumber = 9l;
        assertThat(testedNumber, allOf(greaterThan(8l), lessThan(16l)));

        // anyof
        int testedNumber1 = 7;
        assertThat(testedNumber1, anyOf(greaterThan(16), lessThan(8)));

        // anything -> OK
        assertThat(testedNumber1, anything());

        // not
        assertThat(10.01 - 1, not(closeTo(11.00, 1.00)));

        // ============ string related ======= //
        String testedString = "tested";
        assertThat(testedString, containsString("ed"));
        assertThat(testedString, anyOf(startsWith("t"), endsWith("ed")));
        assertThat(testedString, allOf(equalTo(testedString), equalToIgnoringCase(testedString.toUpperCase()), equalToIgnoringWhiteSpace("   \t" + testedString.toUpperCase())));

        // ============ numeric related ========== //
        double testedNum = 12.03d;
        assertThat(testedNum, lessThan(12.04));
        assertThat(testedNum, lessThanOrEqualTo(12.04d));
        assertThat(testedNum, greaterThan(12.02d));
        assertThat(testedNum, greaterThanOrEqualTo(12.03d));

        // ========== collection related ====== //
        Collection<String> coll = Lists.newArrayList("123", "234", "345");
        assertThat(coll, hasItem("234"));
        Map<String, Integer> map = ImmutableMap.of("111", 1, "2222", 3);
        assertThat(map, hasEntry("111", 1));
        assertThat(map, hasKey("111"));
        assertThat(map, hasValue(3));
    }

}
