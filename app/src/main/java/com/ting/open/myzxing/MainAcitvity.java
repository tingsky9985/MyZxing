package com.ting.open.myzxing;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

/**
 * Created by lt on 2016/8/8.
 */
public class MainAcitvity extends Activity {

    private TextView mTvResult;
    private EditText mEdInput;
    private ImageView mIvImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化组件
        init();
    }

    /**
     * 初始化组件
     */
    private void init() {
        mTvResult = (TextView) findViewById(R.id.tv_result);
        mEdInput = (EditText) findViewById(R.id.et_input);
        mIvImage = (ImageView) findViewById(R.id.iv_image);
    }

    /**
     * 实现点击事件，发送扫描二维码请求
     * @param view
     */
    public void scan(View view){
        startActivityForResult(new Intent(MainAcitvity.this, CaptureActivity.class),0);
    }

    /**
     *根据输入的字符串，生成二维码
     * @param view
     */
    public void make(View view){
        String strInput = mEdInput.getText().toString();
        //判断输入内容，若是空,则返回
        if(TextUtils.isEmpty(strInput)){
            Toast.makeText(MainAcitvity.this,"请输入内容",Toast.LENGTH_SHORT).show();
            return;
        }

        //通过传入内容，生成二维码
        Bitmap bitmap = EncodingUtils.createQRCode(strInput,600,600,
                BitmapFactory.decodeResource(getResources(),R.drawable.lie));
        mIvImage.setImageBitmap(bitmap);
    }

    /**
     * 获取返回值
     *
     * @param requestCode
     * @param resultCode RESULT_OK，得到二维码信息返回值
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(RESULT_OK == resultCode){
            Bundle bundle = data.getExtras();
            String result = bundle.getString("result");
            mTvResult.setText(result);
        }
    }
}
