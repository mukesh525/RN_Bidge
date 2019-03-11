/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow
 * @lint-ignore-every XPLATJSCOPYRIGHT1
 */
import React, {Component} from 'react';
import {StyleSheet, Text, View,Button,NativeModules} from 'react-native';

import { DeviceEventEmitter } from 'react-native';


type Props = {};
export default class App extends Component<Props> {


  constructor(props) {
    super(props);
    DeviceEventEmitter.addListener('onSessionConnect', (event) => console.log(event));

  }

  

  handleImage=()=>{  
    // NativeModules.ImageNew.openSelectDialog(
    //   (uri) => { console.log(uri) }, 
    //   (error) => { console.log(error) }
    // )
    // NativeModules.ImagePickerModule.pickImage()
    //   .then((resp)=> console.log(resp))
    //   .catch((error)=>console.log(error))
    
     NativeModules.ActivityStarter.navigateToExample()
        .then((resp)=> console.log(resp))
        .catch((error)=>console.log(error))
  }



  render() {
    return (
      <View style={styles.container}>
        <Text style={styles.welcome}>Welcome to React Native!</Text>
        <Button
                onPress={this.handleImage }
                title='Start example activity'
            />
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
});
