/**
 * @作者: Simplation
 * @日期: 2021/8/2 9:32
 * @描述: DependenciesConfig And DependenciesVersion
 * @更新:
 */
object DependenciesVersion {
    const val Gradle = "7.0.0"
    const val Kotlin = "1.6.0"
    const val Core_Ktx = "1.3.2"
    const val Junit = "4.13.2"
    const val Ext_Junit = "1.1.2"
    const val Espresso_Core = "3.3.0"

    // Basic
    const val AppCompact = "1.3.0"
    const val CardView = "1.0.0"
    const val Material = "1.3.0"
    const val Constraintlayout = "2.0.4"


    // Retrofit
    const val Retrofit = "2.5.0"
    const val ConverterGson = "2.5.0"
    const val AdapterRxJava2 = "2.5.0"
    const val LoggingInterceptor = "3.12.0"
    const val ConverterMoshi = "2.4.0"
    const val MoshiKotlin = "1.7.0"

    // RxJava
    const val RxJava = "2.2.2"
    const val RxAndroid = "2.1.0"
    const val RxKotlin = "2.3.0"
    const val Anko = "0.10.7"

    // Common
    const val EventBus = "3.1.1"
    const val Swipeback = "1.0.1"
    const val Logger = "2.1.1"
    const val Autosize = "1.1.0"
    const val Easypermissions = "1.2.0"
    const val Litepal = "3.1.0"
    const val BaseRecyclerViewAdapterHelper = "2.9.35"
    const val Dialog = "0.9.4.4"
    const val Bugly = "3.1.0"
}

object DependenciesConfig {
    const val Kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${DependenciesVersion.Kotlin}"
    const val CoreKtx = "androidx.core:core-ktx:${DependenciesVersion.Core_Ktx}"
    const val Junit = "junit:junit:${DependenciesVersion.Junit}"
    const val ExtJunit = "androidx.test.ext:junit:${DependenciesVersion.Ext_Junit}"
    const val EspressoCore = "androidx.test.espresso:espresso-core:${DependenciesVersion.Espresso_Core}"

    // Basic
    const val AppCompact = "androidx.appcompat:appcompat:${DependenciesVersion.AppCompact}"
    const val CardView = "androidx.cardview:cardview:${DependenciesVersion.CardView}"
    const val Material =
        "com.google.android.material:material:${DependenciesVersion.Material}"
    const val ConstraintLayout =
        "androidx.constraintlayout:constraintlayout:${DependenciesVersion.Constraintlayout}"

    // Retrofit
    const val Retrofit = "com.squareup.retrofit2:retrofit:${DependenciesVersion.Retrofit}"
    const val ConverterGson =
        "com.squareup.retrofit2:converter-gson:${DependenciesVersion.ConverterGson}"
    const val AdapterRxJava2 =
        "com.squareup.retrofit2:adapter-rxjava2:${DependenciesVersion.AdapterRxJava2}"
    const val LoggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${DependenciesVersion.LoggingInterceptor}"
    const val ConverterMoshi =
        "com.squareup.retrofit2:converter-moshi:${DependenciesVersion.ConverterMoshi}"
    const val MoshiKotlin = "com.squareup.moshi:moshi-kotlin:${DependenciesVersion.MoshiKotlin}"

    // RxJava
    const val RxJava = "io.reactivex.rxjava2:rxjava:${DependenciesVersion.RxJava}"
    const val RxAndroid = "io.reactivex.rxjava2:rxandroid:${DependenciesVersion.RxAndroid}"
    const val RxKotlin = "io.reactivex.rxjava2:rxkotlin:${DependenciesVersion.RxKotlin}"
    const val Anko = "org.jetbrains.anko:anko:${DependenciesVersion.Anko}"

    // Common
    const val EventBus = "org.greenrobot:eventbus:${DependenciesVersion.EventBus}"
    const val SwipeBack = "com.cxz:swipeback:${DependenciesVersion.Swipeback}"
    const val Logger = "com.orhanobut:logger:${DependenciesVersion.Logger}"
    const val AutoSize = "me.jessyan:autosize:${DependenciesVersion.Autosize}"
    const val EasyPermissions = "pub.devrel:easypermissions:${DependenciesVersion.Easypermissions}"
    const val Litepal = "org.litepal.guolindev:core:${DependenciesVersion.Litepal}"
    const val BaseRecyclerViewAdapterHelper =
        "com.github.CymChad:BaseRecyclerViewAdapterHelper:${DependenciesVersion.BaseRecyclerViewAdapterHelper}"
    const val Dialogs = "com.afollestad.material-dialogs:commons:${DependenciesVersion.Dialog}"
    const val Bugly = "com.tencent.bugle:crashreport:${DependenciesVersion.Bugly}"
}
