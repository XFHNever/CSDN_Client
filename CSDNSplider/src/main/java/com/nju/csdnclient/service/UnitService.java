package com.nju.csdnclient.service;

import com.nju.csdnclient.bean.CSDNUnit;
import com.nju.csdnclient.bean.exception.CSDNException;
import com.nju.csdnclient.util.NetUtil;
import com.nju.csdnclient.util.URLUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by never on 2014/8/25.
 */
public class UnitService {
    public static List<CSDNUnit> getCSDNUnits(int unitType, int currentPage) throws CSDNException {
        List<CSDNUnit> csdnUnits = new ArrayList<CSDNUnit>();

        String url = URLUtil.generateUrl(unitType,currentPage);
        String htmlStr = NetUtil.doGet(url);

        CSDNUnit csdnUnit = null;

        Document document = Jsoup.parse(htmlStr);
        Elements units = document.getElementsByClass("unit");

        for (int i = 0; i < units.size(); i++) {
            csdnUnit = new CSDNUnit();
            csdnUnit.setType(unitType);

            Element unit = units.get(i);

            Element unit_h1 = unit.getElementsByTag("h1").get(0);
            Element unit_h1_a = unit_h1.child(0);
            String title = unit_h1_a.text();
            String link = unit_h1_a.attr("href");

            csdnUnit.setTitle(title);
            csdnUnit.setLink(link);

            Element unit_h4 = unit.getElementsByTag("h4").get(0);
            Element unit_h4_date = unit_h4.getElementsByClass("ago").get(0);
            String date = unit_h4_date.text();

            csdnUnit.setDate(date);

            if(unit.getElementsByTag("dl").size() > 0) {
                Element unit_d1 = unit.getElementsByTag("dl").get(0);
                Element unit_d1_dt = unit_d1.child(0);
                String imgPath = "";
                if(unit_d1_dt.getElementsByTag("a").size() > 0) {
                    Element unit_d1_dt_a = unit_d1_dt.child(0);
                    imgPath = unit_d1_dt_a.child(0).attr("src");
                }

                csdnUnit.setImgPath(imgPath);

                Element unit_d1_dd = unit_d1.child(1);
                String content = unit_d1_dd.text();

                csdnUnit.setContent(content);
            }

            csdnUnits.add(csdnUnit);
        }

        return csdnUnits;
    }
}
