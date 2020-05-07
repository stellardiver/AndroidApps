#include <jni.h>
#include <string>


extern "C" JNIEXPORT jstring JNICALL
Java_com_app_pipeditorpro_Utils_FullAdId(
        JNIEnv *env,
        jclass clazz) {
    std::string hello = "ca-app-pub-3940256099942544/1033173712";
    return env->NewStringUTF(hello.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_app_pipeditorpro_Utils_BannerAdId(
        JNIEnv *env,
        jclass clazz) {
    std::string hello = "ca-app-pub-3940256099942544/6300978111";
    return env->NewStringUTF(hello.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_app_pipeditorpro_Utils_FBFullAdId(
        JNIEnv *env,
        jclass clazz) {
    std::string hello = "GCP2rt7zaizarT8+Iunw3RKCHHxKBtHpMFu6iExPQ90=]YbwW3SfVoacc9YykXofoRA==]quwaa8eTYTy9WKYI19RZD3x5PT0ypPGDZ4ofObug7Ck=";
    return env->NewStringUTF(hello.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_app_pipeditorpro_Utils_FBBannerAdId(
        JNIEnv *env,
        jclass clazz) {
    std::string hello = "VXdRwTaWvCgukGEhBDoEdhoWoLzuV6V09VBpuBu15sI=]gM2ihG2boyewkOxuXWG9kA==]o5xQgRS9DQPj3ijLj0sibNMNMC5yI4ch8M6RfA+cRHA=";
    return env->NewStringUTF(hello.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_app_pipeditorpro_Edata_help_InitFV_pKeyU(JNIEnv *env, jclass type, jstring mKey_) {
    const char *mKey = env->GetStringUTFChars(mKey_, NULL);

    std::string hello = mKey + std::string("pubgspecialist");
    return env->NewStringUTF(hello.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_app_pipeditorpro_Edata_help_InitFV_00024Companion_pKeyU__Ljava_lang_String_2(JNIEnv *env, jclass type, jstring mKey_) {
    const char *mKey = env->GetStringUTFChars(mKey_, NULL);

    std::string hello = mKey + std::string("pubgspecialist");
    return env->NewStringUTF(hello.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_app_pipeditorpro_Edata_retrofit_Urls_BASE_1URL(JNIEnv *env, jclass type) {
//    return env->NewStringUTF("sPo3tZTahE6fc32gk+St/l0dEcl5wPSrtNq3knfQBWU=]VYeH/d7URGHbSAh0DemhvA==]Go8RVPmIHdcNn4tW3AU3tp5B7p8Imf8mC3z3HQC6oCI=");
    return env->NewStringUTF("PRK58enr5v6rCv2IvKe8rPzkFKd245LvwvsqHZwCL1Y=]jx47bFZ4fnnLdcUZaoybvA==]sFCKue2LNr1joMVcJmtWRB7g6jEclfja2SFhs06lmJ0=");
}
extern "C" JNIEXPORT jstring JNICALL
Java_com_app_pipeditorpro_Edata_help_EnCryptos_key(JNIEnv *env, jclass clazz) {
//    return env->NewStringUTF("4Uo0BLN+r/i74ysX93/x5g/8NU4F7Wq6M8yLZ1P7u6Y=]x0SKrz/eAp2dsU7cb1xrng==]xyXZQ0aIpSt+govSzC6h4A=="); // debug
    return env->NewStringUTF("ITmdFJHJzkTDrd9tvkF1LbRmXvJJW3RUCeaulUC3Gm4=]NAl2ysf22OShq+JXBEqQBg==]o9/za1JdEjwEgsnGkigHaw=="); // signed
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_app_pipeditorpro_Edata_retrofit_Urls_Pinnig(JNIEnv *env, jclass type) {
//    return env->NewStringUTF("+O3unoGC2eWYrc8tOA8J0NXcNlYY6rO0C0AcelDjt7I=]307TUU3IMikQA9XTz11msA==]AXZl+kZhntPfrrUIDuEktw==");
//    return env->NewStringUTF("misi.co.in");
    return env->NewStringUTF("dailytask.mobi");

}