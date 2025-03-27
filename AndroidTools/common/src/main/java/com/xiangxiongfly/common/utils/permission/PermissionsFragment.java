package com.xiangxiongfly.common.utils.permission;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Random;

/**
 * 权限请求Fragment
 */
public class PermissionsFragment extends Fragment {
    public static final String REQUEST_CODE = "requestCode"; //请求码
    public static final String PARAM_PERMISSIONS = "permissionList";

    /**
     * 权限结果回调
     */
    @Nullable
    private OnPermissionCallback mOnPermissionCallback;
    /**
     * 设置结果回调
     */
    @Nullable
    private OnSettingsCallback mOnSettingsCallback;
    /**
     * 获取当前请求的屏幕方向
     */
    private int mScreenOrientation;
    private PermissionHelper mHelper;
    private ArrayList<String> allPermissionList;
    private int mRequestCode;
    private static final ArrayList<Integer> requestCodeList = new ArrayList<>();

    public static void launch(@NonNull FragmentActivity activity, @NonNull ArrayList<String> permissionList, @NonNull PermissionHelper helper,
                              @NonNull OnPermissionCallback onPermissionCallback, @Nullable OnSettingsCallback onSettingsCallback) {
        PermissionsFragment fragment = new PermissionsFragment();
        Bundle bundle = new Bundle();
        int requestCode = generateCode();
        bundle.putInt(REQUEST_CODE, requestCode);
        bundle.putStringArrayList(PARAM_PERMISSIONS, permissionList);
        fragment.setArguments(bundle);
        fragment.setRetainInstance(true);
        fragment.setCallback(onPermissionCallback, onSettingsCallback);
        fragment.setHelper(helper);
        fragment.attachActivity(activity);
    }

    private void setHelper(PermissionHelper helper) {
        mHelper = helper;
    }

    /**
     * 随机生成requestCode
     */
    private static int generateCode() {
        int requestCode = new Random().nextInt(65536);
        while (requestCodeList.contains(requestCode)) {
            requestCode = new Random().nextInt(65536);
        }
        requestCodeList.add(requestCode);
        return requestCode;
    }

    /**
     * 绑定Activity
     */
    private void attachActivity(@NonNull FragmentActivity activity) {
        activity.getSupportFragmentManager().beginTransaction().add(this, this.toString()).commitAllowingStateLoss();
    }

    /**
     * 解绑Activity
     */
    private void detachActivity(@NonNull FragmentActivity activity) {
        activity.getSupportFragmentManager().beginTransaction().remove(this).commitAllowingStateLoss();
    }

    /**
     * 设置权限监听回调监听
     */
    public void setCallback(@NonNull OnPermissionCallback onPermissionCallback, @Nullable OnSettingsCallback onSettingsCallback) {
        mOnPermissionCallback = onPermissionCallback;
        mOnSettingsCallback = onSettingsCallback;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }

        Bundle bundle = getArguments();
        if (bundle != null) {
            ArrayList<String> permissionList = bundle.getStringArrayList(PARAM_PERMISSIONS);
            if (permissionList == null || permissionList.isEmpty()) {
                return;
            }
            mRequestCode = bundle.getInt(REQUEST_CODE);
            allPermissionList = permissionList;
        }

        // 获取请求的屏幕方向
        mScreenOrientation = activity.getRequestedOrientation();
        // 如果未指定屏幕方向，就锁定当前屏幕方向
        if (mScreenOrientation == ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED) {
            PermissionUtils.lockScreenOrientation(activity);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        // 如果当前屏幕方向是未指定，同时当前请求的屏幕方向是横屏或竖屏时，则屏幕方向切换为未指定方向模式
        if (mScreenOrientation == ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED &&
                activity.getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED) {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        requestPermissions(allPermissionList.toArray(new String[allPermissionList.size() - 1]), mRequestCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (permissions.length == 0 || grantResults.length == 0) {
            return;
        }
        if (requestCode == mRequestCode) {
            FragmentActivity activity = getActivity();
            if (activity == null) {
                return;
            }
            ArrayList<String> grantedList = new ArrayList<>(); //已授予权限
            ArrayList<String> deniedList = new ArrayList<>(); //已拒绝权限
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    deniedList.add(permissions[i]);
                } else {
                    grantedList.add(permissions[i]);
                }
            }
            requestCodeList.remove((Integer) mRequestCode);
            OnPermissionCallback onPermissionCallback = mOnPermissionCallback;
            mOnPermissionCallback = null;
            OnSettingsCallback onSettingsCallback = mOnSettingsCallback;
            mOnSettingsCallback = null;
            PermissionHelper helper = mHelper;
            mHelper = null;
            detachActivity(activity);

            boolean allGranted = grantedList.size() == permissions.length;
            if (allGranted) {
                onPermissionCallback.onGranted(true, grantedList);
                return;
            }

            boolean deniedNever = PermissionUtils.isDeniedNeverPermissions(activity, deniedList);
            onPermissionCallback.onDenied(deniedNever, deniedList);

            if (grantedList.size() > 0) {
                onPermissionCallback.onGranted(false, grantedList);
            }

            if (deniedNever) {
                helper.showPermissionSettingDialog(activity, deniedList, onSettingsCallback);
            } else if (deniedList.size() > 0) {
                helper.showPermissionInfoDialog(activity, deniedList, onPermissionCallback, onSettingsCallback);
            }
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mOnPermissionCallback = null;
    }
}
