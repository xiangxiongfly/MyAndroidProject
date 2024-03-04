package com.example.tools.permissions.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.view.Surface;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

/**
 * 权限工具类
 */
public class PermissionUtils {
    public static final int REQUEST_SETTING_CODE = 0X123456;

    /**
     * 权限是否授予
     */
    public static boolean isGranted(@NonNull Context context, @NonNull String permission) {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * 权限是否全部授予
     */
    public static boolean isGrantedPermissions(@NonNull Context context, @NonNull ArrayList<String> permissionList) {
        for (String permission : permissionList) {
            if (!isGranted(context, permission)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 权限是否拒绝且不再询问
     */
    public static boolean isDeniedNever(@NonNull Activity activity, @NonNull String permission) {
        return !ActivityCompat.shouldShowRequestPermissionRationale(activity, permission);
    }

    /**
     * 权限是否包含拒绝且不再询问
     */
    public static boolean isDeniedNeverPermissions(@NonNull Activity activity, @NonNull ArrayList<String> permissions) {
        for (String permission : permissions) {
            if (isDeniedNever(activity, permission)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取未授权权限
     */
    public static ArrayList<String> getDeniedPermissions(@NonNull Context context, @NonNull ArrayList<String> permissionList) {
        ArrayList<String> deniedPermissionList = new ArrayList<>();
        for (String permission : permissionList) {
            if (!isGranted(context, permission)) {
                deniedPermissionList.add(permission);
            }
        }
        return deniedPermissionList;
    }

    /**
     * 获取包名Uri
     */
    static Uri getPackageNameUri(@NonNull Context context) {
        return Uri.parse("package:" + context.getPackageName());
    }

    /**
     * 跳转设置界面
     */
    public static void gotoSetting(@NonNull Activity activity) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(getPackageNameUri(activity));
        activity.startActivityForResult(intent, REQUEST_SETTING_CODE);
    }

    public static void gotoSetting(@NonNull Fragment fragment) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(getPackageNameUri(fragment.getContext()));
        fragment.startActivityForResult(intent, REQUEST_SETTING_CODE);
    }

    /**
     * 锁定屏幕方向
     */
    public static void lockScreenOrientation(@NonNull Activity activity) {
        try {
            // 获取实际屏幕方向
            int screenOrientation = activity.getResources().getConfiguration().orientation;
            switch (screenOrientation) {
                case Configuration.ORIENTATION_LANDSCAPE:
                    activity.setRequestedOrientation(PermissionUtils.isActivityReverse(activity) ?
                            ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE :
                            ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    break;
                case Configuration.ORIENTATION_PORTRAIT:
                    activity.setRequestedOrientation(PermissionUtils.isActivityReverse(activity) ?
                            ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT :
                            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    break;
                default:
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Activity是否反方向旋转
     */
    public static boolean isActivityReverse(@NonNull Activity activity) {
        int activityRotation;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            activityRotation = activity.getDisplay().getRotation();
        } else {
            activityRotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        }
        switch (activityRotation) {
            case Surface.ROTATION_180:
            case Surface.ROTATION_270:
                return true;
            case Surface.ROTATION_0:
            case Surface.ROTATION_90:
            default:
                return false;
        }
    }
}
