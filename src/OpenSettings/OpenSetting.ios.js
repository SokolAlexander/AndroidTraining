const noSuchFunction = () => {
  throw new Error('This module does not exist on ios');
};

export default {
  openAppNotificationSettings: noSuchFunction,
  triggerEvent: noSuchFunction,
  someMethodWithCallback: noSuchFunction,
  someMethodWithPromise: noSuchFunction,
};
