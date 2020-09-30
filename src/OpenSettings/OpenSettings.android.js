import {NativeModules} from 'react-native';

const {
  OpenSettings: {
    openAppNotificationSettings,
    triggerEvent,
    someMethodWithCallback,
    someMethodWithPromise,
  },
} = NativeModules;

export default {
  openAppNotificationSettings,
  triggerEvent,
  someMethodWithCallback,
  someMethodWithPromise,
};
