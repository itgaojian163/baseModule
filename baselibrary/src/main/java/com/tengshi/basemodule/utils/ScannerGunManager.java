package com.tengshi.basemodule.utils;

import android.view.KeyEvent;

/**
 * 拦截扫码枪和外接设备 的输入事件
 * <p>
 * 扫码枪和 外接键盘的处理是一样的
 */
public class ScannerGunManager {

    String codeStr = "";
    OnScanListener listener;
    private boolean mCaps;
    boolean isInterrupt = true;

    public ScannerGunManager(OnScanListener listener) {
        this.listener = listener;
    }

    /**
     * 处理输入事件
     *
     * @param event
     * @return true 表示消费掉，拦截不在传递， false 不管
     */
    public boolean dispatchKeyEvent(KeyEvent event) {
//        int keyCode = event.getKeyCode();
        //字母大小写判断

//
//        if (event.getAction() == KeyEvent.ACTION_DOWN) {
//            char aChar = getInputCode(event);
//            if (aChar != 0) {
//
//            }
//
//            if (keyCode == KeyEvent.KEYCODE_ENTER) {
//                //若为回车键，直接返回
//            } else {
//                //延迟post，若500ms内，有其他事件
//            }
//
//        }
        if (event.getDeviceId() == -1) {
            return false;
        }
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            return false;
        }
        checkLetterStatus(event);
        if (event.getAction() == KeyEvent.ACTION_UP) {
            int code = event.getKeyCode();
//            if (code >= KeyEvent.KEYCODE_0 && code <= KeyEvent.KEYCODE_9) {
//                codeStr += (code - KeyEvent.KEYCODE_0);
            codeStr += getInputCode(event);
//            }
            if (code == KeyEvent.KEYCODE_ENTER) {
                if (listener != null) {
                    listener.onResult(codeStr.trim());
                    codeStr = "";
                }
            }
        }
        //都是扫码枪来的事件，选择消费掉

        return isInterrupt;
    }

    //获取扫描内容
    private char getInputCode(KeyEvent event) {

        int keyCode = event.getKeyCode();

        char aChar;

        if (keyCode >= KeyEvent.KEYCODE_A && keyCode <= KeyEvent.KEYCODE_Z) {
            //字母
            aChar = (char) ((mCaps ? 'A' : 'a') + keyCode - KeyEvent.KEYCODE_A);
        } else if (keyCode >= KeyEvent.KEYCODE_0 && keyCode <= KeyEvent.KEYCODE_9) {
            //数字
            aChar = (char) ('0' + keyCode - KeyEvent.KEYCODE_0);
        } else {
            //其他符号
            switch (keyCode) {
                case KeyEvent.KEYCODE_PERIOD:
                    aChar = '.';
                    break;
                case KeyEvent.KEYCODE_MINUS:
                    aChar = mCaps ? '_' : '-';
                    break;
                case KeyEvent.KEYCODE_SLASH:
                    aChar = '/';
                    break;
                case KeyEvent.KEYCODE_BACKSLASH:
                    aChar = mCaps ? '|' : '\\';
                    break;
                default:
                    aChar = 0;
                    break;
            }
        }

        return aChar;

    }


    /**
     * shift键检查
     *
     * @param event
     */
    private void checkLetterStatus(KeyEvent event) {
        int keyCode = event.getKeyCode();
        if (keyCode == KeyEvent.KEYCODE_SHIFT_RIGHT || keyCode == KeyEvent.KEYCODE_SHIFT_LEFT) {
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                //按着shift键，表示大写
                mCaps = true;
            } else {
                //松开shift键，表示小写
                mCaps = false;
            }
        }
    }

    public interface OnScanListener {

        void onResult(String content);
    }

    public void setInterrupt(boolean interrupt) {
        isInterrupt = interrupt;
    }
}
