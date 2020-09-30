import React, {useCallback, useEffect} from 'react';
import {
  StyleSheet,
  View,
  Button,
  Alert,
  NativeEventEmitter,
} from 'react-native';

import {Colors} from 'react-native/Libraries/NewAppScreen';
import {VideoView} from './src/VideoView/VideoView';
import OpenSettings from './src/OpenSettings/OpenSetting';

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

  // VideoView example
  /* 
  const handleEnd = useCallback((event) => {
    Alert.alert('Video ended!');
    console.log(event);
  }, []);

  return (
    <VideoView style={styles.video} url="someVideoUrl" onEnd={handleEnd} />
  );
  */

  // Open settings example
  const handlePress = useCallback(() => {
    OpenSettings.openAppNotificationSettings();
  }, []);

  return (
    <View style={styles.buttonWrapper}>
      <Button title="Open Settings" onPress={handlePress} />
    </View>
  );
};

const styles = StyleSheet.create({
  buttonWrapper: {
    flex: 1,
    justifyContent: 'center',
    width: '90%',
    alignSelf: 'center',
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
