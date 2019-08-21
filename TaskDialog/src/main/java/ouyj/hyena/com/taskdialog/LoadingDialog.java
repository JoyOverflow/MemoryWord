package ouyj.hyena.com.taskdialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class LoadingDialog extends AsyncTask<Void,Integer,Boolean> {

    AlertDialog alertDialog;
    Context mContext;
    ProgressBar progressBar;
    TextView textView;
    public LoadingDialog(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        AlertDialog.Builder builder=new AlertDialog.Builder(mContext);
        View view =LayoutInflater.from(mContext).inflate(R.layout.progress_bar,null);
        progressBar=view.findViewById(R.id.progressBar);
        textView=view.findViewById(R.id.result);
        builder.setView(view);
        alertDialog = builder.create();
        alertDialog.show();
    }



    @Override
    protected Boolean doInBackground(Void... voids) {
        try {
            for(int i=0;i<=100;i++){
                publishProgress(i);
                Thread.sleep(50);
            }

            alertDialog.dismiss();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        textView.setText(values[0]+"%");
        progressBar.setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        Toast.makeText(mContext, "task completed", Toast.LENGTH_SHORT).show();
    }
}
