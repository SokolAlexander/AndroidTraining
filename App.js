import React, {useCallback, useEffect, useRef} from 'react';
import {
  StyleSheet,
  View,
  Button,
  Alert,
  NativeEventEmitter,
  NativeModules,
  UIManager,
  findNodeHandle,
} from 'react-native';

import {Colors} from 'react-native/Libraries/NewAppScreen';
import {VideoView} from './src/VideoView/VideoView';
import OpenSettings from './src/OpenSettings/OpenSettings';

const settingsEmitter = new NativeEventEmitter(OpenSettings);

const App = () => {
  // Event subscription example
  /*
  const handleEvent = useCallback((params) => {
    Alert.alert(params.someKey);
  }, []);

  useEffect(() => {
    const subscribtion = settingsEmitter.addListener(
      'nativeEvent',
      handleEvent,
    );
    return () => subscribtion.remove();
  }, [handleEvent]);

  const handlePress = useCallback(() => {
    OpenSettings.triggerEvent();
  }, []);

  return (
      <View style={styles.buttonWrapper}>
        <Button title="Trigger Event" onPress={handlePress} />
      </View>
  );
  */

  // Video example
  const videoRef = useRef();

  const pauseVideo = useCallback(() => {
    UIManager.dispatchViewManagerCommand(
      findNodeHandle(videoRef.current),
      'Pause',
      [],
    );
  }, []);

  return <VideoView style={styles.video} url="someVideoUrl" ref={videoRef} />;

  // Open settings example
  // const handlePress = useCallback(() => {
  //   OpenSettings.openAppNotificationSettings();
  // }, []);

  // return (
  //   <View style={styles.buttonWrapper}>
  //     <Button title="Open Settings" onPress={handlePress} />
  //   </View>
  // );
};

const styles = StyleSheet.create({
  buttonWrapper: {
    flex: 1,
    justifyContent: 'center',
    width: '90%',
    alignSelf: 'center',

    shadowColor: '#000',
    shadowOffset: {
      width: 4,
      height: 2,
    },
    shadowOpacity: 0.25,
    shadowRadius: 3.84,

    elevation: 15,
  },
  video: {
    flex: 1,
    width: '100%',
    height: '100%',
    borderWidth: 2,
  },
  body: {
    backgroundColor: Colors.white,
  },
  sectionContainer: {
    marginTop: 32,
    paddingHorizontal: 24,
  },
});

export default App;
