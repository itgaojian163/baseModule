package com.tengshi.basemodule.retrofitNet.download;

/**
 *
 */
public class DownloadParams {
    private final String baseUrl;
    private final String relativeUrl;
    private final String loadedFilePath;
    private final boolean isCallbackOnUiThread;

    private DownloadParams(Builder builder) {
        this.baseUrl = builder.baseUrl;
        this.relativeUrl = builder.relativeUrl;
        this.loadedFilePath = builder.loadedFilePath;
        this.isCallbackOnUiThread = builder.isCallbackOnUiThread;
    }


    public String getBaseUrl() {
        return baseUrl;
    }

    public String getRelativeUrl() {
        return relativeUrl;
    }

    public String getLoadedFilePath() {
        return loadedFilePath;
    }

    public boolean isCallbackOnUiThread() {
        return isCallbackOnUiThread;
    }

    public static class Builder {
        String baseUrl;
        String relativeUrl;
        String loadedFilePath;
        boolean isCallbackOnUiThread;

        public Builder(String baseUrl, String relativeUrl, String loadedFilePath) {
            this.baseUrl = baseUrl;
            this.relativeUrl = relativeUrl;
            this.loadedFilePath = loadedFilePath;
        }

        public Builder setCallbackOnUiThread(boolean callbackOnUiThread) {
            isCallbackOnUiThread = callbackOnUiThread;
            return this;
        }


        public DownloadParams build() {
            return new DownloadParams(this);
        }
    }
}
