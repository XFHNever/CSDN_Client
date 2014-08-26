package com.nju.csdnclient.service;

import com.nju.csdnclient.bean.CSDNUnit;
import com.nju.csdnclient.bean.exception.CSDNException;
import com.nju.csdnclient.util.constant.UnitConstant;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by never on 2014/8/25.
 */
public class UnitServiceTest {
    private int currentPage  = 0;
    @Before
    public void setUp() {
    }

    @Test
    public void testYEJIE() throws CSDNException {
        List<CSDNUnit> list = UnitService.getCSDNUnits(UnitConstant.Unit_TYPE_YEJIE, currentPage);
        for (CSDNUnit csdnUnit: list) {
            System.out.println(csdnUnit);
        }
    }
}
