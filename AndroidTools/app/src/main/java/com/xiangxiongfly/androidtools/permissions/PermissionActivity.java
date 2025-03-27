package com.xiangxiongfly.androidtools.permissions;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.xiangxiongfly.androidtools.R;
import com.xiangxiongfly.common.base.BaseActivity;
import com.xiangxiongfly.common.utils.permission.OnPermissionCallback;
import com.xiangxiongfly.common.utils.permission.OnSettingsCallback;
import com.xiangxiongfly.common.utils.permission.PermissionHelper;
import com.xiangxiongfly.common.utils.permission.PermissionUtils;

import java.util.ArrayList;

public class PermissionActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        initViews();
    }

    private void initViews() {
        Button btn_permissions = findViewById(R.id.btn_permissions);
        Button btn_fragment_activity = findViewById(R.id.btn_fragment_activity);

        btn_permissions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request();
            }
        });

        btn_fragment_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, TwoActivity.class));
            }
        });
    }

    private void request() {
        ArrayList<String> permissionList = new ArrayList<>();
        permissionList.add(Manifest.permission.CAMERA);
        permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        permissionList.add(Manifest.permission.CALL_PHONE);

        if (PermissionUtils.isGrantedPermissions(context, permissionList)) {
            Toast.makeText(context, "权限都通过了", Toast.LENGTH_SHORT).show();
        } else {
            PermissionHelper.with(this)
                    .permissions(permissionList)
                    .request(new OnPermissionCallback() {
                        @Override
                        public void onGranted(boolean allGranted, @NonNull ArrayList<String> permissions) {
                            if (allGranted) {
                                Toast.makeText(context, "权限全部通过", Toast.LENGTH_SHORT).show();
                            }
                            Log.e("TAG", "onGranted: " + allGranted + " - " + permissions);
                        }

                        @Override
                        public void onDenied(boolean deniedNever, @NonNull ArrayList<String> permissions) {
                            if (deniedNever) {
                                Toast.makeText(context, "权限拒绝且不再询问", Toast.LENGTH_SHORT).show();
                            }
                            Log.e("TAG", "onDenied: " + deniedNever + " - " + permissions);
                        }
                    }, new OnSettingsCallback() {
                        @Override
                        public void onResult() {
                            Log.e("TAG", "onResult");
                        }
                    });
        }
    }
}