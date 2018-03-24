package com.itlty.webapp;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.itlty.webapp.service.LoginService;

public class MainActivity extends AppCompatActivity{
    private EditText et_userName;
    private EditText et_password;

    @Override
    protected void onCreate( Bundle savedInstanceState ){
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        et_userName = findViewById( R.id.et_username );
        et_password = findViewById( R.id.et_password );
    }

    public void get( View view ){
        final String username = et_userName.getText( ).toString( ).trim( );
        final String password = et_password.getText( ).toString( ).trim( );
        new Thread( ){
            @Override
            public void run(){
                final String result = LoginService.loginByGet( username, password );
                if( result != null ){
                    runOnUiThread( new Runnable( ){
                        @Override
                        public void run(){
                            Toast.makeText( MainActivity.this, result, Toast.LENGTH_SHORT ).show( );
                        }
                    } );
                }else{
                    runOnUiThread( new Runnable( ){
                        @Override
                        public void run(){
                            Toast.makeText( MainActivity.this, "请求失败...", Toast.LENGTH_SHORT )
                                .show( );
                        }
                    } );
                }
            }
        }.start( );
    }

    public void post( View view ){
        final String username = this.et_userName.getText( ).toString( ).trim( );
        final String password = this.et_password.getText( ).toString( ).trim( );
        new Thread( ){
            public void run(){
                final String result = LoginService.loginByPost( username,
                    password );
                if( result != null ){
                    runOnUiThread( new Runnable( ){
                        @Override
                        public void run(){
                            Toast.makeText( MainActivity.this, result, Toast.LENGTH_SHORT ).show( );
                        }
                    } );
                }else{
                    runOnUiThread( new Runnable( ){
                        @Override
                        public void run(){
                            Toast.makeText( MainActivity.this, "请求失败...", Toast.LENGTH_SHORT )
                                .show( );
                        }
                    } );
                }
            }
        }.start( );
    }

    public void clientGet( View view ){
        final String username = this.et_userName.getText( ).toString( ).trim( );
        final String password = this.et_password.getText( ).toString( ).trim( );
        new Thread( ){
            public void run(){
                final String result = LoginService.loginByClientGet( username,
                    password );
                if( result != null ){
                    runOnUiThread( new Runnable( ){
                        @Override
                        public void run(){
                            Toast.makeText( MainActivity.this, result, Toast.LENGTH_SHORT ).show( );
                        }
                    } );
                }else{
                    runOnUiThread( new Runnable( ){
                        @Override
                        public void run(){
                            Toast.makeText( MainActivity.this, "请求失败...", Toast.LENGTH_SHORT )
                                .show( );
                        }
                    } );
                }
            }
        }.start( );
    }

    public void clientPost( View view ){
        final String userName = this.et_userName.getText( ).toString( ).trim( );
        final String password = this.et_password.getText( ).toString( ).trim( );
        new Thread( ){
            public void run(){
                final String result = LoginService.loginByClientPost( userName, password );
                if( result != null ){
                    runOnUiThread( new Runnable( ){
                        @Override
                        public void run(){
                            Toast.makeText( MainActivity.this, result, Toast.LENGTH_SHORT ).show( );
                        }
                    } );
                }else{
                    runOnUiThread( new Runnable( ){
                        @Override
                        public void run(){
                            Toast.makeText( MainActivity.this, "请求失败...", Toast.LENGTH_SHORT )
                                .show( );
                        }
                    } );
                }
            }
        }.start( );
    }
}
