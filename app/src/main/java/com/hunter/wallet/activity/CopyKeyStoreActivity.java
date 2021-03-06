package com.hunter.wallet.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hunter.wallet.R;
import com.hunter.wallet.utils.RemindUtils;

/**
 * Created by DT0814 on 2018/8/24.
 */

public class CopyKeyStoreActivity extends SecurityActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.copy_keystore_layout);
        String key = getIntent().getStringExtra("key");
        if (null == key || key.trim().equals("")) {
            Log.e("CopyKeyStoreActivity", "nokey");
        } else {
           /* AlertDialog.Builder builder = new AlertDialog.Builder(CopyKeyStoreActivity.this);
            View dangerView = getLayoutInflater().inflate(R.layout.danger_msg_dialog, null);
            builder.setView(dangerView);
            AlertDialog show = builder.show();
            show.setCancelable(false);
            ((TextView) dangerView.findViewById(R.id.dangerMsgText)).setText(R.string.keyStoreDangerMsg);
            dangerView.findViewById(R.id.dangerMsgBut).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    show.dismiss();
                }
            });*/

            TextView prvText = findViewById(R.id.keyStoreText);
            prvText.setMovementMethod(ScrollingMovementMethod.getInstance());
            prvText.setText(key);
            findViewById(R.id.copyKeyStorePerBut).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CopyKeyStoreActivity.this.finish();
                }
            });
            Button copyBut = findViewById(R.id.copyBut);
            copyBut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ClipData mClipData;
                    ClipboardManager clipManager;
                    clipManager = (ClipboardManager) CopyKeyStoreActivity.this.getSystemService(Context.CLIPBOARD_SERVICE);
                    mClipData = ClipData.newPlainText("Label", key);
                    clipManager.setPrimaryClip(mClipData);
                    RemindUtils.toastShort(CopyKeyStoreActivity.this, "复制成功");
                    copyBut.setText("已复制");
                }
            });
        }

    }
}
