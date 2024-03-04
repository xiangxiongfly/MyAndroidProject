package com.example.tools.permissions.utils;

import androidx.annotation.NonNull;

import java.util.ArrayList;

/**
 * 权限结果回调
 */
public interface OnPermissionCallback {

    /**
     * 权限授予时回调
     *
     * @param allGranted  是否全部被授予
     * @param permissions 已授予权限
     */
    void onGranted(boolean allGranted, @NonNull ArrayList<String> permissions);

    /**
     * 权限拒绝时回调
     *
     * @param deniedNever 是否拒绝且不再询问
     * @param permissions 被拒绝权限
     */
    void onDenied(boolean deniedNever, @NonNull ArrayList<String> permissions);
}

