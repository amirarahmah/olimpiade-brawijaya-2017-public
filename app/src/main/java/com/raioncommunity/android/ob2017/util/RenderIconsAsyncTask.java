package com.raioncommunity.android.ob2017.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;


import static com.raioncommunity.android.ob2017.Const.COLOR.MATERIAL_DEEP_PURPLE_500;

/**
 * Created by arifinfirdaus on 7/20/17.
 */

public class RenderIconsAsyncTask extends AsyncTask<Context, String, Context> {

    private Context mContext;


    public RenderIconsAsyncTask(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    protected void onPreExecute() {
        // super.onPreExecute();

        Toast.makeText(mContext, "onPreExecute", Toast.LENGTH_SHORT).show();
        DataList.renderIconWithColorOf(MATERIAL_DEEP_PURPLE_500, mContext);
    }

    @Override
    protected Context doInBackground(Context... params) {
        return params[0];
    }

    @Override
    protected void onPostExecute(Context context) {
        // super.onPostExecute(context);

        Toast.makeText(mContext, "onPostExecute", Toast.LENGTH_SHORT).show();


    }
}


