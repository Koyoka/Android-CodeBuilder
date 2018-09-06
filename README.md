# Android-CodeBuilder

`build.gradle`:  
```gradle
maven{ url 'https://elevengroup.bintray.com/ElevenPublicRepository'}
       
compile 'com.eleven.devlib:CodeBuilder:1.0.0'
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
