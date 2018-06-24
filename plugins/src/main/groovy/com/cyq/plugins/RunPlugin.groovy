package com.cyq.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project

class RunPlugin implements Plugin<Project>{
    @Override
    void apply(Project project) {
        System.out.println('#####################################################################')
        project.android.applicationVariants.all { variant ->
            if (variant.install) {
                System.out.println("run${variant.name.capitalize()}")
                project.tasks.create(name: "run${variant.name.capitalize()}", dependsOn: variant.install) {
                    description "Installs the ${variant.description} and runs the main launcher activity."

                    doFirst {
                        /*def classpath = variant.applicationId
                        System.out.println("classpath:：" + classpath)
                        if (variant.buildType.applicationIdSuffix) {
                            classpath -= "${variant.buildType.applicationIdSuffix}"
                            System.out.println("classpath:：" + classpath)
                        }*/
                        def classpath = "com.example.leo_cao.myapplication"
                        def launchClass = "${variant.applicationId}/${classpath}.MainActivity"
                        System.out.println("launchClass: " + launchClass)
                        project.exec {
                            executable = 'adb'
                            args = ['shell', 'am', 'start', '-n', launchClass]
                        }
                    }
                }
            }
        }
    }
}
