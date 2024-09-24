import com.android.build.api.dsl.ApplicationExtension
import com.tripmate.android.Plugins
import com.tripmate.android.applyPlugins
import com.tripmate.android.configureCompose
import org.gradle.kotlin.dsl.configure

internal class AndroidApplicationComposeConventionPlugin : BuildLogicConventionPlugin(
    {
        applyPlugins(Plugins.ANDROID_APPLICATION)

        extensions.configure<ApplicationExtension> {
            configureCompose(this)
        }
    },
)
