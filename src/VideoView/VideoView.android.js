import {requireNativeComponent, ViewPropTypes} from 'react-native';

const VideoView = requireNativeComponent('VideoView');

VideoView.propTypes = {
  ...ViewPropTypes,
  url: String,
};

export {VideoView};
