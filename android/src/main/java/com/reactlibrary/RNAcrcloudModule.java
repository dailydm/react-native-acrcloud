
package com.reactlibrary;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.NoSuchKeyException;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.Promise;

public class RNAcrcloudModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;
  private RNAcrcloud acrCloud;

  public RNAcrcloudModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "Acrcloud";
  }

  @ReactMethod
  public void initACRCloud(ReadableMap options, Callback errorCallback) {
    try {
      this.acrCloud = new RNAcrcloud(options);
    }
    catch (NoSuchKeyException e) {
      errorCallback.invoke("Required key " + e.getMessage() + " is missing");
    }
  }

  @ReactMethod
  public void startRecognition(Promise promise) {
    if (this.acrCloud != null) {
      this.acrCloud.start(promise);
    }
  }

  @ReactMethod
  public void stopRecognition() {
    if(this.acrCloud != null) {
      this.acrCloud.stop();
    }
  }

  @ReactMethod
  public void cancelRecognition() {
    if(this.acrCloud != null) {
      this.acrCloud.cancel();
    }
  }
}
