import com.android.build.gradle.LibraryExtension
import com.tripmate.android.Plugins
import com.tripmate.android.applyPlugins
import com.tripmate.android.configureAndroid
import com.tripmate.android.libs
import org.gradle.kotlin.dsl.configure

internal class AndroidLibraryConventionPlugin : BuildLogicConventionPlugin({
    applyPlugins(Plugins.AndroidLibrary, Plugins.KotlinAndroid)

    extensions.configure<LibraryExtension> {
        configureAndroid(this)

        defaultConfig.apply {
            targetSdk = libs.versions.targetSdk.get().toInt()
        }
    }
})
