# Android-CodeBuilder

`build.gradle`:  
```gradle
maven{ url 'https://jitpack.io'}
       
compile 'com.github.Koyoka:Android-CodeBuilder:1.0.0'
```

```java
   public static void main(String[] args) {

        String nameSpace = "com.eleven.httpvolleypromiser.proxy";
        String savePath =
                "C:\\ElevenWorkSpace\\proxy\\";
        String jsonFileDir =
                "C:\\ElevenWorkSpace\\json\\";
        WebApiDataBuilder.newInstance().build(
                nameSpace,
                savePath,
                jsonFileDir
        );

    }
```

`bat`: 
```gradle
set ns=com.eleven.httpvolleypromiser.proxy
set savePath=C:\ElevenWorkSpace\proxy\
set jsonFileDir=C:\ElevenWorkSpace\apiJson\
java -jar CodeBuilder.jar %ns% %savePath% %jsonFileDir%
pause
```
`json template`: 
```json
WebApi模板
{
  "apiPath": "api/v1/user/1/Sys/Login/{sUserID}/{sPassword}",
  "controller": "user",
  "method": "get",
  "req":{
       "Datekey": "Date",
      "StringKey": "",
      "ClassKey": "org.google.JsonObject",
      "intKey": 1,
      "doubleKey": 1.1,
      "BooleanKey": true
  },
  "res": {
    "Content": {
      "Datekey": "Date",
      "StringKey": "",
      "ClassKey": "org.google.JsonObject",
      "intKey": 1,
      "doubleKey": 1.1,
      "BooleanKey": true
    },
    "StatusCode": 1,
    "State": 1,
    "Message": ""
  }
}
```
