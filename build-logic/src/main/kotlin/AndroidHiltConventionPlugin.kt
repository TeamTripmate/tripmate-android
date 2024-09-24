import com.tripmate.android.Plugins
import com.tripmate.android.applyPlugins
import com.tripmate.android.implementation
import com.tripmate.android.ksp
import com.tripmate.android.libs
import org.gradle.kotlin.dsl.dependencies

internal class AndroidHiltConventionPlugin : BuildLogicConventionPlugin(
    {
        applyPlugins(Plugins.HILT, Plugins.KSP)

        dependencies {
            implementation(libs.hilt.android)
            ksp(libs.hilt.android.compiler)
        }
    },
)
