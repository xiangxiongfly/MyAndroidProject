package com.example.tools.permissions.utils;

import android.Manifest;
import android.app.AlertDialog;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import java.util.ArrayList;

/**
 * 权限请求类
 */
public class PermissionHelper {

    @Nullable
    private final FragmentActivity mActivity;
    @NonNull
    private final ArrayList<String> mPermissions = new ArrayList<>();

    private PermissionHelper(FragmentActivity activity) {
        mActivity = activity;
    }

    /**
     * 设置请求对象
     */
    public static PermissionHelper with(FragmentActivity activity) {
        return new PermissionHelper(activity);
    }

    public static PermissionHelper with(Fragment fragment) {
        FragmentActivity activity = fragment.getActivity();
        return new PermissionHelper(activity);
    }

    public PermissionHelper permissions(@NonNull ArrayList<String> permissionList) {
        mPermissions.addAll(permissionList);
        return this;
    }

    /**
     * 请求权限
     */
    public void request(@NonNull OnPermissionCallback onPermissionCallback) {
        request(onPermissionCallback, null);
    }

    public void request(@NonNull OnPermissionCallback onPermissionCallback, @Nullable OnSettingsCallback onSettingsCallback) {
        if (mActivity == null || mActivity.isFinishing() || mActivity.isDestroyed()) {
            return;
        }
        showPermissionInfoDialog(mActivity, mPermissions, onPermissionCallback, onSettingsCallback);
    }

    /**
     * 权限说明弹窗
     */
    public void showPermissionInfoDialog(@NonNull FragmentActivity activity, @NonNull ArrayList<String> allPermissions, @NonNull OnPermissionCallback onPermissionCallback) {
        showPermissionInfoDialog(activity, allPermissions, onPermissionCallback, null);
    }

    public void showPermissionInfoDialog(FragmentActivity activity, @NonNull ArrayList<String> allPermissions, @NonNull OnPermissionCallback onPermissionCallback, @Nullable OnSettingsCallback onSettingsCallback) {
        if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
            return;
        }
        ArrayList<String> permissionList = PermissionUtils.getDeniedPermissions(activity, allPermissions);
        String title = "权限说明";
        String message = "使用此功能需要先授予：" + listToString(getPermissionNames(permissionList));
        new AlertDialog.Builder(activity)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("授予", (dialog, which) -> {
                    dialog.dismiss();
                    PermissionsFragment.launch(activity, permissionList, this, onPermissionCallback, onSettingsCallback);
                })
                .setNegativeButton("取消", (dialog, which) -> {
                })
                .show();
    }

    /**
     * 获取权限名
     */
    public static ArrayList<String> getPermissionNames(ArrayList<String> permissions) {
        ArrayList<String> permissionNames = new ArrayList<>();
        for (String permission : permissions) {
            switch (permission) {
                case Manifest.permission.CAMERA:
                    permissionNames.add("相机权限");
                    break;
                case Manifest.permission.ACCESS_FINE_LOCATION:
                    permissionNames.add("定位权限");
                    break;
                case Manifest.permission.CALL_PHONE:
                    permissionNames.add("拨号权限");
                    break;
                default:
                    break;
            }
        }
        return permissionNames;
    }

    /**
     * ArrayList转String
     */
    public static String listToString(ArrayList<String> permissionNames) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0, size = permissionNames.size(); i < size; i++) {
            if (i == size - 1) {
                builder.append(permissionNames.get(i));
            } else {
                builder.append(permissionNames.get(i)).append("、");
            }
        }
        return builder.toString();
    }

    /**
     * 权限提醒弹窗
     */
    public void showPermissionSettingDialog(FragmentActivity activity, ArrayList<String> permissionList, @Nullable OnSettingsCallback onSettingsCallback) {
        if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
            return;
        }
        String title = "权限提醒";
        String message = "获取权限失败，请手动授予：" + listToString(getPermissionNames(permissionList));
        new AlertDialog.Builder(activity)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("前往授权", (dialog, which) -> {
                    dialog.dismiss();
                    SettingsFragment.launch(activity, onSettingsCallback);
                })
                .show();
    }
}
