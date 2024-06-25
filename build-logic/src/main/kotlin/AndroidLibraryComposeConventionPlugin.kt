import com.android.build.gradle.LibraryExtension
import com.tripmate.android.Plugins
import com.tripmate.android.applyPlugins
import com.tripmate.android.configureCompose
import org.gradle.kotlin.dsl.configure

internal class AndroidLibraryComposeConventionPlugin : BuildLogicConventionPlugin(
    {
        applyPlugins(Plugins.AndroidLibrary)

        extensions.configure<LibraryExtension> {
            configureCompose(this)
        }
    },
)


