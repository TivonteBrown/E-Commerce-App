package com.revature.eCommerce;
import com.revature.eCommerce.utils.JavalinUtil;

import java.sql.SQLException;
import java.io.IOException;
import io.javalin.Javalin;

import static io.javalin.apibuilder.ApiBuilder.*;

public class App {
    public static void main( String[] args ) throws SQLException, IOException  {

         new JavalinUtil().getJavalin().start(7070);
    }

}



