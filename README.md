
# react-native-acrcloud

## Getting started

`$ npm install react-native-acrcloud --save`

### Mostly automatic installation

`$ react-native link react-native-acrcloud`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-acrcloud` and add `RNAcrcloud.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNAcrcloud.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNAcrcloudPackage;` to the imports at the top of the file
  - Add `new RNAcrcloudPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-acrcloud'
  	project(':react-native-acrcloud').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-acrcloud/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-acrcloud')
  	```

#### Windows
[Read it! :D](https://github.com/ReactWindows/react-native)

1. In Visual Studio add the `RNAcrcloud.sln` in `node_modules/react-native-acrcloud/windows/RNAcrcloud.sln` folder to their solution, reference from their app.
2. Open up your `MainPage.cs` app
  - Add `using Acrcloud.RNAcrcloud;` to the usings at the top of the file
  - Add `new RNAcrcloudPackage()` to the `List<IReactPackage>` returned by the `Packages` method


## Usage
```javascript
import RNAcrcloud from 'react-native-acrcloud';

// TODO: What to do with the module?
RNAcrcloud;
```
  