package com.lxj.execldemo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



import android.app.Activity;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	Button btn ;
	List<Order> orders = new ArrayList<Order>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		int length = Const.OrderInfo.orderOne.length;
		for(int i = 0;i < length;i++){
			Order order = new Order( Const.OrderInfo.orderOne[i][0],  Const.OrderInfo.orderOne[i][1],  Const.OrderInfo.orderOne[i][2],  Const.OrderInfo.orderOne[i][3]);
			orders.add(order);
		}
		btn = (Button)super.findViewById(R.id.btn);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
					ExcelUtil.writeExcel(MainActivity.this, orders, "excel_"+new Date().toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
