package name.findspace.gridviewexample;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
public class GridViewAdapter extends BaseAdapter {
    /**选中状态*/
    private boolean []list;
    private Context context;
    //用来导入布局
    private LayoutInflater inflater = null;
    public GridViewAdapter(boolean[] list, Context context) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
        initData();
    }
    //初始化选中的状态
    private void initData() {
        for (int i = 0; i < list.length; i++) {
           setIsSelected(i, list[i]);
        }
    }

    @Override
    public int getCount() {
        return list.length;
    }

    @Override
    public Object getItem(int position) {
        return list[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder holder=null;
        if(convertView==null){
            holder=new ViewHolder();
            convertView=inflater.inflate(R.layout.itemlistview, null);
            holder.tv = (TextView) convertView.findViewById(R.id.item_tv);
            holder.cb = (CheckBox) convertView.findViewById(R.id.item_cb);
            //为view设置标签
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }
        //设置list中textview的显示
        holder.tv.setText(position+"");
        //根据isselected来设置checkbox的选中状态
        holder.cb.setChecked(getIsSelected(position));
        //背景色调整
        if(getIsSelected(position)){
            convertView.setBackgroundColor(Color.RED);
        }else{
            convertView.setBackgroundColor(Color.WHITE);
        }

        return convertView;
    }
    public boolean getIsSelected(int position) {
        return list[position];
    }
    public boolean [] getSelectedList(){
        return list;
    }
    public void setIsSelected(int position, boolean value) {
        list[position] = value;
    }
    public static class ViewHolder {
        TextView tv;
        CheckBox cb;
    }
}
