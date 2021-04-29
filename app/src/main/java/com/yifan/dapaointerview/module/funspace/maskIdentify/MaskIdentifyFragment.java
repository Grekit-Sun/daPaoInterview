package com.yifan.dapaointerview.module.funspace.maskIdentify;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Environment;
import android.view.SurfaceView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;

import com.yifan.dapaointerview.R;
import com.yifan.dapaointerview.base.BaseFragment;
import com.yifan.dapaointerview.databinding.FragmentMaskidentifyBinding;
import com.yifan.dapaointerview.ui.CameraSurfaceView;
import com.yifan.dapaointerview.util.CameraUtils;
import com.yifan.dapaointerview.util.ImageUtils;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description:
 * @Author: ZhengXiang Sun
 * @Data: 2021-03-12
 */
public class MaskIdentifyFragment extends BaseFragment<FragmentMaskidentifyBinding, MaskIdentifyViewModel> {

    @BindView(R.id.container_sfv_masked)
    LinearLayout mContainerSfvMasked;

    private CameraSurfaceView mCameraSurfaceView;
    private int mOrientation;
    private boolean mCameraRequested;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_maskidentify;
    }

    @Override
    protected void initViewModel() {
        mViewModel = new ViewModelProvider(this).get(MaskIdentifyViewModel.class);
    }

    @Override
    protected void bindViewModel() {

    }

    @Override
    protected void init() {
        ButterKnife.bind(this, mDataBinding.getRoot());
        checkCameraHardware(getActivity());
        mCameraSurfaceView = new CameraSurfaceView(getActivity());
        mContainerSfvMasked.addView(mCameraSurfaceView);
        CameraUtils.calculateCameraPreviewOrientation(getActivity());
    }

    private boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            Toast.makeText(getActivity(), "搜索到摄像头硬件", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(getActivity(), "不具备摄像头硬件", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mCameraSurfaceView != null) {
            CameraUtils.switchCamera(1, mCameraSurfaceView.getHolder());
            // 切换相机后需要重新计算旋转角度
            mOrientation = CameraUtils.calculateCameraPreviewOrientation(getActivity());
        }
        if (mCameraRequested) {
            CameraUtils.startPreview();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        CameraUtils.stopPreview();
    }

    /**
     * 拍照
     */
    private void takePicture() {
        CameraUtils.takePicture(new Camera.ShutterCallback() {
            @Override
            public void onShutter() {

            }
        }, null, new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                CameraUtils.startPreview();
                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                if (bitmap != null) {
                    bitmap = ImageUtils.getRotatedBitmap(bitmap, mOrientation);
                    String path = Environment.getExternalStorageDirectory() + "/DCIM/Camera/"
                            + System.currentTimeMillis() + ".jpg";
                    try {
                        FileOutputStream fout = new FileOutputStream(path);
                        BufferedOutputStream bos = new BufferedOutputStream(fout);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                        bos.flush();
                        bos.close();
                        fout.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                CameraUtils.startPreview();
            }
        });
    }

    /**
     * 切换相机
     */
    private void switchCamera() {
        if (mCameraSurfaceView != null) {
            CameraUtils.switchCamera(1 - CameraUtils.getCameraID(), mCameraSurfaceView.getHolder());
            // 切换相机后需要重新计算旋转角度
            mOrientation = CameraUtils.calculateCameraPreviewOrientation(getActivity());
        }
    }
}
