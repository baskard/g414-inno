package com.g414.inno.db;

import com.g414.inno.jna.impl.InnoDB;

public class Util {
    public static void assertSuccess(int code) {
        if (code != InnoDB.db_err.DB_SUCCESS) {
            throw new InnoException("INNODB Error " + code + " : "
                    + InnoDB.ib_strerror(code).getString(0));
        }
    }

    public static void assertSchemaOperationSuccess(int code) {
        if (code != InnoDB.IB_TRUE) {
            throw new InnoException("INNODB Error " + code + " : "
                    + InnoDB.ib_strerror(code).getString(0));
        }
    }

    public static boolean wasInsertSuccess(int code) {
        switch (code) {
        case InnoDB.db_err.DB_SUCCESS:
            return true;
        case InnoDB.db_err.DB_DUPLICATE_KEY:
            return false;
        default:
            throw new InnoException("INNODB Error " + code + " : "
                    + InnoDB.ib_strerror(code).getString(0));
        }
    }

    public static boolean wasUpdateSuccess(int code) {
        switch (code) {
        case InnoDB.db_err.DB_SUCCESS:
            return true;
        case InnoDB.db_err.DB_NO_REFERENCED_ROW:
        case InnoDB.db_err.DB_RECORD_NOT_FOUND:
        case InnoDB.db_err.DB_DUPLICATE_KEY:
            return false;
        default:
            throw new InnoException("INNODB Error " + code + " : "
                    + InnoDB.ib_strerror(code).getString(0));
        }
    }
}
