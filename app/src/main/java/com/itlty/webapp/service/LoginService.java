package com.itlty.webapp.service;
import com.itlty.webapp.utils.StreamTool;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class LoginService{
    /* 采用get方式请求*/
    public static String loginByGet( String username, String pasword ){
        try{
            String path = "http://192.168.1.104:9001/web/LoginServlet?username=" + URLEncoder
                .encode( username, "utf-8" ) + "&password" + "=" + URLEncoder.encode( pasword,
                "utf-8" );
            URL url = new URL( path );
            HttpURLConnection conn = (HttpURLConnection)url.openConnection( );
            conn.setRequestMethod( "GET" );
            conn.setReadTimeout( 5000 );
            int code = conn.getResponseCode( );
            if( code == 200 ){
                InputStream is = conn.getInputStream( );
                String result = StreamTool.readInputStream( is );
                is.close( );
                return result;
            }else{
                return null;
            }
        }catch( Exception e ){
            e.printStackTrace( );
        }
        return null;
    }

    /* 采用post方式*/
    public static String loginByPost( String username, String password ){
        String path = "http://192.168.1.104:9001/web/LoginServlet";
        try{
            URL url = new URL( path );
            HttpURLConnection conn = (HttpURLConnection)url.openConnection( );
            conn.setRequestMethod( "POST" );
            conn.setReadTimeout( 5000 );
            String params = "username=" + URLEncoder.encode( username, "utf-8" ) + "&password=" +
                URLEncoder.encode( password, "utf-8" );
            conn.setRequestProperty( "content-type", "application/x-www-form-urlencoded" );
            /* 获取服务器提供的数据 */
            conn.setRequestProperty( "content-length", params.length( ) + "" );
            /* 是否运行向外面写数据*/
            conn.setDoOutput( true );
            /* 获取输出流*/
            OutputStream outputStream = conn.getOutputStream( );
            /* 向服务器端写数据*/
            outputStream.write( params.getBytes( ) );
            int code = conn.getResponseCode( );
            if( code == 200 ){
                InputStream is = conn.getInputStream( );
                String result = StreamTool.readInputStream( is );
                is.close( );
                return result;
            }else{
                return null;
            }
        }catch( Exception e ){
            e.printStackTrace( );
        }
        return null;
    }

    /*
     * 采用HttpGet方式
	 */
    public static String loginByClientGet( String username, String password ){
        try{
            // 1.打开浏览器
            HttpClient client = new DefaultHttpClient( );
            // 2.输入网址
            String path = "http://192.168.1.104:9001/web/LoginServlet?username="
                + username + "&password=" + password;
            HttpGet httpGet = new HttpGet( path );
            // 3.敲回车
            HttpResponse response = client.execute( httpGet );
            /* 获取显示状态行*/
            int code = response.getStatusLine( ).getStatusCode( );
            if( code == 200 ){
                /* 返回服务器端输入的流，即传送数据的实体s*/
                InputStream is = response.getEntity( ).getContent( );
                String result = StreamTool.readInputStream( is );
                is.close( );
                return result;
            }else{
                return null;
            }
        }catch( Exception e ){
            System.err.println( "异常..." );
        }
        return null;
    }

    public static String loginByClientPost( String username, String password ){
        try{
            // 1.打开浏览器
            HttpClient client = new DefaultHttpClient( );
            // 2.输入网址
            String path = "http://192.168.1.104:9001/web/LoginServlet";
            HttpPost httpPost = new HttpPost( path );
            // 3.设置参数
            /* 因为提交给表单的数据都是键值对，即username（key）对应值为aaa（value）；
            *  所有创建一个键值对的集合*/
            ArrayList<NameValuePair> parameters = new ArrayList<NameValuePair>( );
            parameters.add( new BasicNameValuePair( "username", username ) );
            parameters.add( new BasicNameValuePair( "password", password ) );
            /*  设置表单的实体*/
            httpPost.setEntity( new UrlEncodedFormEntity( parameters, "utf-8" ) );
            // 4.敲回车
            HttpResponse response = client.execute( httpPost );
            int code = response.getStatusLine( ).getStatusCode( );
            if( code == 200 ){
                InputStream is = response.getEntity( ).getContent( );
                String result = StreamTool.readInputStream( is );
                is.close( );
                return result;
            }else{
                return null;
            }
        }catch( Exception e ){
            System.err.println("异常...");
        }
        return null;
    }
}
