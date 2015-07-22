package com.openhr.employee;

import static com.openhr.factories.EmployeeFactory.findLastId;
import static java.lang.String.valueOf;

public class EmployeeIdUtility {

    public static String nextId() throws Exception {
        Integer lastId = findLastId() + 1;
        int length = valueOf(lastId).length();

        if (length == 1) {
            return valueOf("000" + lastId);
        }
        if (length == 2) {
            return valueOf("00" + lastId);
        }
        if (length == 3) {
            return valueOf("0" + lastId);
        }

        return valueOf(lastId);

    }

}
