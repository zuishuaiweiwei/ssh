package myconverter;

import ognl.DefaultTypeConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author Administrator
 * @create 6/9
 */
public class DateConverter extends DefaultTypeConverter {
    @Override
    public Object convertValue(Map context, Object value, Class toType) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String dateValue = ((String[]) value)[0];
            System.out.println(dateValue);
            if (toType == Date.class) {
                System.out.println("111111111" + dateValue);
                System.out.println("111111111" + format.parse(dateValue));
                return format.parse(dateValue);
            } else if (toType == String.class) {
                System.out.println("```````````" + dateValue.toString());
                System.out.println("```````````" + ((Date) value).toString());
                return ((Date) value).toString();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}