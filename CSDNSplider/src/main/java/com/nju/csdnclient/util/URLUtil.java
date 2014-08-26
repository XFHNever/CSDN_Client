package com.nju.csdnclient.util;

import com.nju.csdnclient.util.constant.URLConstant;
import com.nju.csdnclient.util.constant.UnitConstant;

/**
 * Created by never on 2014/8/25.
 */
public class URLUtil {
    public static String generateUrl(int unitType, int currentPage) {
        currentPage = currentPage > 0 ? currentPage : 1;

        String url = "";

        switch (unitType) {
            case UnitConstant.Unit_TYPE_YEJIE:
                url = URLConstant.Unit_URL_YEJIE;
                break;
            case UnitConstant.Unit_TYPE_CHENGXUYUAN:
                url = URLConstant.Unit_URL_CHENGXUYUAN;
                break;
            case UnitConstant.Unit_TYPE_YANFA:
                url = URLConstant.Unit_URL_YANFA;
                break;
            case UnitConstant.Unit_TYPE_YIDONG:
                url = URLConstant.Unit_URL_YIDONG;
                break;
            case UnitConstant.Unit_TYPE_YUNJISUAN:
                url = URLConstant.Unit_URL_YUNJISUAN;
                break;
            default:
                break;
        }

        url = url + "/" + currentPage;

        return url;
    }
}
