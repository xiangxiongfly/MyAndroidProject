
package com.example.tools.permissions;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.base.BaseActivity;
import com.example.tools.R;
import com.example.tools.permissions.utils.OnPermissionCallback;
import com.example.tools.permissions.utils.OnSettingsCallback;
import com.example.tools.permissions.utils.PermissionHelper;
import com.example.tools.permissions.utils.PermissionUtils;

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
                startActivity(new Intent(mContext, TwoActivity.class));
            }
        });
    }

    private void request() {
        ArrayList<String> permissionList = new ArrayList<>();
        permissionList.add(Manifest.permission.CAMERA);
        permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        permissionList.add(Manifest.permission.CALL_PHONE);

        if (PermissionUtils.isGrantedPermissions(mContext, permissionList)) {
            Toast.makeText(mContext, "权限都通过了", Toast.LENGTH_SHORT).show();
        } else {
            PermissionHelper.with(this)
                    .permissions(permissionList)
                    .request(new OnPermissionCallback() {
                        @Override
                        public void onGranted(boolean allGranted, @NonNull ArrayList<String> permissions) {
                            if (allGranted) {
                                Toast.makeText(mContext, "权限全部通过", Toast.LENGTH_SHORT).show();
                            }
                            Log.e("TAG", "onGranted: " + allGranted + " - " + permissions);
                        }

                        @Override
                        public void onDenied(boolean deniedNever, @NonNull ArrayList<String> permissions) {
                            if (deniedNever) {
                                Toast.makeText(mContext, "权限拒绝且不再询问", Toast.LENGTH_SHORT).show();
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