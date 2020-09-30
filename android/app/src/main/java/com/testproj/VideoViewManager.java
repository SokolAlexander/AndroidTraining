package com.testproj;

import android.app.AlertDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.widget.VideoView;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.views.image.ReactImageView;
// import com.facebook.react.views.;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.drawee.backends.pipeline.Fresco;
import javax.annotation.Nullable;
import java.util.Map;


public class VideoViewManager extends SimpleViewManager<VideoView> {
    public static final String REACT_CLASS = "VideoView";

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    protected VideoView createViewInstance(final ThemedReactContext reactContext) {
        final VideoView videoView = new VideoView(reactContext);
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                WritableMap event = Arguments.createMap();
                event.putString("message", "MyMessage");
                ReactContext reactContext = (ReactContext) videoView.getContext();
                reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(
                        videoView.getId(),
                        "onVideoEnd",
                        event);
            }
        });
        return videoView;
    }

    @ReactProp(name="url")
    public void setVideoPath(VideoView videoView, String urlPath) {
        Uri uri = Uri.parse(urlPath);
        videoView.setVideoURI(uri);
        videoView.start();
    }


    @Override
    public Map getExportedCustomBubblingEventTypeConstants() {
        return MapBuilder.builder()
                .put(
                        "onVideoStart",
                        MapBuilder.of(
                                "phasedRegistrationNames",
                                MapBuilder.of("bubbled", "onStart"))
                )
                .put(
                        "onVideoEnd",
                        MapBuilder.of(
                                "phasedRegistrationNames",
                                MapBuilder.of("bubbled", "onEnd")))
                .put(
                        "onVideoProgress",
                        MapBuilder.of(
                                "phasedRegistrationNames",
                                MapBuilder.of("bubbled", "onProgress"))
                )
                .build();
    }

    @Override
    public @Nullable Map getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.of(
                "onVideoEnd",
                MapBuilder.of("registrationName", "onEnd")
        );
    }
}