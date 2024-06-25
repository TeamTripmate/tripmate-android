import com.tripmate.android.Plugins
import com.tripmate.android.applyPlugins
import com.tripmate.android.implementation
import com.tripmate.android.libs
import org.gradle.kotlin.dsl.dependencies

internal class AndroidFirebaseConventionPlugin : BuildLogicConventionPlugin(
    {
        applyPlugins(Plugins.GoogleServices, Plugins.FirebaseCrashlytics)

        dependencies {
            implementation(platform(libs.firebase.bom))
            implementation(libs.firebase.analytics)
            implementation(libs.firebase.crashlytics)
            implementation(libs.firebase.config)
        }
    },
)
