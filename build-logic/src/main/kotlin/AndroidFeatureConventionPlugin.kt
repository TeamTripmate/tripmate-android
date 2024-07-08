import com.tripmate.android.applyPlugins
import com.tripmate.android.implementation
import com.tripmate.android.libs
import com.tripmate.android.project
import org.gradle.kotlin.dsl.dependencies

internal class AndroidFeatureConventionPlugin : BuildLogicConventionPlugin(
    {
        applyPlugins(
            "tripmate.android.library",
            "tripmate.android.library.compose",
            "tripmate.android.hilt",
        )

        dependencies {
            implementation(project(path = ":core:common"))
            implementation(project(path = ":core:designsystem"))
            implementation(project(path = ":core:domain"))
            implementation(project(path = ":core:ui"))

            implementation(libs.androidx.navigation.compose)
            implementation(libs.androidx.hilt.navigation.compose)
            implementation(libs.bundles.androidx.lifecycle)
        }
    },
)
