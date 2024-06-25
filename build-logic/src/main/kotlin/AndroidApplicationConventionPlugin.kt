import com.android.build.api.dsl.ApplicationExtension
import com.tripmate.android.Plugins
import com.tripmate.android.applyPlugins
import com.tripmate.android.configureAndroid
import com.tripmate.android.libs
import org.gradle.kotlin.dsl.configure

internal class AndroidApplicationConventionPlugin : BuildLogicConventionPlugin(
    {
        applyPlugins(Plugins.AndroidApplication, Plugins.KotlinAndroid)

        extensions.configure<ApplicationExtension> {
            configureAndroid(this)

            defaultConfig {
                targetSdk = libs.versions.targetSdk.get().toInt()
                versionCode = libs.versions.versionCode.get().toInt()
                versionName = libs.versions.versionName.get()
            }
        }
    },
)
