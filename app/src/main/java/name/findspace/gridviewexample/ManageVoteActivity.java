package name.findspace.gridviewexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;

import name.findspace.gridviewexample.GridViewAdapter.ViewHolder;

public class ManageVoteActivity extends Activity{
    private GridView listview;
    private Button reset;
    private Button confirm;
    /*选中了多少个*/
    private int checkNum;
    private int num1;
    private GridViewAdapter mAdapter;
    /**存储选中状态*/
    private boolean [] cookie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        //实例化各个控件，初始化一些数据
        listview=(GridView)findViewById(R.id.list);
        reset=(Button)findViewById(R.id.reset);
        confirm=(Button)findViewById(R.id.confirm);
        checkNum=0;
        //接收来自主界面的数据
        getInput();
        //实例化自定义的adapter
        mAdapter=new GridViewAdapter(cookie, this);
        listview.setAdapter(mAdapter);
        //重置按钮
        reset.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //这里添加个确认提示
                //将myadapter中的map值全部设为false
                for(int i = 0; i < num1; i ++){
                    mAdapter.setIsSelected(i, false);
                }
                checkNum=0;
                //刷新listview和textview的显示
                dataChanged();
            }
        });
        //确认按钮
        confirm.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                voteCheck();
            }
        });
        //绑定listview监听器
        listview.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // 取得ViewHolder对象，这样就省去了通过层层的findViewById去实例化我们需要的cb实例的步骤
                ViewHolder holder=(ViewHolder)view.getTag();
                // 改变CheckBox的状态
                holder.cb.toggle();
                // 将CheckBox的选中状况记录下来
                mAdapter.setIsSelected(position, holder.cb.isChecked());
                // 调整选定条目
                if (holder.cb.isChecked()){
                    checkNum++;
                }else{
                    checkNum--;
                }
                mAdapter.notifyDataSetChanged();
//				choiceState();

            }

        });

    }
    /**@author find<br>
     * 获取来自主界面的信息，分有无cookie两种情况<br>
     * 同时初始化一些数据
     * */
    private void getInput(){
        Bundle receiveBundle=this.getIntent().getExtras();
        num1 = receiveBundle.getInt("numAll");
        cookie = new boolean[num1];
        cookie = receiveBundle.getBooleanArray("cookie");
    }

    /**发送给主activity数据，此处可添加检查数据状态*/
    private void voteCheck(){
        Intent send=new Intent();
        cookie = mAdapter.getSelectedList();
        send.putExtra("checkedResult", cookie);
        ManageVoteActivity.this.setResult(1,send);
        ManageVoteActivity.this.finish();
    }
    // 刷新listview和TextView的显示
    private void dataChanged(){
        // 通知listView刷新
        mAdapter.notifyDataSetChanged();
    }
    /**返回按钮*/
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            voteCheck();
        }
        return  super.onKeyDown(keyCode, event);

    }
}
