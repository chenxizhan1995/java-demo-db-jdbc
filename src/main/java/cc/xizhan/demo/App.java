package cc.xizhan.demo;

import java.sql.JDBCType;

/**
 * Hello world!
 *
 */
public class App
{
    public void listJDBCType(){
        for (JDBCType jt: JDBCType.values()){
            System.out.println(String.format("%-2d, %-12s, %s"
                    , jt.ordinal(), jt.getName(), jt.getVendor()));
        }
    }
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}
