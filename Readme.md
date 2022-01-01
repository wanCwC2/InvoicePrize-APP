# wanCwC2超讚
要不是因為行動裝置的作業要用這東西，我還真的不想接觸android studio，
有一大半的時間都在調整gradle, sdk, api之類的問題，
根本不是在練習程式，是在練習調整系統qwq <br>
如果看到commit上是寫 "hi OwO"，那只是我commit的時候沒有選到而已。

## 統一發票對獎機 Android Studio 版本
* GRADLE JDK: JAVA 11 
  > 我原本想要用1.8，但是不曉得為何程式要求我一定要用11
* SDK: Android 11.0 (R)
  > 實際上我10.0至12.0都有安裝
  > 因為想要做機器學習，至少要API29或30以上
* AVD: Pixel 5 API 30

## Debug
`Permission is only granted to system app` <br>
主要發生在`AndroidManifest.xml`檔案中`<uses-permission android:name="android.permission.WRITE_SETTINGS" />`
這行可能會顯示錯誤，解決方法如下：
> File -> Settings -> Editor -> Inspections -> Android -> Lint -> Correctness -> Using system app permission 這個不要打勾或調低安全級別