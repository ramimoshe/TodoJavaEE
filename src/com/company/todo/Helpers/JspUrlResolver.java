package com.company.todo.Helpers;

/**
 * Created by rami.moshe on 4/29/2015.
 */
public class JspUrlResolver {
    public static String getJspUrl(String jspName){
        return "/WEB-INF/jsp/" + jspName;
    }
}
