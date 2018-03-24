package com.itlty.webapp.utils;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class StreamTool{
    public static String readInputStream( InputStream is ){
        try{
            ByteArrayOutputStream out = new ByteArrayOutputStream( );
            int length = 0;
            byte[] buffer = new byte[1024];
            if( ( length = is.read( buffer ) ) != -1 ){
                out.write( buffer, 0, length );
            }
            is.close( );
            byte[] result = out.toByteArray( );
            return new String( result );
        }catch( Exception e ){
            e.printStackTrace( );
        }
        return "转换失败...";
    }
}
