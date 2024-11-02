package be.luxuryoverdosis.framework.base.tool.adapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import be.luxuryoverdosis.framework.base.tool.DateTool;

public class DateAdapter extends XmlAdapter<String, Date> {

    @Override
    public String marshal(final Date date) {
        return new SimpleDateFormat(DateTool.UTIL_DATE_PATTERN).format(date);
    }

    @Override
    public Date unmarshal(final String date) throws ParseException {
        return new SimpleDateFormat(DateTool.UTIL_DATE_PATTERN).parse(date);
    }

}
