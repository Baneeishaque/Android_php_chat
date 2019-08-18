package com.example.android_php_chat;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.android_php_ajax_chat.R;

public class MainActivity extends ActionBarActivity {

	private DefaultHttpClient httpclient;
	private HttpPost httppost;
	private ProgressDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
	}
	
	public void refresh(View v)
	{
		/*dialog = ProgressDialog.show(MainActivity.this, "", 
                "Loading...", true);
		 new Thread(new Runnable() {
			    public void run() {
			    	load();					      
			    }
			  }).start();*/
		startActivity(new Intent(MainActivity.this, Signup.class));
	}

	protected void load() {
		// TODO Auto-generated method stub
		try{			
			Toast.makeText(MainActivity.this,"Try",Toast.LENGTH_LONG).show();
			httpclient=new DefaultHttpClient();
			httppost= new HttpPost("http://"+Globaldata.ip+"/phpchat/chat_recv_ajax.php");
			Toast.makeText(MainActivity.this,"Address : http://"+Globaldata.ip+"/phpchat/chat_recv_ajax.php",Toast.LENGTH_LONG).show();
			// make sure the url is correct.
			//add your data
			/*nameValuePairs = new ArrayList<NameValuePair>(7);
			// Always use the same variable name for posting i.e the android side variable name and php side variable name should be similar, 
			nameValuePairs.add(new BasicNameValuePair("userid",userid));  // $Edittext_value = $_POST['Edittext_value'];
			nameValuePairs.add(new BasicNameValuePair("password",password));
			nameValuePairs.add(new BasicNameValuePair("type","u"));  // $Edittext_value = $_POST['Edittext_value'];
			nameValuePairs.add(new BasicNameValuePair("email",email)); 
			nameValuePairs.add(new BasicNameValuePair("name",name));  // $Edittext_value = $_POST['Edittext_value'];
			nameValuePairs.add(new BasicNameValuePair("security",security)); 
			nameValuePairs.add(new BasicNameValuePair("answer",answer));  // $Edittext_value = $_POST['Edittext_value'];
			
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));*/
			//Execute HTTP Post Request
			// edited by James from coderzheaven.. from here....
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			final String response = httpclient.execute(httppost, responseHandler);
			//System.out.println("Response : " + response); 
			
			runOnUiThread(new Runnable() {
			    public void run() {
			    	Toast.makeText(MainActivity.this,"Response from PHP : " + response,Toast.LENGTH_LONG).show();
					dialog.dismiss();
			    }
			});
			
			/*if(response.equalsIgnoreCase("Inserted")){
				runOnUiThread(new Runnable() {
				    public void run() {
				    	Toast.makeText(Signup.this,"Success", Toast.LENGTH_SHORT).show();
				    }
				});
				
				startActivity(new Intent(Signup.this, Uploadnews.class));
			}else{
				showAlert();				
			}*/
			
		}catch(Exception e){
			Toast.makeText(getApplicationContext(),"Catch",Toast.LENGTH_LONG).show();
			dialog.dismiss();
			//System.out.println("Exception : " + e.getMessage());
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
