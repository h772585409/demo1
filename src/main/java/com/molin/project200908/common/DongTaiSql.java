package com.molin.project200908.common;

import org.apache.ibatis.jdbc.SQL;

public class DongTaiSql {
    public static String itemFindByString(String queryString) {
        SQL sql = new SQL();
        sql.SELECT("*").FROM("t_checkitem");

        if (queryString != "" && queryString != null) {
            queryString = "'%" + queryString + "%'";
            sql.WHERE(" code like " + queryString + " or " + " name like " + queryString);
        }
        return sql.toString();
    }

    public static String groupFindByString(String queryString) {
        SQL sql = new SQL();
        sql.SELECT("*").FROM("t_checkgroup");

        if (queryString != "" && queryString != null) {
            queryString = "'%" + queryString + "%'";
            sql.WHERE(" code like " + queryString + " or " + " name like " + queryString);
        }
        return sql.toString();
    }

    public static String setmealFindByString(String queryString) {
        SQL sql = new SQL();
        sql.SELECT("*").FROM("t_setmeal");

        if (queryString != "" && queryString != null) {
            queryString = "'%" + queryString + "%'";
            sql.WHERE(" code like " + queryString + " or " + " name like " + queryString);
        }
        return sql.toString();
    }

    public static String permissionFindByString(String table, String queryString) {
        SQL sql = new SQL();
        sql.SELECT("*").FROM(table);

        if (queryString != "" && queryString != null) {
            queryString = "'%" + queryString + "%'";
            sql.WHERE(" name like " + queryString);
        }
        return sql.toString();
    }
    public static String userFindByString(String table, String queryString) {
        SQL sql = new SQL();
        sql.SELECT("*").FROM(table);

        if (queryString != "" && queryString != null) {
            queryString = "'%" + queryString + "%'";
            sql.WHERE(" username like " + queryString);
        }
        return sql.toString();
    }

}
