import androidx.room.gradle.RoomExtension
import com.tripmate.android.Plugins
import com.tripmate.android.applyPlugins
import com.tripmate.android.implementation
import com.tripmate.android.ksp
import com.tripmate.android.libs
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidRoomConventionPlugin : BuildLogicConventionPlugin(
    {
        applyPlugins(Plugins.ANDROIDX_ROOM, Plugins.KOTLINX_SERIALIZATION, Plugins.KSP)

        extensions.configure<RoomExtension> {
            // The schemas directory contains a schema file for each version of the Room database.
            // This is required to enable Room auto migrations.
            // See https://developer.android.com/reference/kotlin/androidx/room/AutoMigration.
            schemaDirectory("$projectDir/schemas")
        }

        dependencies {
            implementation(libs.androidx.room.runtime)
            implementation(libs.androidx.room.ktx)
            ksp(libs.androidx.room.compiler)
            implementation(libs.kotlinx.serialization.json)
        }
    },
)