package ouyj.hyena.com.taskdialog;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    Button codeButonu;
    Button button;
    public int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //查找按钮引用并设置事件
        codeButonu= findViewById(R.id.btnID);
        codeButonu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new BackgroundTask().execute();
            }
        });


        button=findViewById(R.id.btnShowProgress);
        button.setOnClickListener(v->{
            new LoadingDialog(MainActivity.this).execute();
        });
    }
    public class BackgroundTask extends AsyncTask<Void, Integer, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //显示下载进度对话框
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMax(100);
            progressDialog.setProgress(0);
            progressDialog.setMessage("Yükleniyor");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.show();
        }
        @Override
        protected Void doInBackground(Void... params) {
            for (i = 0; i < 101; i = i + 10) {
                try {
                    publishProgress(i);
                    Thread.sleep(1000);
                }
                catch (InterruptedException e) { }
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            //关闭下载进度对话框
            progressDialog.dismiss();
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Integer currentProgress = values[0];
            progressDialog.setProgress(currentProgress);
        }
        @Override
        protected void onCancelled(Void result) {
            super.onCancelled(result);
            progressDialog.dismiss();
        }
    }



}
