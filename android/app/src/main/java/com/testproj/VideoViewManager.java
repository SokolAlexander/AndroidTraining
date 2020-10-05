package com.testproj;

// import android.app.AlertDialog;
import androidx.appcompat.app.AlertDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.widget.VideoView;

import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;
// import com.facebook.react.views.;
import com.facebook.react.uimanager.ThemedReactContext;

import javax.annotation.Nullable;
import java.util.Map;


public class VideoViewManager extends ViewManager<VideoView, LayoutShadowNode> {
    public static final String REACT_CLASS = "VideoView";

    public static final String COMMAND_PAUSE = "Pause";

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
    public Class<LayoutShadowNode> getShadowNodeClass() {
        return LayoutShadowNode.class;
    }

    @Override
    public void updateExtraData(VideoView root, Object extraData) {}

    @Override
    public LayoutShadowNode createShadowNodeInstance() {
        return new LayoutShadowNode();
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

    // @Override
    // public @Nullable Map getExportedCustomDirectEventTypeConstants() {
    //     return MapBuilder.of(
    //             "onVideoEnd",
    //             MapBuilder.of("registrationName", "onEnd")
    //     );
    // }

    @Override
    public void receiveCommand(
            VideoView view,
            String commandType,
            @Nullable ReadableArray args) {
        Log.d("video", "command received: " + commandType);
        switch (commandType) {
            case COMMAND_PAUSE: {
                // view.pause();
                return;
            }
            default:
                throw new IllegalArgumentException(String.format(
                        "Unsupported command %d received by %s.",
                        commandType,
                        getClass().getSimpleName()));
        }
    }
}