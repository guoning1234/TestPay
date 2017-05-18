package com.yaya.testpay;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.unionpay.UPPayAssistEx;

public class MainActivity extends Activity implements View.OnClickListener {
    Button mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        mBtn= (Button) findViewById(R.id.btn_pay);
        mBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // “00” – 银联正式环境
// “01” – 银联测试环境，该环境中不发生真实交易
        String serverMode = "01";
        UPPayAssistEx.startPay (this, null, null, "758846964053088136701", serverMode);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        {
            if (data == null) {
                return;
            }
            String msg = "";
            String str = data.getExtras().getString("pay_result");
            if (str.equalsIgnoreCase("success")) {

//                // 如果想对结果数据验签，可使用下面这段代码，
//// 但建议不验签，直接去商户后台查询交易结果
//// result_data结构见c）result_data参数说明
//                if(data.hasExtra("result_data")) {
//                    String sign =  data.getExtras().getString("result_data");
//// 此处的verify建议送去商户后台做验签
//// 如要放在手机端验，则代码必须支持更新证书
//                    if(verify(sign)) {
//                        //验签成功，显示支付结果
//                        showResultDialog(" 支付成功！ ");
//                    } else {
//// 验签失败
//                    }
//                }

// 结果result_data为成功时，去商户后台查询一下再展示成功
            } else if (str.equalsIgnoreCase("fail")) {
//                showResultDialog(" 支付失败！ ");
                Toast.makeText(this, "支付失败！！！", Toast.LENGTH_SHORT).show();
            } else if (str.equalsIgnoreCase("cancel")) {
//                showResultDialog(" 你已取消了本次订单的支付！ ");
                Toast.makeText(this, "你已取消了本次订单的支付!!!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
