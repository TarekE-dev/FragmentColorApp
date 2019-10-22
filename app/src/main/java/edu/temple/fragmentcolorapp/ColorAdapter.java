package edu.temple.fragmentcolorapp;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ColorAdapter extends BaseAdapter {

    private Context context;
    private String[] colors;
    private String[] translated;
    private boolean isTranslated;

    public ColorAdapter(Context context, String[] colors){
        this.context = context;
        this.colors = colors;
        translated = new String[colors.length];
        this.translate();
    }

    private void translate(){

        for(int i=1; i<colors.length; i++){
            String[] split = colors[i].split(",");
            if(split.length != 2){
                System.out.println(colors[i]);
                isTranslated = false;
                return;
            }
            translated[i] = split[1];
        }
        isTranslated = true;
    }


    @Override
    public int getCount() {
        return colors.length;
    }

    @Override
    public Object getItem(int i) {
        if(i < 0 || i >= colors.length){
            return null;
        }
        return colors[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView textView = new TextView(context);
        textView.setText(((String) getItem(i)).split(",")[0]);
        if(i != 0 ) {
            if(isTranslated)
                textView.setBackgroundColor(Color.parseColor((String) translated[i]));
            else
                textView.setBackgroundColor(Color.parseColor((String) getItem(i)));
        }
        textView.setTextSize(25);
        return textView;
    }
}