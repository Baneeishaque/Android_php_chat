package com.example.android_php_chat;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.example.android_php_ajax_chat.R;

import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Signup extends ActionBarActivity {
	
	
	public static String rslt="";
	ProgressDialog dialog = null;
	HttpPost httppost;
	StringBuffer buffer;
	HttpResponse response;
	HttpClient httpclient;
	List<NameValuePair> nameValuePairs;
	private String password;
	private String password2;
	private String name;
	private String userid;
	private String email;
	private String security;
	private String answer;
	private EditText txt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);		
	}

	

	
	
	public void signup(View v)
	{
		EditText txtn=(EditText)findViewById(R.id.password);
		password = txtn.getText().toString();
		
		txtn=(EditText)findViewById(R.id.password2);
		password2 = txtn.getText().toString();
		
		if(password.equals(password2)){
			txt=(EditText)findViewById(R.id.name);
			name=txt.getText().toString();
			
			txt=(EditText)findViewById(R.id.username);
			userid=txt.getText().toString();
			
			txt=(EditText)findViewById(R.id.email);
			email=txt.getText().toString();
			
			txt=(EditText)findViewById(R.id.security);
			security=txt.getText().toString();
			
			txt=(EditText)findViewById(R.id.answer);
			answer=txt.getText().toString();
		
			/*try 
			{
					rslt="START";	
					CallerSignup c=new CallerSignup();
					c.userid=username1;
					c.name=user1;
					c.password=passch1;
					c.email=email;
					c.security=security;
					c.answer=answer;
					c.join();
					c.start();
					while(rslt=="START")
	 		        {
	 		        	try
	 		        	{
	 		        		Thread.sleep(10);
	 		        		
	 		        	}
	 		        	catch(Exception ex)
	 		        	{
	 		        		Toast.makeText(getApplicationContext(),
		 								"Thread sleep Error", Toast.LENGTH_LONG)
		 								.show();

	 		        	}
	 		        }
					
	 		       	if(rslt.equals("ok"))
	 		       	{
	 		       		
	 		       	Intent target2 = new Intent(this, Uploadnews.class);
	 				startActivity(target2);
	 	 			
	 		        }
	 		       else
	 		       {
			       				
			       						Toast.makeText(getApplicationContext(),
								"Communication Error resul in", Toast.LENGTH_LONG)
								.show();
			       					
			       						Toast.makeText(getApplicationContext(),
			       								rslt, Toast.LENGTH_LONG)
			       								.show();
			       			       					
			       	}
			       		
			}catch(Exception ex)
	 		{
				Toast.makeText(getApplicationContext(),
							"Thread start Error", Toast.LENGTH_LONG)
							.show();

						Toast.makeText(getApplicationContext(),
								ex.getMessage(), Toast.LENGTH_LONG)
								.show();
	 		        	
	 		}*/
			
			dialog = ProgressDialog.show(Signup.this, "", 
                    "Inserting user...", true);
			 new Thread(new Runnable() {
				    public void run() {
				    	sign();					      
				    }
				  }).start();	
			
		}
		else
		{
			

			Toast.makeText(getApplicationContext(),
					"Password Mismatch", Toast.LENGTH_LONG)
					.show();
		
   	
		}
		
	}
	
	void sign(){
		try{			
			 
			httpclient=new DefaultHttpClient();
			httppost= new HttpPost("http://"+Globaldata.ip+"/News/signup.php"); // make sure the url is correct.
			//add your data
			nameValuePairs = new ArrayList<NameValuePair>(7);
			// Always use the same variable name for posting i.e the android side variable name and php side variable name should be similar, 
			nameValuePairs.add(new BasicNameValuePair("userid",userid));  // $Edittext_value = $_POST['Edittext_value'];
			nameValuePairs.add(new BasicNameValuePair("password",password));
			nameValuePairs.add(new BasicNameValuePair("type","u"));  // $Edittext_value = $_POST['Edittext_value'];
			nameValuePairs.add(new BasicNameValuePair("email",email)); 
			nameValuePairs.add(new BasicNameValuePair("name",name));  // $Edittext_value = $_POST['Edittext_value'];
			nameValuePairs.add(new BasicNameValuePair("security",security)); 
			nameValuePairs.add(new BasicNameValuePair("answer",answer));  // $Edittext_value = $_POST['Edittext_value'];
			
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			//Execute HTTP Post Request
			// edited by James from coderzheaven.. from here....
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			final String response = httpclient.execute(httppost, responseHandler);
			System.out.println("Response : " + response); 
			
			runOnUiThread(new Runnable() {
			    public void run() {
			    	Toast.makeText(Signup.this,"Response from PHP : " + response,Toast.LENGTH_LONG).show();
					dialog.dismiss();
			    }
			});
			
			if(response.equalsIgnoreCase("Inserted")){
				runOnUiThread(new Runnable() {
				    public void run() {
				    	Toast.makeText(Signup.this,"Success", Toast.LENGTH_SHORT).show();
				    }
				});
				
				
			}else{
				showAlert();				
			}
			
		}catch(Exception e){
			dialog.dismiss();
			System.out.println("Exception : " + e.getMessage());
		}
	}
	public void showAlert(){
		Signup.this.runOnUiThread(new Runnable() {
		    public void run() {
		    	AlertDialog.Builder builder = new AlertDialog.Builder(Signup.this);
		    	builder.setTitle("Signup Error");
		    	builder.setMessage("Try again...")  
		    	       .setCancelable(false)
		    	       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
		    	           public void onClick(DialogInterface dialog, int id) {
		    	           }
		    	       });		    	       
		    	AlertDialog alert = builder.create();
		    	alert.show();		    	
		    }
		});
	}
	
}
