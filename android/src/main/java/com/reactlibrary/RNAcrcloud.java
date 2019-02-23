package com.reactlibrary;

import com.acrcloud.rec.sdk.ACRCloudConfig;
import com.acrcloud.rec.sdk.ACRCloudClient;
import com.acrcloud.rec.sdk.IACRCloudListener;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.Promise;

public class RNAcrcloud implements IACRCloudListener {

    private ACRCloudConfig mConfig;
    private ACRCloudClient mClient;

    private Promise promise;

    private boolean initState = false;

    public RNAcrcloud(ReadableMap options) {
        this.mConfig = new ACRCloudConfig();
        this.mConfig.acrcloudListener = this;
        this.mConfig.host = options.getString("host");
        this.mConfig.accessKey = options.getString("accessKey");
        this.mConfig.accessSecret = options.getString("accessSecret");
        this.mConfig.protocol = ACRCloudConfig.ACRCloudNetworkProtocol.PROTOCOL_HTTP;
        this.mConfig.reqMode = ACRCloudConfig.ACRCloudRecMode.REC_MODE_REMOTE;

        this.mClient = new ACRCloudClient();
        this.initState = this.mClient.initWithConfig(this.mConfig);
        if (this.initState) {
            this.mClient.startPreRecord(3000);
        }
    }

    public void start(Promise promise) {
        this.promise = promise;
        if(this.initState) {
            this.mClient.startRecognize();
        }
        else {
            promise.reject("blahhh", "There was an issue initializing ACRCloudClient");
        }
    }

    public void stop() {
        if(this.mClient != null) {
            this.mClient.stopRecordToRecognize();
        }
    }

    public void cancel() {
        if(this.mClient != null) {
            this.mClient.cancel();
        }
    }

    @Override
    public void onVolumeChanged(double vol) {
    }

    @Override
    public void onResult(String result) {
        if (this.mClient != null) {
            this.mClient.cancel();
        }
        this.promise.resolve(result);
    }
}
