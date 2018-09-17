import org.gradle.api.Plugin
import org.gradle.api.Project

class ApkDistPlugin implements Plugin<Project> {

    @Override
    void apply(Project target) {
        def extension = target.extensions.create('apkdistconf', ApkDistExtension)

        target.task('apkdist') << {
            println 'hello, world!'

            extension.nameMap('wow!')

            println extension.destDir
        }
    }
}

