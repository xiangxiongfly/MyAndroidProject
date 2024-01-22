package com.example.others.permissions.utils;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

/**
 * 跳转设置页Fragment
 */
public class SettingsFragment extends Fragment {

    @Nullable
    private OnSettingsCallback mCallBack;

    public static void launch(@NonNull FragmentActivity activity, @Nullable OnSettingsCallback callback) {
        SettingsFragment fragment = new SettingsFragment();
        fragment.setRetainInstance(true);
        if (callback != null)
            fragment.setCallback(callback);
        fragment.attachActivity(activity);
    }

    /**
     * 设置回调
     */
    public void setCallback(@NonNull OnSettingsCallback callback) {
        mCallBack = callback;
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

    @Override
    public void onResume() {
        super.onResume();
        PermissionUtils.gotoSetting(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PermissionUtils.REQUEST_SETTING_CODE) {
            OnSettingsCallback callback = mCallBack;
            mCallBack = null;
            if (callback != null) {
                callback.onResult();
            }
        }

        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        detachActivity(activity);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCallBack = null;
    }
}
