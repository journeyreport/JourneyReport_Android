package com.edicasoft.journeyreport.network.api.transformers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.simpleframework.xml.transform.Transform;

public class DateTransformer implements Transform<Date> {

    private final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.US);

    @Override
    public Date read(String value) throws Exception {
        return format.parse(value);
    }

    @Override
    public String write(Date value) {
        return format.format(value);
    }
}
