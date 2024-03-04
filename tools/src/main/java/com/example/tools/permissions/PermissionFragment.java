package com.example.tools.permissions;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tools.R;
import com.example.tools.permissions.utils.OnPermissionCallback;
import com.example.tools.permissions.utils.OnSettingsCallback;
import com.example.tools.permissions.utils.PermissionHelper;
import com.example.tools.permissions.utils.PermissionUtils;
import com.xiangxiongfly.common.base.BaseFragment;

import java.util.ArrayList;

public class PermissionFragment extends BaseFragment {

    public PermissionFragment() {
    }

    public static PermissionFragment newInstance() {
        PermissionFragment fragment = new PermissionFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_permission, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btn_permissions = view.findViewById(R.id.btn_permissions);
        btn_permissions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request();
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