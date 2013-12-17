package org.feuyeux.jaxrs2.atup.core.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JaxbDateSerializer extends XmlAdapter<String, Date> {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

    @Override
    public String marshal(final Date date) throws Exception {
        if (date == null) {
            return "";
        } else {
            return dateFormat.format(date);
        }
    }

    @Override
    public Date unmarshal(final String date) throws Exception {
        if (date == null || date.isEmpty()) {
            return null;
        } else {
            return dateFormat.parse(date);
        }
    }
}
