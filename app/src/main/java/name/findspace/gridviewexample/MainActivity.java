package name.findspace.gridviewexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button work;
    private boolean[] cookie;
    /**numAll: 所有要展示的个数*/
    private int numAll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        numAll = 10;
        cookie = new boolean[numAll];
        //初始所有数据为未选中状态
        for(int i =0; i<numAll; i++)
            cookie[i] = false;
        work = (Button)findViewById(R.id.work);
        work.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent send = new Intent(MainActivity.this,ManageVoteActivity.class);
                send.putExtra("numAll", numAll);
                send.putExtra("cookie", cookie);
                MainActivity.this.startActivityForResult(send, 1);
            }
            });
    }
    /**接收来自view的结果，并更新cookie */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==1)
            cookie=data.getBooleanArrayExtra("checkedResult");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
