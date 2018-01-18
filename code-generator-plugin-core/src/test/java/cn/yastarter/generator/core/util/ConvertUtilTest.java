package cn.yastarter.generator.core.util;

import org.junit.Test;

import java.sql.Types;

import static org.junit.Assert.*;

/**
 * @author OovEver
 * 2018/1/18 10:38
 */
public class ConvertUtilTest {

    @Test
    public void convert2CamelCase() {
        assertEquals("SysTestUser",ConvertUtil.convert2CamelCase("sys_test_user"));
    }


    @Test
    public void convert2VariableName() {
        assertEquals("sysTestUser",ConvertUtil.convert2VariableName("SysTestUser"));
    }
}